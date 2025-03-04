rootProject.name = "cafegory"

include(
    "api",
    "db",
    "domain:study-domain",
    "domain:member-domain",
    "domain:cafe-domain",
    "domain:qna-domain",
    "domain:common-domain",
    "auth"
)

pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagement: String by settings
    val restdocsApiSpecVersion: String by settings
    val swaggerGenerator: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagement)

                "com.epages.restdocs-api-spec" -> useVersion(restdocsApiSpecVersion)
                "org.hidetake.swagger.generator" -> useVersion(swaggerGenerator)
            }
        }
    }
}
