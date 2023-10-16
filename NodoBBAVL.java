/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import java.awt.Graphics;

/**
 *
 * @author eduar
 */
public class NodoBBAVL<T extends Comparable<T>> {
    private T elemento;
    private NodoBBAVL<T> izq, der, papa;
    private int fe;
    private int x, y; //para grafica

    
    

    public NodoBBAVL(T elemento) {
        this.elemento = elemento;
        this.izq = null;
        this.der = null;
        this.papa = null;
        this.fe=0;
        this.x = 0;
        this.y = 0;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
    
    public T getElemento() {
        return elemento;
    }

    public NodoBBAVL<T> getIzq() {
        return izq;
    }

    public NodoBBAVL<T> getDer() {
        return der;
    }

    public NodoBBAVL<T> getPapa() {
        return papa;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public void setIzq(NodoBBAVL<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoBBAVL<T> der) {
        this.der = der;
    }

    public void setPapa(NodoBBAVL<T> papa) {
        this.papa = papa;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public String toString() {
        return elemento + "\n";
    }
    public void cuelga(NodoBBAVL<T> n){
        if(n==null){
            return;
        }
        if(n.getElemento().compareTo(elemento)<=0){
            izq=n;
        }else{
            der=n;
        }
        n.setPapa(this);
    }
    public void cuelga2(NodoBBAVL<T> n, char c){//cuelga para rotaciones donde podemos colgar algo null
        if(c=='I'){
            izq=n;
        }else{
            der=n;
        }
        if(n!=null){
            n.setPapa(this);
        }
        
    }
    
    // Método para dibujar el nodo gráficamente
    public void dibujar(Graphics g) {
        // Dibuja el círculo en las coordenadas x e y
        g.drawOval(x - 15, y - 15, 30, 30);
        g.drawString(String.valueOf(elemento), x - 5, y + 5);

        // Dibuja las líneas a los hijos si existen
        if (izq != null) {
            g.drawLine(x, y, izq.getX(), izq.getY());
        }
        if (der != null) {
            g.drawLine(x, y, der.getX(), der.getY());
        }
        
        // Dibuja la "fe" arriba a la derecha del óvalo
        g.drawString(""+fe, x + 10, y - 20);
    }
}
