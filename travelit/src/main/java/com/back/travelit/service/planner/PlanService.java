package com.back.travelit.service.planner;

import com.back.travelit.domain.common.PagingSearchCriteria;
import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.request.planner.ScheduleCreateReq;
import com.back.travelit.dto.request.planner.ScheduleReplaceReq;
import com.back.travelit.dto.response.planner.*;
import com.back.travelit.mapper.planner.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {

    private final PlanMapper planMapper;

    //planMapper.xml
    //플래너 생성 (플래너제목,여행일정 insert)
    public int setMakePlan(PlanCreateReq createReqDTO) {
        planMapper.insertMakePlan(createReqDTO);
        int planId = createReqDTO.getPlanId();
        setMakePlanLoc(createReqDTO.getLocCode(), planId);

        return planId;
    }

    //플래너 생성 (여행지 지역코드 insert)
    private void setMakePlanLoc(String str, int planId) {
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        planMapper.insertMakePlanLoc(strArr, planId);
    }

    //지역 코드로 지역명 값 조회 for 플래너 여행지역
    public List<PlanLocCodeRes> selectAllLocCode(String str) {
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        return planMapper.selectAllLocCode(strArr);
    }

    ;

    //유저 여행 상세 스케줄 생성
    public void setMakeSched(ScheduleCreateReq schedCreateReqDTO) {
        planMapper.insertMakeSched(schedCreateReqDTO);
        int schedId = schedCreateReqDTO.getSchedId();
//        List<Integer> locInfoIds = schedCreateReqDTO.getLocInfoId();
//        planMapper.insertMakeSchedLocInfo(locInfoIds,schedId);
    }

    //플래너 상세 일정 수정
    public void setReschedule(ScheduleReplaceReq scheduleReplaceReq) {
        // 일정 삭제
        planMapper.deleteSched(scheduleReplaceReq.getPlanId());
        //새로운 일차 생성
        planMapper.insertReschedule(scheduleReplaceReq);
        //새로운 일정 생성
        planMapper.insertRescheduleLocInfo(scheduleReplaceReq);
    }

    //플래너 삭제
    public void deleteSched(int planId) {
        planMapper.deletePlan(planId);
    }

//planListMapper.xml

    //지역 데이터 조회 for schedule
    //유저 즐겨찾기 지역 정보 조회
    @Transactional(readOnly = true)
    public List<PlanLocInfo> getMarkLocInfo(int userId){
        List<PlanLocInfo> userMark = planMapper.selectMarkLocInfo(userId);
        return userMark;
    }
    //전체 지역 정보 조회
    @Transactional(readOnly = true)
    public List<PlanLocInfo> getLocInfo(PagingSearchCriteria pagingSearchCriteria) {
        List<PlanLocInfo> planLocInfos = planMapper.selectAllLocInfo(pagingSearchCriteria);
        return planLocInfos;
    }

    //플래너 상세 보기
    //플래너 기본 정보
    @Transactional(readOnly = true)
    public List <DetailPlan> getPlanDetail(int planId) {
        return planMapper.selectPlanDetail(planId);
    }
    //플래너 스케줄
    @Transactional(readOnly = true)
    public List <DetailSched>  getSchedDetail(int planId) {
        return planMapper.selectSchedDetail(planId);
    }

    //내 플래너 리스트
    @Transactional(readOnly = true)
    public List <PlannerList> getMyPlanList(int userId) {
        return planMapper.selectMyPlan(userId);
    }

    //페이징처리
//    int count(SearchRequest params);


}
