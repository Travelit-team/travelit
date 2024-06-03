function moveToLocation(locationCode) {
    // 서버로 locationCode 값을 포함한 요청을 보냅니다.
    const url = `/location/list?locationCode=${locationCode}`;
    window.location.href = url;
}

function moveToLocationDetail(locationInfoId) {
    const url = '/location/detail/' + locationInfoId;
    window.location.href = url;
}