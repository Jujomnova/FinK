const appDataEstadoAnimal = () => ({
    especies: [],
    razas: [],
    estadosFiltrados: [],
    registros: [],
    registrosFiltrados: [],
    animalSeleccionado: null,
    filtroEspecie: '',
    filtroRaza: '',
    filtroEstado: '',
    nuevoEstadoSeleccionado: '',
    nombreFinca: ''
});

const appEstadoAnimal = Vue.createApp({
    data: appDataEstadoAnimal,
    methods: {
        cargarRegistros() {
            const nombreFinca = localStorage.getItem('nombreFinca');
            fetch('http://localhost:8080/api/v1/registroEstado/obtenerEstados', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nombre: nombreFinca })
            })
                .then(response => response.json())
                .then(data => {
                    this.registros = data.datos;
                    this.registrosFiltrados = this.registros;
                    this.actualizarFiltros();
                })
                .catch(error => {
                    console.error('Error al cargar los registros:', error);
                });
        },
        cargarEstados() {
            fetch('http://localhost:8080/api/v1/estado/obtener')
                .then(response => response.json())
                .then(data => {
                    this.estadosFiltrados = data.datos.map(estado => estado.estado);
                })
                .catch(error => {
                    console.error('Error al cargar los estados:', error);
                });
        },
        actualizarFiltros() {
            this.especies = [...new Set(this.registros.map(registro => registro.animal.raza.especie.nombre))];
            this.razas = [...new Set(this.registros.map(registro => registro.animal.raza.nombre))];

            let registrosFiltrados = this.registros;

            if (this.filtroEspecie) {
                registrosFiltrados = registrosFiltrados.filter(registro => registro.animal.raza.especie.nombre === this.filtroEspecie);
            }

            if (this.filtroRaza) {
                registrosFiltrados = registrosFiltrados.filter(registro => registro.animal.raza.nombre === this.filtroRaza);
            }

            if (this.filtroEstado) {
                registrosFiltrados = registrosFiltrados.filter(registro => registro.estado.estado === this.filtroEstado);
            }

            this.registrosFiltrados = registrosFiltrados;
        },
        actualizarEstado() {
            if (!this.animalSeleccionado || !this.nuevoEstadoSeleccionado) {
                alert("Seleccione un animal y un nuevo estado.");
                return;
            }

            const datos = {
                animal: {
                    raza: {
                        nombre: this.animalSeleccionado.animal.raza.nombre,
                        especie: {
                            nombre: this.animalSeleccionado.animal.raza.especie.nombre
                        }
                    },
                    codigo: this.animalSeleccionado.animal.codigo,
                    finca: {
                        nombre: this.animalSeleccionado.animal.finca.nombre
                    }
                },
                estado: {
                    estado: this.nuevoEstadoSeleccionado
                }
            };

            fetch('http://localhost:8080/api/v1/registroEstado/actualizarEstado', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            })
                .then(response => response.json())
                .then(data => {
                    alert(data.mensajes.join(', '));
                    this.cargarRegistros();
                })
                .catch(error => {
                    console.error('Error al actualizar el estado:', error);
                });
        }
    },
    mounted() {
        this.cargarRegistros();
        this.cargarEstados();
    },
    watch: {
        filtroEspecie() {
            this.actualizarFiltros();
        },
        filtroRaza() {
            this.actualizarFiltros();
        },
        filtroEstado() {
            this.actualizarFiltros();
        }
    }
});

appEstadoAnimal.mount('#app');
