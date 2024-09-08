import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
    id("kotlin")
    id("kotlin-kapt")
}

extensions.configure(KaptExtension::class.java) {
    correctErrorTypes = true

    javacOptions {
        option("-Adagger.hilt.disableModulesHaveInstallInCheck=true")
    }
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.immutable.collections)
    implementation(libs.datetime)
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)

    testImplementation(libs.mockk)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
