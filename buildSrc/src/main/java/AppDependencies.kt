import Versions.paging
import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    //Room Database
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomDatabase}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomDatabase}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomDatabase}"
    const val roomPaging = "androidx.room:room-paging:${Versions.roomDatabase}"

    //Okhttp & Retrofit
    private const val retrofitConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    private const val okhttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //Coroutine
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    //Coil
    const val coilImage = "io.coil-kt:coil-compose:${Versions.coil}"

    //Compose Navigation
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeUI = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
    const val composeFoundation =
        "androidx.compose.foundation:foundation:${Versions.composeVersion}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"

    //Splash Api
    const val splashApi = "androidx.core:core-splashscreen:${Versions.splashApi}"

    //Accompanist
    const val accompanistDp =
        ("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")

    //Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"

    //Paging
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val pagingCompose = ("androidx.paging:paging-compose:${Versions.paging}")


    //Unit Testing
    private const val junitTest = "junit:junit:${Versions.junitTest}"
    private const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
    private const val mockitoCoreTest = "org.mockito:mockito-core:${Versions.mockitoTest}"
    const val hiltTest = "com.google.dagger:hilt-android-testing:${Versions.hiltTest}"
    const val hiltTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltTest}"
    const val truth ="com.google.truth:truth:${Versions.truthTest}"
    const val mockk= "io.mockk:mockk:${Versions.mockkTest}"

    object AppLibraries {

        val retrofitLibrary = arrayListOf<String>().apply {
            add(retrofitConverter)
            add(retrofit)
            add(okhttp)
            add(okhttpInterceptor)
        }
        val roomDatabase = arrayListOf<String>().apply {
            add(roomRuntime)
            add(roomKtx)
            add(roomCompiler)
            add(roomPaging)
        }
        val coroutineLibrary = arrayListOf<String>().apply {
            add(coroutine)
        }

        val compose = arrayListOf<String>().apply {
            add(composeFoundation)
            add(composeNavigation)
            add(hiltCompose)
            add(pagingCompose)
        }
        val coil = arrayListOf<String>().apply {
            add(coilImage)
        }
        val accompanist = arrayListOf<String>().apply {
            add(accompanistDp)
        }
        val splashScreen = arrayListOf<String>().apply {
            add(splashApi)
        }

        val unitTest = arrayListOf<String>().apply {
            add(junitTest)
            add(coroutineTest)
            add(mockitoCoreTest)
            add(hiltTest)
            add(truth)
            add(mockk)
        }
    }

}

fun DependencyHandler.installRetrofit() {
    implementation(AppDependencies.AppLibraries.retrofitLibrary)
}


fun DependencyHandler.installRoomDatabase() {
    implementation(AppDependencies.AppLibraries.roomDatabase)
    kapt(AppDependencies.roomCompiler)
}


fun DependencyHandler.installHilt() {
    implementation(AppDependencies.hilt)
    kapt(AppDependencies.hiltAndroidCompiler)
}

fun DependencyHandler.installCoroutine() {
    implementation(AppDependencies.AppLibraries.coroutineLibrary)
}


fun DependencyHandler.installCompose() {
    implementation(AppDependencies.AppLibraries.compose)
}

fun DependencyHandler.installCoil() {
    implementation(AppDependencies.AppLibraries.coil)
}

fun DependencyHandler.installAccompanist() {
    implementation(AppDependencies.AppLibraries.accompanist)
}

fun DependencyHandler.installSplashScreen() {
    implementation(AppDependencies.AppLibraries.splashScreen)
}

fun DependencyHandler.installUnitTest() {
    testImplementation(AppDependencies.AppLibraries.unitTest)
    kaptTest(AppDependencies.hiltTestCompiler)
}


//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.kaptTest(dependency: String) {
    add("kaptTest", dependency)
}


fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

