
package algoritmos;
import conceptos.Pieza;
import conceptos.Puzzle;
import conceptos.PuzzleFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;


public class AlgGenetico {
    private ArrayList<Puzzle> puzzleList;
    private int tamano;
    private int poblacionInicial = 30;
    private ArrayList<Puzzle> hijos;
    private long asignaciones = 0;
    private long comparaciones = 0;
    private static final Random RAND = new Random();

    /**
    * Constructor del algoritmo genético.
    * Inicializa la población, el tamaño del puzzle y la lista de hijos.
    * Ajusta el tamaño de la población inicial si el puzzle es pequeño.
    */
    public AlgGenetico(ArrayList<Puzzle> puzzlelist) {
        asignaciones += 3; // puzzleList, tamano, hijos
        this.puzzleList = puzzlelist;
        this.tamano = puzzlelist.get(0).getSize();
        this.hijos = new ArrayList<>();

        comparaciones++;
        if (puzzlelist.get(0).getSize() < 30) {
            asignaciones++;
            poblacionInicial = puzzlelist.get(0).getSize();
        }
    }
    /**
    * Realiza el proceso de cruce entre los individuos de la población.
    * Selecciona padres mediante torneo, aplica cruce PMX y optimización local
    * para generar una nueva lista de hijos.
    */
    public ArrayList<Puzzle> cruce(ArrayList<Puzzle> ancestros) {
        hijos.clear();
        asignaciones += 2; // random, listaPuzzles
        int listaPuzzles = this.tamano * this.tamano;

        while (hijos.size() < 2 * poblacionInicial) {
            comparaciones++; 
            asignaciones += 2; // padre, madre
            Puzzle padre = torneo(ancestros, 3);
            Puzzle madre = torneo(ancestros, 3);


            comparaciones++;
            if (padre == madre) continue;

            asignaciones += 4; // puntoA, puntoB, hijo1, hijo2
            int puntoA = this.RAND.nextInt(listaPuzzles);
            int puntoB = this.RAND.nextInt(listaPuzzles);

            Puzzle hijo1 = crucePMX(padre, madre, puntoA, puntoB);
            Puzzle hijo2 = crucePMX(madre, padre, puntoA, puntoB);
            int intentos =
                (tamano <= 3) ? 200 :
                (tamano <= 5) ? 120 :
                (tamano <= 10) ? 40 : 5;
            optimizacionLocal(hijo1, intentos);
            optimizacionLocal(hijo2, intentos);


            
            System.out.println("CRUCE:");

            System.out.println("Padre 1 fitness: " + padre.evaluateFitness());

            System.out.println("Padre 2 fitness: " + madre.evaluateFitness());

            System.out.println("Hijo 1 fitness: " + hijo1.evaluateFitness());

            System.out.println("Hijo 2 fitness: " + hijo2.evaluateFitness());

            System.out.println("-----------------------------");
            
            hijos.add(hijo1);
            hijos.add(hijo2);
            asignaciones += 2; // adds
        }
        return hijos;
    }
    /**
    * Aplica el operador de cruce PMX (Partially Mapped Crossover) entre dos padres.
    * Genera un nuevo Puzzle manteniendo la validez de las piezas y evitando duplicados.
    */
    public Puzzle crucePMX(Puzzle padre, Puzzle madre, int puntoA, int puntoB) {
        int totPiezas = tamano * tamano;
        asignaciones += 5; 
        Pieza[] piezaP = new Pieza[totPiezas];
        Pieza[] piezaM = new Pieza[totPiezas];
        Pieza[] hijo = new Pieza[totPiezas];
        Puzzle nuevo = new Puzzle(tamano);

        comparaciones++;
        if (puntoA > puntoB) {
            asignaciones += 3; 
            int t = puntoA;
            puntoA = puntoB;
            puntoB = t;
        }

        
        for (int i = 0; i < totPiezas; i++) {
            comparaciones++; 
            asignaciones += 4; 
            int row = i / tamano;
            int column = i % tamano;
            piezaP[i] = padre.getPieza(row, column);
            piezaM[i] = madre.getPieza(row, column);
        }

        
        for (int posicion = puntoA; posicion <= puntoB; posicion++) {
            comparaciones++;
            asignaciones += 2; 
            hijo[posicion] = copiar(piezaP[posicion]);
        }

        for (int posicion = puntoA; posicion <= puntoB; posicion++) {
            comparaciones++;
            asignaciones += 2; 
            int id = piezaM[posicion].getId();

            comparaciones++; 
            if (!contiene(hijo, id)) {
                asignaciones++; 
                int pos = posicion;

                while (pos >= puntoA && pos <= puntoB) {
                    comparaciones += 2; 
                    asignaciones += 2; 
                    int idMap = piezaP[pos].getId();
                    pos = buscarPos(piezaM, idMap);
                }
                comparaciones += 2;
                
                asignaciones++; 
                hijo[pos] = copiar(piezaM[posicion]);
            }
        }

        
        for (int i = 0; i < totPiezas; i++) {
            comparaciones += 2;
            asignaciones++; 
            if (hijo[i] == null) {
                asignaciones++;
                hijo[i] = copiar(piezaM[i]);
            }
        }

        for (int i = 0; i < totPiezas; i++) {
            comparaciones++;
            asignaciones += 3; 
            int row = i / tamano;
            int column = i % tamano;
            nuevo.colocarPieza(row, column, hijo[i]);
        }
        return nuevo;
    }
    
