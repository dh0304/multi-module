package com.cafegory.db.study.tag;

import com.cafegory.domain.study.domain.CafeStudyTagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyTagJpaRepository extends JpaRepository<CafeStudyTagEntity, Long> {

	@Query("select t.id from CafeStudyTagEntity t" +
		" where t.type in :tags")
	List<Long> countByTags(@Param("tags") List<CafeStudyTagType> tags);
}
