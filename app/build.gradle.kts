plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.devtools.get().pluginId)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id("kotlin-parcelize")
    kotlin(libs.plugins.serialization.get().pluginId)
}

android {
    namespace = "com.telda.movieapp"

    defaultConfig {
        applicationId = "com.telda.movieapp"
        minSdk = 29
        compileSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = libs.versions.appVersionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    kotlin {
        jvmToolchain(17)
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + listOf(
            // Avoid having to stutter experimental annotations all over the codebase
            "-Xjvm-default=all",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.runtime.ExperimentalComposeApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.ExperimentalUnsignedTypes",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=1.8.10"
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }


    buildTypes.all {
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            versionNameSuffix = "-debug"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}


dependencies {
    // Import the Compose BOM
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.core.splashscreen)

    implementation(libs.navigation.runtime.ktx)
    implementation(libs.navigation.compose)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)


    // accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insets.ui)
    implementation(libs.accompanist.flowlayout)

    // Kotlin-based dependency injection Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.storage)

    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Kotlin Coroutines dependencies
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.lifecycle.common.java8)
    implementation(libs.lifecycle.extensions)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckerRelease)

    implementation(libs.core)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.datastore.preferences)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}