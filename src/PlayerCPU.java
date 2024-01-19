public class PlayerCPU extends Player {
    private View view;
    public PlayerCPU(View view){
        super((char)(Math.random()*255));
        this.view = view;
            view.inform_cpu_representation(this);
    }
    @Override
    public int[] getMoveFromPlayer() {
        view.inform_cpu_move(this);
        return new int[]{(int)(Math.random()*3), (int)(Math.random()*3)};
    }
}
