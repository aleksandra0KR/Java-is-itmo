plugins {
    id("java")
}


group = "aleksandra0KR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {


    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.6")
    implementation("org.springframework.data:spring-data-jpa:3.0.6")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    implementation("org.projectlombok:lombok:1.18.22")


    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")


    // https://mvnrepository.com/artifact/at.favre.lib/bcrypt
    implementation("at.favre.lib:bcrypt:0.9.0")

    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")

    implementation("org.springframework.amqp:spring-rabbit:3.0.6")



    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:4.13.1")


    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.6")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.6")
    implementation("org.springframework.data:spring-data-jpa:3.0.6")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    implementation("org.projectlombok:lombok:1.18.22")



    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.6")


}

tasks.test {
    useJUnitPlatform()
}

