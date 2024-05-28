$('.slider').bxSlider({
    auto:false,
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

const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
const options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(detailLocation.latitude, detailLocation.longitude), //지도의 중심좌표.
    level: 3 //지도의 레벨(확대, 축소 정도이며 1 ~ 14 범위)
};

const map = new kakao.maps.Map(container, options);

const marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter()
});

marker.setMap(map);