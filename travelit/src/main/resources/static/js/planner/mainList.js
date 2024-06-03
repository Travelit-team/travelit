// 해당 지역 정보 페이지
function moveToLocation(locationCode) {
    // 서버로 locationCode 값을 포함한 요청을 보냅니다.
    const url = `/location/list?locationCode=${locationCode}`;
    window.location.href = url;
}

// 플래너 상세 페이지
function moveToPlanDetail(planId) {
    const url = '/planner/plan-detail/' + planId;
    window.location.href = url;
}


// 지역 정보 상세 페이지
function moveToLocationDetail(locationInfoId) {
    const url = '/location/detail/' + locationInfoId;
    window.location.href = url;
}
// 게시글 상세 페이지
function goViewPage(pro_ID) {
    const queryString = (location.search) ? location.search + `&PRO_ID=${pro_ID}` : `?PRO_ID=${pro_ID}`;
    location.href = '/product/productView' + queryString;
}

$('.slider').bxSlider({
    auto:true,
    speed:500,
    pause:5000,
    mode:'fade',
    pager:false,
    adaptiveHeight: true
});

$('.bx-viewport').mouseenter(function(){
    $('.bx-prev').css('display','inline-block');
    $('.bx-next').css('display','inline-block');
})
$('.bx-viewport').mouseleave(function(){
    $('.bx-prev').css('display','none');
    $('.bx-next').css('display','none');
})


$('.bx-next').mouseover(function(){
    $('.bx-next').css('display','inline-block');
    $('.bx-prev').css('display','inline-block');

});
$('.bx-prev').mouseover(function(){
    $('.bx-prev').css('display','inline-block');
    $('.bx-next').css('display','inline-block');

});
