/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author feder
 */
public class PuzzleFactory {
    ArrayList<Pieza> Piezas;
    int pieceIdCcounter;
    public static Puzzle createFixed3x3(){
        return null;
    }
    public static Puzzle createRandom(int size, int maxValue){
        int pieceIdCounter = 0;
        Random generador = new Random();
        ArrayList<Pieza> piezas = new ArrayList<>();
        Puzzle puzzle = new Puzzle(size);
        // recorrer fila poer fila e ir generando piezas
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Pieza pieza = new Pieza();
                pieza.setId(pieceIdCounter);
                pieceIdCounter++;
                // Derecha y abajo son aleatorias
                pieza.setRight(generador.nextInt(maxValue+1));
                pieza.setBottom(generador.nextInt(maxValue+1));
                // arriba e izquierda son aleatorias si son bordes si no toma la de la pieza adyacente
                pieza.setTop( i!= 0 ? puzzle.getPieza(i-1, j).getBottom() : generador.nextInt(maxValue+1) );
                pieza.setLeft( j!= 0 ? puzzle.getPieza(i, j-1).getRight() : generador.nextInt(maxValue+1) );

                if (puzzle.isValid(i, j, pieza))
                    puzzle.colocarPieza(i, j, pieza);

                    piezas.add(pieza);
                    
            }
        }
        puzzle.setPiezas(piezas);
        
        return puzzle;
    }
     public static Puzzle desordenarPuzzle(Puzzle rompCabezas){
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
        return rompCabezas;
    }
}
