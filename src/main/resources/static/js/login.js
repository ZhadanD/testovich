let listErrors = []

async function login() {
    clearErrors(listErrors)

    let user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
    }

    let response = await fetch(domain + '/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })

    switch(response.status) {
        case 200:
        {
            localStorage.setItem(
                'token',
                (await response.json()).data.token
            )

            document.location = '/'
        }
            break
        case 400:
            alert('Неверный логин или пароль!')
            break
        case 403:
            alert('Неверный логин или пароль!')
            break
        case 422:
            let errors = (await response.json()).errors

            listErrors = errors

            showErrors(errors)
            break
    }
}
