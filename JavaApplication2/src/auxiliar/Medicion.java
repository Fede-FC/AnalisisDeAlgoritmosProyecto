
package auxiliar;


public class Medicion {
    Long inicio, fin, tiempo, memoriaAntes, memoriaDespues, memoriaUsada;
    Runtime rt;

    public Medicion() {
        this.rt = Runtime.getRuntime();
    }
    public void iniciarMedicion(){
        inicio = System.nanoTime();
        memoriaAntes = rt.totalMemory() - rt.freeMemory();
    }
    public void finMedicion(){
        memoriaDespues = rt.totalMemory() - rt.freeMemory();
        memoriaUsada = memoriaDespues- memoriaAntes;
        
        fin = System.nanoTime();
        tiempo = fin - inicio;
        
        System.out.println("Tiempo: " + tiempo + " nanosegundos");
        System.out.println("Memoria usada (bytes): " + memoriaUsada);
        System.out.println("Memoria usada (KB): " + (memoriaUsada / 1024.0));
        System.out.println("Memoria usada (MB): " + (memoriaUsada / 1024.0 / 1024.0));
    }
    
}
