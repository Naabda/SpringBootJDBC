package es.davidabellannavarro.springbootjdbc;

/**
 * @author David Abellán Navarro
 * @project SpringBootJDBC
 * @course 2ºD.A.M.
 * @date 18/01/2022
 */
public class Habilidad {

    // Atributos
    private String nombre;
    private int vida;
    private int energia;
    private String tipo;

    /** Constructores. */

    // Constructor por defecto
    public Habilidad() {
        nombre = "ninguno";
        vida = 0;
        energia = 0;
        tipo = "ninguno";
    }

    // Constructor sobrecargado
    public Habilidad(String nombre, int vida, int energia, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.energia = energia;
        this.tipo = tipo;
    }

    // Constructor de copia
    public Habilidad(Habilidad habilidad) {
        this.nombre = habilidad.nombre;
        this.vida = habilidad.vida;
        this.energia = habilidad.energia;
        this.tipo = habilidad.tipo;
    }

    /** Métodos. */

    // Getters & Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
