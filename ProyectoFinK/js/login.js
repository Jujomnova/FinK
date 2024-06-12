const appDataLogin = () => ({
    documento: '',
    clave: ''
});

const appLogin = Vue.createApp({
    data: appDataLogin,
    methods: {
        validarDocumento(event) {
            const input = event.target;
            input.value = input.value.replace(/\D/g, '').slice(0, 10);
        },
        login() {
            const datosLogin = {
                documento: this.documento,
                clave: this.clave
            };

            fetch('http://localhost:8080/api/v1/empleado/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datosLogin)
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
                    Swal.fire({
                        icon: 'success',
                        title: 'Éxito',
                        text: data.mensajes.join(', ')
                    }).then(() => {
                        if (data.datos.length > 0) {
                            const nombreFinca = data.datos[0].finca.nombre;
                            localStorage.setItem('nombreFinca', nombreFinca);
                            window.location.href = "index.html";
                        }
                    });
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: error.mensajes.join(', ')
                    });
                    console.error('Error al iniciar sesión:', error);
                });
        }
    }
});

appLogin.mount('#LoginApp');






