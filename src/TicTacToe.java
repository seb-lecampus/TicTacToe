import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final int sizeX;
    private final int sizeY;

    private Cell[][] board;

    private final Player p1;
    private final Player p2;

    TicTacToe(int sizeX, int sizeY, Player p1, Player p2) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Cell[sizeY][sizeX];
            for(int i=0; i < board.length; ++i)
                for(int j=0; j < board[i].length; ++j)
                    board[i][j] = new Cell();
        this.p1 = p1;
        this.p2 = p2;
    }

    public void play(){
        Player current = p1;

        int[] move;
        do {
            display();
            do {
                move = getMoveFromPlayer(current);
                if(isValidMove(move))
                    break;
                else
                    System.out.println("Invalid case try again");
            } while (true);

            this.board[move[1]][move[0]].setOwner(current); // set owner
            current = (current == p1) ? p2 : p1;            // change current player
        } while(isEnd(move));
    }

    public void display(){
        for(Cell[] line : this.board){
            for(Cell cell : line)
                System.out.print(cell.getRepresentation());
            System.out.print("|");
            System.out.println();
        }
    }

    public int[] getMoveFromPlayer(Player p){
        int x, y;
        Scanner input = new Scanner(System.in);

        System.out.printf("Joueur %c coup x: ", p.getRepresentation());
        x = input.nextInt();
        System.out.printf("Joueur %c coup y: ", p.getRepresentation());
        y = input.nextInt();

        return new int[]{x, y};
    }

    public Boolean isOnBoard(int[] move){
        int x = move[0];
        int y = move[1];

        if(!(x >= 0 && x < sizeX))
            return false;
        if(!(y >= 0 && y < sizeY))
            return false;

        return true;
    }

    public Boolean isValidMove(int[] move) {
        // Is on board ?
        isOnBoard(move);
        // Is owned ?
        if(this.board[move[1]][move[0]].getOwner() != null)
            return false;

        return true;
    }

    public Boolean isEnd(int[] move){
        if(checkfull()){
            System.out.println("execo");
            return false;
        }
        return true;
    }

    private Boolean checkDir(int[] move, int[] dir, int consecutive) {
        Player owner = this.board[move[1]][move[0]].getOwner();
        for(int i=0; i < consecutive; ++i){
            if(!isOnBoard(move))
                return false;
            if(this.board[move[1]][move[0]].getOwner() != owner)
                return false;
            move[0] += dir[0];
            move[1] += dir[1];
        }
        return true;
    }

    private Boolean checkfull() {
        for(var line : this.board)
            for(Cell cell : line)
                if(cell.getOwner() == null)
                    return false;
        return true;
    }
}
