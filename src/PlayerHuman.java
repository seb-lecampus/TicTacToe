public class PlayerHuman extends Player {
    private InteractionUtilisateur userInterface;
    public PlayerHuman(InteractionUtilisateur userInterface, char representation) {
        super(representation);
        this.userInterface = userInterface;
    }

    @Override
    public int[] getMoveFromPlayer() {
        return userInterface.askMoveFromPlayer(this);
    }
}
