function login() {
    let voornaam = document.querySelector("#vn").value;
    let achternaam = document.querySelector("#an").value;
    let wachtwoord = document.querySelector("#ww").value;

    let formData = new FormData(document.querySelector("#formData"));
    let encData = new URLSearchParams(formData.entries());

    if (voornaam.length <= 2 || achternaam.length <= 2 || wachtwoord.length <= 2) {
        window.alert("Voer de onderstaande velden volledig in!");
    } else {
        fetch("rest/login", {method: "POST", body: encData})
            .then(response => {
                if (response.ok) {
                    window.alert("succesvol ingelogd!");
                    window.location.href = "eigenaar.html";
                    return response.json();
                } else {
                    window.alert("Dit account bestaat niet/ inloggegevens zijn onjuist!");
                }
            }).then(myJson => {
            window.sessionStorage.setItem("auth", myJson.JWT);
            window.localStorage.setItem("voornaam", voornaam);
            window.localStorage.setItem("achternaam", achternaam);
        })
            .catch(error => error)
    }
}
