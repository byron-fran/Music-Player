plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.byrondev.musicplayer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.byrondev.musicplayer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        ndk {
            abiFilters; "armeabi-v7a"; "arm64-v8a"; "x86"; "x86_64"
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

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
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // For media playback using ExoPlayer
    implementation(libs.androidx.media3.exoplayer)
    // For DASH playback support with ExoPlayer
    implementation(libs.androidx.media3.exoplayer.dash)
    // For HLS playback support with ExoPlayer
    implementation(libs.androidx.media3.exoplayer.hls)
    // For building media playback UIs
    implementation(libs.androidx.media3.ui)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    //Dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Navigation Jetpack Compose Integration
    implementation (libs.androidx.navigation.compose)

    implementation(libs.cloudy)
    implementation(libs.ui)
    implementation(libs.androidx.navigation.compose.v270beta01)
    implementation (libs.glide.v4160)
//    kapt ("com.github.bumptech.glide:compiler:4.16.1")
    implementation (libs.compose.v100alpha3)
//    Fmmpeg

    implementation(libs.toolbar.compose)
//    Coil compoaw
    implementation(libs.coil.compose)
    implementation (libs.coil.network.okhttp)
//    implementation(libs.coil.transformations.v230)

    implementation (libs.accompanist.systemuicontroller)

    //Coil Trasnformations
    implementation (libs.glide.transformations)
    // If you want to use the GPU Filters
    implementation (libs.gpuimage)

    implementation (libs.gson)

    // Preferences data store
    implementation(libs.androidx.datastore.preferences)

//    ToolbarCol
    implementation(libs.collapsingtopbarcompose)

    implementation (libs.androidx.palette)


    implementation (libs.ffmpegmediametadataretriever.core)
    implementation (libs.ffmpegmediametadataretriever.native)
}

