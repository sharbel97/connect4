import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Themes {
    public int activeTheme = 0;// 0 default , 1 = theme1, 2 = theme2

    public Background originalColor;
    public String originalPlayer1Color ="-fx-color: red;";
    public String originalPlayer2Color = "-fx-color: yellow;";

    public Background theme1Color;
    public String theme1Player1Color ="-fx-color: purple";
    public String theme1Player2Color = "-fx-color: green";

    public Background theme2Color;
    public String theme2Player1Color ="-fx-color: pink";
    public String theme2Player2Color = "-fx-color: black";

    public Themes() {
        originalColor = new Background(new BackgroundFill(Color.AQUAMARINE,null,null ));
        theme1Color = new Background(new BackgroundFill(Color.BISQUE,null,null ));
        theme2Color = new Background(new BackgroundFill(Color.CORAL,null,null ));
    }

    public void updateButton(GameButton b, int player) {
        b.player = player;
        b.clicked = true;
        if (activeTheme == 0) {
            if (player == 1) {
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        originalPlayer1Color);
            }
            if (player == 2){
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        originalPlayer2Color);
            }
        }
        if (activeTheme == 1) {
            if (player == 1) {
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        theme1Player1Color);
            }
            if (player == 2) {
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        theme1Player2Color);
            }
        }

        if (activeTheme == 2) {
            if (player==1) {
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        theme2Player1Color);
            }

            if (player ==2 ){
                b.setStyle("-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        theme2Player2Color);
            }
        }
    }

    public void activateOriginal(BorderPane start, BorderPane play, GridPane grid){
        if (activeTheme != 0) {
            start.setBackground(originalColor);
            play.setBackground(originalColor);

            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                GameButton b = (GameButton) node;
                if (b.player==1) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            originalPlayer1Color);
                }
                if (b.player==2) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            originalPlayer2Color);
                }
            }
            activeTheme = 0;
        }
    }

    public void activateTheme1(BorderPane start, BorderPane play, GridPane grid) {
        if (activeTheme!=1) {
            start.setBackground(theme1Color);
            play.setBackground(theme1Color);
            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                GameButton b = (GameButton) node;
                if (b.player==1) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            theme1Player1Color);
                }
                if (b.player==2) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            theme1Player2Color);
                }
            }

            activeTheme = 1;
        }
    }

    public void activateTheme2(BorderPane start, BorderPane play, GridPane grid) {
        if (activeTheme!=2) {
            start.setBackground(theme2Color);
            play.setBackground(theme2Color);

            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                GameButton b = (GameButton) node;
                if (b.player==1) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            theme2Player1Color);
                }
                if (b.player==2) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            theme2Player2Color);
                }
            }
            activeTheme = 2;
        }
    }
    public void HighlightTheme2(BorderPane start, BorderPane play, GridPane grid) {
        if (activeTheme!=2) {
            start.setBackground(theme2Color);
            play.setBackground(theme2Color);

            ObservableList<Node> children = grid.getChildren();
            for (Node node : children) {
                GameButton b = (GameButton) node;
                if (b.player==1) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            "-fx-color: blue;");
                }
                if (b.player==2) {
                    b.setStyle("-fx-min-width: 75px; " +
                            "-fx-min-height: 75px; " +
                            "-fx-max-width: 75px; " +
                            "-fx-max-height: 75px;" +
                            "\"-fx-color: red;\"");
                }
            }
            activeTheme = 2;
        }
    }
}
