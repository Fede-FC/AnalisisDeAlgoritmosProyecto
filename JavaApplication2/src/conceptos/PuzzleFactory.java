/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conceptos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author feder
 */
public class PuzzleFactory {
    ArrayList<Pieza> Piezas;
    int pieceIdCcounter;
    public static Puzzle createFixed3x3(){
        int pieceIdCounter = 0;
        Puzzle puzzle = new Puzzle(3);
        ArrayList<Pieza> piezas = new ArrayList<>();
        
        //Piezzas del rompecabezas predeterminado
        // Formato: (top, right, bottom, left)
        
        piezas.add(new Pieza (2, 3, 3, 0, 0));
        piezas.add(new Pieza (0, 4, 9, 3, 1));
        piezas.add(new Pieza (0, 0, 4, 4, 2));

        piezas.add(new Pieza (3, 9, 7, 0, 3));
        piezas.add(new Pieza (9, 7, 8, 9, 4));
        piezas.add(new Pieza (4, 0, 9, 7, 5));

        piezas.add(new Pieza (7, 8, 0, 0, 6));
        piezas.add(new Pieza (8,4, 8, 8, 7));
        piezas.add(new Pieza (9, 0, 0, 4, 8));
        
        puzzle.setPiezas(piezas);
        
        return puzzle;
    }
    public static Puzzle createRandom(int size, int maxValue){
        int pieceIdCounter = 0;
        Random generador = new Random();
        HashSet<String> firmas = new HashSet<>();
        String firma;
        ArrayList<Pieza> piezas = new ArrayList<>();
        Puzzle puzzle = new Puzzle(size);
        // recorrer fila poer fila e ir generando piezas
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Pieza pieza = new Pieza();
                pieza.setId(pieceIdCounter);
                do{    
                    // Derecha y abajo son aleatorias
                    pieza.setRight(generador.nextInt(maxValue+1));
                    pieza.setBottom(generador.nextInt(maxValue+1));
                    // arriba e izquierda son aleatorias si son bordes si no toma la de la pieza adyacente
                    pieza.setTop( i!= 0 ? puzzle.getPieza(i-1, j).getBottom() : generador.nextInt(maxValue+1) );
                    pieza.setLeft( j!= 0 ? puzzle.getPieza(i, j-1).getRight() : generador.nextInt(maxValue+1) );
                    // creando firma para asegurar originalidad 
                    firma = pieza.getTop()+"-"+pieza.getRight()+"-"+pieza.getBottom()+"-"+pieza.getLeft();
                } while(firmas.contains(firma));
                firmas.add(firma);
                pieceIdCounter++;
                puzzle.colocarPieza(i, j, pieza);
                piezas.add(pieza);
            }
        }
        puzzle.setPiezas(piezas);
        
        return puzzle;
    }
    public static Puzzle copiarPuzzle(Puzzle original){
        Puzzle nuevo = new Puzzle(original.getSize());

        for (int row = 0; row < original.getSize(); row++) {
            for (int column = 0; column < original.getSize(); column++) {
                Pieza piezaOriginal = original.getPieza(row, column);
                Pieza piezaNueva = new Pieza(
                    piezaOriginal.getTop(),
                    piezaOriginal.getRight(),
                    piezaOriginal.getBottom(),
                    piezaOriginal.getLeft(),
                    piezaOriginal.getId()
                );
                nuevo.colocarPieza(row, column, piezaNueva);
            }
        }

        return nuevo;
    }

    public static Puzzle desordenarPuzzle(Puzzle rompCabezas){
        ArrayList<Pieza> lista = new ArrayList<>();
        int tamano= rompCabezas.getSize();
        int indice=0;

        //Basicamente cree una lista de piezas, esto lo hago por que hay un
        //metodo que desordena las lista automaticamente
        for (int row=0; row<tamano; row++ ){
            for (int column=0; column<tamano; column++ ){
                Pieza original = rompCabezas.getPieza(row, column);
                Pieza pieza= new Pieza(
                        original.getTop(), 
                        original.getRight(), 
                        original.getBottom(), 
                        original.getLeft(),
                        original.getId());
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
        for (int row=0; row<tamano; row++ ){
            for (int column=0; column<tamano; column++ ){
                rompCabezas.colocarPieza(row, column, lista.get(indice++));
                
            }
        }
        
        //se devuelve la lista desordenada
        return rompCabezas;
    }
}
