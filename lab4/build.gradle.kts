plugins {
    id("java")
}

group = "ru.aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":lab4:service")))
    implementation(project(mapOf("path" to ":lab4:dao")))
    implementation(project(mapOf("path" to ":lab4:controller")))
    implementation(project(mapOf("path" to ":lab4:security")))

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


    testImplementation("com.h2database:h2:2.2.224")

    implementation("org.hibernate.orm:hibernate-core:6.2.5.Final")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")
    testImplementation("org.springframework:spring-test:5.3.23")
    testImplementation("org.springframework.boot:spring-boot-test:2.7.3")
    implementation("jakarta.persistence:jakarta.persistence-api:2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure:2.7.3")
    implementation("org.springframework:spring-web:5.0.0.RELEASE")

    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.springframework.security:spring-security-test:6.1.9")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0-M2")


}

tasks.test {
    useJUnitPlatform()
}