function showButtons() {
    let token = localStorage.getItem('token')

    let content = ''

    if(token)
        content = `
            <div>
                <a href="/myTests" class="btn btn-outline-success">
                    Мои тесты
                </a>
            </div>
        `
    else
        content = `
            <div>
                <a href="/auth/login" class="btn btn-outline-success">
                    Войти
                </a>

                <a href="/auth/register" class="btn btn-success">
                    Регистрация
                </a>
            </div>
        `

    document.getElementById('headerNav').innerHTML = content
}

showButtons()
