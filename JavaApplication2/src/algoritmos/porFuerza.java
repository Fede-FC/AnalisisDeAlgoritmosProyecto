
package algoritmos;

import conceptos.Pieza;
import conceptos.Puzzle;


public class porFuerza {
    Puzzle puzzle;
    int c, a;
    public porFuerza() {
    }
    
    public void resolver(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.c = 0; this.a = 0;
        this.solve(0);
    }
    public void printInfo(){
        System.out.println("Realizo "+ this.c+" comparaciones y " + this.a+" asignaciones");
    }
    
    boolean solve(int pos){ 
        c++;
        if (pos == this.puzzle.getSize()*this.puzzle.getSize())
            return true;
        int row = puzzle.getRow(pos); int col = puzzle.getCol(pos);
        a+=2;
        for (int i = 0; i < this.puzzle.getPiezas().size(); i++){ 
            c++;
            if (this.puzzle.conocerUsed(i)) continue;
            
            Pieza piece = this.puzzle.getPiezas().get(i); a++;
            a++;
            
            c++;
            if (this.puzzle.isValid(row, col, piece)){ 
                this.puzzle.colocarPieza(row, col, piece); 
                this.puzzle.definirUsed(i, true); 
                a+=2;
                
                c++;
                if (solve(pos+ 1)) return true; 

                this.puzzle.quitarPieza(row, col); a++;
                this.puzzle.definirUsed(i, false); a++;
                a+=2;
            }
        }
        return false;
    }
        
        
    
    
    
}
