buildscript {
//    ext {
//        compose_version = '1.3.0-rc01'
//    }
    repositories {
        mavenCentral()
        google()
    }
//    dependencies {
//        classpath libs.com.google.firebase.crashlytics
//        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.9.2'
//        classpath "com.google.firebase:perf-plugin:1.4.2"
//        classpath "com.google.gms:google-services:4.3.14"
//        classpath "com.google.dagger:hilt-android-gradle-plugin:2.44"
//    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application).apply(false)
    alias(libs.plugins.com.android.library).apply(false)
    alias(libs.plugins.org.jetbrains.kotlin.android).apply(false)
    alias(libs.plugins.nl.littlerobots.version.catalog.update)
    alias(libs.plugins.com.github.ben.manes.versions)
    alias(libs.plugins.com.google.dagger.hilt.android).apply(false)
    alias(libs.plugins.com.google.gms.google.services).apply(false)
    alias(libs.plugins.com.google.firebase.crashlytics).apply(false)
    alias(libs.plugins.com.google.firebase.firebase.perf).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}