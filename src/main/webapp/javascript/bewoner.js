function bewonerToevoegen() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let formData = new FormData(document.querySelector("#bewonerToevoegen"));
    let encData = new URLSearchParams(formData.entries());

    let aquariumnaam = document.querySelector("#aqnaam").value;
    let soortnaam = document.querySelector("#snaam").value;
    let kleurnaam = document.querySelector("#knaam").value;
    let groepsdier = document.querySelector("#gdier").value;
    let type = document.querySelector("#tp").value;
    let aantal = document.querySelector("#at").value;

    (async () => {

        let response = await fetch("rest/eigenaar/bewonertoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        });

        if (aquariumnaam.length <= 2 || soortnaam.length <= 2 ||
            kleurnaam.length <= 2 || groepsdier.length <= 2 ||
            type.length <= 2 || aantal === 0) {
            window.alert("Niet alle velden zijn volledig ingevoerd!");
        } else if (response.ok) {
            let data = await response.json();
            console.log(data);
            window.alert("bewoner is succesvol toegevoegd!");
        } else {
            window.alert("aquarium naam bestaat niet!");
        }
    })();
}

function bewonersBekijken() {
    let aquariumnaam = document.getElementById("aquanaamo").value;
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#bewonersoverzichtveld");

    overzicht.innerHTML = "";

    (async () => {
        let response = await fetch("rest/eigenaar/bewoners/" + voornaam + "/" +
            achternaam + "/" + aquariumnaam);

        if (aquariumnaam === "" || aquariumnaam.length <= 2) {
            window.alert("Het veld is leeg! Vul het veld volledig in!");
        } else if (response.ok) {
            let data = await response.json();
            console.log(data);

            let ul = document.createElement('ul')
            ul.setAttribute("id", "bewoner");
            data.forEach(function (item) {
                const li = document.createElement('li');
                li.innerHTML = "soortnaam: " + item.soortnaam + "  kleurnaam: " + item.kleurnaam +
                    "  groepsdier: " + item.groepsdier + "  type: "
                    + item.type + "  aantal: " + item.aantal;
                ul.appendChild(li);
            });

            overzicht.appendChild(ul);


        } else {
            window.alert("aquarium naam bestaat niet!");
        }
    })();
}
