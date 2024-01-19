public class Cell {
    private Player owner;
    public Cell(){

    }

    /**
     * give the cell representation
     * @return  String representing the cell
     */
    public String getRepresentation(){
        char representation = (owner != null) ? owner.getRepresentation() : ' ';
        return "| "+ representation +" ";
    }

    /**
     * Set the owner of the cell
     * @param owner Player will own the cell
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * give owner of the cell
     * @return Player who own the cell, else null
     */
    public Player getOwner() {
        return owner;
    }
}
