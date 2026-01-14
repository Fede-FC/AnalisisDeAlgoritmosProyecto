/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

/**
 *
 * @author feder
 */
public class Pieza {
    
    public Pieza() {}
    public Pieza(int top, int right, int bottom, int left, int id) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.id = id;
    }
    
    int top;
    int right;
    int bottom;
    int left;
    int id;          
    
    public void rotarIzq() { // mas 5 aasignaciones 
        int aux = this.top;
        this.top = this.right;
        this.right = this.bottom;
        this.bottom = this.left; 
        this.left = aux;
    }
    public void rotarDer(){ // mas 5 aasignaciones 
        int aux = this.top;
        this.top = this.left;
        this.left = this.bottom;
        this.bottom = this.right;
        this.right = aux;
    }
    
    public int getTop() {
        return top;
    }

    public void setTop(int top) { // mas 1 asignacion
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) { // mas 1 asignacion
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) { // mas 1 asignacion
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) { // mas 1 asignacion
        this.left = left;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { // mas 1 asignacion
        this.id = id;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.top)+"-" + String.valueOf(this.right)+"-"+String.valueOf(this.bottom)+"-"+ String.valueOf(this.left);
    }
    
}
