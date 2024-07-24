plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.com.google.gms)
}

android {
    namespace = "timeline.lizimumu.com.t"
    compileSdk = 35

    defaultConfig {
        applicationId = "timeline.lizimumu.com.t"
        minSdk = 22
        targetSdk = 29
        versionCode = 29
        versionName = "1.9.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = "app123456"
            keyAlias = "appkeystore"
            keyPassword = "app123456"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isJniDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.palette)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.bumptech.glide)
    annotationProcessor(libs.bumptech.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // reactivex
    implementation(libs.reactivex.rxjava)
    implementation(libs.reactivex.rxandroid)

    // background process
    implementation(libs.androidx.work.runtime)
}