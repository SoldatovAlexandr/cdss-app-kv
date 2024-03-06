import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.6.0-dev1296"
}

group = "edu.asoldatov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.exposed:exposed-core:0.44.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.44.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.44.0")

    implementation("org.xerial:sqlite-jdbc:3.43.0.0")
    implementation("ai.catboost:catboost-prediction:1.2.2")
    implementation("io.github.epicarchitect:calendar-compose-basis:1.0.5")
    implementation("io.github.epicarchitect:calendar-compose-datepicker:1.0.5")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "cdss-app-kv"
            packageVersion = "1.0.0"
        }
    }
}
