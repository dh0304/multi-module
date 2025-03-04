package com.cafegory.api.config;

import com.cafegory.db.config.JpaConfig;
import com.cafegory.db.testfixtures.config.DatabaseCleanup;
import com.cafegory.db.testfixtures.config.RepositoryHolderConfig;
import com.cafegory.db.testfixtures.config.TestContainer;
import com.cafegory.api.helper.MemberSignupAcceptanceTestHelper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import({SignupAcceptanceTestConfig.class, DatabaseCleanup.class, RepositoryHolderConfig.class})
@ExtendWith(RestDocumentationExtension.class)
@ContextConfiguration(classes = {TestApiConfig.class, JpaConfig.class})
public abstract class ApiDocsTest extends TestContainer {

	@LocalServerPort
	private int port;

	protected RequestSpecification spec;

	@Autowired
	private DatabaseCleanup databaseCleanup;
	@Autowired
	protected MemberSignupAcceptanceTestHelper memberSignupHelper;
	@Autowired
	private RepositoryHolderConfig repositoryHolderConfig;

	@BeforeEach
	public void setUp(RestDocumentationContextProvider provider) {
		if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
			RestAssured.port = port;
			databaseCleanup.afterPropertiesSet();
		}

		databaseCleanup.execute();
		repositoryHolderConfig.init();

		this.spec = new RequestSpecBuilder().addFilter(documentationConfiguration(provider))
			.build();
	}
}
