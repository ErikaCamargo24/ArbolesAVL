package tests;

/**
* Erika
*/


public class Nodo {
    int valor;
    Nodo izquierda, derecha;
    int altura;

    Nodo(int valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
        this.altura = 1;
    }
}