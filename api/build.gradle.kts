import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
    enabled = true
}

tasks.named<Jar>("jar") {
    enabled = false
}

dependencies {
    implementation(project(":db"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}