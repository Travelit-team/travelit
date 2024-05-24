/*document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var startDate; // 선택한 시작일을 저장하는 변수
    var endDate; // 선택한 종료일을 저장하는 변수

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'multiMonthYear',
        selectable: true,
        multiMonthMaxColumns: 1, // force a single column
        dateClick: function(info) {
             alert('clicked ' + info.dateStr);
        },
        select: function(info) {
            startDate = info.startStr;
            endDate = info.end.toISOString().split('T')[0];
            $("#startDate").val(startDate);
            $("#endDate").val(endDate);
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
                currentDate.setDate(currentDate.getDate());
            }
        }
    });
    calendar.render();
});*/

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var isSelectingStartDate = true;
    var startDate, endDate;

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'multiMonthYear',
        selectable: true,
        multiMonthMaxColumns: 1, // 한 열로 강제 설정
        dateClick: function(info) {
            if (isSelectingStartDate) {
                startDate = info.dateStr;
                $("#startDate").val(startDate);
                isSelectingStartDate = false;
                alert('시작일 선택: ' + startDate);
                console.log(startDate);
            } else {
                endDate = info.dateStr;
                $("#endDate").val(endDate);
                isSelectingStartDate = true;
                alert('종료일 선택: ' + endDate);
                console.log(endDate);
                highlightRange(startDate, endDate);
            }
        }
    });

    function highlightRange(startDate, endDate) {
        // 기존 선택 영역 제거
        var selectedElements = document.querySelectorAll('.selected-range');
        selectedElements.forEach(function(element) {
            element.classList.remove('selected-range');
        });

        // 선택한 영역 강조
        var currentDate = new Date(startDate);
        var end = new Date(endDate);

        while (currentDate <= end) {
            var formattedDate = currentDate.toISOString().split('T')[0];
            var dayEl = calendarEl.querySelector(`[data-date="${formattedDate}"]`);
            if (dayEl) {
                dayEl.classList.add('selected-range');
            }
            currentDate.setDate(currentDate.getDate() + 1);
        }
    }

    calendar.render();

    // 날짜 클릭 시 다시 선택 가능하게 설정
    calendarEl.addEventListener('click', function(event) {
        var clickedElement = event.target;
        if (clickedElement.classList.contains('fc-daygrid-day')) {
            var clickedDate = clickedElement.getAttribute('data-date');
            if (clickedDate === startDate || clickedDate === endDate) {
                if (clickedDate === startDate) {
                    isSelectingStartDate = true;
                    alert('시작일을 다시 선택해주세요.');
                } else if (clickedDate === endDate) {
                    isSelectingStartDate = false;
                    alert('종료일을 다시 선택해주세요.');
                }
            }
        }
    });
});