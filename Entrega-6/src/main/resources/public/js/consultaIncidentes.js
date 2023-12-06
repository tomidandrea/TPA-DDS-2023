
Vue.component('form-incidentes', {
    template: `
    <form method="get" @submit.prevent="onSubmit">
            <div class="row d-block d-md-flex my-2 p-2 border-bottom border-secondary">

                <div class="col-8 col-md-6 mx-auto mx-md-0 my-1">
                    <select class="form-select" name="estado" required  v-model="estado">
                        <option value="" selected disabled hidden>Seleccione estado de incidente</option>
                        <option value="todos">Todos</option>
                        <option value="abierto">Abierto</option>
                        <option value="cerrado">Cerrado</option>
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
            estado: ''
        }
    },
    methods: {
        onSubmit() {
            this.$emit('get-incidentes', this.estado)
        }
    }
})

//Falta ver si agrupacion es null, usar servicio y viceversa
Vue.component('elemento-tabla', {
    props:{
        incidentes:{
            type: Array,
            required: true
        },
        estado:{
            type: String,
            required: true
        }
    },
    template:`
        <table class="table table-hover table-bordered" v-if="incidentes.length > 0">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Servicio afectado</th>
                    <th scope="col">Observación</th>
                    <th scope="col" v-if="estado == 'todos'">Estado</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(incidente, index) in incidentes" :key="index">
                    <th scope="row">{{index}}</th>
                    <td>
                        <template v-if="incidente.servicio == null">
                            {{ obtenerNombresDeServicios(incidente.agrupacion) }}
                        </template>
                        <template v-else>
                            {{ incidente.servicio.nombre }}
                        </template>
                    </td>
                    <td>{{incidente.observacion}}</td>
                    <td v-if="estado == 'todos'">{{incidente.estadoIncidente}}</td>
                </tr>
            </tbody>
        </table>`,

    data() {
        return {
            incidentesLocal: [] // Copia local para trabajar con los incidentes
        };
    },
    created() {
        // Copiar el valor de la prop a la copia local al inicio
        this.incidentesLocal = [...this.incidentes];
    },
    methods: {
        obtenerNombresDeServicios(agrupacion) {
            return agrupacion.servicios.map(servicio => servicio.nombre).join(', ');
        }
    }
})

var app = new Vue({
    el: '#app-table',
    data:{
        incidentes:[],
        estado:''
    },
    methods:{
        getIncidentes(estado) {
            this.estado = estado
            let idSesion = localStorage.getItem("IDSESION")
            fetch(`/api/${idSesion}/incidentes?estado=${estado}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Error al obtener la respuesta');
                })
                .then(data => {
                    // Manejar la respuesta JSON obtenida
                    console.log(data);
                    this.incidentes = data;
                    // Hacer algo con los datos recibidos, por ejemplo, actualizar el DOM con estos datos
                })
                .catch(error => {
                    // Manejar errores en la solicitud o la respuesta
                    console.error('Error:', error);
                });
        }
    }
})