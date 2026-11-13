plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    // namespace = pakinimi, mille alla R-klass ja BuildConfig genereeritakse
    namespace = "ee.ut.udukaart"
    compileSdk = 36

    defaultConfig {
        applicationId = "ee.ut.udukaart"
        minSdk = 29          // Android 10 ja uuem
        targetSdk = 36
        versionCode = 1
        versionName = "0.1"
    }

    buildTypes {
        release {
            // R8 kahandab ja hägustab ainult väljalaskeehituses
            isMinifyEnabled = false
        }
    }
    kotlinOptions { jvmTarget = "11" }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures { compose = true }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // Kontrolltöö 1, ülesanne 4: uus teek versioonikataloogi kaudu
    implementation(libs.androidx.core.splashscreen)

    // Eelvaate RENDERDAJA. Ainult silumisehitusse: väljalaskes teda vaja ei ole
    // ja ta suurendaks APK-d asjata.
    debugImplementation(libs.androidx.ui.tooling)
}
