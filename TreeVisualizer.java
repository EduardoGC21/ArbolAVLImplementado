/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

/**
 *
 * @author eduar
 */
import javax.swing.*;
import java.awt.*;
//para graficar el arbol
public class TreeVisualizer<T extends Comparable<T>> extends JPanel {
    private NodoBBAVL<T> raiz;

    public TreeVisualizer(NodoBBAVL<T> root) {
        this.raiz = root;
        asignarCoordenadas(root,400, 200, 400);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g, raiz);
    }

    private void asignarCoordenadas(NodoBBAVL<T> nodo, int x, int y, int separacionHorizontal) {
        if (nodo != null) {
            nodo.setX(x);
            nodo.setY(y);

            int nuevaSeparacion = separacionHorizontal / 2;
            asignarCoordenadas(nodo.getIzq(), x - nuevaSeparacion, y + 20, nuevaSeparacion);
            asignarCoordenadas(nodo.getDer(), x + nuevaSeparacion, y + 20, nuevaSeparacion);
        }
    }

    private void dibujarArbol(Graphics g, NodoBBAVL<T> nodo) {
        if (nodo != null) {
            nodo.dibujar(g);
            dibujarArbol(g, nodo.getIzq());
            dibujarArbol(g, nodo.getDer());
        }
    }
}
