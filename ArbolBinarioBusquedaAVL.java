/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author eduar
 */
public class ArbolBinarioBusquedaAVL<T extends Comparable<T>> {
    private NodoBBAVL<T> raiz;
    private int cont;

    public ArbolBinarioBusquedaAVL() {
        this.raiz = null;
        this.cont = 0;
    }
    
    
    
    public boolean isEmpty() {
        return raiz==null || cont==0;
    }

    
    public int size() {
        return cont;
    }

    public NodoBBAVL<T> getRaiz() {
        return raiz;
    }

    public int getCont() {
        return cont;
    }
    
    //encuentra un elemento en el arbol AVL
    public NodoBBAVL<T> encuentraOrd(T elem){
        NodoBBAVL<T> actual = raiz;
        boolean encontre =false;
        while(encontre==false && actual!= null){
            if(elem.equals(actual.getElemento())){
                encontre = true;
            }else{
                if(elem.compareTo(actual.getElemento())<0){
                    actual=actual.getIzq();
                }else{
                    actual=actual.getDer();
                }
            }
        }
        return actual;
    }
    /*
    Pasos para borrar:
    Encuentra
    Caso hoja sin hijos
        Raiz==null
        Papa apunta a null        
    Caso hoja con 1 hijo
        Si es raiz, hoja se vuelve raiz
        Abuelo se hace el papa de hijo
    Caso hoja con 2 hijos
        Sustituyo sucesor inorden, uno a la derecha, el mas chico
        Elimino el nodo donde estaba el sucesor inorden que funciona como 1 hijo
    */
    public void borra(T elem) {
        NodoBBAVL<T> actual= encuentraOrd(elem);//buscamos
        if(actual==null){//si no estuvo no hacemos nada
            return;
        }
        cont--;
        if(actual.getDer()==null && actual.getIzq()==null){//caso hoja sin hijos
            if(actual==raiz){//caso raiz
                raiz=null;
            }else{
                if(actual.getPapa().getIzq()!= null && actual.getPapa().getIzq().equals(actual)){//checamos si estamos a der o izq de papa
                    actual.getPapa().setIzq(null);
                    checaFeviajaRaizBorra(actual);
                }else{
                    actual.getPapa().setDer(null);
                    checaFeviajaRaizBorra(actual);
                }
            }
            return;
        }
        if(actual.getIzq()==null || actual.getDer()==null){//caso 1 hijo
            if(actual==raiz){//raiz
                if(actual.getIzq()!=null){//se queda con la fe que tenia y no necesitamos checar rotaciones
                    raiz=actual.getIzq();
                    raiz.setPapa(null);
                }else{
                    raiz=actual.getDer();
                    raiz.setPapa(null);
                }
            }else{//papa de actual conectamos a hijo
                if(actual.getIzq()!=null){
                    actual.getPapa().cuelga(actual.getIzq());
                    checaFeviajaRaizBorra(actual.getIzq());//mandamos checar fe desde nuevo que tendra papa al que necesitamos actualizar
                }else{
                    actual.getPapa().cuelga(actual.getDer());
                    checaFeviajaRaizBorra(actual.getDer());//nuevo der
                }
            }
            return;
        }
        //dos hijos
        NodoBBAVL<T> sucesor = actual.getDer();
        while(sucesor.getIzq()!=null){//buscamos sucesor inorden
            sucesor=sucesor.getIzq();
        }
        actual.setElemento(sucesor.getElemento());//ponemos elemento de sucesor inorden en el que queremos borrar
        NodoBBAVL<T> papa = sucesor.getPapa();
        if(papa!=raiz){
            if(sucesor.getPapa().equals(actual)){//si el sucesor es solo el de la derecha
                actual.setDer(sucesor.getDer());
                checaFeviajaRaizBorra(sucesor);
            }else if(sucesor.getDer()==null){//si sucesor no tiene nadie ha derecha
                papa.setIzq(null);
                checaFeviajaRaizBorra(sucesor);//checamos fes desde sucesor que actualiza a papa pues sigue conectado a papa
            }else{
                papa.cuelga(sucesor.getDer());//sucesor si tiene alguien a la derecha
                //mandamos a checar fes desde hijo nuevo
                checaFeviajaRaizBorra(sucesor.getDer());
            }
            
        }else{
            //papa es raiz  
            papa.setDer(sucesor.getDer());
            if(papa.getDer()!=null){
                papa.getDer().setPapa(papa);
            }
            raiz.setFe(raiz.getFe()-1);
            checaFeviajaRaizBorra(raiz);
        }
        
    }
    
    
    //Nos ayuda a calcular la fe del nodo para el caso de que borra y va hacia arriba
    private int calcularFE(NodoBBAVL<T> nodo) {
    if (nodo == null) {
        return 0;
    }
    
    int alturaIzquierda = alturaArbolR(nodo.getIzq());
    int alturaDerecha = alturaArbolR(nodo.getDer());

    return alturaDerecha - alturaIzquierda;
    }
    //metodo para calcular la fe, el cual calcula la altura que tiene un subarbol
    private int alturaArbolR(NodoBBAVL<T> actual){
        if(actual==null){
            return 0;
        }
        int a1=alturaArbolR(actual.getIzq())+1;
        int a2=alturaArbolR(actual.getDer())+1;
        return Math.max(a1, a2);
    }
    
