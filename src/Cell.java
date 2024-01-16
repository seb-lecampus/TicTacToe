public class Cell {
    Player owner;
    Cell(){

    }

    public String getRepresentation(){
        char representation = (owner != null) ? owner.getRepresentation() : ' ';
        return "| "+ representation +" ";
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
