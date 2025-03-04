package com.cafegory.domain.config;

import com.cafegory.db.config.JpaConfig;
import com.cafegory.db.testfixtures.config.DatabaseCleanup;
import com.cafegory.db.testfixtures.config.RepositoryHolderConfig;
import com.cafegory.db.testfixtures.config.TestContainer;
import com.cafegory.domain.common.time.FakeTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@Import({DatabaseCleanup.class, FakeTimeUtil.class, RepositoryHolderConfig.class})
@ContextConfiguration(classes = {ServiceConfig.class, JpaConfig.class})
public abstract class ServiceTest extends TestContainer {

	@Autowired
	private DatabaseCleanup databaseCleanup;
	@Autowired
	private RepositoryHolderConfig repositoryHolderConfig;

	@BeforeEach
	public void setUp() {
		databaseCleanup.execute();
		repositoryHolderConfig.init();
	}
}
