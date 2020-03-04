package Chess;

public class Knight extends Piece {

    public Knight() {
        this.name = "Knight";
        this.PIC_URL = this.PIC_URL + this.name + ".png";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean move() {
        return false;
    }

}
