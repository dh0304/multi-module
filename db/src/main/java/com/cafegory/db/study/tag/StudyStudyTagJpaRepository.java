package com.cafegory.db.study.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyStudyTagJpaRepository extends JpaRepository<CafeStudyCafeStudyTagEntity, Long> {

	List<CafeStudyCafeStudyTagEntity> findByCafeStudy_Id(Long studyId);
}
