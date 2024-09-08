import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import org.owasp.dependencycheck.gradle.DependencyCheckPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.compose.compiler) apply false
    id("io.gitlab.arturbosch.detekt") version(libs.versions.detekt)
    id("org.owasp.dependencycheck") version(libs.versions.dependencyCheck)
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    val kotlinVersion = libs.versions.kotlin.get()
    dependencies {
        classpath("com.android.tools.build:gradle:${libs.versions.agp.get()}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.dagger.get()}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

allprojects {
    configureDetektPlugin()
    configureDependencyCheck()
}

internal fun Project.configureDetektPlugin() {

    // apply detekt plugin
    pluginManager.apply(DetektPlugin::class.java)

    // enable Ktlint formatting
    dependencies.add(
        "detektPlugins",
        "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6"
    )

    tasks.withType<Detekt> {
        jvmTarget = JavaVersion.VERSION_17.toString()
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom(files("${project.rootDir}/detekt.yml"))
        reports {
            html.outputLocation.set(
                file("${project.layout.buildDirectory.get()}/reports/detekt/${project.name}.html")
            )
        }
    }
}

internal fun Project.configureDependencyCheck() {

    // apply dependency plugin
    pluginManager.apply(DependencyCheckPlugin::class.java)

    dependencyCheck {
        failBuildOnCVSS = 0F

        fun String.startsWithAny(
            vararg prefixCollection: String,
            ignoreCase: Boolean = false
        ): Boolean {
            prefixCollection.forEach {
                if (this.startsWith(it, ignoreCase))
                    return true
            }
            return false
        }

        scanConfigurations = configurations.filter {
            !it.name.startsWithAny("androidTest", "test", "debug") &&
                    it.name.contains("DependenciesMetadata") && (
                    it.name.startsWithAny("api", "implementation", "runtimeOnly") ||
                            it.name.contains("Api") ||
                            it.name.contains("Implementation") ||
                            it.name.contains("RuntimeOnly")
                    )
        }.map { it.name }
    }
}