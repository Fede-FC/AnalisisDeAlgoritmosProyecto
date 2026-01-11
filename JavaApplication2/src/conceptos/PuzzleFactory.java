/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

import java.util.ArrayList;
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
        
        
        return puzzle;
    }
}
