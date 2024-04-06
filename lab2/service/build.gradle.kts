plugins {
    id("java")
}

group = "ru.aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")

    implementation("org.hibernate:hibernate-gradle-plugin:5.6.15.Final")


    implementation(project(mapOf("path" to ":lab2:DAO")))
}

tasks.test {
    useJUnitPlatform()
}