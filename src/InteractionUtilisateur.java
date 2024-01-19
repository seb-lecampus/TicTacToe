import java.util.Scanner;

public class InteractionUtilisateur {
    private View view;
    private Scanner scanner = new Scanner(System.in);

    public InteractionUtilisateur(View view){
        this.view = view;
    }

    public char askRepresentation() {
        view.ask_for_player_representation();
        return scanner.nextLine().charAt(0);
    }

    public int[] askMoveFromPlayer(PlayerHuman p) {
        int x, y;


        do {
            try {
                view.ask_coordinate_for_player('X', p);

                x = scanner.nextInt();
                break;
            } catch (Exception e){
                view.warn_number_only();
            }
        }while(true);

        do {
            try {
                view.ask_coordinate_for_player('Y', p);
                y = scanner.nextInt();
                break;
            } catch (Exception e){
                view.warn_number_only(); scanner.next();
            }
        }while(true);

        return new int[]{x, y};
    }

    private static void clear_scanner(Scanner scanner){
        while(scanner.hasNext())
            scanner.next();
    }
}
