function getAquariumOverzicht() {
    let overzicht = document.querySelector("#aquariumoverzichtveld");
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");

    overzicht.innerHTML = "";

    (async () => {
        const response = await fetch("rest/eigenaar/aquarium/" + voornaam + "/" + achternaam, {
            method: "GET"
        });

        if (response.status === 200) {
            let data = await response.json();
            console.log(data);
            let ul = document.createElement('ul')
            ul.setAttribute('id', 'aquarium');
            data.forEach(function (item) {
                const li = document.createElement('li');
                li.innerHTML = "aquariumnaam: " + item.naam + "  lengte: " + item.lengte + "  breedte: " + item.breedte + "  hoogte: "
                    + item.hoogte + "  bodemsoort: " + item.bodemSoort + "  watersoort: " + item.waterSoort;
                ul.appendChild(li);
            });
            overzicht.appendChild(ul);
        }
    })();
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

    (async () => {
        let response = await fetch("rest/eigenaar/aquariumtoevoegen/" + voornaam + "/" + achternaam, {
            method: "PUT",
            body: encData
        });

        if (an.length <= 2 || lt === "" || lt === 0 || bt === "" || bt === 0 || ht === "" || ht === 0 || bs.lengte <= 2 || ws.lengte <= 2) {
            window.alert("Niet alle velden zijn volledig ingevoerd!");
        } else if (response.ok) {
            let data = await response.json();
            console.log(data);
            window.alert("aquarium is succesvol toegevoegd!");
        } else {
            window.alert("aquarium is niet succesvol toegevoegd/aquarium naam bestaat al!");
        }
    })();
}

function aquariumVerwijderen() {
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let aquariumnaam = document.querySelector("#aquanaam").value;
    let formData = new FormData(document.querySelector("#aquariumVerwijderen"));
    let encData = new URLSearchParams(formData.entries());

    (async () => {

        let response = await fetch("rest/eigenaar/aquariumverwijderen/" + voornaam + "/" + achternaam, {
            method: "DELETE",
            body: encData
        });

        if (aquariumnaam.length <= 2) {
            window.alert("Vul het tekstveld volledig in!");
        } else if (response.ok) {
            let data = await response.json();
            console.log(data);
            window.alert("aquarium is succesvol verwijderd!");
        } else {
            window.alert("aquarium bestaat niet!");
        }

    })();
}
