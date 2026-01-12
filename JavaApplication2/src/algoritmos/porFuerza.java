/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import conceptos.Pieza;
import conceptos.Puzzle;

/**
 *
 * @author feder
 */
public class porFuerza {
    Puzzle puzzle;

    public porFuerza(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.solve(0);
    }
    boolean solve(int pos){
        if (pos == this.puzzle.getSize()*this.puzzle.getSize())
            return true;
        int row = puzzle.getRow(pos); int col = puzzle.getCol(pos);
        for (int i = 0; i < this.puzzle.getPiezas().size(); i++){
            
            if (this.puzzle.conocerUsed(i)) continue;
            
            Pieza piece = this.puzzle.getPiezas().get(i);
            
            
            if (this.puzzle.isValid(row, col, piece)){
                this.puzzle.colocarPieza(row, col, piece);
                this.puzzle.definirUsed(pos, true);

                if (solve(pos+ 1)) return true;

                this.puzzle.quitarPieza(row, col);
                this.puzzle.definirUsed(pos, false);

            }
        }
        return false;
    }
        
        
    
    
    
}
