


//Falta ver si agrupacion es null, usar servicio y viceversa
Vue.component('elemento-tabla', {
    template:`
        <tr v-for="(incidente, index) in incidentes">
            <th scope="row">{{index}}</th>
            <td>{{incidente.servicio}}</td>
            <td>{{incidente.estadoIncidente}}</td>
        </tr>`,

    data(){
        return {
            incidentes: []
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
                this.incidentes = data; // Asignar los datos a tu propiedad de datos

            } catch (error) {
                console.error('Error al obtener datos:', error);
            }
        },
    }
})

var app = new Vue({
    el: '#app-table',
    // data:{
    //     servicios:[]
    // },
})