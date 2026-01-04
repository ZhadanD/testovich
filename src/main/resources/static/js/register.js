let listErrors = []

async function register() {
    clearErrors(listErrors)

    let password = document.getElementById('password').value

    let repeatPassword = document.getElementById('repeat-password').value

    if(password != repeatPassword)
        alert('Пароли не совпадают!')
    else {
        let newUser = {
            username: document.getElementById('username').value,
            password,
        }

        let response = await fetch(domain + '/api/v1/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        })

        switch(response.status) {
            case 201:
            {
                localStorage.setItem(
                    'token',
                    (await response.json()).data.token
                )

                document.location = '/'
            }
                break
            case 403:
                alert('Такой пользователь уже существует!')
                break
            case 422:
                let errors = (await response.json()).errors

                listErrors = errors

                showErrors(errors)
                break
        }
    }
}
