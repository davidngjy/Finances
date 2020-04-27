plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

fun DependencyHandler.implementationAll(
    dependencies: List<String>,
    configuration: (dependencyNotation: Any) -> Unit = { implementation(it) }
) {
    dependencies.forEach(configuration)
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf(".*jar"))))

    implementation(project(":core:core-api"))
    implementation(project(":injector"))

    implementation(Libraries.KOTLIN)
    implementation(Libraries.KOTLIN_SERIALIZATION)
    implementation(Libraries.Coroutines.CORE)
    implementation(Libraries.Coroutines.ANDROID)

    // region UI.

    implementation(Libraries.COIL)

    // Navigation.
    implementationAll(Libraries.Android.Navigation.DEPENDENCIES)

    // Android.
    implementation(Libraries.Android.CORE)
    implementation(Libraries.Android.DESIGN)
    implementation(Libraries.Android.FRAGMENT)
    implementation(Libraries.Android.APPCOMPAT)
    implementation(Libraries.Android.CONSTRAINT_LAYOUT)

    implementationAll(Libraries.Adapter.DEPENDENCIES)

    // endregion

    // region DI.

    kapt(Libraries.Dagger.COMPILER)
    implementationAll(Libraries.Dagger.DEPENDENCIES)

    // endregion

    // Architecture components.
    implementationAll(Libraries.Android.Lifecycle.DEPENDENCIES)

}