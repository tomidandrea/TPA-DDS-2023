
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

Vue.component('select-servicios', {
    // props:{
    //     servicios: {
    //         type: Array,
    //         required: true
    //     }
    // },
    template:`
        <select class="chosen-select" multiple>
            <option v-for="servicio in servicios"
                    :key="servicio.id" 
                    :value="servicio.id">
                {{servicio.nombre}}
            </option>
        </select>`,

    data(){
        return {
            servicios: []
            //servicios:[{id:200,nombre:"servicio 2"}, {id:400,nombre:"servicio baño coto"}]
        }
    },
    created() {
        this.fetchData();
    },
    methods: {
        async fetchData() {
            try {
                const response = await fetch('http://localhost:4567/api/servicios');
                const data = await response.json();
                this.servicios = data; // Asignar los datos a tu propiedad de datos
                this.$nextTick(() => {
                    // Llamar a la función de inicialización del plugin Chosen aquí
                    $(this.$el).trigger('chosen:updated');
                });
            } catch (error) {
                console.error('Error al obtener datos:', error);
            }
        },
    }
})

var app = new Vue({
    el: '#app-select',
    // data:{
    //     servicios:[]
    // },
})
