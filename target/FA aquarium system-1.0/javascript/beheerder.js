function verwijderEigenaar() {
    let vn = document.getElementById("vn").value;
    let an = document.getElementById("an").value;

    (async () => {
        let fetchoptions = {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + window.sessionStorage.getItem("auth")
            }
        };

        let response = await fetch("rest/eigenaar/verwijderen/" + vn + "/" + an, fetchoptions);

        if (vn.length <= 2 || an.length <= 2) {
            window.alert("Voer de onderstaande velden volledig in!");
        } else if (response.ok) {
            let data = await response.json();
            console.log(data);
            window.alert("eigenaar succesvol verwijderd!");
        } else {
            window.alert("eigenaar bestaat niet! Let op de ingevoerde velden!");
        }

    })();
}

function overzichtEigenaar() {

    let overzicht = document.querySelector("#eigenaaroverzicht");
    overzicht.innerHTML = "";

    (async () => {
        let fetchoptions = {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + window.sessionStorage.getItem("auth")
            }
        };

        let response = await fetch("rest/eigenaar", fetchoptions);
        if (response.ok) {
            let data = await response.json();
            console.log(data);
            let ul = document.createElement('ul');
            ul.setAttribute("id", "eigenaren");
            data.forEach(function (item, index) {
                const li = document.createElement('li');
                li.innerHTML = "voornaam: " + item.voornaam + "  achternaam: " + item.achternaam;
                ul.appendChild(li);
            });
            overzicht.appendChild(ul);
        }
    })();
}
