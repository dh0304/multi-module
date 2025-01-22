package study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.StudyId;
import study.implement.StudyMemberReader;

@Service
@RequiredArgsConstructor
public class StudyMemberQueryService {

    private final StudyMemberReader studyMemberReader;

    public int getParticipantCount(StudyId studyId) {
        return studyMemberReader.readParticipantCountBy(studyId);
    }
}
