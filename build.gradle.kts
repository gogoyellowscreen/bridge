import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
}

javafx {
    version = "17.0.0.1"
    modules("javafx.controls")
}

group = "me.ggys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apurebase:arkenv:3.1.0")
    implementation("org.openjfx:javafx-controls:17.0.0.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}