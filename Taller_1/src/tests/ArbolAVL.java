package tests;

/**
* Erika
*/


public class ArbolAVL {
    Nodo raiz;

    int altura(Nodo nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    int getBalance(Nodo nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    Nodo insertar(Nodo nodo, int valor) {
        if (nodo == null)
            return (new Nodo(valor));

        if (valor < nodo.valor)
            nodo.izquierda = insertar(nodo.izquierda, valor);
        else if (valor > nodo.valor)
            nodo.derecha = insertar(nodo.derecha, valor);
        else
            return nodo;

        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = getBalance(nodo);

        if (balance > 1 && valor < nodo.izquierda.valor)
            return rotarDerecha(nodo);

        if (balance < -1 && valor > nodo.derecha.valor)
            return rotarIzquierda(nodo);

        if (balance > 1 && valor > nodo.izquierda.valor) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && valor < nodo.derecha.valor) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    Nodo minValueNode(Nodo nodo) {
        Nodo current = nodo;
        while (current.izquierda != null)
            current = current.izquierda;
        return current;
    }

    Nodo eliminar(Nodo nodo, int valor) {
        if (nodo == null)
            return nodo;

        if (valor < nodo.valor)
            nodo.izquierda = eliminar(nodo.izquierda, valor);
        else if (valor > nodo.valor)
            nodo.derecha = eliminar(nodo.derecha, valor);
        else {
            if ((nodo.izquierda == null) || (nodo.derecha == null)) {
                Nodo temp = null;
                if (temp == nodo.izquierda)
                    temp = nodo.derecha;
                else
                    temp = nodo.izquierda;

                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else
                    nodo = temp;
            } else {
                Nodo temp = minValueNode(nodo.derecha);
                nodo.valor = temp.valor;
                nodo.derecha = eliminar(nodo.derecha, temp.valor);
            }
        }

        if (nodo == null)
            return nodo;

        nodo.altura = max(altura(nodo.izquierda), altura(nodo.derecha)) + 1;

        int balance = getBalance(nodo);

        if (balance > 1 && getBalance(nodo.izquierda) >= 0)
            return rotarDerecha(nodo);

        if (balance > 1 && getBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && getBalance(nodo.derecha) <= 0)
            return rotarIzquierda(nodo);

        if (balance < -1 && getBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }
}