    /**
    * Selecciona un individuo de la población mediante torneo.
    * Elige k candidatos aleatorios y devuelve el de mayor fitness.
    */
    private Puzzle torneo(ArrayList<Puzzle> poblacion, int k) {
        Random rand = new Random();
        Puzzle mejor = null;

        for (int i = 0; i < k; i++) {
            Puzzle candidato = poblacion.get(rand.nextInt(poblacion.size()));
            if (mejor == null || candidato.evaluateFitness() > mejor.evaluateFitness()) {
                mejor = candidato;
            }
        }
        return mejor;
    }
    
    /**
     * Verifica si un arreglo de piezas contiene una pieza con un ID específico.
     */
    private boolean contiene(Pieza[] arr, int id) {
        for (Pieza p : arr) {
            comparaciones += 2; 
            if (p != null && p.getId() == id) return true;
        }
        return false;
    }
    
    /**
    * Busca la posición de una pieza con un ID específico dentro de un arreglo.
    * Devuelve el índice donde se encuentra o -1 si no existe.
    */
    private int buscarPos(Pieza[] arr, int id) {
        for (int i = 0; i < arr.length; i++) {
            comparaciones += 2; 
            if (arr[i].getId() == id) return i;
        }
        return -1;
    }
    
    /**
    * Crea una copia profunda de una pieza, evitando usar las
    * referencias de los padres.
    */
    private Pieza copiar(Pieza p) {
        asignaciones++; 
        return new Pieza(p.getTop(), p.getRight(), p.getBottom(), p.getLeft(), p.getId());
    }
    
    /**
     * Aplica mutaciones a la población para mantener diversidad genética.
     * Mutan los individuos repetidos o con cierta probabilidad,
     * aceptando solo mutaciones que no empeoren el fitness.
     */
    public void mutarPoblacion() {
        HashSet<Puzzle> vistos = new HashSet<>();
        asignaciones++;

        for (int i = 0; i < puzzleList.size(); i++) {
            asignaciones++;
            Puzzle actual = puzzleList.get(i);

            boolean esRepetido = vistos.contains(actual);

            
            if (esRepetido || RAND.nextDouble() < 0.20) {

                int fitnessAntes = actual.evaluateFitness();
                Puzzle mutado = actual.clonar();
                mutacionLeve(mutado);
                int fitnessDespues = mutado.evaluateFitness();

                
                if (fitnessDespues >= fitnessAntes) {
                    puzzleList.set(i, mutado);
                    vistos.add(mutado);
                } else {
                    vistos.add(actual);
                }

            } else {
                vistos.add(actual);
            }
        }
    }

