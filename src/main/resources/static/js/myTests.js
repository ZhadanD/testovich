let listErrors = []

async function createTest() {
    clearErrors(listErrors)

    let newTest = {
        name: document.getElementById('test-name').value,
        type: document.getElementById('select-test-type').value,
        isPublic: document.getElementById('select-test-isPublic').value
    }

    let token = localStorage.getItem('token')

    if(!token)
        document.location = '/auth/login'

    let response = await fetch('/api/v1/tests', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newTest)
    })

    switch(response.status) {
        case 201:
            let createdTest = (await response.json()).data

            showTest(createdTest)

            document.getElementById('test-name').value = ''
            document.getElementById('select-test-type').value = 'FINAL_CHECK'
            document.getElementById('select-test-isPublic').value = 'false'
            break
        case 422:
            let errors = (await response.json()).errors

            listErrors = errors

            showErrors(errors)
            break
        case 403:
            document.location = '/auth/login'
            break
    }
}

async function getMyTests() {
    let token = localStorage.getItem('token')

    let response = await fetch('/api/v1/tests/my', {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })

    switch(response.status) {
        case 200:
            let tests = (await response.json()).data

            return tests
        case 403:
            document.location = '/auth/login'
            break
    }
}

function showTest(test) {
    let card = `
        <div class="test-card">
            <div class="test-card-header">
                <h2 class="test-card-title">${test.name}</h2>
            </div>
        
            <div class="test-card-content">
                <p>Тип: <span class="test-card-type">${
                                                        test.type == 'FINAL_CHECK' 
                                                        ? 'Итоговая проверка' 
                                                        : 'Мгновенная проверка'
                                                    }</span></p>
                <p class="test-card-isPublic">${test.isPublic ? 'Публичный' : 'Приватный'}</p>
            </div>

            <div class="test-card-footer">
                <a href="/myTests/test/${test.id}" class="test-card-button">Открыть</a>
            </div>
        </div>
    `

    document.getElementById('list-tests').innerHTML += card
}

async function showTests() {
    let tests = await getMyTests()

    for(let test of tests)
        showTest(test)
}

showTests()
