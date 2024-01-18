import java.util.Scanner;

public class PlayerHuman extends Player {
    PlayerHuman() {
        super(PlayerHuman.askRepresentation());
    }

    @Override
    public int[] getMoveFromPlayer() {
        int x, y;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.printf("Joueur %c coup x: ", getRepresentation());

                x = input.nextInt();
                break;
            } catch (Exception e){
                System.out.println("only number"); input.next();
            }
        }while(true);

        do {
            try {
                System.out.printf("Joueur %c coup y: ", getRepresentation());
                y = input.nextInt();
                break;
            } catch (Exception e){
                System.out.println("only number"); input.next();
            }
        }while(true);

        return new int[]{x, y};
    }

    public static char askRepresentation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your character : ");
        return input.nextLine().charAt(0);
    }
}
