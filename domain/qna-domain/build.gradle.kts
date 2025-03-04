dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    implementation(project(":domain:study-domain"))
    implementation(project(":domain:member-domain"))
    implementation(project(":domain:common-domain"))

    testFixturesImplementation(project(":domain:study-domain"))
    testFixturesImplementation(project(":domain:member-domain"))
    testFixturesImplementation(project(":domain:common-domain"))

    testFixturesImplementation(testFixtures(project(":domain:common-domain")))
    testFixturesImplementation(testFixtures(project(":domain:member-domain")))
}