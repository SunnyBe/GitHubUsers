import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Dependencies {
    const val kotlinCore = "androidx.core:core-ktx:${Versions.kotlinCoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val swipeToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val constraint =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:1.4.0"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
    const val navTesting = "androidx.navigation:navigation-testing:${Versions.navVersion}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifeCycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifeCycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    const val CoreTesting = "androidx.arch.core:core-testing:${Versions.archVersion}"

    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val mockitoCore = "org.mockito:mockito-core:3.10.0"
    const val jUnitExtKtx = "androidx.test.ext:junit-ktx:1.1.3"
    const val testRunner = "androidx.test:runner:1.4.0"
    const val idlingResourceTest =
        "androidx.test.espresso.idling:idling-concurrent:${Versions.espressoVersion}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoVersion}"
    const val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espressoVersion}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLibVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinStdLibVersion}"
    const val buildToolGradle = "com.android.tools.build:gradle:${Versions.buildToolGradleVersion}"

    const val hilt = "com.google.dagger:hilt-android:2.38.1"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.38.1"
    const val hiltTest = "com.google.dagger:hilt-android-testing:2.38.1"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2"
    const val turbine = "app.cash.turbine:turbine:0.7.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:4.8.1"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:2.8.1"
    const val mockServer = "com.squareup.okhttp3:mockwebserver:4.6.0"
    const val idlingResource = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val room = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomGuava = "androidx.room:room-guava:${Versions.roomVersion}"
    const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"

    const val glide = "com.github.bumptech.glide:glide:4.12.0"
    const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"

    const val coreTest = "androidx.test:core:1.4.0"
}

object Versions {
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31

    const val kotlinStdLibVersion = "1.4.32"
    const val buildToolGradleVersion = "4.1.3"

    const val kotlinCoreVersion = "1.7.0"
    const val appCompatVersion = "1.4.1"
    const val materialVersion = "1.5.0"
    const val constraintVersion = "2.1.3"
    const val jUnitVersion = "4.13.2"
    const val jUnitExtVersion = "1.1.2"
    const val espressoVersion = "3.4.0"
    const val navVersion = "2.4.1"
    const val lifecycleVersion = "2.4.1"
    const val archVersion = "2.1.0"
    const val roomVersion = "2.4.2"
}

object AppDetail {
    const val applicationId = "com.sundayndu.githubusers"
    const val appName = "WeDey"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

fun getDate(forTxtFile: Boolean): String {
    val current = LocalDateTime.now()
    val formatter = if (forTxtFile) {
        DateTimeFormatter.ofPattern("dd MMM, yyy")
    } else {
        DateTimeFormatter.ofPattern("yyyyMMdd")
    }
    return current.format(formatter)
}