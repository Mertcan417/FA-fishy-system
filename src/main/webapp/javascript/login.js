function login() {
    let voornaam = document.querySelector("#vn").value;
    let achternaam = document.querySelector("#an").value;
    let wachtwoord = document.querySelector("#ww").value;
    (async () => {
        let formData = new FormData(document.querySelector("#formData"));
        let encData = new URLSearchParams(formData.entries());

        let response = await fetch("rest/login", {method: "POST", body: encData});

        if (voornaam.length <= 2 || achternaam.length <= 2 || wachtwoord.length <= 2) {
            window.alert("Voer de onderstaande velden volledig in!");
        } else if (response.status === 200) {
            let myJson = await response.json();
            console.log(myJson);
            window.sessionStorage.setItem("auth", myJson.JWT);
            window.localStorage.setItem("voornaam", voornaam);
            window.localStorage.setItem("achternaam", achternaam);
            window.alert("succesvol ingelogd!");
            window.location.href = "eigenaar.html";

        } else {
            window.alert("Dit account bestaat niet/ inloggegevens zijn onjuist!");
        }
    })();
}
