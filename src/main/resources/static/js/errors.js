function showErrors(errors) {
    for(let error of errors)
        document.getElementById(`error-${error.name}`).innerText = error.message
}

function clearErrors(errors) {
    for(let error of errors)
        document.getElementById(`error-${error.name}`).innerText = ''
}
