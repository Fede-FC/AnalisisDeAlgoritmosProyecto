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
                    Pieza pPadre= padre.getPieza(i, j);

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
    public void mutarPoblacion() {
        for (int i = 0; i < puzzleList.size(); i++) {
            Puzzle original = puzzleList.get(i);
            Puzzle mutado = PuzzleFactory.copiarPuzzle(original);

            int fitnessAntes = original.evaluateFitness();

            boolean reparo = repararRepetidos(mutado);

            if (!reparo) {
                mutacionLeve(mutado);
            }

            int fitnessDespues = mutado.evaluateFitness();

            if (fitnessDespues > fitnessAntes) {
                puzzleList.set(i, mutado);
            }
        }
    }

    private boolean repararRepetidos(Puzzle p) {
        int size = p.getSize();
        int n = size * size;
        boolean[] vistos = new boolean[n];
        ArrayList<int[]> repetidas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int r = i / size;
            int c = i % size;
            Pieza pieza = p.getPieza(r, c);
            if (pieza == null) continue;

            int id = pieza.getId();
            if (vistos[id]) {
                repetidas.add(new int[]{r, c});
            } else {
                vistos[id] = true;
            }
        }

        ArrayList<Integer> faltantes = new ArrayList<>();
        for (int id = 0; id < n; id++) {
            if (!vistos[id]) {
                faltantes.add(id);
            }
        }

        if (repetidas.isEmpty()) return false;

        Pieza molde = null;
        for (int i = 0; i < size && molde == null; i++) {
            for (int j = 0; j < size; j++) {
                Pieza tmp = p.getPieza(i, j);
                if (tmp != null) {
                    molde = tmp;
                    break;
                }
            }
        }

        int idx = 0;
        for (int[] pos : repetidas) {
            int idFaltante = faltantes.get(idx++);

            Pieza nueva = new Pieza(
                    molde.getTop(),
                    molde.getRight(),
                    molde.getBottom(),
                    molde.getLeft(),
                    idFaltante
            );

            p.colocarPieza(pos[0], pos[1], nueva);
        }

        return true;
    }

    private void mutacionLeve(Puzzle p) {
        Random rand = new Random();
        int size = p.getSize();

        if (rand.nextBoolean()) {
            int r1 = rand.nextInt(size);
            int c1 = rand.nextInt(size);
            int r2 = rand.nextInt(size);
            int c2 = rand.nextInt(size);

            Pieza a = p.getPieza(r1, c1);
            Pieza b = p.getPieza(r2, c2);

            if (a != null && b != null) {
                p.colocarPieza(r1, c1, b);
                p.colocarPieza(r2, c2, a);
            }
        } else {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);

            Pieza pieza = p.getPieza(r, c);
            if (pieza != null) {
                if (rand.nextBoolean()) pieza.rotarIzq();
                else pieza.rotarDer();
            }
        }
    }


    private Pieza buscarPiezaPorId(Puzzle p, int id) {
        int size = p.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (p.getPieza(i, j).getId() == id) {
                    return p.getPieza(i, j);
                }
            }
        }
        return null;
    }


    
    
    public void resolver(){
        ArrayList<Puzzle> hijos;
        for (int generation = 0; generation < 20000; generation++) {
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
            mutarPoblacion();

        }
         
        return ;
        
    }
    
    
    public static void main(String[] args) {

        int size = 3;
        int maxValue = 9;

        ArrayList<Puzzle> puzzleList = new ArrayList<>();

        Puzzle base = PuzzleFactory.createRandom(size, maxValue);
        base = PuzzleFactory.desordenarPuzzle(base);
        base.print();
        System.out.println("--------------------------------");

        int poblacionInicial = 3;
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
