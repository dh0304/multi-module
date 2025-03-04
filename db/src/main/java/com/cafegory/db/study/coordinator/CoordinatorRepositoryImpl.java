//package com.cafegory.db.study.coordinator;
//
//import com.cafegory.domain.member.domain.MemberId;
//import com.cafegory.domain.study.domain.Coordinator;
//import com.cafegory.domain.study.repository.CoordinatorRepository;
//import com.cafegory.db.study.study.CafeStudyEntity;
//import com.cafegory.db.study.study.StudyJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//@RequiredArgsConstructor
//public class CoordinatorRepositoryImpl implements CoordinatorRepository {
//
//	private final StudyJpaRepository studyJpaRepository;
//
//	@Override
//	public List<Coordinator> findBy(MemberId memberId) {
//		return studyJpaRepository.findByMember_Id(memberId.getId()).stream()
//			.map(CafeStudyEntity::toCoordinator)
//			.collect(Collectors.toList());
//	}
//}
