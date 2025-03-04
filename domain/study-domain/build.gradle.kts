dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    implementation(project(":domain:member-domain"))
    implementation(project(":domain:cafe-domain"))
    implementation(project(":domain:common-domain"))

    testImplementation(testFixtures(project(":domain:common-domain")))

    testFixturesImplementation(project(":domain:cafe-domain"))
    testFixturesImplementation(project(":domain:member-domain"))
    testFixturesImplementation(project(":domain:common-domain"))

    testFixturesImplementation(testFixtures(project(":domain:common-domain")))
}