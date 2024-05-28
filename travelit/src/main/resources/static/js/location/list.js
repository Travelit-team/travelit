// 페이지 HTML draw
function drawPage(pagination, params) {

    // 1. 필수 파라미터가 없는 경우, 페이지 번호(페이지네이션) HTML을 제거(초기화)한 후 로직 종료
    if ( !pagination || !params ) {
        document.querySelector('.paging').innerHTML = '';
        throw new Error('Missing required parameters...');
    }

    // 2. 렌더링 할 HTML을 저장할 변수
    let html = '';

    // 3. 이전 페이지가 있는 경우, 즉 시작 페이지(startPage)가 1이 아닌 경우 첫 페이지 버튼과 이전 페이지 버튼을 HTML에 추가
    if (pagination.existPrevPage) {
        html += `
                    <a href="javascript:void(0);" onclick="movePage(1)" class="page_bt first">첫 페이지</a>
                    <a href="javascript:void(0);" onclick="movePage(${pagination.startPage - 1})" class="page_bt prev">이전 페이지</a>
                `;
    }

    /*
     * 4. 시작 페이지(startPage)와 끝 페이지(endPage) 사이의 페이지 번호(i)를 넘버링 하는 로직
     *    페이지 번호(i)와 현재 페이지 번호(params.page)가 동일한 경우, 페이지 번호(i)를 활성화(on) 처리
     */
    html += '<p>';
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        html += (i !== params.page)
            ? `<a href="javascript:void(0);" onclick="movePage(${i});">${i}</a>`
            : `<span class="on">${i}</span>`
    }
    html += '</p>';

    // 5. 현재 위치한 페이지 뒤에 데이터가 더 있는 경우, 다음 페이지 버튼과 끝 페이지 버튼을 HTML에 추가
    if (pagination.existNextPage) {
        html += `
                    <a href="javascript:void(0);" onclick="movePage(${pagination.endPage + 1});" class="page_bt next">다음 페이지</a>
                    <a href="javascript:void(0);" onclick="movePage(${pagination.totalPageCount});" class="page_bt last">마지막 페이지</a>
                `;
    }

    // 6. class가 "paging"인 요소를 찾아 HTML을 렌더링
    document.querySelector('.paging').innerHTML = html;
}


// 페이지 이동
function movePage(page) {

    const selectedSortElement = document.querySelector('.sort-option.selected');
    const sort = selectedSortElement ? selectedSortElement.getAttribute('data-sort') : 'views';

    // 1. drawPage( )의 각 버튼에 선언된 onclick 이벤트를 통해 전달받는 page(페이지 번호)를 기준으로 객체 생성
    const queryParams = {
        page: (page) ? page : 1,
        keyword: $("#keyword").val(),
        locationCode: params.locationCode,
        sort: sort
    }

    /*
     * 2. location.pathname : 리스트 페이지의 URI("/post/list.do")를 의미
     *    new URLSearchParams(queryParams).toString() : queryParams의 모든 프로퍼티(key-value)를 쿼리 스트링으로 변환
     *    URI + 쿼리 스트링에 해당하는 주소로 이동
     */
    location.href = location.pathname + '?' + new URLSearchParams(queryParams).toString();
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

const regions = document.querySelectorAll('.region');

regions.forEach(region => {
    region.addEventListener('click', function() {
        const locationCode = this.getAttribute('data-location-code');
        moveToLocation(locationCode);
    });
});

function moveToLocation(locationCode) {
    // 서버로 locationCode 값을 포함한 요청을 보냅니다.
    const url = `/location/list?locationCode=${locationCode}`;
    window.location.href = url;
}

function moveToLocationDetail(locationInfoId) {
    const url = '/location/detail/' + locationInfoId;
    window.location.href = url;
}

// 버튼 텍스트 변경
const sortButton = $('.dropbtn');
if (params.sort === 'latest') {
    sortButton.html('최신순 <i class="fas fa-caret-down"></i>');
} else if (params.sort === 'views') {
    sortButton.html('조회순 <i class="fas fa-caret-down"></i>');
} else if (params.sort === 'likes') {
    sortButton.html('좋아요순 <i class="fas fa-caret-down"></i>');
}

$('.sort-option').click(function() {
    // 모든 sort-option 요소에서 selected 클래스 제거
    $('.sort-option').removeClass('selected');

    // 클릭된 요소에 selected 클래스 추가
    $(this).addClass('selected');

    movePage(1);
});

 $('#keyword').on('keyup', function(e){
     if (e.keyCode === 13 || e.key === "Enter") {
         movePage(1);
     }
 });

drawPage(pagination, params);