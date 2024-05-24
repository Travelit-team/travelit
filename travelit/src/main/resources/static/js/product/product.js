// 게시글 저장(수정)
function savePost() {
    const form = document.getElementById('saveForm');
    const fields = [form.PRO_NAME, form.PRO_CATEGORY_TOTAL, form.PRO_CATEGORY_DETAIL, form.PRO_CONTENT,
        form.PRO_RES, form.PRO_PRICE, form.PRO_PER_MAX, form.PRO_LOCATION,
        form.TOUR_DATE, form.TOUR_TIME, form.TOUR_START, form.TOUR_END];
    const fieldNames = ['상품명', '카테고리1', '카테고리2', '상품 설명', '예약 가능 여부', '가격',
        '구매 가능 수량', '지역', '소요일', '소요시간', '투어시작일', '투어종료일'];

    for (let i = 0, len = fields.length; i < len; i++) {
        isValid(fields[i], fieldNames[i]);
    }

    document.getElementById('saveBtn').disabled = true;
    form.action = [[ ${post == null} ]] ? '/product/save' : '/product/update';
    form.submit();
}

function hasCoda(value) {
    return ((value.charCodeAt(value.length - 1) - 0xAC00) % 28) > 0;
}

function isValid(target, fieldName, focusTarget) {
    if (target.value.trim()) {
        return true;
    }

    const particle = (hasCoda(fieldName)) ? '을' : '를';
    const elementType = (target.type === 'text' || target.type === 'password' || target.type === 'search' || target.type === 'textarea') ? '입력' : '선택';
    alert( `${fieldName + particle} ${elementType}해 주세요.` );

    target.value = '';
    ( !focusTarget ? target : focusTarget).focus();
    throw new Error(`"${target.id}" is required...`)
}