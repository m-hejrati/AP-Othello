public class Player {

    private String name;
    private int colorCode;
    // white: 1, black: 0;

    public Player(String name, int colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }
}
