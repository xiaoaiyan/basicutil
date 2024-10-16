import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

mavenPublishing{
    //Configuring what to publish
    configure(AndroidSingleVariantLibrary(
        variant = "release",
        sourcesJar = true,
        publishJavadocJar = true
    ))

    //Configuring Maven Central
    publishToMavenCentral(SonatypeHost.DEFAULT)
    // or when publishing to https://s01.oss.sonatype.org
//    publishToMavenCentral(SonatypeHost.S01)
    // or when publishing to https://central.sonatype.com/
//    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    //Configuring the POM - group:artifactId:version
    coordinates("com.basic.aiyan.basicutil", "fileutil", "1.0")
    pom{
        name.set("filtutil")
        description.set("A util for reading and writing files")
        inceptionYear.set("2020")
        url.set("https://github.com/xiaoaiyan/basicutil/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("xiaoaiyan")
                name.set("xiaoaiyan")
                url.set("https://github.com/xiaoaiyan/")
            }
        }
        scm {
            url.set("https://github.com/xiaoaiyan/basicutil/")
            connection.set("scm:git:git://github.com/xiaoaiyan/basicutil.git")
            developerConnection.set("scm:git:ssh://git@github.com/xiaoaiyan/basicutil.git")
        }
    }
}

android {
    namespace = "com.basic.aiyan.fileutil"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}