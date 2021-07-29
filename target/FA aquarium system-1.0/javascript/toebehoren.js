function toebehorenToevoegen() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let model = document.querySelector("#mdl").value;
    let snummer = document.querySelector("#srn").value;
    let os = document.querySelector("#os").value;
    let formData = new FormData(document.querySelector("#toebehorenToevoegen"));
    let encData = new URLSearchParams(formData.entries());

    (async () => {

        let response = await fetch("rest/eigenaar/toebehorentoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        });

        let data = await response.json();
        if (model.length <= 2 || snummer.lengte <= 2 || os.length <= 2) {
            window.alert("voer alle velden volledig in!");
        } else if (response.ok) {
            window.alert("toebehoren is succesvol toegevoegd!");
        } else {
            window.alert("kan toebehoren niet toevoegen!");
        }
    })();
}

function toebehorenBekijken() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#toebehorenbekijkenveld");

    overzicht.innerHTML = "";

    (async () => {

        let response = await fetch("rest/eigenaar/toebehoren/" + voornaam + "/" +
            achternaam, {method: "GET"});

        if (response.ok) {
            let data = await response.json();
            console.log(data);
            let ul = document.createElement('ul');
            ul.setAttribute("id", "toebehoren");
            data.forEach(function (item, index) {
                const li = document.createElement('li');
                li.innerHTML = "model: " + item.model + "  serienummer: " + item.serienummer +
                    "  omschrijving: " + item.omschrijving;
                ul.appendChild(li);
            });

            overzicht.appendChild(ul);

        } else {
            window.alert("Toebehoren bestaat niet/kan niet bekeken worden!");
        }
    })();
}
