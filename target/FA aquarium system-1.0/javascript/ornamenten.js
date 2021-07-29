function ornamentenBekijken() {
    let aquariumnaam = document.querySelector("#aqonaam").value;
    let voornaam = localStorage.getItem("voornaam");
    let achternaam = localStorage.getItem("achternaam");
    let overzicht = document.querySelector("#ornamentenoverzichtveld");


    overzicht.innerHTML = "";
    (async () => {
        let response = await fetch("rest/eigenaar/ornamenten/" + voornaam + "/" +
            achternaam + "/" + aquariumnaam);

        if (response.ok) {
            let data = await response.json();
            console.log(data);
            let ul = document.createElement('ul');
            ul.setAttribute("id", "ornamenten");
            data.forEach(function (item, index) {
                const li = document.createElement('li');
                li.innerHTML = "naam: " + item.naam + "  kleur: " + item.kleur
                    + "  omschrijving: " + item.omschrijving + "  kanOpLuchtPomp: " + item.kanOpLuchtpomp;
                ul.appendChild(li);
            });

            overzicht.appendChild(ul);

        } else {
            window.alert("Ornament bestaat niet/kan niet bekeken worden!");
        }
    })();
}

