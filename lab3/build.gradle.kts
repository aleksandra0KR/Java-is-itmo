plugins {
    id("java")
}

group = "ru.aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":lab3:service")))
    implementation(project(mapOf("path" to ":lab3:dao")))
    implementation(project(mapOf("path" to ":lab3:controller")))

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


    testImplementation("com.h2database:h2:2.2.224")

    implementation("org.hibernate.orm:hibernate-core:6.2.5.Final")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
    testImplementation("org.springframework:spring-test:5.3.23")
    testImplementation("org.springframework.boot:spring-boot-test:2.7.3")
    implementation("jakarta.persistence:jakarta.persistence-api:2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure:2.7.3")
    implementation("org.springframework:spring-web:5.3.23")

    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
}