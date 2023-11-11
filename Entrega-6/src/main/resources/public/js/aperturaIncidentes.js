
function abrirIncidente(){
    console.log("voy a abrir un incidente");
    var formData = {
        observacion: document.getElementById("observacion").value
    };

    fetch("http://localhost:4567/api/incidentes", {
        method: "POST",
        body: JSON.stringify(formData),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (response.ok) {
            console.log("Incidente creado");
            //return response.json();
        } else {
            console.log("Error al crear incidente");
        }
    })
}

var app = new Vue({
    el: '#app-select',
    data:{
        servicios:[]
    },
    created () {

        fetch('http://localhost:4567/api/servicios')
            .then(response => response.json())
            .then(data => {
                this.servicios = data
            })
    }

})