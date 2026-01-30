/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author feder
 */
public class Puzzle {
    int size;
    Pieza[][] tablero;
    ArrayList<Pieza> Piezas;
    boolean[] used;
    public Puzzle(int size) {
        this.size = size;
        this.tablero = new Pieza[size][size];
        this.used = new boolean[size*size];
    }
    public void definirUsed(int pos, boolean valor){
        this.used[pos] = valor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Puzzle other = (Puzzle) obj;

        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pieza p1 = this.tablero[i][j];
                Pieza p2 = other.tablero[i][j];

                if (p1 == null && p2 == null) continue;
                if (p1 == null || p2 == null) return false;
                if (p1.getId() != p2.getId()) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                hash = 31 * hash + (tablero[i][j] == null ? 0 : tablero[i][j].getId());
            }
        }
        return hash;
    }
    public Puzzle clonar() {
        Puzzle copia = new Puzzle(this.size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pieza p = this.tablero[i][j];
                if (p != null) {
                    copia.colocarPieza(i, j,
                        new Pieza(
                            p.getTop(),
                            p.getRight(),
                            p.getBottom(),
                            p.getLeft(),
                            p.getId()
                        )
                    );
                }
            }
        }
        return copia;
    }
    

    public boolean conocerUsed(int pos){return this.used[pos];}
    
    public void colocarPieza(int row, int col, Pieza pieza){
        this.tablero[row][col] = pieza;
    }
    public void quitarPieza(int row, int col){
        this.tablero[row][col] = null;
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
    public void limpiarTablero(){
        for (int i =0; i< size; i++){
            for (int j = 0; j <size; j++){
                tablero[i][j] = null;
            }
        }
        Arrays.fill(used, false);
    }
    public void print() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            System.out.print(this.tablero[i][j] == null ? "- - - - " : this.tablero[i][j] + " ");
            }
        System.out.println();
        }
    }
    public void printPiezas(){
        
        for (int i = 0; i < this.Piezas.size(); i++){
            System.out.print(this.Piezas.get(i)+ " ");
            if ((i+1)%size == 0)
                System.out.println("");
        }
    }
    public void piezasToTablero(){
        this.limpiarTablero();
        for (int i = 0; i < this.Piezas.size(); i++){
            this.colocarPieza(this.getRow(i), this.getCol(i), this.Piezas.get(i));
        }
    }
    public int contarCoincidenciasReales() {
        int coincidencias = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Pieza actual = tablero[r][c];
                if (actual == null) continue;

                if (r < size - 1) {
                    if (actual.getBottom() == tablero[r + 1][c].getTop())
                        coincidencias++;
                }

                if (c < size - 1) {
                    if (actual.getRight() == tablero[r][c + 1].getLeft())
                        coincidencias++;
                }
            }
        }
        return coincidencias;
    }

    //Esta funcion devuelve la cantidad de piezas que coinciden entre si
    //Es decir, si algun lado coincide sin importar su posicion
    public int evaluateFitness() {
        int score = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Pieza actual = tablero[r][c];
                if (actual == null) continue;

                int peso = 1;

                // centro > borde > esquina
                if (r > 0 && r < size - 1 && c > 0 && c < size - 1)
                    peso = 3;
                else if (r > 0 && r < size - 1 || c > 0 && c < size - 1)
                    peso = 2;

                // abajo
                if (r < size - 1 && tablero[r + 1][c] != null) {
                    if (actual.getBottom() == tablero[r + 1][c].getTop())
                        score += 2 * peso;
                    else
                        score -= 1 * peso;
                }

                // derecha
                if (c < size - 1 && tablero[r][c + 1] != null) {
                    if (actual.getRight() == tablero[r][c + 1].getLeft())
                        score += 2 * peso;
                    else
                        score -= 1 * peso;
                }
            }
        }
        return score;
    }



    public void definirPiezas(ArrayList<Pieza> Piezas) {   this.Piezas = Piezas;  }

    public ArrayList<Pieza> getPiezas() {  return Piezas; }

    public void setPiezas(ArrayList<Pieza> Piezas) {   
        this.Piezas = Piezas; 
        this.limpiarTablero();
        for (int i = 0; i < this.Piezas.size(); i++){
            this.colocarPieza(this.getRow(i), this.getCol(i), this.Piezas.get(i));
        }
    }
    public int getRow(int posicion){ return posicion / this.size; }
    
    public int getCol(int posicion){  return posicion % this.size;  }
    
    public int getSize() {  return size;}
    public void setSize(int size) { this.size = size;  }
    
}
