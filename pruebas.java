/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import javax.swing.JFrame;

/**
 *
 * @author eduar
 */
public class pruebas {
    public static void main(String[] args) {
        ArbolBinarioBusquedaAVL<Integer> a1 = new ArbolBinarioBusquedaAVL<Integer>();
        a1.insertaAVL(0);
        a1.insertaAVL(10);
        a1.insertaAVL(-10);
        a1.insertaAVL(100);
        a1.insertaAVL(1000);
        a1.insertaAVL(-100);
        a1.insertaAVL(-50);
        a1.insertaAVL(-5);
        a1.insertaAVL(9);
        a1.insertaAVL(5);
        a1.insertaAVL(-20);
        a1.insertaAVL(13);
        a1.insertaAVL(39);
        a1.insertaAVL(-150);
        a1.insertaAVL(5000);
        a1.insertaAVL(6000);
        a1.insertaAVL(2);
        
        
        /*
        
        
        a1.borra(0);
        a1.borra(-100);
        a1.borra(100);
        a1.borra(-50);
        a1.borra(10);
        a1.borra(1000);
        a1.borra(-10);
        a1.borra(-5);
        a1.borra(5);
        a1.borra(9);
        a1.insertaAVL(100);
        a1.insertaAVL(450);
        a1.insertaAVL(500);
        a1.borra(450);
        */
        System.out.println("");
        System.out.println("");
        a1.imprimirPorNivel();
        NodoBBAVL<Integer> raiz = a1.getRaiz();
        TreeVisualizer visualizer = new TreeVisualizer(raiz);//grafica
        
        // Crea un JFrame para mostrar el árbol gráficamente
        JFrame frame = new JFrame("Árbol AVL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(visualizer);
        frame.setSize(1000, 1000); // Establece el tamaño de la ventana
        frame.setVisible(true);
    }
}
