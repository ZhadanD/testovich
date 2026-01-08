let answerId = 1

let addedAnswers = []

let listErrors = []

let renderedQuestions = []

function removeAnswer(answerId) {
    document.getElementById(`new-answer-${answerId}`).remove()

    addedAnswers = addedAnswers.filter(id => id != answerId)
}

function deleteAnswer(questionId, answerId) {
    document.getElementById(`question-${questionId}-answer-${answerId}`).remove()

    for(let question of renderedQuestions)
        if(question.id == questionId) {
            question.answers = question.answers.filter(answer => answer.id != answerId)

            break
        }
}

function addNewAnswer() {
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

function addAnswer(questionId) {
    let newAnswer = {
        id: answerId,
        name: document.getElementById(`question-${questionId}-answer-name`).value,
        isTrue: document.getElementById(`question-${questionId}-answer-isTrue`).checked,
    }

    document.getElementById(`question-${questionId}-answer-name`).value = ''
    document.getElementById(`question-${questionId}-answer-isTrue`).checked = false

    document.getElementById(`question-${questionId}-answers`).innerHTML += showAnswer(
        { id: questionId },
        newAnswer, 
        answerId
    )

    answerId++

    for(let question of renderedQuestions)
        if(question.id == questionId) {
            question.answers.push(newAnswer)

            break
        }
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

            alert('Вопрос создан!')

            let question = (await response.json()).data

            let answers = showAnswers(question)

            showQuestion(question, answers)
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

async function getTest() {
    let testId = document.getElementById('testId').value

    let token = localStorage.getItem('token')

    let response = await fetch(`/api/v1/tests/my/${testId}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })

    switch(response.status) {
        case 200:
            let test = (await response.json()).data

            return test
        case 403:
            document.location = '/auth/login'
            break
    }
}

function showAnswer(question, answer, answerId) {
    return `
            <div
                id="question-${question.id}-answer-${answerId}"
                class="container border border-success mb-10"
            >
                                            <input
                                                type="text"
                                                value="${answer.name}"
                                                class="beautiful-input"
                                                id="question-${question.id}-answer-${answerId}-name"
                                                placeholder="Введите название ответа"
                                            />

                                            <br />

                                            <div class="checkbox-wrapper">
                                                <input
                                                    type="checkbox"
                                                    id="question-${question.id}-answer-${answerId}-isTrue"
                                                    ${answer.isTrue ? 'checked' : ''}
                                                    class="checkbox-input"
                                                />
                                                <label
                                                    for="question-${question.id}-answer-${answerId}-isTrue"
                                                    class="checkbox-label"
                                                >
                                                    <span
                                                        class="custom-checkbox"
                                                    ></span>
                                                    Правильный?
                                                </label>
                                            </div>

                                            <button
                                                type="button"
                                                class="btn btn-danger"
                                                onclick="deleteAnswer(${question.id}, ${answerId})"
                                            >
                                                Удалить
                                            </button>
                                        </div>    
        `
}

function showAnswers(question) {
    let answers = ''
    
    for(let answer of question.answers)
        answers += showAnswer(question, answer, answer.id)

    return answers
}

async function saveQuestion(questionId) {
    let answers = []

    for(let question of renderedQuestions) {
        if(question.id == questionId) {
            for(let answer of question.answers) {
                answers.push(
                    {
                        name: document.getElementById(`question-${questionId}-answer-${answer.id}-name`).value,
                        isTrue: document.getElementById(`question-${questionId}-answer-${answer.id}-isTrue`).checked
                    }
                )
            }

            break
        }
    }
    
    let question = {
        id: questionId,
        name: document.getElementById(`question-${questionId}-name`).value,
        answers
    }

    let token = localStorage.getItem('token')

    let response = await fetch('/api/v1/questions', {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(question)
    })

    switch(response.status) {
        case 200:
            document.getElementById(`btn-question-${question.id}-text`).innerText = question.name

            alert('Успешное сохранение!')
            break
        case 403:
            document.location = '/auth/login'
            break
        case 422:
            alert('Ошибка валидации!')
            break
    }
}

async function deleteQuestion(questionId) {
    let decision = confirm('Вы точно хотите удалить вопрос?')

    if(decision) {
        let token = localStorage.getItem('token')

        let response = await fetch(`/api/v1/questions/${questionId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })

        switch(response.status) {
            case 204:
                document.getElementById(`question-${questionId}`).remove()

                alert('Успешное удаление!')
                break
            case 403:
                document.location = '/auth/login'
                break
        }
    }
}

function showQuestion(question, answers) {
    document.getElementById('questions').innerHTML += `
            <div id="question-${question.id}">
                            <div
                                id="btn-question-${question.id}"
                                class="accordion-btn"
                                onclick="toggleAccordion('show-question-${question.id}-accordion')"
                            >
                                <p style="font-size: 23px" id="btn-question-${question.id}-text">${question.name}</p>
                            </div>

                            <div
                                class="accordion"
                                id="show-question-${question.id}-accordion"
                            >
                                <div class="accordion-content">
                                    <div class="content-center">
                                        <h2>Настройки вопроса</h2>

                                        <button
                                            type="button"
                                            class="btn btn-success mb-10"
                                            id="save-question-btn"
                                            onclick="saveQuestion(${question.id})"
                                        >
                                            Сохранить
                                        </button>

                                        <button
                                            type="button"
                                            class="btn btn-danger mb-10"
                                            id="delete-question-btn"
                                            onclick="deleteQuestion(${question.id})"
                                        >
                                            Удалить
                                        </button>

                                        <label
                                            class="beautiful-label"
                                            for="question-${question.id}-name"
                                            >Название вопроса</label
                                        >
                                        <input
                                            type="text"
                                            class="beautiful-input mb-10"
                                            id="question-${question.id}-name"
                                            value="${question.name}"
                                            placeholder="Введите название вопроса"
                                        />
                                        <p
                                            id="error-name"
                                            class="error-text"
                                        ></p>
                                    </div>

                                    <button
                                        type="button"
                                        class="btn btn-success mb-10"
                                        id="addAnswerBtn"
                                        onclick="toggleAccordion('add-answer-question-${question.id}-accordion')"
                                    >
                                        Добавить ответ
                                    </button>

                                    <div
                                        class="accordion"
                                        id="add-answer-question-${question.id}-accordion"
                                    >
                                        <div class="accordion-content">
                                            <div class="beautiful-form">
                                                <h2>Добавление ответа</h2>

                                                <label
                                                    class="beautiful-label"
                                                    for="question-${question.id}-answer-name"
                                                    >Название ответа</label
                                                >
                                                <input
                                                    type="text"
                                                    class="beautiful-input"
                                                    id="question-${question.id}-answer-name"
                                                    placeholder="Введите название ответа"
                                                />
                                                <p
                                                    id="error-answer-name"
                                                    class="error-text"
                                                ></p>

                                                <br />

                                                <div class="checkbox-wrapper">
                                                    <input
                                                        type="checkbox"
                                                        id="question-${question.id}-answer-isTrue"
                                                        class="checkbox-input"
                                                    />
                                                    <label
                                                        for="question-${question.id}-answer-isTrue"
                                                        class="checkbox-label"
                                                    >
                                                        <span
                                                            class="custom-checkbox"
                                                        ></span>
                                                        Правильный?
                                                    </label>
                                                </div>

                                                <br />

                                                <button
                                                    type="button"
                                                    class="btn btn-success"
                                                    onclick="addAnswer(${question.id})"
                                                >
                                                    Добавить ответ
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <h2>Ответы</h2>

                                    <div id="question-${question.id}-answers" class="container border border-success content-center">
                                        ${answers}
                                    </div>
                                </div>
                            </div>
                        </div>
    `
}

async function showTest() {
    let test = await getTest()

    document.getElementById('test-name').value = test.name
    document.getElementById('select-test-type').value = test.type
    document.getElementById('select-test-isPublic').value = test.isPublic

    renderedQuestions = test.questions

    for(let question of test.questions) {
        let answers = showAnswers(question)

        showQuestion(question, answers)
    }
}

showTest()
