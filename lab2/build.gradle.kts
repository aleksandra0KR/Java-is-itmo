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
    testImplementation(project(mapOf("path" to ":lab2:dao")))
    testImplementation("org.mockito:mockito-core:5.2.0")


    implementation("org.hibernate:hibernate-gradle-plugin:5.6.15.Final")

    implementation(project(mapOf("path" to ":lab2:dao")))
    implementation(project(mapOf("path" to ":lab2:service")))
    implementation(project(mapOf("path" to ":lab2:controller")))
    compileOnly ("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    testCompileOnly ("org.projectlombok:lombok:1.18.30")
    implementation("org.postgresql:postgresql:42.2.23")
}

tasks.test {
    useJUnitPlatform()
}