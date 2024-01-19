public abstract class Player implements PlayerInterface {
    public final char representation;

    public Player(char representation) {
        this.representation = representation;
    }

    public final char getRepresentation(){
        return representation;
    }
}
