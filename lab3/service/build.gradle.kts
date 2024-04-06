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

    implementation(project(mapOf("path" to ":lab3:dao")))

    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.6")
    implementation("org.springframework.data:spring-data-jpa:3.2.2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    implementation("org.projectlombok:lombok:1.18.22")


}

tasks.test {
    useJUnitPlatform()
}