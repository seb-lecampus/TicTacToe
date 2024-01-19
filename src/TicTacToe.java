public class TicTacToe {
    private final int sizeX;
    private final int sizeY;

    private Cell[][] board;

    private final Player p1;
    private final Player p2;
    private Player winner = null; // todo not player type ?

    private View view = new View();
    private InteractionUtilisateur userInterface = new InteractionUtilisateur(view);

    TicTacToe(int sizeX, int sizeY, boolean player1_is_human, boolean player2_is_human) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Cell[sizeY][sizeX];
            for(int i=0; i < board.length; ++i)
                for(int j=0; j < board[i].length; ++j)
                    board[i][j] = new Cell();
        this.p1 = (player1_is_human) ? new PlayerHuman(userInterface, userInterface.askRepresentation()) : new PlayerCPU(view);
        this.p2 = (player2_is_human) ? new PlayerHuman(userInterface, userInterface.askRepresentation()) : new PlayerCPU(view);
    }

    public void play(){
        Player current = p1;

        int[] move;
        do {
            view.display_board(board);

            // change current player
            current = (current == p1) ? p2 : p1;

            do {    // Ask cell
                move = current.getMoveFromPlayer();
                if(isValidMove(move))
                    break;
                else
                    view.warn_invalid_cell();
            } while (true);

            // set owner
            this.board[move[1]][move[0]].setOwner(current);
        } while(!isEnd(move, current));

        view.display_board(board);
        view.inform_winner(winner);
    }

    protected boolean isOnBoard(int[] move){
        int x = move[0];
        int y = move[1];

        if(!(x >= 0 && x < sizeX))
            return false;
        if(!(y >= 0 && y < sizeY))
            return false;

        return true;
    }

    protected void wrapOnBoard(int[] move){
        move[0] = Math.max(Math.min(move[0], sizeX-1), 0);
        move[1] = Math.max(Math.min(move[1], sizeY-1), 0);
    }

    protected boolean isValidMove(int[] move) {
        // Is on board ?
        if(!isOnBoard(move))
            return false;
        // Is owned ?
        if(this.board[move[1]][move[0]].getOwner() != null)
            return false;

        return true;
    }

    /**
     * check if the game can exit
     * @param move
     * @param p
     * @return true if there is a winner of a draw
     */
    protected boolean isEnd(int[] move, Player p){
        boolean a = checkDir(move, new int[]{1, 1}, 3);
        boolean b = checkDir(move, new int[]{1, 0}, 3);
        boolean c = checkDir(move, new int[]{0, 1}, 3);
        boolean d = checkDir(move, new int[]{1, -1}, 3);

        if(a || b || c || d) {
            winner = p;
            return true;
        } else
            return checkfull();
    }

    /**
     * jrso^thijtsroij
     * @param move a player movement
     * @param dir vector like direction. Added to move each iteration.
     * @param consecutive number of consecutive cell to win
     * @return true if okrighh
     */
    protected boolean checkDir(int[] move, int[] dir, int consecutive) {
        int[] cpy = {move[0], move[1]}; // copy of move
        int[] next = {move[0]+dir[0], move[1]+dir[1]}; // next cell in the dir

        // follow owner in the dir
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

        // own sufficient consecutive cell in the dir  ?
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

    /**
     * check if board is full
     * @return true if board is full, else false
     */
    protected boolean checkfull() {
        for(var line : this.board)
            for(Cell cell : line)
                if(cell.getOwner() == null)
                    return false;
        return true;
    }
}
