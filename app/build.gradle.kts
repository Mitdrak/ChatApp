import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
    kotlin("kapt") version "2.1.0"
    alias(libs.plugins.dagger.hilt.plugin)
    /*alias(libs.plugins.kotlin.kapt)*/
}

android {
    namespace = "com.example.chatproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chatproject"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val localProperties = Properties().apply {
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                localPropertiesFile.inputStream().use { load(it) }
            }
        }
        buildConfigField("String", "GITHUB_TOKEN", "\"${localProperties["GITHUB_TOKEN"]}\"")
        buildConfigField("String", "GLOBAL_URL", "\"${localProperties["GLOBAL_URL"]}\"")
        /*buildConfigField("String", "GITHUB_TOKEN", "\"${GITHUB_TOKEN}\"")*/


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    /*kapt("groupId:artifactId:version")*/
    implementation(libs.androidx.okhttp)
    implementation(libs.androidx.gson)
    implementation(libs.androidx.hilt)
    kapt(libs.androidx.hilt.compiler)

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation(libs.androidx.hilt.navigation)

    implementation(libs.hilt.android.gradle.plugin)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
kapt{
    correctErrorTypes = true
}