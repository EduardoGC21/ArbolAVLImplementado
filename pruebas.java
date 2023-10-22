/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import javax.swing.JFrame;
import java.util.Random;
/**
 *
 * @author eduar
 */
public class pruebas {
    public static void main(String[] args) {
        ArbolBinarioBusquedaAVL<Integer> a1 = new ArbolBinarioBusquedaAVL<Integer>();
        a1.insertaAVL(100);
        a1.insertaAVL(300);
        a1.insertaAVL(400);
        a1.insertaAVL(50);
        a1.insertaAVL(200);
        a1.insertaAVL(250);
        a1.insertaAVL(75);
        a1.insertaAVL(350);
        a1.insertaAVL(500);
        a1.insertaAVL(375);
        
        
        
        a1.borra(500);
        a1.borra(400);
        a1.borra(200);
        
        Random random =  new Random();
        int n;
        for(int i=0; i<50;i++){
            n=random.nextInt(0, 100);
            System.out.println(n);
            a1.insertaAVL(n);
        }
        
        
        

        
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
