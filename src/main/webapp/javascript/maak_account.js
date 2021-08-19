function aanmaken() {

    let voornaam = document.querySelector("#vn").value;
    let achternaam = document.querySelector("#an").value;

    let formData = new FormData(document.querySelector("#formData"));
    let encData = new URLSearchParams(formData);

    if (voornaam.length <= 2 || achternaam.length <= 2) {
        window.alert("Voer beide velden volledig in!");
    } else {
        fetch("rest/eigenaar", {
            method: "POST",
            body: encData
        })
            .then(response => {
                if (response.ok) {
                    window.localStorage.setItem("voornaam", voornaam);
                    window.localStorage.setItem("achternaam", achternaam);
                    window.location.href = "eigenaar.html";
                    window.alert("U account is succesvol aangemaakt!");
                } else {
                    window.alert("Dit account bestaat al! Kies een andere voornaam/achternaam!");
                }

            })
            .catch(error => error);
    }
}
