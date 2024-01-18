import java.util.Scanner;

public class Main {
    public static void main(String[] Args){
        TicTacToe ticTacToe = new TicTacToe(4, 4, false, true);
        ticTacToe.play();
    }
}