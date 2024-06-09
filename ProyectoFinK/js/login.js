const appDataLogin = () => ({
    identificador: '',
    clave: ''
});

const appLogin = Vue.createApp({
    data: appDataLogin,
    methods: {
        login() {
            fetch('http://localhost:8080/api/v1/empleado/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    documento: this.identificador,
                    clave: this.clave
                })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.mensajes && data.mensajes.length > 0) {
                        alert(data.mensajes.join(', '));
                    } 
                    if (data.datos && data.datos.length > 0) {
                        const empleado = data.datos[0];
                        localStorage.setItem('nombreFinca', empleado.finca.nombre);
                        window.location.href = 'index.html';
                    }
                })
                .catch(error => {
                    console.error('Error al iniciar sesión:', error);
                });
        }
    }
});

appLogin.mount('#login-app');

