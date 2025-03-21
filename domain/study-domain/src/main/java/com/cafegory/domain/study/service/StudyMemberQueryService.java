package com.cafegory.domain.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.implement.StudyMemberReader;

@Service
@RequiredArgsConstructor
public class StudyMemberQueryService {

    private final StudyMemberReader studyMemberReader;

    public int getParticipantCount(StudyId studyId) {
        return studyMemberReader.readParticipantCountBy(studyId);
    }
}
