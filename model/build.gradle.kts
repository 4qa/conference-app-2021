import io.github.droidkaigi.feeder.Dep
import io.github.droidkaigi.feeder.Versions

plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

apply(from = rootProject.file("gradle/android.gradle"))

kotlin {
    android()
    ios()
    sourceSets {
//        android
        val commonMain by getting{
            dependencies {
                api(project.dependencies.platform(Dep.Coroutines.bom))
                api(Dep.Coroutines.core)
//                {
//                    version {
//                        strictly(Versions.coroutines)
//                    }
//                }
                api(Dep.datetime)
                    api(Dep.napier)
            }
        }
        val commonTest by getting{
            dependencies {
                implementation(kotlin("test"))
            }
        }
        all{
            languageSettings.apply  {
            progressiveMode = true
//            optIn("kotlin.Experimental")
//            useExperimentalAnnotation("kotlinx.io.unsafe.ExperimentalTime")
//            useExperimentalAnnotation("kotlin.contracts.ExperimentalContracts")
        }}
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
//        languageVersion = "1.5"
//        freeCompilerArgs = ["-Xjvm-enable-preview"]
    }
}
