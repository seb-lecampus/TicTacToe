import java.util.Scanner;

public class View {
    public View(){};
    public void display_board(Cell[][] board){
        for(Cell[] line : board){
            for(Cell cell : line)
                System.out.print(cell.getRepresentation());
            System.out.print("|");
            System.out.println();
        }
    }

    public void ask_for_player_representation(){
        System.out.println("Choose your character : ");
    }

    public void ask_coordinate_for_player(char coordinate, Player p){
        System.out.printf("Player %c coord %c: ", p.getRepresentation(), coordinate);
    }

    public void warn_number_only(){
        System.out.println("only number");
    }
    public void warn_invalid_cell() {
        System.out.println("Invalid cell try again");
    }

    public void inform_cpu_representation(PlayerCPU p){
        System.out.printf("CPU choose %c\n", p.getRepresentation());
    }

    public void inform_winner(Player p) {
        if (p != null)
            System.out.printf("Winner %c !!!\n", p.getRepresentation());
        else
            System.out.println("Draw !");
    }
}
