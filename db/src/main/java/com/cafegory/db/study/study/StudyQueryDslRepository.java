package com.cafegory.db.study.study;

import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.MemberComms;
import com.cafegory.domain.study.domain.RecruitmentStatus;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.dto.StudySearchListResponse;
import com.cafegory.db.util.PagingUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cafegory.db.cafe.cafe.QCafeEntity.cafeEntity;
import static com.cafegory.db.member.QMemberEntity.memberEntity;
import static com.cafegory.db.study.study.QCafeStudyEntity.cafeStudyEntity;
import static com.cafegory.db.study.studymember.QCafeStudyMemberEntity.cafeStudyMemberEntity;
import static com.cafegory.db.study.tag.QCafeStudyCafeStudyTagEntity.cafeStudyCafeStudyTagEntity;
import static com.cafegory.db.study.tag.QCafeStudyTagEntity.cafeStudyTagEntity;


@Repository
@RequiredArgsConstructor
public class StudyQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public SliceResponse<StudySearchListResponse> findStudies(CafeStudySearchListRequest request) {
        Pageable pageable = PagingUtil.of(request.getPage(), request.getSizePerPage());
        Slice<StudySearchListResponse.StudyInfo> studyInfos = findSlicedCafeStudyEntities(request, pageable);

        List<Long> studyIds = toStudyIds(studyInfos.getContent());

        Map<Long, List<CafeStudyTagType>> studyTagTypeMap = findStudyTagTypeMap(studyIds);
        Map<Long, Integer> currentParticipantsMap = findCurrentParticipantsMap(studyIds);
        studyInfos.getContent()
                .forEach(
                        studyInfo -> {
                            studyInfo.setTags(studyTagTypeMap.get(studyInfo.getId()));
                            //TODO getOrDeafult 메서드가 사용되면 안되고 get()이 사용되어야한다. get()을 사용하면 NullPointer예외 터진다. 카공 등록할 때 Coordinator가 StudyMember로 등록되는지 확인필요
                            studyInfo.setCurrentParticipants(currentParticipantsMap.getOrDefault(studyInfo.getId(), 0));
                        }
                );

        Map<Long, StudySearchListResponse.WriterInfo> writerInfoMap = findWriterInfoMap(studyIds);
        Map<Long, StudySearchListResponse.CafeInfo> cafeInfoMap = findCafeInfoMap(studyIds);

        List<StudySearchListResponse> responses = studyInfos.getContent().stream()
                .map(studyInfo -> StudySearchListResponse.of(
                                studyInfo,
                                writerInfoMap.get(studyInfo.getId()),
                                cafeInfoMap.get(studyInfo.getId())
                        )
                )
                .collect(Collectors.toList());

