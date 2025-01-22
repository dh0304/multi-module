package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.study.domain.Coordinator;

import java.util.List;

public interface CoordinatorRepository {

	List<Coordinator> findBy(MemberId memberId);
}
