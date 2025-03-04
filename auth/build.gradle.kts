val jjwtVersion: String by project
val springCloudStarterAwsVersion: String by project
val lombokVersion: String by project
dependencies {
    compileOnly("org.springframework:spring-context")
    implementation("org.springframework:spring-web")
    implementation("org.springframework:spring-tx")
    implementation("org.springframework.cloud:spring-cloud-starter-aws:${springCloudStarterAwsVersion}.RELEASE")

    implementation("io.jsonwebtoken:jjwt-api:${jjwtVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${jjwtVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jjwtVersion}")
    testFixturesImplementation("io.jsonwebtoken:jjwt-api:${jjwtVersion}")

    implementation(project(":domain:member-domain"))
    implementation(project(":domain:common-domain"))

    testFixturesImplementation(project(":domain:member-domain"))
    testFixturesImplementation(project(":domain:common-domain"))

    testFixturesImplementation(testFixtures(project(":domain:member-domain")))
}