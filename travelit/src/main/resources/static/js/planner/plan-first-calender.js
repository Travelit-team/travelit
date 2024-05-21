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
    { name: '충천도'},
    { name: '경상도'},
    { name: '제주도'},
];

const regionsContainer = document.getElementById('regions-container');
const itineraryContainer = document.getElementById('itinerary');
const titleInput = document.getElementById('title');

let selectedRegions = [];

function createRegionCard(region) {
    const card = document.createElement('div');
    card.classList.add('region-card');

    const nameElement = document.createElement('h3');
    nameElement.textContent = region.name;
    card.appendChild(nameElement);

    const button = document.createElement('button');
    button.textContent = '+';
    button.addEventListener('click', function() {
        addRegionToItinerary(region);
    });
    card.appendChild(button);

    return card;
}

function addRegionToItinerary(region) {
    if (!selectedRegions.includes(region)) {
        selectedRegions.push(region);
        updateItinerary();
    }
}

function updateItinerary() {
    itineraryContainer.innerHTML = '';

    if (selectedRegions.length > 0) {
        const title = document.createElement('h2');
        title.textContent = titleInput.value;
        itineraryContainer.appendChild(title);

        const list = document.createElement('ul');
        selectedRegions.forEach(region => {
            const listItem = document.createElement('li');
            listItem.textContent = region.name;
            list.appendChild(listItem);
                });
                itineraryContainer.appendChild(list);
              }
            }

            // Call the function to create and display region cards initially
            regions.forEach(region => regionsContainer.appendChild(createRegionCard(region)));


