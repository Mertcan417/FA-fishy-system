function securecheck() {
    let fetchoptions = {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("auth")
        }
    };

    fetch("rest/eigenaar", fetchoptions)
        .then(response => {
            if (response.ok) {
                window.location.href = "beheerder.html";
            } else {
                window.alert("verboden toegang alleen voor beheerders bestemt!");
            }
        })
        .catch(error => error)
}

function uitloggen(){
    sessionStorage.clear();
    localStorage.clear();
    window.location.href = 'login.html';
}
