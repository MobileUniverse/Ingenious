import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

android {
    namespace = "com.che.githubusers.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

extensions.configure(KaptExtension::class.java) {
    correctErrorTypes = true

    javacOptions {
        option("-Adagger.hilt.disableModulesHaveInstallInCheck=true")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.datetime)
    implementation(libs.dagger.core)
    implementation(libs.dagger.runtime)
    kapt(libs.dagger.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.immutable.collections)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(kotlin("test"))

    testImplementation(libs.coroutines.test)
    testImplementation(libs.ktor.ktorMockClient)
    testImplementation(kotlin("test"))
    testImplementation(libs.logback.classic)

}

tasks.withType<Test> {
    useJUnitPlatform()
}
