plugins {
    id("java")
}

group = "aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("info.picocli:picocli:4.7.5")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    compileOnly ("org.projectlombok:lombok:1.18.30")
    implementation(project(":Bank"))
}

tasks.test {
    useJUnitPlatform()
}