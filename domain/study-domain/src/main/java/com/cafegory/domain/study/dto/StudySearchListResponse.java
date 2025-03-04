package com.cafegory.domain.study.dto;

import lombok.*;
import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.MemberComms;
import com.cafegory.domain.study.domain.RecruitmentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudySearchListResponse {

    private StudyInfo studyInfo;
    private WriterInfo writerInfo;
    private CafeInfo cafeInfo;

    public static StudySearchListResponse of(StudyInfo studyInfo, WriterInfo writerInfo, CafeInfo cafeInfo) {
        StudySearchListResponse response = new StudySearchListResponse();
        response.studyInfo = studyInfo;
        response.writerInfo = writerInfo;
        response.cafeInfo = cafeInfo;

        return response;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudyInfo {

        private Long id;
        private String name;
        private List<CafeStudyTagType> tags;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private int maximumParticipants;
        private int currentParticipants;
        private int views;
        private MemberComms memberComms;
        private RecruitmentStatus recruitmentStatus;
    }

    @Getter
    @Setter
    @Builder
    public static class WriterInfo {

        private Long id;
        private String nickname;
    }

    @Getter
    @Setter
    @Builder
    public static class CafeInfo {

        private Long id;
        private String imgUrl;
        private String name;
    }
}
