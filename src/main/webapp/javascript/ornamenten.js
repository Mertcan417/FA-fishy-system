function ornamentenBekijken() {

    let aquariumnaam = document.querySelector("#aqonaam").value;
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#ornamentenoverzichtveld");

    overzicht.innerHTML = "";

    fetch("rest/eigenaar/ornamenten/" + voornaam + "/" + achternaam + "/" + aquariumnaam)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                window.alert("Ornament bestaat niet/kan niet bekeken worden!");
            }
        })
        .then(data => {
            let ul = document.createElement('ul');
            ul.setAttribute("id", "ornamenten");
            data.forEach(item => {
                const li = document.createElement('li');
                li.innerHTML = "naam: " + item.naam + "  kleur: " + item.kleur
                    + "  omschrijving: " + item.omschrijving + "  kanOpLuchtPomp: " + item.kanOpLuchtpomp;
                ul.appendChild(li);
            });

            overzicht.appendChild(ul);

        })
        .catch(error => error)
}
