/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

/**
 *
 * @author feder
 */
public class Puzzle {
    int size;
    Pieza[][] tablero;

    public Puzzle(int size) {
        this.size = size;
        this.tablero = new Pieza[size][size];
        
    }
    
    public void colocarPieza(int row, int col, Pieza pieza){
        this.tablero[row][col] = pieza;
    }
    public void quitarPieza(int row, int col){
        this.tablero[row][col] = null;
    }
    // Funcion que indica si el puzle esta completo :D
    public boolean isComplet(){ // O(n!)
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                if (this.tablero[i][j] == null) return false;
            }
        }
        return true; 
    }
    public Pieza getPieza(int row, int col){
        return this.tablero[row][col];
    }
    /*
    Funcion que verifica si la pieza a colocar es valida (para verificar solucion).
    verifica arriba y izquierda.
    Derecha y abajo no ya que no sean crean todavia.
    */
    public boolean isValid(int row, int col, Pieza pieza){
        // arriba
        if (row > 0 && this.tablero[row - 1][col] != null) {
            if (this.tablero[row - 1][col].getBottom() != pieza.getTop())
                return false;
        }
        // izquierda
        if (col > 0 && this.tablero[row][col - 1] != null){
            if (this.tablero[row][col - 1].getRight() != pieza.getLeft())
                return false;
        }
        return true;
    }
    
    public void print() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            System.out.print(this.tablero[i][j] == null ? "---- " : this.tablero[i][j] + " ");
        }
        System.out.println();
    }
}
}
