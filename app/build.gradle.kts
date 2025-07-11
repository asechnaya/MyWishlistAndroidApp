// app/build.gradle.kts

// Add this import statement at the top of the file
import org.gradle.api.JavaVersion
// CORRECTED LINE: Changed comment from # to //
import org.jetbrains.kotlin.gradle.dsl.JvmTarget // Needed for jvmToolchain in newer Gradle versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myfirstwishlistapp"
    compileSdk = 34 // Or your target Android SDK version

    defaultConfig {
        applicationId = "com.example.myfirstwishlistapp"
        minSdk = 24 // Or your minimum supported Android SDK version
        targetSdk = 34 // Or your target Android SDK version
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        // Also ensure jvmTarget is set correctly for Kotlin compilation
        jvmTarget = JavaVersion.VERSION_11.toString()
        // OR for newer Gradle/Kotlin versions:
        // jvmTarget = JvmTarget.JVM_11.toString()
    }
}

dependencies {
    // ... (your existing dependencies) ...
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Coroutines for asynchronous operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
}
