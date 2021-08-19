function bewonerToevoegen() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let aquariumnaam = document.querySelector("#aqnaam").value;
    let soortnaam = document.querySelector("#snaam").value;
    let kleurnaam = document.querySelector("#knaam").value;
    let groepsdier = document.querySelector("#gdier").value;
    let type = document.querySelector("#tp").value;
    let aantal = document.querySelector("#at").value;

    groepsdier = groepsdier === "ja";

    let formData = new FormData(document.querySelector("#bewonerToevoegen"));
    formData.append('groepsdier', groepsdier);

    let encData = new URLSearchParams(formData.entries());

    if (aquariumnaam.length <= 2 || soortnaam.length <= 2 ||
        kleurnaam.length <= 2 || groepsdier.length <= 1 ||
        type.length <= 2 || aantal === 0) {
        window.alert("Niet alle velden zijn volledig ingevoerd!");
    } else {
        fetch("rest/eigenaar/bewonertoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        })
            .then(response => {
                if (response.ok) {
                    window.alert("bewoner is succesvol toegevoegd!");
                    return response.json();
                } else {
                    window.alert("aquarium naam bestaat niet!");
                }
            })
            .catch(error => error)
    }
}

function bewonersBekijken() {

    let aquariumnaam = document.getElementById("aquanaamo").value;
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#bewonersoverzichtveld");

    if (aquariumnaam === "" || aquariumnaam.length <= 2) {
        window.alert("Het veld voor de benaming van het aquariumnaam is leeg! Vul het veld volledig in!");
    } else {
        overzicht.innerHTML = "";

        fetch("rest/eigenaar/bewoners/" + voornaam + "/" + achternaam + "/" + aquariumnaam)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    window.alert("aquarium naam bestaat niet!");
                }
            }).then(data => {
            let ul = document.createElement('ul')
            ul.setAttribute("id", "bewoner");
            data.forEach(item => {
                const li = document.createElement('li');
                li.innerHTML = "soortnaam: " + item.soortnaam + "  kleurnaam: " + item.kleurnaam
                    + "  type: " + item.type + "  aantal: " + item.aantal;
                ul.appendChild(li);
            });
            overzicht.appendChild(ul);
        })
    }
}
