package com.cafegory.db.config;

import com.cafegory.db.testfixtures.config.DatabaseCleanup;
import com.cafegory.db.testfixtures.config.RepositoryHolderConfig;
import com.cafegory.db.testfixtures.config.TestContainer;
import com.cafegory.domain.common.time.FakeTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ActiveProfiles("test")
@Import({QueryDslConfig.class, DatabaseCleanup.class,
	FakeTimeUtil.class, TestAuditingConfig.class, RepositoryHolderConfig.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = TestJpaConfig.class)
public abstract class JpaTest extends TestContainer {

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
