import javafx.scene.control.*;

public class GameButton extends Button {
    public int row;
    public int col;
    public int player;
    public boolean clicked;

    public GameButton(int i, int j) {
        row = i;
        col = j;
        player = -1;
        clicked = false;
    }
    // I made all the variables here public. easier to work with.
}