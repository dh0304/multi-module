dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    implementation(project(":domain:common-domain"))
}