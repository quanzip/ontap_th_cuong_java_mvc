function deleteUser(id) {
    fetch(`https://localhost:8080/api/v1/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json'
        }
    })
        // .then(response => response.text())
        // .then(text => console.log(text))
}

function updateUser(user) {
    fetch('http://localhost:8080/api/v1/users', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json'
        },
        body: user
    })
}