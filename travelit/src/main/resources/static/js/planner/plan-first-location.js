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
            $("#state").val(Array.from(selectedCardIds).join(','));
            console.log(`Selected card IDs: ${Array.from(selectedCardIds).join(',')}`);
        });
    });
});