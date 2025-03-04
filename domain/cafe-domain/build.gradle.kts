dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    implementation(project(":domain:common-domain"))
    testImplementation(testFixtures(project(":domain:common-domain")))
    testFixturesImplementation(testFixtures(project(":domain:common-domain")))
}