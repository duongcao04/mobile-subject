plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.2" apply false  // Thêm dòng này

    // Existing plugins
    alias(libs.plugins.compose.compiler) apply false
}
