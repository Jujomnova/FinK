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
                .then(response => {
                    return response.json().then(data => {
                        if (!response.ok) {
                            return Promise.reject(data);
                        }
                        return data;
                    });
                })
                .then(data => {
                    this.registros = data.datos;
                    this.registrosFiltrados = this.registros;
                    this.actualizarFiltros();
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Hubo un error al cargar los registros.'
                    });
                    console.error('Error al cargar los registros:', error);
                });
        },
        cargarEstados() {
            fetch('http://localhost:8080/api/v1/estado/obtener')
                .then(response => {
                    return response.json().then(data => {
                        if (!response.ok) {
                            return Promise.reject(data);
                        }
                        return data;
                    });
                })
                .then(data => {
                    this.estadosFiltrados = data.datos.map(estado => estado.estado);
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Hubo un error al cargar los estados.'
                    });
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
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Debe seleccionar un animal y un estado para actualizar.'
                });
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
                .then(response => response.json().then(data => ({ data, status: response.status })))
                .then(({ data, status }) => {
                    if (status >= 200 && status < 300) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Éxito',
                            text: data.mensajes.join(', ')
                        });
                        this.cargarRegistros();
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: data.mensajes.join(', ')
                        });
                    }
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Hubo un error al actualizar el estado.'
                    });
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

