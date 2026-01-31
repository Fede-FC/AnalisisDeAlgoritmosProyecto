
package algoritmos;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import auxiliar.Medicion;
import java.util.ArrayList;

public class Main {
    
    static int[] fuerzaSizes = {3,5};
    static int[] greedySizes = {3,5,10,15,20,30,40};
    static int[] geneticoSizes = {3,5,10,15,20,30,60};
    static int[] combinaciones = {9,15};

    public static void main(String[] args) {

        for(int maxValue : combinaciones){

            System.out.println("\n===============================");
            System.out.println(" COMBINACIÓN 0.." + maxValue);
            System.out.println("===============================\n");

            for(int size : geneticoSizes){

                System.out.println("\n========== Puzzle " + size + "x" + size + " ==========");

                // Crear UN SOLO puzzle base
                System.out.println("Puzzle base :");
                Puzzle base = PuzzleFactory.createRandom(size, maxValue);base.print();
                PuzzleFactory.desordenarPuzzle(base);
                
                System.out.println("\nPuzzle base desordenado:");
                base.print();

                // Copias para cada algoritmo
                if(size == 3 || size == 5){
                    ejecutarFuerza(PuzzleFactory.copiarPuzzle(base), size);
                }

                if(size <= 40){
                    ejecutarGreedy(PuzzleFactory.copiarPuzzle(base), size, maxValue);
                }

                ejecutarGenetico(PuzzleFactory.copiarPuzzle(base), size, maxValue);
            }
        }
    }

    // ================= FUERZA =================
    public static void ejecutarFuerza(Puzzle puzzle, int size){
        porFuerza solver = new porFuerza();
        Medicion medidor = new Medicion();

        System.out.println("\n--- Fuerza Bruta ---");

        puzzle.limpiarTablero();

        medidor.iniciarMedicion();
        solver.resolver(puzzle);
        medidor.finMedicion();

        solver.printInfo();
        puzzle.print();
    }

    // ================= GREEDY =================
    public static void ejecutarGreedy(Puzzle puzzle, int size, int maxValue){
        Greedy greedy = new Greedy();
        Medicion medidor = new Medicion();

        System.out.println("\n--- Greedy ---");

        Puzzle tablero = new Puzzle(size);
        tablero.definirPiezas(puzzle.getPiezas());

        medidor.iniciarMedicion();
        greedy.solve(tablero, maxValue);
        medidor.finMedicion();

        greedy.variables();
        tablero.print();
    }

    // ================= GENÉTICO =================
    public static void ejecutarGenetico(Puzzle puzzle, int size, int maxValue){

        System.out.println("\n--- Genético ---");

        Medicion medidor = new Medicion();
        ArrayList<Puzzle> poblacion = new ArrayList<>();

        int poblacionInicial = Math.min(size + 1, 30);
        poblacion.add(puzzle);

        for(int i=0;i<poblacionInicial-1;i++){
            Puzzle copia = PuzzleFactory.copiarPuzzle(puzzle);
            PuzzleFactory.desordenarPuzzle(copia);
            poblacion.add(copia);
        }

        AlgGenetico ag = new AlgGenetico(poblacion);

        medidor.iniciarMedicion();
        ag.resolver();
        medidor.finMedicion();

        System.out.println("Asignaciones: " + ag.getAsignaciones());
        System.out.println("Comparaciones: " + ag.getComparaciones());
    }
}

