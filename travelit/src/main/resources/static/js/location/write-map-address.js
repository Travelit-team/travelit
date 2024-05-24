const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
const options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng( 35.95, 128.2), //지도의 중심좌표.
    level: 13 //지도의 레벨(확대, 축소 정도이며 1 ~ 14 범위)
};


const map = new kakao.maps.Map(container, options);

const marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter()
});
// 주소-좌표 변환 객체를 생성합니다
const geocoder = new kakao.maps.services.Geocoder();

const infowindow = new kakao.maps.InfoWindow({zindex:1});

// 지도에 마커를 표시합니다
marker.setMap(map);

kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {

            let detailAddr = '';

            if(!!result[0].road_address) {
                detailAddr += '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>';
                $("#addr").val(result[0].road_address.address_name);
            }
            if(!!result[0].address) {
                if(detailAddr === '') $("#addr").val(result[0].address.address_name);
                detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
            }

            var content = '<div class="bAddr">' +
                '<span class="title">주소정보</span>' +
                detailAddr +
                '</div>';

            // 마커를 클릭한 위치에 표시합니다
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            $("#latitude").val(mouseEvent.latLng.Ma);
            $("#longitude").val(mouseEvent.latLng.La);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
        }
    });
});

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}


const onClickAddr = () => {
    new daum.Postcode({
        oncomplete: function(data) {
            let addr = '';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            $("#addr").val(addr);

            //아래부턴 주소 값을 통해 지도에 마커를 표시하는 로직입니다.

            geocoder.addressSearch(addr, function(result, status) {

                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {
                    const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    marker.setPosition(coords);

                    $("#latitude").val(result[0].y);
                    $("#longitude").val(result[0].x);

                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setLevel(3);
                    map.setCenter(coords);
                }
            });
        },
    }).open();
};