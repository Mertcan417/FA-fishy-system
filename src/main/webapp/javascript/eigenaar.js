function securecheck() {
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
            window.location.href = "beheerder.html";
        } else {
            window.alert("Verboden Toegang alleen voor beheerders!");
        }
    })();
}
