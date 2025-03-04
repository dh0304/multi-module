import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI
import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
    enabled = true
    mainClass.set("com.cafegory.api.CafegoryBackendRestApiApplication")
}

tasks.named<Jar>("jar") {
    enabled = false
}

plugins {
    id("com.epages.restdocs-api-spec")
    id("org.hidetake.swagger.generator")
}

val restdocsApiSpecVersion: String by project
val restAssuredVersion: String by project
val swaggerUI: String by project
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation(project(":domain:study-domain"))
    implementation(project(":domain:member-domain"))
    implementation(project(":domain:cafe-domain"))
    implementation(project(":domain:qna-domain"))
    implementation(project(":domain:common-domain"))
    implementation(project(":auth"))
    runtimeOnly(project(":db"))

    testImplementation(testFixtures(project(":auth")))
    testImplementation(testFixtures(project(":db")))
    testImplementation(testFixtures(project(":domain:member-domain")))

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-restassured")
    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:${restdocsApiSpecVersion}")
    testImplementation("com.epages:restdocs-api-spec-restassured:${restdocsApiSpecVersion}")
    swaggerUI("org.webjars:swagger-ui:${swaggerUI}")
}

//https://github.com/ePages-de/restdocs-api-spec?tab=readme-ov-file#gradle-configuration-options
configure<com.epages.restdocs.apispec.gradle.OpenApi3Extension> {
    setServer("http://localhost:8080")
    title = "Cafegory Server API"
    description = "Cafegory Server API for client"
    version = "0.0.1"
    format = "yaml"
}
swaggerSources {
    register("cafegoryApi").configure {
        setInputFile(file("${project.buildDir}/api-spec/openapi3.yaml"))
    }
}
tasks.withType<GenerateSwaggerUI> {
    dependsOn("openapi3")
}
tasks.register<Copy>("copySwaggerUI") {
    dependsOn("generateSwaggerUICafegoryApi")

    val generateSwaggerTask = tasks.named<GenerateSwaggerUI>("generateSwaggerUICafegoryApi").get()

    from(generateSwaggerTask.outputDir)
    into("${project.buildDir}/resources/main/static/docs")
}
tasks.named("bootRun") {
    dependsOn("copySwaggerUI")
}
tasks.named("bootJar") {
    dependsOn("copySwaggerUI")
}
tasks.withType<BootJar> {
    dependsOn("copySwaggerUI")
}