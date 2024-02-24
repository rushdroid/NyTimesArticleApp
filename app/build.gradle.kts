plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("jacoco")
}

android {
    namespace = "com.example.nytimesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nytimesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            // Enable test coverage
            enableUnitTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")

// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

// Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

// Jetpack Compose
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("io.coil-kt:coil-compose:2.5.0")

// Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

// ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

// Retrofit with Coroutine Adapter
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Mockito
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

    // Kotlin Coroutines Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("io.mockk:mockk:1.12.0")

    // AndroidX Test Core
    testImplementation("androidx.test:core:1.5.0")

    // AndroidX Test Extensions
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}

kapt {
    correctErrorTypes = true
}


tasks.withType<Test> {
    // Enable Jacoco for unit tests
    extensions.configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf(
            "jdk.internal.*",
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*"
        ) // Allows it to run on Java 11
    }
}

//val jacocoTestReports by tasks.registering(JacocoReport::class) {
//    dependsOn("testDebugUnitTest", "createDebugCoverageReport")
//
//    reports {
//        xml.required.set(true)
//        html.required.set(true)
//    }
//
//    val sourceDirectories = mutableListOf(files("${project.projectDir}/src/main/java"))
//    val classDirectories = mutableListOf(files("${project.buildDir}/intermediates/javac/debug"))
//    val executionData = mutableListOf(files("${project.buildDir}/jacoco/testDebugUnitTest.exec"))
//
//    sourceDirectories.forEach { sourceDirectory -> sourceDirectories += sourceDirectory }
//    classDirectories.forEach { classDirectory -> classDirectories += classDirectory }
//    executionData.forEach { executionDatum -> executionData += executionDatum }
//
//    sourceDirectories.forEach { sourceDirectory ->
//        sourceDirectories += sourceDirectory
//    }
//    classDirectories.forEach { classDirectory ->
//        classDirectories += classDirectory
//    }
//    executionData.forEach { executionDatum ->
//        executionData += executionDatum
//    }
//
//    sourceDirectories.forEach { sourceDirectory ->
//        sourceDirectories += sourceDirectory
//    }
//    classDirectories.forEach { classDirectory ->
//        classDirectories += classDirectory
//    }
//    executionData.forEach { executionDatum ->
//        executionData += executionDatum
//    }
//
//    group = "Reporting"
//}

val jacocoTestReport by tasks.registering(JacocoReport::class) {
    dependsOn("testDebugUnitTest", "createDebugCoverageReport")

    val coverageSourceDirs = files("${project.projectDir}/src/main/java")
    val coverageClassDirs = files("${project.buildDir}/intermediates/javac/debug")
    val coverageExecutionData = files("${project.buildDir}/jacoco/testDebugUnitTest.exec")

    // Set source and class directories
    sourceDirectories.setFrom(coverageSourceDirs)
    classDirectories.setFrom(coverageClassDirs)

    // Set execution data
    executionData.setFrom(coverageExecutionData)

    // Configure report
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    // Exclude unwanted classes or packages
    doFirst {
        val excludes = listOf(
            // Exclude Hilt classes
            "com/google/dagger/*",
            "dagger/*",
            // Add more exclusions as needed
        )
        val includes = listOf(
            // Include only your code package
            "com/example/nytimesapp/*",
        )
        // Filter files based on includes and excludes
        classDirectories.filter {
            val filePath = it.absolutePath.replace("\\", "/")
            includes.any { include -> filePath.contains(include) } &&
                    excludes.none { exclude -> filePath.contains(exclude) }
        }
    }

    group = "Reporting"
}
