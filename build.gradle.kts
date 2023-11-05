import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    alias(libs.plugins.style)
    alias(libs.plugins.dokka)
    id("fopbot-publish")
    id("fopbot-sign")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

buildscript {
    dependencies {
        classpath(libs.dokkaBase)
    }
}

group = "org.tudalgo"
version = file("version").readLines().first()

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

javafx {
    version = "17.0.1"
    modules = listOf("javafx.controls")
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.batik)
    implementation(libs.imageio.batik)
    dokkaPlugin(libs.dokkaKotlinAsJavaPlugin)
    dokkaPlugin(libs.dokkaVersioningPlugin)
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set("FOPBot")
    dokkaSourceSets.configureEach {
        includes.from("Module.md")
        jdkVersion.set(17)
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl.set(URL("https://github.com/tudalgo/fopbot/tree/master/src"))
            remoteLineSuffix.set("#L")
        }
        perPackageOption {
            reportUndocumented.set(true)
            documentedVisibilities.set(setOf(Visibility.PUBLIC, Visibility.PROTECTED))
        }
    }
}

tasks.dokkaHtml {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        customAssets = listOf(file("logo-icon.svg"))
        footerMessage = "(c) 2023 tudalgo.org"
    }
}
