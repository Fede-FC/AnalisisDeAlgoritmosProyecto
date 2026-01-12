/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmos;
import conceptos.Pieza;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: 
 * 
 * 
 */
public class Main {
    
    public static Puzzle desordenarPuzzle(Puzzle rompCabezas){
        ArrayList<Pieza> lista = new ArrayList<>();
        int tamano= rompCabezas.getSize();
        int indice=0;
        
        //Basicamente crre una lista de piezas, esto lo hago por que hay una
        //funcion que desordena las lista automaticamente
        for (int i=0; i<tamano; i++ ){
            for (int j=0; j<tamano; j++ ){
                Pieza pieza= rompCabezas.getPieza(i, j);
                if (pieza!=null){
                    lista.add(pieza);
                }
            }
        }
        
        //Ahora se desordena la lista
        Collections.shuffle(lista);
        
        //
        //
        for (int i=0; i<tamano; i++ ){
            for (int j=0; j<tamano; j++ ){
                rompCabezas.colocarPieza(i, j, lista.get(indice++));
                
            }
        }
        

        return rompCabezas;
    }
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3);
        puzzle.print();
        puzzle = PuzzleFactory.createRandom(3, 5);
        puzzle.print();
        System.out.println("---------------------------");
        Puzzle desordenarpuzzle = desordenarPuzzle(puzzle);
        desordenarpuzzle.print();
    }

}
