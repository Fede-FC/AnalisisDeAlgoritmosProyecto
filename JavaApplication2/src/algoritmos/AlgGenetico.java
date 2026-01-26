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
        hijos.clear();
        Random rand = new Random();
        int listaPuzzles = this.tamano * this.tamano;
        
        while (hijos.size() < 2 * poblacionInicial) {
            Puzzle padre = ancestros.get(random.nextInt(ancestros.size()));
            Puzzle madre = ancestros.get(random.nextInt(ancestros.size()));
            
            if (padre == madre) continue;
            
            int puntoA = rand.nextInt(listaPuzzles);
            int puntoB = rand.nextInt(listaPuzzles);
            hijos.add(crucePMX(padre, madre, puntoA, puntoB));
            hijos.add(crucePMX(madre, padre, puntoA, puntoB));
        }
        return hijos;
    }

    public Puzzle crucePMX(Puzzle padre, Puzzle madre, int puntoA, int puntoB) {
        int totPiezas = tamano * tamano;
        Pieza[] piezaP = new Pieza[totPiezas]; 
        Pieza[] piezaM = new Pieza[totPiezas]; 
        Pieza[] hijo = new Pieza[totPiezas];
        Puzzle nuevo = new Puzzle(tamano);
        
        if (puntoA > puntoB) {
            int t = puntoA;
            puntoA = puntoB;
            puntoB = t;
        }        
        
        for (int i = 0; i < totPiezas; i++) {
            
            int row = i / tamano;
            int column = i % tamano;
            
            piezaP[i] = padre.getPieza(row, column);
            piezaM[i] = madre.getPieza(row, column);
        }

        
        for (int posicion = puntoA; posicion <= puntoB; posicion++) {
            hijo[posicion] = copiar(piezaP[posicion]);
        }

        for (int posicion = puntoA; posicion <= puntoB; posicion++) {
            int id = piezaM[posicion].getId();

            if (!contiene(hijo, id)) {
                int pos = posicion;

                while (pos >= puntoA && pos <= puntoB) {
                    int idMap = piezaP[pos].getId();
                    pos = buscarPos(piezaM, idMap);
                }

                hijo[pos] = copiar(piezaM[posicion]);
            }
        }

        for (int i = 0; i < totPiezas; i++) {
            if (hijo[i] == null) {
                hijo[i] = copiar(piezaM[i]);
            }
        }

        for (int i = 0; i < totPiezas; i++) {
            int row = i / tamano;
            int column = i % tamano;
            nuevo.colocarPieza(row, column, hijo[i]);
        }

        return nuevo;
    }

 
    private boolean contiene(Pieza[] arr, int id) {
        for (Pieza p : arr)
            if (p != null && p.getId() == id) return true;
        return false;
    }

    private int buscarPos(Pieza[] arr, int id) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i].getId() == id) return i;
        return -1;
    }

    private Pieza copiar(Pieza p) {
        return new Pieza(p.getTop(), p.getRight(), p.getBottom(), p.getLeft(), p.getId());
    }

    public void mutarPoblacion() {
        Random rand = new Random();

        for (int i = 0; i < puzzleList.size(); i++) {
            Puzzle p = puzzleList.get(i);

            if (rand.nextDouble() < 0.2) { 
                mutacionLeve(p);
            }
        }
    }



    private void mutacionLeve(Puzzle p) {
        Random rand = new Random();
        int size = p.getSize();

        int r1 = rand.nextInt(size);
        int c1 = rand.nextInt(size);
        int r2 = rand.nextInt(size);
        int c2 = rand.nextInt(size);

        
        if (r1 == r2 && c1 == c2) return;

        Pieza a = p.getPieza(r1, c1);
        Pieza b = p.getPieza(r2, c2);

        if (a != null && b != null) {
            p.colocarPieza(r1, c1, b);
            p.colocarPieza(r2, c2, a);
        }
    }


    public void resolver(){
        ArrayList<Puzzle> hijos;
        for (int generation = 0; generation < 100000; generation++) {
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
            
            //metodo para mutar a la poblacion :)
            mutarPoblacion();

        }
         
        return ;
        
    }
    
    
    public static void main(String[] args) {

        int size = 10;
        int maxValue = 9;

        ArrayList<Puzzle> puzzleList = new ArrayList<>();

        Puzzle base = PuzzleFactory.createRandom(size, maxValue);
        base = PuzzleFactory.desordenarPuzzle(base);
        base.print();
        System.out.println("--------------------------------");

        int poblacionInicial = 30;
        if (size < 30) {
            poblacionInicial = size;
        }

        puzzleList.add(base);

        for (int i = 0; i < poblacionInicial - 1; i++) {
            Puzzle copia = PuzzleFactory.copiarPuzzle(base);
            copia = PuzzleFactory.desordenarPuzzle(copia);
            puzzleList.add(copia);
            copia.print();
            System.out.println("Fitness: " + copia.evaluateFitness());
            System.out.println("--------------------------------");
        }

        AlgGenetico ag = new AlgGenetico(puzzleList);
        ag.resolver();

        System.out.println("Resultado final:");
        Puzzle mejor = puzzleList.get(0);
        mejor.print();
        System.out.println("Fitness final: " + mejor.evaluateFitness());
    }

}
