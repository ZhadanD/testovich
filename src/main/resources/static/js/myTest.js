let answerId = 1

let addedAnswers = []

let listErrors = []

function removeAnswer(answerId) {
    document.getElementById(`new-answer-${answerId}`).remove()

    addedAnswers = addedAnswers.filter(id => id != answerId)
}

function addAnswer() {
    let newAnswer = {
        name: document.getElementById("answer-name").value,
        isTrue: document.getElementById("answer-isTrue").checked,
    }

    document.getElementById("answer-name").value = ''
    document.getElementById("answer-isTrue").checked = false

    addedAnswers.push(answerId)

    document.getElementById("new-answers").innerHTML += `
        <div id="new-answer-${answerId}" class="container border border-success mb-10">
                                    <input
                                        type="text"
                                        value="${newAnswer.name}"
                                        class="beautiful-input"
                                        id="new-answer-name-${answerId}"
                                        placeholder="Введите название ответа"
                                    />

                                    <br />

                                    <div class="checkbox-wrapper">
                                        <input
                                            type="checkbox"
                                            ${newAnswer.isTrue ? 'checked' : ''}
                                            id="new-answer-isTrue-${answerId}"
                                            class="checkbox-input"
                                        />
                                        <label
                                            for="new-answer-isTrue-${answerId}"
                                            class="checkbox-label"
                                        >
                                            <span
                                                class="custom-checkbox"
                                            ></span>
                                            Правильный?
                                        </label>
                                    </div>

                                    <button type="button" class="btn btn-danger" onclick="removeAnswer(${answerId})">Удалить</button>
                                </div>
    `

    answerId++
}

async function createQuestion() {
    clearErrors(listErrors)

    let answers = []

    for(let answerId of addedAnswers) {
        let newAnswer = {
            name: document.getElementById(`new-answer-name-${answerId}`).value,
            isTrue: document.getElementById(`new-answer-isTrue-${answerId}`).checked
        }

        answers.push(newAnswer)
    }

    let newQuestion = {
        testId: document.getElementById('testId').value,
        name: document.getElementById('question-name').value,
        answers
    }

    let token = localStorage.getItem('token')

    let response = await fetch('/api/v1/questions', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newQuestion)
    })
    
    switch(response.status) {
        case 201:
            document.getElementById('question-name').value = ''
            document.getElementById('new-answers').innerHTML = ''

            addedAnswers = []

            toggleAccordion('createQuestionAccordion')
            break
        case 403:
            document.location = '/auth/login'
            break
        case 422:
            let errors = (await response.json()).errors

            listErrors = errors

            showErrors(errors)

            break
    }
}
