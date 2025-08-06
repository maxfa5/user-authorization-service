plugins {
    id("java")
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.8.0"
}

group = "org.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://build.shibboleth.net/nexus/content/repositories/releases/")
    }
}

dependencies {
    // Spring Boot starters (версии управляются dependency-management)
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    
    // Keycloak
    implementation("org.keycloak:keycloak-spring-boot-starter:24.0.2") // Обновлено до актуальной версии
    implementation("org.keycloak:keycloak-admin-client:24.0.2")
    
    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.5") // Обновлено
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
    
    // SAML
    implementation("org.springframework.security:spring-security-saml2-service-provider:6.2.7")
    
    // База данных
    runtimeOnly("com.h2database:h2:2.2.224")
    
    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
    // Тестирование
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.2.0")
}

tasks.test {
    useJUnitPlatform()
}