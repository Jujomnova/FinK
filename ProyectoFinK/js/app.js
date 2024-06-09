const { createApp } = Vue;

function createVueApp(dataFunction) {
    return createApp({
        data: dataFunction,
        methods: {
            enviarFormulario() {
                if (this.validarFormulario()) {
                    const url = 'http://localhost:8080/api/v1/tareaFinca';
                    const data = {
                        empleadoAsignado: {
                            documento: parseInt(this.tarea.idEmpleado.replace(/\./g, '')) || 0
                        },
                        tipoTrabajo: {
                            tipo: this.tarea.tipoTrabajoFinca
                        },
                        lugar: {
                            finca: {
                                nombre: this.tarea.finca
                            },
                            ubicacion: this.tarea.ubicacion,
                            nomenclatura: this.tarea.nomenclatura
                        },
                        descripcion: this.tarea.descripcion
                    };

                    fetch(url, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data)
                    })
                    .then(response => {
                        if (!response.ok) {
                            return response.json().then(errorData => {
                                throw new Error(errorData.mensajes.join(', '));
                            });
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log('Éxito:', data);
                        alert('Tarea creada con éxito.');
                        this.restablecerFormulario();
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert(`Hubo un error al crear la tarea: ${error.message}`);
                    });
                }
            },
            validarFormulario() {
                if (!this.tarea.idEmpleado || !this.tarea.tipoTrabajoFinca || !this.tarea.finca || !this.tarea.ubicacion || !this.tarea.nomenclatura) {
                    alert("Todos los campos excepto Descripción son obligatorios.");
                    return false;
                }
                return true;
            },
            formatearCedula() {
                if (this.tarea.idEmpleado === '0') return;
                let cedula = this.tarea.idEmpleado.replace(/\D/g, '');
                cedula = cedula.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
                this.tarea.idEmpleado = cedula;
            },
            cargarDatos() {
                fetch('data/datos.json')
                    .then(response => response.json())
                    .then(data => {
                        this.fincas = data.fincas;
                    })
                    .catch(error => {
                        console.error('Hubo un error al cargar los datos:', error);
                    });
            },
            validarUbicacion() {
                this.tarea.ubicacion = this.tarea.ubicacion.replace(/[^a-zA-Z]/g, '');
                this.tarea.ubicacion = this.tarea.ubicacion.charAt(0).toUpperCase() + this.tarea.ubicacion.slice(1).toLowerCase();
            },
            validarNomenclatura() {
                this.tarea.nomenclatura = this.tarea.nomenclatura.replace(/[^a-zA-Z]/g, '').toUpperCase();
            },
            restablecerFormulario() {
                this.tarea.idEmpleado = '0';
                this.tarea.tipoTrabajoFinca = '';
                this.tarea.finca = '';
                this.tarea.ubicacion = '';
                this.tarea.nomenclatura = '';
                this.tarea.descripcion = '';
            }
        },
        mounted() {
            this.cargarDatos();
        }
    });
}

const appData = () => ({
    tarea: {
        idEmpleado: '0',
        tipoTrabajoFinca: '',
        finca: '',
        ubicacion: '',
        nomenclatura: '',
        descripcion: ''
    },
    fincas: []
});

const app = createVueApp(appData);
app.mount('#app');
