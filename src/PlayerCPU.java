public class PlayerCPU extends Player {

    public PlayerCPU(){
        super(PlayerCPU.askRepresentation());
    }
    @Override
    public int[] getMoveFromPlayer() {
        return new int[]{(int)(Math.random()*3), (int)(Math.random()*3)};
    }

    public static char askRepresentation() {
        return (char)(Math.random()*255);
    }
}