    /**
    * Aplica una optimización local tipo búsqueda por intercambio.
    * Intenta mejorar el fitness intercambiando pares de piezas
    * durante un número limitado de intentos.
    */
    private void optimizacionLocal(Puzzle p, int intentos) {
        Random rand = new Random();
        int mejorFitness = p.evaluateFitness();

        for (int i = 0; i < intentos; i++) {
            Puzzle copia = p.clonar();

            int r1 = rand.nextInt(p.getSize());
            int c1 = rand.nextInt(p.getSize());
            int r2 = rand.nextInt(p.getSize());
            int c2 = rand.nextInt(p.getSize());

            if (r1 == r2 && c1 == c2) continue;

            Pieza a = copia.getPieza(r1, c1);
            Pieza b = copia.getPieza(r2, c2);

            if (a != null && b != null) {
                copia.colocarPieza(r1, c1, b);
                copia.colocarPieza(r2, c2, a);

                int nuevoFitness = copia.evaluateFitness();
                if (nuevoFitness > mejorFitness) {
                    p.colocarPieza(r1, c1, b);
                    p.colocarPieza(r2, c2, a);
                    mejorFitness = nuevoFitness;
                }
            }
        }
    }
    /**
    * Realiza una mutación leve sobre un puzzle.
    * Consiste en uno o varios intercambios aleatorios de piezas.
    */
    private void mutacionLeve(Puzzle p) {
        Random rand = new Random();
        int size = p.getSize();

        int swaps = 1 + rand.nextInt(3); // 1 a 3 swaps

        for (int s = 0; s < swaps; s++) {
            int r1 = rand.nextInt(size);
            int c1 = rand.nextInt(size);
            int r2 = rand.nextInt(size);
            int c2 = rand.nextInt(size);

            if (r1 == r2 && c1 == c2) continue;

            Pieza a = p.getPieza(r1, c1);
            Pieza b = p.getPieza(r2, c2);

            if (a != null && b != null) {
                p.colocarPieza(r1, c1, b);
                p.colocarPieza(r2, c2, a);
            }
        }
    }
    
    /**
    * Elimina puzzles repetidos de la población.
    * Intenta rescatar los repetidos aplicando mutaciones que mejoren el fitness.
    */
    private void eliminarRepetidosConMutacion() {
        HashSet<Puzzle> vistos = new HashSet<>();
        ArrayList<Puzzle> nuevaPoblacion = new ArrayList<>();

        for (Puzzle p : puzzleList) {

            // si no es repetido, se queda
            if (!vistos.contains(p)) {
                vistos.add(p);
                nuevaPoblacion.add(p);
                continue;
            }

            // es repetido, se intentamos rescatarlo
            int fitnessOriginal = p.evaluateFitness();
            boolean rescatado = false;

            for (int intentos = 0; intentos < 5; intentos++) {
                Puzzle mutado = p.clonar();
                mutacionLeve(mutado);

                // si no cambió, es inútil
                if (mutado.equals(p)) continue;

                int fitnessNuevo = mutado.evaluateFitness();

                // solo aceptamos si mejora
                if (fitnessNuevo > fitnessOriginal) {
                    vistos.add(mutado);
                    nuevaPoblacion.add(mutado);
                    rescatado = true;
                    break;
                }
            }

        }

    puzzleList = nuevaPoblacion;
}

    public void resolver(){
        ArrayList<Puzzle> hijos;
        for (int generation = 0; generation < 10; generation++) {
            comparaciones++;
            asignaciones++;
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
            
            eliminarRepetidosConMutacion();
            while (puzzleList.size() < poblacionInicial) {
                Puzzle nuevo = PuzzleFactory.copiarPuzzle(
                    puzzleList.get(new Random().nextInt(puzzleList.size()))
                );
                mutacionLeve(nuevo);
                puzzleList.add(nuevo);
            }


        }
        Collections.sort(puzzleList, (a,b) -> b.evaluateFitness() - a.evaluateFitness());

        System.out.println(" MEJORES 3 RESULTADOS FINALES ");
        for (int i = 0; i < 3 && i < puzzleList.size(); i++) {
            Puzzle p = puzzleList.get(i);
            System.out.println(
                "Puesto " + (i + 1) +
                " | Fitness: " + p.evaluateFitness() +
                " | Coincidencias reales: " + p.contarCoincidenciasReales()
            );
            p.print();
        }
        return ;
        
    }

    public long getAsignaciones() {
        return asignaciones;
    }

    public long getComparaciones() {
        return comparaciones;
    }
    
    public long getTotalInstrucciones() { 
        return asignaciones + comparaciones; 
    }
    

}
