import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "cn.xiaowine.app"
    compileSdk = 34

    defaultConfig {
        minSdk = 27
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}

dependencies {
//    implementation(libs.core.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-rc01")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(mapOf("path" to ":ui")))
}