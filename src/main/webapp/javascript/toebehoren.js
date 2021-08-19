function toebehorenToevoegen() {

    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let model = document.querySelector("#mdl").value;
    let snummer = document.querySelector("#srn").value;
    let os = document.querySelector("#os").value;

    let formData = new FormData(document.querySelector("#toebehorenToevoegen"));
    let encData = new URLSearchParams(formData.entries());

    if (model.length <= 2 || snummer.length <= 2 || os.length <= 2) {
        window.alert("voer alle velden volledig in!");
    } else {
        fetch("rest/eigenaar/toebehorentoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        })
            .then(response => {
                if (response.ok) {
                    window.alert("toebehoren is succesvol toegevoegd!");
                } else {
                    window.alert("kan toebehoren niet toevoegen/de serienummer is al in bezit van een ander toebehoren");
                }
            }).catch(error => error);
    }
}

function toebehorenBekijken() {

    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#toebehorenbekijkenveld");

    overzicht.innerHTML = "";

    fetch("rest/eigenaar/toebehoren/" + voornaam + "/" +
        achternaam, {method: "GET"})
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                window.alert("Toebehoren bestaat niet/kan niet bekeken worden!");
            }
        })
        .then(data => {
            let ul = document.createElement('ul');
            ul.setAttribute("id", "toebehoren");
            data.forEach(function (item) {
                const li = document.createElement('li');
                li.innerHTML = "model: " + item.model + "  serienummer: " + item.serienummer +
                    "  omschrijving: " + item.omschrijving;
                ul.appendChild(li);
            });
            overzicht.appendChild(ul);
        })
        .catch(error => error);
}


