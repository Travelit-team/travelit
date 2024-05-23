const fileContainer = document.getElementById('file-container');
const addFileBtn = document.getElementById('add-file-btn');
const maxFiles = 5;
let fileCount = 1;

function updateRemoveButtons() {
    const removeButtons = document.querySelectorAll('.remove-file-btn');
    if (fileCount > 1) {
        removeButtons.forEach(button => {
            button.style.display = 'inline-block';
        });
    } else {
        removeButtons.forEach(button => {
            button.style.display = 'none';
        });
    }
}

addFileBtn.addEventListener('click', function() {
    if (fileCount < maxFiles) {
        fileCount++;
        const newFormGroup = document.createElement('div');
        newFormGroup.classList.add('form-group');
        newFormGroup.innerHTML = `
                <label>파일</label>
                <input type="file" name="files" class="form-control-file" id="file${fileCount}">
                <button class="remove-file-btn">삭제</button>
              `;
        fileContainer.appendChild(newFormGroup);
        if (fileCount === maxFiles) {
            addFileBtn.disabled = true;
        }
        updateRemoveButtons();
    }
});

fileContainer.addEventListener('click', function(event) {
    const target = event.target;
    if (target.classList.contains('remove-file-btn')) {
        const fileGroup = target.parentElement;
        fileGroup.remove();
        fileCount--;
        addFileBtn.disabled = false;
        updateRemoveButtons();
    }
});



const infoContainer = document.getElementById('additional-info-container');
const addInfoBtn = document.getElementById('add-info-btn');
const maxInfoFields = 10; // 최대 필드 수

let infoCount = document.querySelectorAll('#additional-info-container > div').length;

function updateRemoveButtons() {
    const removeButtons = document.querySelectorAll('.remove-info-btn');
    if (infoCount > 0) {
        removeButtons.forEach(button => {
            button.disabled = false;
        });
    } else {
        removeButtons.forEach(button => {
            button.disabled = true;
        });
    }
    reIndexFields();
}

function reIndexFields() {
    const entries = document.querySelectorAll('#additional-info-container > .form-group');
    entries.forEach((entry, index) => {
        const keyInput = entry.querySelector('input[type="text"]');
        const valueTextarea = entry.querySelector('textarea');
        keyInput.id = `infoKey${index}`;
        keyInput.name = `subInfoList[${index}].infoKey`;
        valueTextarea.id = `infoValue${index}`;
        valueTextarea.name = `subInfoList[${index}].infoValue`;
    });
}

addInfoBtn.addEventListener('click', function() {
    if (infoCount < maxInfoFields) {
        infoCount++;
        const newFormGroup = document.createElement('div');
        newFormGroup.classList.add('form-group');
        newFormGroup.innerHTML = `
                <div class="width-100-percent">
                    <div class="sub-info-key-div">
                        <label>정보명</label>
                        <input type="text" class="form-control" id="infoKey${infoCount - 1}" th:field="*{subInfoList[${infoCount - 1}].infoKey}" th:errorclass="field-error" name="subInfoList[${infoCount - 1}].infoKey" placeholder="정보명 입력">
                        <div class="field-error" th:errors="*{subInfoList[${infoCount - 1}].infoKey}"></div>
                    </div>
                    <div class="sub-info-value-div">
                        <label>정보값</label>
                        <textarea rows="3" class="form-control" id="infoValue${infoCount - 1}" th:field="*{subInfoList[${infoCount - 1}].infoValue}" th:errorclass="field-error" name="subInfoList[${infoCount - 1}].infoValue" placeholder="정보값 입력"></textarea>
                        <div class="field-error" th:errors="*{subInfoList[${infoCount - 1}].infoValue}"></div>
                    </div>
                    <button class="remove-info-btn">삭제</button>
                </div>
          `;
        infoContainer.appendChild(newFormGroup);
        if (infoCount === maxInfoFields) {
            addInfoBtn.disabled = true;
        }
        updateRemoveButtons();
    }
});

infoContainer.addEventListener('click', function(event) {
    const target = event.target;
    if (target.classList.contains('remove-info-btn')) {
        target.parentElement.parentElement.remove();
        infoCount--;
        addInfoBtn.disabled = false;
        updateRemoveButtons();
    }
});
// 처음에 삭제 버튼을 비활성화합니다.
updateRemoveButtons();
