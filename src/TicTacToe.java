import java.io.*;
import java.sql.Array;

public class TicTacToe {
    private final int sizeX;
    private final int sizeY;

    private Cell[][] board;

    TicTacToe(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Cell[sizeY][sizeX];
            for(int i=0; i < board.length; ++i)
                for(int j=0; j < board[i].length; ++j)
                    board[i][j] = new Cell();
    }

    public void display(){
        for(Cell[] celly : this.board){
            for(Cell cellx : celly)
                System.out.print(cellx.getRepresentation());
            System.out.print("|");
            System.out.println();
        }
    }
}