        return SliceResponse.of(studyInfos.hasNext(), studyInfos.getNumber(), responses);
    }

    private Slice<StudySearchListResponse.StudyInfo> findSlicedCafeStudyEntities(
            CafeStudySearchListRequest request, Pageable pageable) {
        JPAQuery<StudySearchListResponse.StudyInfo> query = queryFactory
                .select(Projections.fields(StudySearchListResponse.StudyInfo.class,
                        cafeStudyEntity.id.as("id"),
                        cafeStudyEntity.name.as("name"),
                        cafeStudyEntity.studyPeriod.startDateTime.as("startDateTime"),
                        cafeStudyEntity.studyPeriod.endDateTime.as("endDateTime"),
                        cafeStudyEntity.maxParticipants.as("maximumParticipants"),
                        cafeStudyEntity.views.as("views"),
                        cafeStudyEntity.memberComms.as("memberComms"),
                        cafeStudyEntity.recruitmentStatus.as("recruitmentStatus")
                )).distinct()
                .from(cafeStudyEntity)
                .join(cafeStudyEntity.cafe, cafeEntity)
                .join(cafeStudyEntity.member)
                .where(
                        keywordContains(request.getKeyword())
                                .or(cafeStudyNameContains(request.getKeyword())),
                        dateEq(request.getDate()),
                        cafeStudyTagTypeEq(request.getCafeStudyTag()),
                        hasAllCafeTagTypes(request.getCafeTags()),
                        memberCommsEq(request.getMemberComms())
                )
                .orderBy(
                        getRecruitmentStatusPriority().asc(),
                        cafeStudyEntity.id.desc()
                );

        // 페이징 우선 처리
        return PagingUtil.toSlice(query, pageable);
    }

    private Map<Long, Integer> findCurrentParticipantsMap(List<Long> studyIds) {
        List<Tuple> result = queryFactory
                .select(cafeStudyMemberEntity.cafeStudy.id,
                        cafeStudyMemberEntity.id.count())
                .from(cafeStudyMemberEntity)
                .join(cafeStudyMemberEntity.cafeStudy, cafeStudyEntity)
                .where(cafeStudyMemberEntity.cafeStudy.id.in(studyIds))
                .groupBy(cafeStudyMemberEntity.cafeStudy.id)
                .fetch();

        return result.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(cafeStudyMemberEntity.cafeStudy.id),
                        tuple -> tuple.get(cafeStudyMemberEntity.count()).intValue()
                ));
    }

    private Map<Long, List<CafeStudyTagType>> findStudyTagTypeMap(List<Long> studyIds) {
        List<Tuple> result = queryFactory
                .select(cafeStudyCafeStudyTagEntity.cafeStudy.id,
                        cafeStudyTagEntity.type)
                .from(cafeStudyCafeStudyTagEntity)
                .join(cafeStudyCafeStudyTagEntity.cafeStudyTag, cafeStudyTagEntity)
                .where(cafeStudyCafeStudyTagEntity.cafeStudy.id.in(studyIds))
                .fetch();

        return result.stream()
                .collect(Collectors.groupingBy(
                        tuple -> tuple.get(cafeStudyCafeStudyTagEntity.cafeStudy.id),
                        Collectors.mapping(tuple -> tuple.get(cafeStudyTagEntity.type),
                                Collectors.toList())
                ));
    }

    private Map<Long, StudySearchListResponse.WriterInfo> findWriterInfoMap(List<Long> studyIds) {
        List<Tuple> result = queryFactory
                .select(cafeStudyEntity.id,
                        memberEntity.id, memberEntity.nickname)
                .from(cafeStudyEntity)
                .join(cafeStudyEntity.member, memberEntity)
                .where(cafeStudyEntity.id.in(studyIds))
                .fetch();

        return result.stream()
                .collect(Collectors.toMap(
                                tuple -> tuple.get(cafeStudyEntity.id),
                                tuple -> StudySearchListResponse.WriterInfo.builder()
                                        .id(tuple.get(memberEntity.id))
                                        .nickname(tuple.get(memberEntity.nickname))
                                        .build()
                        )
                );
    }

    private Map<Long, StudySearchListResponse.CafeInfo> findCafeInfoMap(List<Long> studyIds) {
        List<Tuple> result = queryFactory
                .select(cafeStudyEntity.id,
                        cafeEntity.id, cafeEntity.mainImageUrl, cafeEntity.name)
                .from(cafeStudyEntity)
                .join(cafeStudyEntity.cafe, cafeEntity)
                .where(cafeStudyEntity.id.in(studyIds))
                .fetch();

        return result.stream()
                .collect(Collectors.toMap(
                                tuple -> tuple.get(cafeStudyEntity.id),
                                tuple -> StudySearchListResponse.CafeInfo.builder()
                                        .id(tuple.get(cafeEntity.id))
                                        .imgUrl(tuple.get(cafeEntity.mainImageUrl))
                                        .name(tuple.get(cafeEntity.name))
                                        .build()
                        )
                );
    }

    private List<Long> toStudyIds(List<StudySearchListResponse.StudyInfo> studyInfos) {
        return studyInfos.stream()
                .map(StudySearchListResponse.StudyInfo::getId)
                .collect(Collectors.toList());
    }

