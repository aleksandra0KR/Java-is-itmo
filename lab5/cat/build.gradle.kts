plugins {
    id("java")
}

group = "aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(mapOf("path" to ":lab5:base-domain")))
    implementation(project(mapOf("path" to ":lab5:cat-client")))

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



}

tasks.test {
    useJUnitPlatform()
}