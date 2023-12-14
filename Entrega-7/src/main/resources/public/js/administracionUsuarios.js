
Vue.component('form-tipo-miembro', {
    template: `
    <form method="get" @submit.prevent="onSubmit">
        <div class="row d-block d-md-flex my-2 p-2 border-bottom border-secondary">
            <div class="col-8 col-md-6 mx-auto mx-md-0 my-1">
              <select v-model="tipoMiembro" class="form-select" aria-label="Default select example" id="select-miembro" name="tipoMiembro" required>
                <option value="" selected disabled hidden>Seleccione tipo de miembro</option>
                <option value="todos">Todos</option>
                <option value="afectado">Afectado</option>
                <option value="observador">Observador</option>
            </select>
            </div>
            <div class="col-8 col-md-4 mx-auto me-md-0 my-1 d-grid gap-2">
                <button class="btn btn-dark btn-principal" type="submit">Buscar</button>
            </div>
         </div>
    </form>
    `,
    data() {
        return {
            tipoMiembro: ''
        }
    },
    methods: {
        onSubmit() {
            console.log("Mando tipoMiembro: "+this.tipoMiembro)
            this.$emit('get-comunidad', this.tipoMiembro)
        }
    }
})


//Falta ver si agrupacion es null, usar servicio y viceversa
Vue.component('miembros', {
    props:{
        comunidad:{
            type: Object,
            required: true
        },
        tipo_miembro:{
            type: String,
            required: true
        }
    },
    template:`
        <div class="container-fluid" v-if="tipo_miembro!== ''">
            <h4 v-if="tipo_miembro == 'todos'">Miembros de {{comunidad.nombre}}</h4>
            <h4 v-else-if="tipo_miembro == 'afectado'">Afectados de {{comunidad.nombre}}</h4>
            <h4 v-else-if="tipo_miembro == 'observador'">Observadores de {{comunidad.nombre}}</h4>
            <p v-if="comunidad.miembros.length == 0">La comunidad no tiene miembros {{tipo_miembro}}</p>
            <div class="row border-bottom border-dark p-1" v-for="miembro in comunidad.miembros" :key="miembro.id">
                <div class="col-8 d-flex align-items-center pt-2">
                    <p class="mb-2">{{miembro.nombre}} - <span class="fw-bold">{{miembro.tipo}}</span></p>
                </div>
                <div class="col-4 d-flex align-items-center border-start pt-2">
                    <p class="mb-2 me-auto">
                        <a class="" data-bs-toggle="collapse" :href="'#collapseExample' + miembro.id" aria-expanded="false" aria-controls="collapseExample">
                            Ver detalles
                        </a>
                    </p>
                    <a type="" class="mb-2" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        <i class="fa-solid fa-trash-can fa-xl" style="color: #a80000;" @click="eliminarMiembro(miembro)"></i>
                    </a>
                </div>
                <div class="collapse col-12 mb-2" :id="'collapseExample' + miembro.id">
                    <div class="card card-body">
                        <ul>
                            <li>Apellido: {{miembro.apellido}}</li>
                            <li>Correo electrónico: {{miembro.correoElectronico}}</li>
                            <li>Teléfono: {{miembro.nroDeTelefono}}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>`,

    data() {
        return {
            miembros: []
        };
    },
    created() {
        this.miembros = this.comunidad.miembros;
    },
    methods: {
        eliminarMiembro(miembro) {
            this.$emit('eliminar-miembro', miembro);
        },
    }

})

var app = new Vue({
    el: '#app-miembros',
    data:{
        comunidades:[],
        comunidad: {},
        tipo_miembro:'',
        idComunidad: '',
        miembro: {}
    },
    created() {
        this.fetchData()
    },
    methods:{
        getComunidad(tipo_miembro) {
            this.tipo_miembro = tipo_miembro
            let idSesion = localStorage.getItem("IDSESION")
            fetch(`/api/${idSesion}/comunidadAdmin/${this.idComunidad}?tipoMiembro=${tipo_miembro}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Error al obtener la respuesta');
                })
                .then(data => {
                    // Manejar la respuesta JSON obtenida
                    console.log(data);
                    this.comunidad = data;
                    // Hacer algo con los datos recibidos, por ejemplo, actualizar el DOM con estos datos
                })
                .catch(error => {
                    // Manejar errores en la solicitud o la respuesta
                    console.error('Error:', error);
                });
        },
        async fetchData() {
            try {
                let idSesion = localStorage.getItem("IDSESION")
                const response = await fetch(`/api/${idSesion}/comunidadAdmin/`);
                const data = await response.json();
                this.comunidades = data; // Asignar los datos a tu propiedad de datos
                console.log(data)
            } catch (error) {
                console.error('Error al obtener datos:', error);
            }
        },
        setMiembro(miembro){
            this.miembro = miembro
            console.log(miembro);
        },
        eliminar(){
            fetch(`/api/comunidad/${this.comunidad.id}`, {
            method: "POST",
            body: JSON.stringify(this.miembro),
            headers: {
                "Content-Type": "application/json"
            }
            }).then((response) => {
                if (response.ok) {
                    console.log("Miembro eliminado");
                    let miembros = this.comunidad.miembros.filter(m => m.id !== this.miembro.id);
                    console.log(miembros);
                    Vue.set(this.comunidad, 'miembros', miembros);

                    // Resto de tu lógica...
                } else {
                    console.log("Error al crear incidente");
                }
            }).catch((error) => {
                console.error("Error en la petición:", error);
            });
        }
    },
})