    //checa fes de nodos para caso de borrar hasta que rote o llege a raiz.
    public void checaFeviajaRaizBorra(NodoBBAVL<T> actual){
        //va subiendo a nodos papas y va actualizando las fes hasta la raiz.
        NodoBBAVL<T> papa;
        while(actual!=raiz){
            papa = actual.getPapa();    
            papa.setFe(calcularFE(papa));
            if(papa.getFe()==2 || papa.getFe()==-2){//checamos rotaciones
                papa = rota(papa);
            }
            actual = papa;
        }
        //checamos si rotamos raiz
        if (actual == raiz) {
            if (actual.getFe() == 2 || actual.getFe() == -2) {
                raiz = rota(actual);
            }
        }
        
    }
    

    
    
    
    //metodo para insertar un nodo
    private NodoBBAVL<T> inserta(T elemento){
        NodoBBAVL<T> actual=raiz;
        NodoBBAVL<T> nuevo = new NodoBBAVL(elemento);
        if(actual==null){//si esta vacio es ese
            raiz = nuevo;
            cont++;
        }else{
            NodoBBAVL<T> actual2=null;
            while(actual!=null){//buscamos nodo papa del nodo nuevo
                actual2=actual;
                if(elemento.compareTo(actual.getElemento())>0){
                    actual=actual.getDer();
                }else{//menor o igual
                    actual=actual.getIzq();
                }

            }
            //insertamos en nodo papa donde corresponda
            if(elemento.compareTo(actual2.getElemento())>0){
                actual2.setDer(nuevo);
            }else{//menor o igual
                actual2.setIzq(nuevo);
            }
            cont++;
            nuevo.setPapa(actual2);
        }
        return nuevo;
        
        
    }
    //metodo que inserta
    public void insertaAVL(T elemento){
        NodoBBAVL<T> nuevo =inserta(elemento);//lo inserta y nos regresa el nodo
        checaFeviajaRaizInserta(nuevo);//mandamos ese nodo para actualizar las fes de los papas.
    }
    
    private void checaFeviajaRaizInserta(NodoBBAVL<T> actual){
        //va actualizando las fes si inserto algo subiendo al papa y actualizando
        NodoBBAVL<T> papa = actual.getPapa();
        boolean termine=false;
        while(termine==false && actual!=raiz){
            if(papa.getDer()!= null && papa.getDer().equals(actual)){//vengo de la derecha
                papa.setFe(papa.getFe()+1);
            }else{
                papa.setFe(papa.getFe()-1);
            }
            if(papa.getFe()==2 || papa.getFe()==-2){//checamos rotaciones
                termine = true;
                papa = rota(papa);
            }else{
                termine=papa.getFe()==0;
            }
            actual=papa;
            papa=actual.getPapa();
        }
        //checamos si rotamos raiz
        if (actual == raiz) {
            if (actual.getFe() == 2 || actual.getFe() == -2) {
                raiz = rota(actual);
            }
        }
    }
    
