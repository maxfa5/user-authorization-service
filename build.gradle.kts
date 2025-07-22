plugins {
    id("java")
    id ("org.springframework.boot") version "3.5.0"
    id ("io.spring.dependency-management") version "1.1.4"
}

group = "org.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-aop:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.5.0")
   

   implementation("io.jsonwebtoken:jjwt-api:0.11.5")
   implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
   implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.0")
    testImplementation("org.mockito:mockito-core:5.2.0")
    testImplementation("com.h2database:h2:2.2.224")
    runtimeOnly("com.h2database:h2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.test {
    useJUnitPlatform()
}