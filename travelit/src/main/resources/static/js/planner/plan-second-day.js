$(document).ready(function() {
    $('#search').click(function() {
        var keyword = $('.keyword').val(); // keyword 입력 필드의 값 가져오기

        $.ajax({
            type: 'GET',
            url: '/planner/all-location', // 요청 보낼 URL
            data: { keyword: keyword }, // 요청에 포함할 데이터
            success: function(data) {
                console.log('검색 결과:', data);
                // 검색 결과를 처리하는 코드 작성
            },
            error: function(xhr, status, error) {
                alert('검색 중 오류가 발생했습니다: ' + xhr.responseJSON.message);
                $('.keyword').addClass('field-error');
                const errorDiv = $('<div class="field-error"></div>').text(xhr.responseJSON.message);
                $('.keyword').after(errorDiv);
            }
        });
    });
});


