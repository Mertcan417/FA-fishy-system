function aanmaken() {
    let voornaam = document.querySelector("#vn").value;
    let achternaam = document.querySelector("#an").value;

    let formData = new FormData(document.querySelector("#formData"));
    let encData = new URLSearchParams(formData);

    (async () => {
        const response = await fetch("rest/eigenaar", {
            method: "POST",
            body: encData
        });
        if (voornaam.length <= 2 || achternaam.length <= 2) {
            window.alert("Voer beide velden volledig in!");
        } else if (response.status === 200) {
            let data = await response.json();
            console.log(data);
            window.localStorage.setItem("voornaam", voornaam);
            window.localStorage.setItem("achternaam", achternaam);
            window.location.href = "eigenaar.html";
            window.alert("U account is succesvol aangemaakt!");
        } else {
            window.alert("Dit account bestaat al! Kies een andere voornaam/achternaam!");
        }
    })();
}
