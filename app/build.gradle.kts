plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.sdsl.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sdsl.myapplication"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    // Add the exp4j library
    implementation(libs.exp4j)

    // Add dependencies for appcompat and material
    implementation(libs.appcompat.v161)
    implementation(libs.material.v180)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
}
