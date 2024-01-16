public class TicTacToe {
    private final int sizeX;
    private final int sizeY;

    private Cell[][] board;

    private final Player p1;

    TicTacToe(int sizeX, int sizeY, Player p1) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Cell[sizeY][sizeX];
            for(int i=0; i < board.length; ++i)
                for(int j=0; j < board[i].length; ++j)
                    board[i][j] = new Cell();
        this.p1 = p1;
    }

    public void play(){
        display();
    }

    public void display(){
        for(Cell[] line : this.board){
            for(Cell cell : line)
                System.out.print(cell.getRepresentation());
            System.out.print("|");
            System.out.println();
        }
    }

    public int[] getMoveFromPlayer(int x, int y){
        // Is on board ?
        x = (x >= 0 && x < sizeX) ? x : -1;
        y = (y >= 0 && y < sizeY) ? y : -1;
        // Is owned ?
        return (this.board[y][x].getOwner() == null) ? new int[]{x, y} : new int[]{-1, -1};
    }
}
