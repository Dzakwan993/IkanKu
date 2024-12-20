plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.ikanku"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ikanku"
        minSdk = 23  // Tingkatkan minSdkVersion menjadi 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2" // atau versi yang cocok dengan versi Compose Anda
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-splashscreen:1.0.1")


    implementation(platform(libs.androidx.compose.bom)) // Menggunakan BOM untuk Compose
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Jika menggunakan converter Gson (opsional)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.0")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.0") // Ini penting untuk observeAsState
    implementation ("androidx.compose.ui:ui:1.5.0")
    implementation ("androidx.compose.ui:ui-text:1.5.0")
    implementation ("androidx.compose.material:material:1.5.0")
    implementation ("com.google.android.material:material:1.9.0")


    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.material:material:1.4.3")

    // Dependensi tambahan untuk gambar dan ViewModel
    implementation("io.coil-kt:coil-compose:2.4.0") // Untuk memuat gambar dengan Coil
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1") // ViewModel untuk Compose

    implementation("com.google.accompanist:accompanist-pager:0.28.0") // Accompanist Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth:23.1.0") // Pastikan versi ini digunakan
    implementation("com.google.android.gms:play-services-auth:20.0.1") // Untuk Google Sign-In


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM untuk testing
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Accompanist Pager dan Pager Indicator
    implementation("com.google.accompanist:accompanist-pager:0.28.0") // Accompanist Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0") // Accompanist Pager Indicator
    implementation ("androidx.compose.foundation:foundation:1.5.0")


    implementation("androidx.navigation:navigation-compose:2.7.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")


}

