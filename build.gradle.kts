// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
            url = uri("https://jitpack.io")
        }
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.gradle)
    }
}

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.devtools)
    alias(libs.plugins.dagger.hilt.android).apply(false)
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.serializationPlugin)
    alias(libs.plugins.android.library).apply(false)
}
