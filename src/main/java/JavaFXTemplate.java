import javafx.animation.PauseTransition;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Stack;

// The main JavaFX template for our Connect4 logic game

public class JavaFXTemplate extends Application {
	private Board board;
	private int Player = 1;

	private MenuBar menu;
	private Scene playScene;
	private static Stage primaryStage;

	private BorderPane borderPaneStartScene;
	private BorderPane borderPanePlayScene;

	private TextField outputArea;
	private TextField playermove;
	private GridPane grid;
	private Stack<GameButton> moves;

	private Themes themes;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Connect Four!");
		themes = new Themes();
		moves = new Stack<>();
		menu = buildMenu();

		Scene startScene = new Scene(startUI(primaryStage), 700, 700);
		startScene.getRoot().setStyle("-fx-font-family: 'serif'");

		playScene = new Scene(playUI(primaryStage), 700, 700);
		playScene.getRoot().setStyle("-fx-font-family: 'serif'");

		primaryStage.setScene(startScene);
		primaryStage.show();
	}

	private Alert HowToPlayAlert() {
		Alert howtoplayAlert = new Alert(Alert.AlertType.INFORMATION);
		howtoplayAlert.setTitle("How To Play");
		howtoplayAlert.setHeaderText("How to Play Info");
		String s = "This is how you play the game bla bla bla :)";
		howtoplayAlert.setContentText(s);
		return howtoplayAlert;
	}

	private MenuBar buildMenu() {
		Alert howtoplayAlert = HowToPlayAlert();

		menu = new MenuBar();
		Menu GamePlay = new Menu("Game Play");
		MenuItem reverse = new MenuItem("Reverse");
		reverse.setOnAction(actionEvent -> {
			if (!moves.empty()) {
				GameButton temp = moves.pop();
				temp.clicked = false;
				temp.player = -1;
				temp.setStyle("-fx-min-width: 75px; " +
						"-fx-min-height: 75px; " +
						"-fx-max-width: 75px; " +
						"-fx-max-height: 75px;");
				// update internal board, validMove
				board.reverseMove(temp.row, temp.col);
				Player = board.switchPlayer(Player);
			}
		});
		GamePlay.getItems().add(reverse);


		Menu Themes = new Menu("Themes");
		MenuItem Original = new MenuItem(("Original"));
		Original.setOnAction(actionEvent -> {
			themes.activateOriginal(borderPaneStartScene, borderPanePlayScene, grid);
		});
		MenuItem Theme1 = new MenuItem(("Theme 1"));
		Theme1.setOnAction(actionEvent -> {
			themes.activateTheme1(borderPaneStartScene, borderPanePlayScene, grid);
		});
		MenuItem Theme2 = new MenuItem(("Theme 2"));
		Theme2.setOnAction(actionEvent -> {
			themes.activateTheme2(borderPaneStartScene, borderPanePlayScene, grid);
		});
		Themes.getItems().add(Original);
		Themes.getItems().add(Theme1);
		Themes.getItems().add(Theme2);

		Menu Options = new Menu("Options");
		MenuItem Exit = new MenuItem("Exit");
		Exit.setOnAction(e -> Platform.exit());



		MenuItem HowToPlay = new MenuItem("How To Play");
		HowToPlay.setOnAction(actionEvent -> {
			howtoplayAlert.show();
		});
		MenuItem NewGame = new MenuItem("New Game");
		NewGame.setOnAction(actionEvent->{
			board.newGame();
			grid.getChildren().clear();
			grid = buildGrid(board, outputArea, playermove,primaryStage);
			grid.setHgap(3); // spacing between buttons
			grid.setVgap(3); // spacing between buttons
			grid.setAlignment(Pos.CENTER);
			borderPanePlayScene.setCenter(grid);
			moves.clear();
			Player=1;
			//
		});
		Options.getItems().add(HowToPlay);
		Options.getItems().add(NewGame);
		Options.getItems().add(Exit);

		menu.getMenus().addAll(GamePlay, Themes, Options);
		return menu;
	}

	private BorderPane startUI(Stage stage) {
		Button startGameButton = new Button("Start Game");
		startGameButton.setOnAction(e -> stage.setScene(playScene));
		borderPaneStartScene = new BorderPane();
		borderPaneStartScene.setBackground(themes.originalColor);
		borderPaneStartScene.setPadding(new Insets(10));
		borderPaneStartScene.setTop(menu);
		borderPaneStartScene.setCenter(startGameButton);
		return borderPaneStartScene;
	}

	private BorderPane playUI(Stage s) {
		outputArea = new TextField("Showing valid/invalid moves here");
		outputArea.setPrefWidth(300);
		playermove = new TextField("Player: "+ Player+ " Turn");
		outputArea.setDisable(true);
		playermove.setDisable(true);
		HBox outputAreas = new HBox(outputArea, playermove);
		outputAreas.setSpacing(20);
		outputAreas.setAlignment(Pos.BOTTOM_CENTER);
		borderPanePlayScene = new BorderPane();
		borderPanePlayScene.setBackground(themes.originalColor);
		borderPanePlayScene.setPadding(new Insets(10));
		borderPanePlayScene.setTop(buildMenu());
		board = new Board();
		grid = buildGrid(board, outputArea,playermove,s);
		grid.setHgap(3); // spacing between buttons
		grid.setVgap(3); // spacing between buttons
		grid.setAlignment(Pos.CENTER);
		borderPanePlayScene.setCenter(grid);
		borderPanePlayScene.setBottom(outputAreas);

		return borderPanePlayScene;
	}

	private GridPane buildGrid(Board board, TextField outputArea,TextField playermove, Stage prime) {
		GridPane localGrid = new GridPane();
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				GameButton b = new GameButton(row, col);
				b.setShape(new Circle(10));
				b.setStyle("-fx-min-width: 75px; " +
						"-fx-min-height: 75px; " +
						"-fx-max-width: 75px; " +
						"-fx-max-height: 75px;");
				BorderPane.setAlignment(b, Pos.CENTER);
				localGrid.add(b, col, row);
				b.setOnAction(actionEvent -> {
					if (board.validMove(b.row, b.col)) {
						board.move(b.row, b.col, Player);
						moves.push(b);
						outputArea.setText("Player: " + Player + " clicked button ["+b.row+"]["+b.col+"]");
						Player = board.switchPlayer(Player);
						playermove.setText("Player: " + Player + " Turn");
						themes.updateButton(b, Player);
						int winner = board.checkWinner();
						if(winner!=0) {
							localGrid.setMouseTransparent(true);
							BorderPane endPain = new BorderPane();
							Label endMethod = new Label();
							BorderPane.setAlignment(endMethod,Pos.TOP_CENTER);
							endMethod.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 40));
							PauseTransition pause = new PauseTransition(Duration.seconds(5));
							pause.play();
							pause.setOnFinished(actionEvent1 -> {
								if (winner == 1) { // player 1 wins
									endMethod.setText("Player1 Won");
								} else if (winner == 2) { // player 2 wins
									endMethod.setText("Player2 Won");
								}
								else if (moves.size()==42) { // no one wins
									endMethod.setText("No one Won");
								}
								endPain.setTop(endMethod);
								Scene endScene=new Scene(endPain,700,700);
								Button exit = new Button("Exit");
								exit.setOnAction(x -> Platform.exit());
								Button newgame2 = new Button("New Game");
								newgame2.setOnAction(x->{
									board.newGame();
									grid.getChildren().clear();
									grid = buildGrid(board, outputArea, playermove,prime);
									grid.setHgap(3); // spacing between buttons
									grid.setVgap(3); // spacing between buttons
									grid.setAlignment(Pos.CENTER);
									borderPanePlayScene.setCenter(grid);
									moves.clear();
									Player=1;
									prime.setScene(playScene);
									outputArea.setText("Showing valid/invalid moves here");
									playermove.setText("Player: " + Player + " Turn");
								});
								HBox h = new HBox(25,newgame2,exit);
								endPain.setCenter(h);
								h.setAlignment(Pos.CENTER);
								prime.setScene(endScene);
								prime.show();
							});
						}
					} else {
						outputArea.setText("Invalid Move! Player " + Player + " turn");
					}
				});
			}
		}
		return localGrid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}