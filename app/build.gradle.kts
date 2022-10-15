plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id 'com.github.ben-manes.versions'
//    id 'dagger.hilt.android.plugin'
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
}

fun getApiKey(): String {
    return com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("TMDB_API_KEY")
}
android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.movplayv3"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "TMDB_API_KEY", getApiKey())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.3.0-rc01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.example.movplayv3"
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.core.ktx)
    implementation(libs.androidx.compose.ui)
//    implementation("androidx.compose.ui:ui:1.3.0-rc01")
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.constraintlayout.constraintlayout.compose)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.lifecycle.runtime.compose)
    implementation(libs.com.airbnb.android.lottie.compose)
    implementation(libs.androidx.lifecycle.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.ui.test.manifest)

    //Foundation
    implementation(libs.androidx.compose.foundation)

    //Paging-Compose
    implementation(libs.androidx.paging.paging.compose)

    //icon
    implementation(libs.androidx.compose.material.material.icons.extended)

    //Dagger-hilt
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    implementation(libs.androidx.hilt.hilt.navigation.compose)
    kapt(libs.androidx.hilt.hilt.compiler)

    //Moshi
    implementation(libs.com.squareup.moshi)
    kapt(libs.com.squareup.moshi.moshi.kotlin.codegen)

    //Retrofit
    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.retrofit2.converter.moshi)

    //OkHttp
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)

    //Firebase
    implementation("com.google.firebase:firebase-bom:30.3.1")
    implementation(libs.com.google.firebase.firebase.crashlytics.ktx)
    implementation(libs.com.google.firebase.firebase.analytics.ktx)
    implementation(libs.com.google.firebase.firebase.perf.ktx)

    //Coil
    implementation(libs.io.coil.kt.coil.compose)

    //Room
    implementation(libs.androidx.room.room.ktx)
    implementation(libs.androidx.room.room.paging)
    kapt(libs.androidx.room.room.compiler)

    //Camera
    implementation(libs.androidx.camera.camera.core)
    implementation(libs.androidx.camera.camera.camera2)
    implementation(libs.androidx.camera.camera.lifecycle)
    implementation(libs.androidx.camera.camera.video)

    implementation(libs.androidx.camera.camera.view)
    implementation(libs.androidx.camera.camera.extensions)

    //MLKit
    implementation(libs.com.google.mlkit.text.recognition)

    //Timber
    implementation(libs.com.jakewharton.timber)

    //Compose-Destinations
    implementation(libs.io.github.raamcosta.compose.destinations.animations.core)
    ksp(libs.io.github.raamcosta.compose.destinations.ksp)

    //Accompanist
    implementation(libs.com.google.accompanist.accompanist.systemuicontroller)
    implementation(libs.com.google.accompanist.accompanist.placeholder)
    implementation(libs.com.google.accompanist.accompanist.flowlayout)
    implementation(libs.com.google.accompanist.accompanist.pager)
    implementation(libs.com.google.accompanist.accompanist.swiperefresh)
    implementation(libs.com.google.accompanist.accompanist.pager.indicators)
    implementation(libs.com.google.accompanist.accompanist.permissions)

    //Chucker
    debugImplementation(libs.com.github.chuckerteam.chucker.library)
    releaseImplementation(libs.com.github.chuckerteam.chucker.library.no.op)

    //Palette
    implementation(libs.androidx.palette.palette.ktx)

}