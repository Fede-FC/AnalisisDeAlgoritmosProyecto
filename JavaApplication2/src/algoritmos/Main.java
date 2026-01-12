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
    
    public static void desordenarPuzzle(Puzzle rompCabezas){
        ArrayList<Pieza> lista = new ArrayList<>();
        int tamano= rompCabezas.getSize();
        int indice=0;
        
        //Basicamente crre una lista de piezas, esto lo hago por que hay un
        //metodo que desordena las lista automaticamente
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
        
        //Aqui se cambia los valores de las piezas de rompCabezas por los de 
        //lista(son los mismos en distinto orden), por lo que ahora si se puede
        //usar con los algoritmos
        for (int i=0; i<tamano; i++ ){
            for (int j=0; j<tamano; j++ ){
                rompCabezas.colocarPieza(i, j, lista.get(indice++));
                
            }
        }
        
        //se devuelve la lista desordenada
        return ;
    }
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3);
        puzzle.print();
        puzzle = PuzzleFactory.createRandom(3, 5);
        puzzle.print();
        System.out.println("---------------------------");
        desordenarPuzzle(puzzle);
        puzzle.print();
    }

}
