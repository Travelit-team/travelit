package com.back.travelit.service.planner;

import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.request.planner.ScheduleDayRequest;
import com.back.travelit.dto.request.planner.ScheduleReplaceReq;
import com.back.travelit.dto.request.planner.ScheduleRequest;
import com.back.travelit.dto.response.location.LocationPostResponse;
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

    private static final int VIEWS_COUNT = 6;
    private final PlanMapper planMapper;

    //planMapper.xml
    //플래너 생성 (플래너제목,여행일정 insert)
    public int setMakePlan(int userId, PlanCreateReq createReqDTO) {
        planMapper.insertMakePlan(userId, createReqDTO);
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
    public List<PlanLocCodeRes> selectAllLocName(String str) {
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        return planMapper.selectAllLocName(strArr);
    }

    //유저 여행 상세 스케줄 생성
    public void setMakeSched(ScheduleRequest scheduleRequest) {
        List<ScheduleRequest.DaySchedule> schedule = scheduleRequest.getSchedule();

        for (ScheduleRequest.DaySchedule daySchedule : schedule) {
            ScheduleDayRequest scheduleDay = new ScheduleDayRequest(daySchedule.getDay());
            planMapper.insertMakeSched(scheduleRequest.getPlanId(), scheduleDay);

            if(!daySchedule.getLocationInfoIds().isEmpty()) {
                planMapper.insertMakeSchedLocInfo(scheduleDay.getSchedId(), daySchedule.getLocationInfoIds());
            }
        }

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
    public void deletePlan(int planId) {
        planMapper.deletePlan(planId);
    }

//planListMapper.xml

    //지역 데이터 조회 for schedule
    //유저 즐겨찾기 지역 정보 조회
    @Transactional(readOnly = true)
    public List<PlanLocInfo> getMarkLocInfo(int userId){
        return planMapper.selectMarkLocInfo(userId);
    }
    //전체 지역 정보 조회
    @Transactional(readOnly = true)
    public List<PlanLocInfo> getLocInfo() {
        List<PlanLocInfo> planLocInfos = planMapper.selectAllLocInfo(null);
        return planLocInfos;
    }

    //키워드 지역 정보 조회
    @Transactional(readOnly = true)
    public List<PlanLocInfo> getLocInfoInKeyword(String keyword) {
        List<PlanLocInfo> planLocInfos = planMapper.selectAllLocInfo(keyword);
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

    //지역정보 리스트(조회수순 6개)
    public List<LocationPostResponse> selectLocList(){
        return planMapper.selectLocList(VIEWS_COUNT);
    }

    //페이징처리
//    int count(SearchRequest params);


}
