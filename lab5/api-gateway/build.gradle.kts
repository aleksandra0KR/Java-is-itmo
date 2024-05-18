plugins {
    id("java")
}

group = "aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":lab5:cat-client")))
    implementation(project(mapOf("path" to ":lab5:owner-client")))
    implementation(project(mapOf("path" to ":lab5:base-domain")))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")


    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.26")


    implementation("org.springframework.boot:spring-boot-starter:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")

    //     implementation("org.postgresql:postgresql:42.5.4")


    implementation("org.springframework.amqp:spring-rabbit:3.0.6")
    implementation("org.postgresql:postgresql:42.5.4")




    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")

    implementation("org.springframework.amqp:spring-rabbit:3.0.6")

    implementation("org.springframework:spring-webflux:6.0.3")
    compileOnly("javax.servlet:servlet-api:2.5")

    implementation("org.slf4j:slf4j-api:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.0.5")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.hibernate.orm:hibernate-core:6.1.7.Final")
    implementation("org.hibernate.common:hibernate-commons-annotations:6.0.6.Final")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.slf4j:slf4j-api:2.0.6")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.6")
    implementation("at.favre.lib:bcrypt:0.9.0")


}

tasks.test {
    useJUnitPlatform()
}