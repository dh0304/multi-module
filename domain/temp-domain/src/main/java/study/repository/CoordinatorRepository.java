package study.repository;

import member.domain.MemberId;
import study.domain.Coordinator;

import java.util.List;

public interface CoordinatorRepository {

	List<Coordinator> findBy(MemberId memberId);
}
