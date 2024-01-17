import java.util.Scanner;

public class TicTacToe {
    private final int sizeX;
    private final int sizeY;

    private Cell[][] board;

    private final Player p1;
    private final Player p2;
    private Player winner = null;

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

            // change current player
            current = (current == p1) ? p2 : p1;

            do {    // Ask cell
                move = getMoveFromPlayer(current);
                if(isValidMove(move))
                    break;
                else
                    System.out.println("Invalid cell try again");
            } while (true);

            // set owner
            this.board[move[1]][move[0]].setOwner(current);
        } while(!isEnd(move, current));

        display();
        if(winner != null)
            System.out.printf("Winner %c !!!\n", winner.getRepresentation());
        else
            System.out.println("Draw !");
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

    public void wrapOnBoard(int[] move){
        move[0] = Math.max(Math.min(move[0], sizeX-1), 0);
        move[1] = Math.max(Math.min(move[1], sizeY-1), 0);
    }

    public Boolean isValidMove(int[] move) {
        // Is on board ?
        if(!isOnBoard(move))
            return false;
        // Is owned ?
        if(this.board[move[1]][move[0]].getOwner() != null)
            return false;

        return true;
    }

    public Boolean isEnd(int[] move, Player p){
        Boolean a = checkDir(move, new int[]{1, 1}, 3);
        Boolean b = checkDir(move, new int[]{1, 0}, 3);
        Boolean c = checkDir(move, new int[]{0, 1}, 3);
        Boolean d = checkDir(move, new int[]{1, -1}, 3);

        if(a || b || c || d) {
            winner = p;
            return true;
        } else
            return checkfull();
    }

    private Boolean checkDir(int[] move, int[] dir, int consecutive) {
        int[] cpy = {move[0], move[1]};
        int[] next = {move[0]+dir[0], move[1]+dir[1]};

        // follow owner in dir
        Player owner = this.board[move[1]][move[0]].getOwner();
        while(isOnBoard(next) && this.board[next[1]][next[0]].getOwner() == owner){
            cpy[0] = next[0];
            cpy[1] = next[1];
            next[0] = next[0]+dir[0];
            next[1] = next[1]+dir[1];
        }

        //reverse dir
        dir[0] *= -1;
        dir[1] *= -1;

        for(int i=0; i < consecutive; ++i){
            if(!isOnBoard(cpy))
                return false;
            if(this.board[cpy[1]][cpy[0]].getOwner() != owner)
                return false;
            cpy[0] += dir[0];
            cpy[1] += dir[1];
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
