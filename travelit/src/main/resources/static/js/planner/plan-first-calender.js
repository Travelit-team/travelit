
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var isSelectingStartDate = true;
    var startDate, endDate;

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        titleFormat: function (date) {
            year = date.date.year;
            month = date.date.month + 1;

            return year + "년 " + month + "월";
        },
        dateClick: function(info) {
            if (isSelectingStartDate) {
                startDate = info.dateStr;
                $("#startDate").val(startDate);
                $("#endDate").val(startDate);
                isSelectingStartDate = false;
                alert('시작일 선택: ' + startDate);
                console.log(startDate);
            } else {
                endDate = info.dateStr;

                // 시작일보다 종료일이 빠른 경우 알림
                if (new Date(endDate) < new Date(startDate)) {
                    alert('종료일은 시작일보다 빠를 수 없습니다.');
                    return;
                }

                // 종료일이 시작일로부터 2주를 초과하는 경우 알림
                var twoWeeksFromStartDate = new Date(startDate);
                twoWeeksFromStartDate.setDate(twoWeeksFromStartDate.getDate() + 14);
                if (new Date(endDate) > twoWeeksFromStartDate) {
                    alert('종료일은 시작일로부터 최대 2주까지만 선택할 수 있습니다.');
                    return;
                }

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

    $(".fc .fc-daygrid-day.fc-day-today").removeClass("fc-day-today");

    $(".fc-next-button").click(function() {
        highlightRange(startDate, endDate);
    });
    $(".fc-prev-button").click(function() {
        highlightRange(startDate, endDate);
    });

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