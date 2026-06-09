package Logica;

import java.util.ArrayList;

/**
 * Clase genérica que sirve como contenedor para almacenar objetos de tipo T.
 * Se utiliza en el sistema para gestionar el stock de productos y el depósito de monedas.
 * @param <T> El tipo de objetos que almacenará este depósito.
 */
public class Deposito<T> {

    /** Lista interna donde se guardan los elementos. */
    private ArrayList<T> lista;

    /**
     * Constructor que inicializa la lista interna del depósito.
     */
    public Deposito(){
        lista = new ArrayList<T>();
    }

    /**
     * Agrega un objeto al depósito.
     * @param a Objeto de tipo T que se desea almacenar.
     */
    public void addAlgo(T a){
        lista.add(a);
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    /**
     * Retira y devuelve el primer objeto almacenado en el depósito.
     * @return El objeto de tipo T retirado, o null si el depósito está vacío.
     */
    public T getAlgo(){
        if(lista.size() == 0){
            return null;
        }
        return lista.remove(0);
    }
}
