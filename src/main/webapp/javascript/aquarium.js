function getAquariumOverzicht() {
    let overzicht = document.querySelector("#aquariumoverzichtveld");
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");

    overzicht.innerHTML = "";

    fetch("rest/eigenaar/aquarium/" + voornaam + "/" + achternaam, {
        method: "GET"
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(data => {
        let ul = document.createElement('ul')
        ul.setAttribute('id', 'aquarium');
        data.forEach((item) => {
            const li = document.createElement('li');
            li.innerHTML = "aquariumnaam: " + item.naam + "  lengte: " + item.lengte + "  breedte: " + item.breedte + "  hoogte: "
                + item.hoogte + "  bodemsoort: " + item.bodemSoort + "  watersoort: " + item.waterSoort;
            ul.appendChild(li);
        });
        overzicht.appendChild(ul);
    }).catch(error => error);
}

function aquariumToevoegen() {

    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let formData = new FormData(document.querySelector("#aquariumToevoegen"));
    let encData = new URLSearchParams(formData.entries());
    let an = document.querySelector("#aquariumnaam").value;
    let lt = document.querySelector("#lengte").value;
    let bt = document.querySelector("#breedte").value;
    let ht = document.querySelector("#hoogte").value;
    let bs = document.querySelector("#bodemsoort").value;
    let ws = document.querySelector("#watersoort").value;

    if (an.length <= 2 || lt === "" || lt === 0 || bt === "" || bt === 0 || ht === "" || ht === 0 || bs.length <= 2 || ws.length <= 2) {
        window.alert("Niet alle velden zijn volledig ingevoerd!");
    } else {
        fetch("rest/eigenaar/aquariumtoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        })
            .then(response => {
                if (response.ok) {
                    window.alert("aquarium is succesvol toegevoegd!");
                } else {
                    window.alert("aquariumnaam bestaat al!");
                }
            })
            .catch(error => error)

    }
}

function aquariumVerwijderen() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let aquariumnaam = document.querySelector("#aquanaam").value;
    let formData = new FormData(document.querySelector("#aquariumVerwijderen"));
    let encData = new URLSearchParams(formData.entries());

    if (aquariumnaam.length <= 2) {
        window.alert("Vul het tekstveld volledig in/aquariumnaam is te kort!");
    }
    else {
        fetch("rest/eigenaar/aquariumverwijderen/" + voornaam + "/" + achternaam, {
            method: "DELETE",
            body: encData
        }).then(response => {
            if (response.ok) {
                window.alert("aquarium is succesvol verwijderd!");
                return response.json();
            } else {
                window.alert("aquarium bestaat niet!");
            }
        })
            .catch(error => error);
    }
}
