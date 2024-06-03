let selectedDay = null;
const planId = parseInt($("#current-planId").val());

// Highlight the selected day card
$('.day-card').click(function() {
    $('.day-card').removeClass('selected');
    $(this).addClass('selected');
    selectedDay = $(this).data('day-index');
});

// Add items to the selected day
$(document).on('click', '.add-btn', function() {
    if (!selectedDay) {
        alert('일차를 선택해 주세요.');
        return;
    }

    const item = $(this).closest('.mark-list-card, .location-list-card').clone();
    item.find('.add-btn').remove();
    item.append('<button type="button" class="remove-btn">-</button>');
    $('.day-card[data-day-index="' + selectedDay + '"] .day-content').append(item);
});

// Remove items from the selected day
$(document).on('click', '.remove-btn', function() {
    $(this).closest('.mark-list-card, .location-list-card').remove();
});

$("#generate-btn").click(function () {
    const scheduleData = [];

    $(".day-card").each(function () {
        const dayIndex = $(this).data('day-index');
        const locationInfoIds = [];

        $(this).find('.day-content .mark-list-card, .day-content .location-list-card').each(function() {
            const locInfoId = $(this).find('input[type="hidden"]').val();
            locationInfoIds.push(parseInt(locInfoId));
        });

        scheduleData.push({
            day: parseInt(dayIndex),
            locationInfoIds: locationInfoIds
        });
    });

    var dataToSend = {
        planId: planId,
        schedule: scheduleData
    };

    $.ajax({
        type: "POST",
        url: "/planner/make-sched",  // replace with your actual endpoint
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function (response) {
            console.log(response);
            window.location.href = "/planner/plan-detail/" + response;
        },
        error: function (error) {
            console.error("Error sending data:", error);
        }
    });
});


function performSearch() {
    var keyword = $(".keyword").val();

    $.ajax({
        type: "GET",
        url: "/planner/location/search",
        data: { keyword: keyword },
        success: function(response) {
            // 기존 검색 결과를 지움
            $("#location-search-div").empty();

            // 새로운 검색 결과를 추가
            response.forEach(function(location) {
                var locationCard = `
                        <div>
                            <div class="location-list-card">
                                <div class="location-card-text-container">
                                    <div class="medium">${location.locTitle}</div>
                                    <div class="small">${location.locAddress}</div>
                                    <input type="hidden" value="${location.locInfoId}" />
                                </div>
                                <div class="location-card-button-container">
                                    <button type="button" class="add-btn">+</button>
                                </div>
                            </div>
                        </div>
                    `;
                $("#location-search-div").append(locationCard);
            });
        },
        error: function(error) {
            console.error("Error fetching locations:", error);
        }
    });
}

$("#search").click(function() {
    performSearch();
});

// Enter 키로 검색 수행
$(".keyword").keypress(function(event) {
    if (event.which == 13) { // Enter 키의 키코드는 13
        event.preventDefault(); // 기본 Enter 키 동작 방지 (폼 제출 방지)
        performSearch();
    }
});



