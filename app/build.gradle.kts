plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 26
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
   // implementation(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    val retrofitVersion= "2.11.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.8")
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.2")
    implementation ("org.mozilla:rhino:1.7.14")
    implementation ("androidx.navigation:navigation-compose:2.7.0")
    implementation ("androidx.compose.ui:ui:1.4.3") // Use the latest version
    implementation ("androidx.compose.material3:material3:1.1.0") // Material 3 support
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("org.mozilla:rhino:1.7.14")
    implementation("androidx.compose.material3:material3:1.2.1") // Or latest version
    implementation("androidx.navigation:navigation-compose:2.7.7") // Add this line! (Use latest version)
    implementation("androidx.compose.material:material-icons-core:1.6.5") // For icons
    implementation("androidx.compose.material:material-icons-extended:1.6.5") // For more icons (optional)


}