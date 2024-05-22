document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var startDate; // 선택한 시작일을 저장하는 변수
    var endDate; // 선택한 종료일을 저장하는 변수

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        dateClick: function(info) {
             alert('clicked ' + info.dateStr);
        },
        select: function(info) {
            startDate = info.startStr;
            endDate = info.end.toISOString().split('T')[0];
            console.log(startDate);
            console.log(endDate);

            // 기존 선택 영역 제거
            var selectedElements = document.querySelectorAll('.selected-range');
            selectedElements.forEach(function(element) {
                element.classList.remove('selected-range');
            });

            // 선택한 영역 강조
            var currentDate = new Date(startDate);

            while (currentDate <= endDate) {
                var formattedDate = currentDate.toISOString().split('T')[0];
                var dayEl = calendarEl.querySelector(`[data-date="${formattedDate}"]`);
                if (dayEl) {
                    dayEl.classList.add('selected-range');
                }
                currentDate.setDate(currentDate.getDate() + 1);
            }
        }
    });
    calendar.render();
});

const regions = [
    { name: '서울'},
    { name: '경기도'},
    { name: '인천'},
    { name: '강원도'},
    { name: '전라도'},
    { name: '충청도'},
    { name: '경상도'},
    { name: '제주도'},
];

document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.card'); //'.card'클래스를 가진 모든 요소를 저장.cards는 NodeList
    const selectedCardIds = new Set();

    cards.forEach(card => { //'.card'요소를 'card'의 인수로 처리
        card.addEventListener('click', () => { //클릭 이벤트 리스너
            card.classList.toggle('selected'); //토글

            // data-id 값을 가져와서 콘솔에 출력
            const dataId = card.getAttribute('data-id');

            if (card.classList.contains('selected')) {
                selectedCardIds.add(dataId);
            } else {
                selectedCardIds.delete(dataId);
            }

            // 선택된 데이터 ID 목록 출력
            console.log(`Selected card IDs: ${Array.from(selectedCardIds).join(', ')}`);
        });
    });
});
