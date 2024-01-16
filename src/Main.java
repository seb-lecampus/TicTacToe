import java.util.Scanner;

public class Main {
    public static void main(String[] Args){
        Scanner input = new Scanner(System.in);

        System.out.println("Joueur 1: ");
        char p1 = input.nextLine().charAt(0);
        System.out.println("Joueur 2: ");
        char p2 = input.nextLine().charAt(0);

        TicTacToe ticTacToe = new TicTacToe(3, 3, new Player(p1), new Player(p2));
        ticTacToe.play();
    }
}