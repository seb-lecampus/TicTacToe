import java.util.Scanner;

public class InteractionUtilisateur {
    private View view;

    public InteractionUtilisateur(View view){
        this.view = view;
    }

    public char askRepresentation() {
        Scanner input = new Scanner(System.in);
        view.ask_for_player_representation();
        return input.nextLine().charAt(0);
    }

    public int[] askMoveFromPlayer(PlayerHuman p) {
        int x, y;
        Scanner input = new Scanner(System.in);

        do {
            try {
                view.ask_coordinate_for_player('X', p);

                x = input.nextInt();
                break;
            } catch (Exception e){
                view.warn_number_only(); input.next();
            }
        }while(true);

        do {
            try {
                view.ask_coordinate_for_player('Y', p);
                y = input.nextInt();
                break;
            } catch (Exception e){
                view.warn_number_only(); input.next();
            }
        }while(true);

        return new int[]{x, y};
    }
}
