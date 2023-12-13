
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

Vue.component('div-eliminar', {
    template: `
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="staticBackdropLabel">Eliminar miembro</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      ¿Realmente desea eliminar el miembro de la comunidad?
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Eliminar</button>
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    </div>
                  </div>
                </div>
              </div>
    `,
    data() {
        return {
            estado: ''
        }
    },
    methods: {
        onSubmit() {
            console.log("Mando eliminar")
            //this.$emit('get-comunidades', this.estado)
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
            <div class="row border-bottom border-dark p-1">
                <div class="col-8 d-flex align-items-center pt-2">
                    <p class="mb-2">Pepe Argento - <span class="fw-bold">Observador</span></p>
                </div>
                <div class="col-4 d-flex align-items-center border-start pt-2">
                    <p class="mb-2 me-auto">
                        <a class="" data-bs-toggle="collapse" href="#collapseExample2" aria-expanded="false" aria-controls="collapseExample">
                            Ver detalles
                        </a>
                    </p>
                    <button type="button" class="btn btn-principal text-white mb-2 me-2" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="Pepe Argento">Modificar</button>
                    <a type="" class="mb-2" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        <i class="fa-solid fa-right-from-bracket fa-xl" style="color: #a80000;" onclick=""></i>
                    </a>
                </div>
                <div class="collapse col-12 mb-2" id="collapseExample2">
                    <div class="card card-body">
                      Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
                    </div>
                </div>
            </div>
        </div>`,

    data() {
        return {
            comunidadLocal: null // Copia local para trabajar con los incidentes
        };
    },
    created() {
        // Copiar el valor de la prop a la copia local al inicio
        this.comunidadLocal = this.comunidad;
    },
    methods: {
        // obtenerNombresDeServicios(agrupacion) {
        //     return agrupacion.servicios.map(servicio => servicio.nombre).join(', ');
        // }
    }
})

var app = new Vue({
    el: '#app-miembros',
    data:{
        comunidad: {},
        tipo_miembro:'',
        idComunidad: 1
    },
    methods:{
        getComunidad(tipo_miembro) {
            this.tipo_miembro = tipo_miembro
            let idSesion = localStorage.getItem("IDSESION")
            fetch(`/api/${idSesion}/comunidadAdmin/${this.idComunidad}`)
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
        }
    }
})