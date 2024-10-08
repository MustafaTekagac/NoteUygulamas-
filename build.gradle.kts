// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()        // Google repository
        mavenCentral()  // Maven Central repository

        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
       // classpath("com.android.tools.build:gradle:8.1.3") // Gradle plugin for Android
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10") // Kotlin Gradle plugin
    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

allprojects {
    repositories {
       google()        // Google repository
        mavenCentral()  // Maven Central repository
        maven { url = uri("https://jitpack.io") }
    }
}




