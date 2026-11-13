// Juurprojekti ehitusfail. Pluginad deklareeritakse siin, aga RAKENDATAKSE
// moodulites (apply false) — nii laaditakse iga plugin täpselt ühe korra.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