//    public SliceResponse<CafeStudyEntity> findCafeStudies(
//            CafeStudySearchListRequest request
//    ) {
//        Pageable pageable = PagingUtil.of(request.getPage(), request.getSizePerPage());
//
//        JPAQuery<CafeStudyEntity> query = queryFactory
//                .select(cafeStudyEntity).distinct()
//                .from(cafeStudyEntity)
//                .join(cafeStudyEntity.cafe, cafeEntity).fetchJoin()
//                .where(
//                        keywordContains(request.getKeyword())
//                                .or(cafeStudyNameContains(request.getKeyword())),
//                        dateEq(request.getDate()),
//                        cafeStudyTagTypeEq(request.getCafeStudyTagType()),
//                        hasAllCafeTagTypes(request.getCafeTagTypes()),
//                        memberCommsEq(request.getMemberComms())
//                )
//                .orderBy(
//                        getRecruitmentStatusPriority().asc(),
//                        cafeStudyEntity.id.desc()
//                );
//
//        return SliceResponse.of(PagingUtil.toSlice(query, pageable));
//    }

    private NumberExpression<Integer> getRecruitmentStatusPriority() {
        return new CaseBuilder()
                .when(cafeStudyEntity.recruitmentStatus.eq(RecruitmentStatus.OPEN)).then(0)
                .when(cafeStudyEntity.recruitmentStatus.eq(RecruitmentStatus.CLOSED)).then(1)
                .otherwise(3);
    }

    private BooleanExpression memberCommsEq(MemberComms memberComms) {
        return memberComms == null ? null : cafeStudyEntity.memberComms.eq(memberComms);
    }

    private BooleanExpression hasAllCafeTagTypes(List<CafeTagType> cafeTagTypes) {
        if (cafeTagTypes == null || cafeTagTypes.isEmpty())
            return null;

        return cafeTagTypes.stream()
                .map(type -> cafeEntity.cafeCafeTags.any().cafeTag.type.eq(type))
                .reduce(BooleanExpression::and)
                .orElse(null);
    }

    private BooleanExpression cafeStudyTagTypeEq(CafeStudyTagType cafeStudyTagType) {
        return cafeStudyTagType == null ? null :
                cafeStudyEntity.cafeStudyCafeStudyTags.any().cafeStudyTag.type.eq(cafeStudyTagType);
    }

    private BooleanExpression dateEq(LocalDate date) {
        if (date == null)
            return null;

        return cafeStudyEntity.studyPeriod.startDateTime.year().eq(date.getYear())
                .and(cafeStudyEntity.studyPeriod.startDateTime.month().eq(date.getMonthValue()))
                .and(cafeStudyEntity.studyPeriod.startDateTime.dayOfMonth().eq(date.getDayOfMonth()));
    }

    private BooleanExpression keywordContains(String keyword) {
        // 만약 이 메서드가 동작하지 않는다면 DB의 맞는 Expressions.stringTemplate 의 내부 구문을 바꿔야 한다.
        // DB에 등록된 키워드와 파라미터의 키워드 둘다 공백제거 한뒤 비교한다.
        return keyword == null ? null :
                Expressions.stringTemplate("function('replace', {0}, ' ', '')", cafeEntity.cafeKeywords.any().keyword)
                        .likeIgnoreCase("%" + keyword.replace(" ", "") + "%");
    }

    private BooleanExpression cafeStudyNameContains(String cafeStudyName) {
        // 만약 이 메서드가 동작하지 않는다면 DB의 맞는 Expressions.stringTemplate 의 내부 구문을 바꿔야 한다.
        // DB에 등록된 키워드와 파라미터의 키워드 둘다 공백제거 한뒤 비교한다.
        return cafeStudyName == null ? null :
                Expressions.stringTemplate("function('replace', {0}, ' ', '')", cafeStudyEntity.name)
                        .likeIgnoreCase("%" + cafeStudyName.replace(" ", "") + "%");
    }
}
