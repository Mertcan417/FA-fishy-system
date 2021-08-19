function verwijderEigenaar() {
    let vn = document.getElementById("vn").value;
    let an = document.getElementById("an").value;

    if (vn.length <= 2 || an.length <= 2) {
        window.alert("Voer de onderstaande velden volledig in!");
    } else {
        let fetchoptions = {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + window.sessionStorage.getItem("auth")
            }
        }
         fetch("rest/eigenaar/verwijderen/" + vn + "/" + an, fetchoptions)
            .then(response => {
                if (response.ok) {
                    window.alert("eigenaar succesvol verwijderd!");
                    return response.json();
                } else {
                    window.alert("eigenaar bestaat niet!");
                    throw new Error("eigenaar bestaat niet!");
                }
            }).catch(error => console.error(error))
    }
}

function overzichtEigenaar() {

    let overzicht = document.querySelector("#eigenaaroverzicht");
    overzicht.innerHTML = "";

    let fetchoptions = {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("auth")
        }
    };

    fetch("rest/eigenaar", fetchoptions)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then(data => {
            let ul = document.createElement('ul');
            ul.setAttribute("id", "eigenaren");
            data.forEach((item) => {
                    const li = document.createElement('li');
                    li.innerHTML = "voornaam: " + item.voornaam + "  achternaam: " + item.achternaam;
                    ul.appendChild(li);
                })
            overzicht.appendChild(ul);
        })
        .catch(error => error);
}
