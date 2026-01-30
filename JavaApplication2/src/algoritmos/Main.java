
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import auxiliar.Medicion;
import java.util.ArrayList;

public class Main {
    static int limNumerico= 9;
    static int supNumerico= 15;
 
    public static void imprimirPorFuerza(){
        porFuerza solverFuerza = new porFuerza();
        Medicion medidor= new Medicion();
        System.out.println("\n\n============Algoritmo por fuerza con backtracking ============\n");
        
        System.out.println("---------Prueba 1---------");
        Puzzle puzzle = new Puzzle(3);
        puzzle = PuzzleFactory.createFixed3x3();
        puzzle.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle);
        puzzle.print();
        puzzle.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
         
        medidor.iniciarMedicion();
        solverFuerza.resolver(puzzle);
        medidor.finMedicion();
        solverFuerza.printInfo();
        
        puzzle.print();
        System.out.println("");
        
        System.out.println("---------Prueba 2--------");
        Puzzle puzzle2 = new Puzzle(5);
        puzzle2 = PuzzleFactory.createRandom(5, supNumerico);
        puzzle2.print();
        System.out.println("--------Deordenado---------");
        PuzzleFactory.desordenarPuzzle(puzzle2);
        puzzle2.print();
        puzzle2.limpiarTablero();
        System.out.println("----Ordenado por fuerza----");
        medidor.iniciarMedicion();
        solverFuerza.resolver(puzzle2);
        medidor.finMedicion();
        solverFuerza.printInfo();
        
        puzzle2.print();
        System.out.println("");
        
        
        
    }
    
    public static void imprimirGreedy(){
        Greedy greedySolve = new Greedy();
        Medicion medidor= new Medicion();
        System.out.println("\n\n============Algoritmo Greedy ============\n");
        System.out.println("---------Prueba avance rapido 1---------");
        Puzzle piezas = PuzzleFactory.createRandom(40, limNumerico);
        Puzzle puzzle1 = new Puzzle(40);
        piezas = PuzzleFactory.desordenarPuzzle(piezas);
        System.out.println("----Ordenado de avance rapido 1----");
        puzzle1.definirPiezas(piezas.getPiezas());
        medidor.iniciarMedicion();
        greedySolve.solve(puzzle1, limNumerico);
        medidor.finMedicion();
        greedySolve.variables();
        puzzle1.print();
        
        System.out.println("---------Prueba avance rapido 1---------");
       
        piezas = PuzzleFactory.createRandom(40, supNumerico);

        piezas = PuzzleFactory.desordenarPuzzle(piezas);

        System.out.println("----Ordenado de avance rapido 1----");
        puzzle1.definirPiezas(piezas.getPiezas());
        medidor.iniciarMedicion();
        greedySolve.solve(puzzle1, supNumerico);
        medidor.finMedicion();
        greedySolve.variables();
        puzzle1.print();
    }
    public static void imprimirGenetico() {

        Medicion medidor = new Medicion();

        int[] tamanos = {5}; // puedes agregar más si quieres
        System.out.println("\n\n============Algoritmo Genetico ============\n");
        for (int t = 0; t < tamanos.length; t++) {

            int size = tamanos[t];
            int maxValue = supNumerico;

            System.out.println("========= PRUEBA GENÉTICO | " + size + "x" + size + " =========");

            ArrayList<Puzzle> puzzleList = new ArrayList<>();

            // Puzzle base
            Puzzle base = PuzzleFactory.createRandom(size, 9);
            PuzzleFactory.desordenarPuzzle(base);

            System.out.println("---- Puzzle base desordenado ----");
            base.print();
            System.out.println("--------------------------------");

            // Tamaño de población
            int poblacionInicial = 30;
            if (size < 30) {
                poblacionInicial = size;
            }
            poblacionInicial++;
            puzzleList.add(base);

            // Generar población inicial
            for (int i = 0; i < poblacionInicial - 1; i++) {
                Puzzle copia = PuzzleFactory.copiarPuzzle(base);
                PuzzleFactory.desordenarPuzzle(copia);
                puzzleList.add(copia);

                System.out.println("Individuo " + (i + 1));
                copia.print();
                System.out.println("Fitness: " + copia.evaluateFitness());
                System.out.println("--------------------------------");
            }

            // Ejecutar algoritmo genético
            AlgGenetico ag = new AlgGenetico(puzzleList);

            System.out.println("---- Ejecutando algoritmo genético ----");
            medidor.iniciarMedicion();
            ag.resolver();
            medidor.finMedicion();
            System.out.println(ag.getAsignaciones()+ "");
            System.out.println(ag.getComparaciones() + "");
            
            System.out.println("=============================================\n");
        }
    }

    
    public static void main(String[] args) {

        imprimirPorFuerza();
        imprimirGreedy();
        imprimirGenetico();
        
    }
    
}
