document.addEventListener('DOMContentLoaded', function () {
    const nombreFinca = localStorage.getItem('nombreFinca');
    if (nombreFinca) {
        document.getElementById('nombreFinca').innerText = `Finca: ${nombreFinca}`;
    }
});
