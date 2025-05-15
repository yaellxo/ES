// Clase.java
public class Clase {
    private String nombre;    // Nombre de la materia/clase
    private String profesor;
    private String lugar;
    private String fecha;     // Podría ser día y hora, ej: "Lunes 10:00-12:00"

    public Clase(String nombre, String profesor, String lugar, String fecha) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Clase: " + nombre + "\n  Profesor: " + profesor + "\n  Lugar: " + lugar + "\n  Fecha/Hora: " + fecha;
    }
}