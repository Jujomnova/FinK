const appDataCrearFinca = () => ({
    nombre: '',
    direccion: '',
    pais: '',
    departamento: '',
    ciudad: '',
    correoElectronico: '',
    telefono: '',
    listaPaises: [],
    listaDepartamentos: [],
    listaCiudades: [],
    errores: {}
});

const appCrearFinca = Vue.createApp({
    data: appDataCrearFinca,
    methods: {
        cargarPaises() {
            fetch('../data/paises.json')
                .then(response => response.json())
                .then(data => {
                    this.listaPaises = data;
                })
                .catch(error => {
                    console.error('Error al cargar los países:', error);
                });
        },
        cargarDepartamentos() {
            fetch('../data/departamentos.json')
                .then(response => response.json())
                .then(data => {
                    this.listaDepartamentos = data[this.pais] || [];
                    this.listaCiudades = []; // Limpiar la lista de ciudades al cambiar el departamento
                })
                .catch(error => {
                    console.error('Error al cargar los departamentos:', error);
                });
        },
        cargarCiudades() {
            fetch('../data/ciudades.json')
                .then(response => response.json())
                .then(data => {
                    const ciudadesPorPais = data[this.pais] || {};
                    this.listaCiudades = ciudadesPorPais[this.departamento] || [];
                })
                .catch(error => {
                    console.error('Error al cargar las ciudades:', error);
                });
        },
        validarFormulario() {
            this.errores = {};

            const regexNombre = /^[a-zA-Z\s]+$/;
            const regexCorreo = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            const regexTelefono = /^\d+$/;

            if (!this.nombre.match(regexNombre)) {
                this.errores.nombre = 'Solo se permiten letras y espacios';
            }
            if (!this.direccion) {
                this.errores.direccion = 'La dirección es obligatoria';
            }
            if (!this.pais) {
                this.errores.pais = 'El país es obligatorio';
            }
            if (!this.departamento) {
                this.errores.departamento = 'El departamento es obligatorio';
            }
            if (!this.ciudad) {
                this.errores.ciudad = 'La ciudad es obligatoria';
            }
            if (!this.correoElectronico.match(regexCorreo)) {
                this.errores.correoElectronico = 'Debe ser un correo electrónico válido';
            }
            if (!this.telefono.match(regexTelefono)) {
                this.errores.telefono = 'Solo se permiten números';
            }

            return Object.keys(this.errores).length === 0;
        },
        crearFinca() {
            if (!this.validarFormulario()) {
                return;
            }

            const datosFinca = {
                nombre: this.nombre,
                direccion: this.direccion,
                pais: this.pais,
                departamento: this.departamento,
                ciudad: this.ciudad,
                correoElectronico: this.correoElectronico,
                telefono: this.telefono
            };

            console.log('Datos de la finca:', datosFinca);
            alert('Simulación de envío de datos de la finca. Ver consola para más detalles.');

            // Pendiente envio POST de datos de la finca
        }
    },
    mounted() {
        this.cargarPaises();
    }
});

appCrearFinca.mount('#app');
