/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;
import conceptos.Pieza;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author araya
 */
public class AlgGenetico {
    private ArrayList<Puzzle> puzzleList;
    private int tamano;
    private int poblacionInicial=30;
    private ArrayList<Puzzle> hijos;

    public AlgGenetico(ArrayList<Puzzle> puzzlelist) {
        this.puzzleList = puzzlelist;
        this.tamano = puzzlelist.get(0).getSize();
        this.hijos= new ArrayList<>();
        
        if (puzzlelist.get(0).getSize()<30){
            poblacionInicial=puzzlelist.get(0).getSize();
        } 
    }
    
    
    public ArrayList<Puzzle> cruce(ArrayList<Puzzle> ancestros){
        Random random = new Random();
        //int aleatorio = random.nextInt(this.tamano);
        
        
        
        
        while (hijos.size() < 2*poblacionInicial) {
            Puzzle padre= ancestros.get(random.nextInt(this.tamano));
            Puzzle madre= ancestros.get(random.nextInt(this.tamano));
            
            if (padre==madre) continue;
            
            Puzzle pHijo =cruzarFilas(padre, madre);
            hijos.add(pHijo);
            pHijo =cruzarFilas(madre, padre);
            hijos.add(pHijo);
        }
        
        
        return hijos;
    }
    
    public Puzzle cruzarFilas(Puzzle padre, Puzzle madre) {
    Puzzle hijo = new Puzzle(tamano);
    
    
    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            if (i % 2 == 0) {
                Pieza pPadre= madre.getPieza(i, j);
                
                Pieza actualP = new Pieza(
                    pPadre.getTop(), 
                    pPadre.getRight(), 
                    pPadre.getBottom(), 
                    pPadre.getLeft(),
                    pPadre.getId());
                
                hijo.colocarPieza(i, j, actualP);
            } else {
                Pieza pMadre= madre.getPieza(i, j);
                
                Pieza actualM = new Pieza(
                    pMadre.getTop(), 
                    pMadre.getRight(), 
                    pMadre.getBottom(), 
                    pMadre.getLeft(),
                    pMadre.getId());
                
                hijo.colocarPieza(i, j, actualM);
            }
        }
    }
    return hijo;
}

    
    
    public void resolver(){
        ArrayList<Puzzle> hijos;
        for (int generation = 0; generation < 10; generation++) {
            //Ordenar
            Collections.sort(puzzleList, (a,b) -> b.evaluateFitness() - a.evaluateFitness());
            //Hijos=2*Poblacion inicial - BUA DE LOCOS
            
            //cruce
            this.hijos = cruce(puzzleList);
            
            //Añadir hijos a la poblacion- TODO: analizar proximamente(Probablemente cambiar)
            puzzleList.addAll(this.hijos);
            
            //Se re-evalua y acomoda de nuevo TODO: verificar luego si no hace falta ordenar 2 veces
            Collections.sort(puzzleList, (a,b) -> b.evaluateFitness() - a.evaluateFitness());
            
            //Eliminar los de menos rendimiento, ya sea padres o hijos - (Parte completa)
            puzzleList.subList(poblacionInicial, puzzleList.size()).clear();
            
            //Falta metodo mutar
            

        }
         
        return ;
        
    }
    
    
    public static void main(String[] args) {
        Puzzle rompCabezas = new Puzzle(4);
        ArrayList<Puzzle> puzzleList= new ArrayList<>();
        rompCabezas = PuzzleFactory.createRandom(5, 9);
        rompCabezas.print();
        //De locos
        int poblacionInicial = 30;
        if (rompCabezas.getSize()<30){
            poblacionInicial=rompCabezas.getSize();
        } 
        
        
        System.out.println("1-----------------------------------1");
        puzzleList.add(rompCabezas);
        Puzzle puzzle = new Puzzle(rompCabezas.getSize());
        puzzle = PuzzleFactory.createRandom(rompCabezas.getSize(), 9);
        puzzle.print();
        System.out.println("1-----------------------------------1");
        System.out.println("1-----------------------------------1");
        System.out.println("1-----------------------------------1");
        for (int individuos = 0; individuos < poblacionInicial-1; individuos++) {
            Puzzle nuevo = PuzzleFactory.copiarPuzzle(puzzle);
            nuevo = PuzzleFactory.desordenarPuzzle(nuevo);
            puzzleList.add(nuevo);
            nuevo.print();
            System.out.println(nuevo.evaluateFitness());
            System.out.println("1-----------------------------------1");
        }
    }
}