    //metodo para rotar
    private NodoBBAVL<T> rota(NodoBBAVL<T> actual){//rota
        //10 casos, usando algebra para no tener que calcular alturas siempre
        //DD con 1 o 0(2casos)
        //instanceamos todos los nodos auxiliares
        NodoBBAVL<T> papa=null;
        NodoBBAVL<T> alfa=null;
        NodoBBAVL<T> beta=null;
        NodoBBAVL<T> gamma=null;
        NodoBBAVL<T> A=null;
        NodoBBAVL<T> B=null;
        NodoBBAVL<T> C=null;
        NodoBBAVL<T> D=null;
        //D-D
        if(actual.getFe()==2 && (actual.getDer()!= null && actual.getDer().getFe()==0 || actual.getDer()!=null && actual.getDer().getFe()==1)){
            //asignamos y colgamos nuevos nodos
            papa = actual.getPapa();
            alfa = actual;
            beta = actual.getDer();
            gamma = beta.getDer();
            A = actual.getIzq();
            B = beta.getIzq();
            C = gamma.getIzq();
            D = gamma.getDer();
            beta.cuelga2(alfa, 'I');
            beta.cuelga2(gamma, 'D');
            alfa.cuelga2(A,'I');
            alfa.cuelga2(B, 'D');
            gamma.cuelga2(C, 'I');
            gamma.cuelga2(D, 'D');
            //si no hay papa va a ser raiz
            if(papa==null){
                raiz=beta;
            }else{
                papa.cuelga(beta);
            }
            //dos casos especiales
            if(beta.getFe()==1){
                //fe de gamma se queda igual
                beta.setFe(0);
                alfa.setFe(0);
            }else{//fue 0 checar esto
                //gama igual
                alfa.setFe(1);
                beta.setFe(-1);
            }
            //regresamos nueva raiz de subarbol
            return beta;
        }
        
        //II con -1 o 0(2casos)
        if(actual.getFe()==-2 && (actual.getIzq()!=null && actual.getIzq().getFe()==0||actual.getIzq()!=null && actual.getIzq().getFe()==-1)){
            //asignamos y colgamos nuevos nodos
            papa = actual.getPapa();
            alfa = actual;
            beta = actual.getIzq();
            gamma = beta.getIzq();
            A = gamma.getIzq();
            B = gamma.getDer();
            C = beta.getDer();
            D = alfa.getDer();
            beta.cuelga2(gamma, 'I');
            beta.cuelga2(alfa, 'D');
            gamma.cuelga2(A, 'I');
            gamma.cuelga2(B, 'D');
            alfa.cuelga2(C,'I');
            alfa.cuelga2(D, 'D');
            //si no hay papa entonces va a ser raiz
            if(papa==null){
                raiz=beta;
            }else{
                papa.cuelga(beta);
            }
            //2 casos especiales para poner fes
            if(beta.getFe()==-1){
                //fe de gamma se queda igual
                beta.setFe(0);
                alfa.setFe(0);
            }else{//fue 0 
                //gama igual
                alfa.setFe(-1);
                beta.setFe(1);
            }
            //regresamos nueva raiz de subarbol
            return beta;
        }
        
        //D-I gamma 1, -1 o 0 (3 casos)
        if(actual.getFe()==2 && actual.getDer()!=null && actual.getDer().getFe()==-1){
            //asignamos y colgamos nuevos nodos.
            papa = actual.getPapa();
            alfa = actual;
            beta = actual.getDer();
            gamma = beta.getIzq();
            A = actual.getIzq();
            B = gamma.getIzq();
            C = gamma.getDer();
            D = beta.getDer();
            gamma.cuelga2(alfa, 'I');
            gamma.cuelga2(beta, 'D');
            alfa.cuelga2(A,'I');
            alfa.cuelga2(B, 'D');
            beta.cuelga2(C, 'I');
            beta.cuelga2(D, 'D');
            //si no hay papa es raiz
            if(papa==null){
                raiz=gamma;
            }else{
                papa.cuelga(gamma);
            }
            //Valores obtenidos algebraicamente
            //3 casos
            if(gamma.getFe()==1){// gamma haya sido fe 1
                gamma.setFe(0);
                alfa.setFe(-1);
                beta.setFe(0);
            }else{
                if(gamma.getFe()==-1){//gamma haya sido fe -1
                    gamma.setFe(0);
                    alfa.setFe(0);
                    beta.setFe(1);
                }else{//fue 0
                    gamma.setFe(0);
                    alfa.setFe(0);
                    beta.setFe(0);
                }
            }
            //regresamos nueva raiz del subarbol
            return gamma;
        }
        //Si no fue ninguno de anteriores sera I-D osea -2 en actual y 1 en hijo izq
        //I-D 
        // gamma 1, -1 o 0 (3 casos)
        
        //asignamos nuevos nodos y colgamos
        papa = actual.getPapa();
        alfa = actual;
        beta = actual.getIzq();
        gamma = beta.getDer();
        A = beta.getIzq();
        B = gamma.getIzq();
        C = gamma.getDer();
        D = actual.getDer();
        gamma.cuelga2(beta, 'I');
        gamma.cuelga2(alfa, 'D');
        beta.cuelga2(A, 'I');
        beta.cuelga2(B, 'D');
        alfa.cuelga2(C,'I');
        alfa.cuelga2(D, 'D');
        //si no hay papa es raiz
        if(papa==null){
            raiz=gamma;
        }else{
            papa.cuelga(gamma);
        }
        //Valores obtenidos algebraicamente
        if(gamma.getFe()==1){// gamma haya sido fe 1
            gamma.setFe(0);
            alfa.setFe(0);
            beta.setFe(-1);
        }else{
            if(gamma.getFe()==-1){//gamma haya sido fe -1
                gamma.setFe(0);
                alfa.setFe(1);
                beta.setFe(0);
            }else{//fue 0
                gamma.setFe(0);
                alfa.setFe(0);
                beta.setFe(0);
            }
        }
        //regresamos nueva raiz del subarbol
        return gamma;
    }
    
    //metodo para imprimir por nivel de izq a derecha un arbol.
    //Metiendolo a una cola 
    public void imprimirPorNivel() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Queue<NodoBBAVL<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoBBAVL<T> nodo = cola.poll();
            System.out.print(nodo.getElemento() + " (FE: " + nodo.getFe() + ") -> ");

            if (nodo.getIzq() != null) {
                System.out.print(nodo.getIzq().getElemento() + " (Izq), ");
                cola.add(nodo.getIzq());
            } else {
                System.out.print("null (Izq), ");
            }

            if (nodo.getDer() != null) {
                System.out.print(nodo.getDer().getElemento() + " (Der), ");
                cola.add(nodo.getDer());
            } else {
                System.out.print("null (Der), ");
            }
            
            
            System.out.println();
        }
    }
    

}

