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
    testImplementation("junit:junit:4.13.1")

    implementation(project(mapOf("path" to ":lab4:service")))
    implementation(project(mapOf("path" to ":lab4:dao")))
    implementation(project(mapOf("path" to ":lab4:security")))


    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")
    implementation("org.springframework.data:spring-data-jpa:3.2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.6")



}

tasks.test {
    useJUnitPlatform()
}