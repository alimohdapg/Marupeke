import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Optional;

public class MarupekeGUI extends Application {

    private static MarupekeGrid marupekeGrid;
    private Stage currentStage;
    private GridPane gridPane;
    private boolean isDarkMode;
    private boolean unmark;
    private AudioClip changeTile;
    private AudioClip error;
    private AudioClip puzzleComplete;

    public static void main(String[] args) throws Exception {
        if (Integer.parseInt(args[0]) < 3 || Integer.parseInt(args[0]) > 10) {
            throw new Exception("IllegalGridSize");
        }
        if (args.length == 1) {
            switch (Integer.parseInt(args[0])) {
                case 3:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(3, 2, 1, 1);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(4, 2, 3, 3);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(5, 4, 4, 4);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(6, 6, 6, 6);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(7, 8, 8, 8);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(8, 10, 10, 10);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(9, 13, 13, 13);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case 10:
                    try {
                        marupekeGrid = MarupekeGrid.randomPuzzle(10, 16, 16, 16);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
            }
        } else {
            marupekeGrid = MarupekeGrid.randomPuzzle(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        currentStage = primaryStage;
        BorderPane root = new BorderPane();
        gridPane = new GridPane();
        updateGrid(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color:#000080");

        VBox timeAndGenerate = new VBox();
        createTimeAndGeneratePane(timeAndGenerate);

        VBox vBox = new VBox();
        createVbox(vBox);

        root.setRight(vBox);
        root.setCenter(gridPane);
        root.setTop(timeAndGenerate);
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.setTitle("Marupeke");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("Press play again to play again with the same size!");
            ButtonType playAgain = new ButtonType("Play Again");
            ButtonType exit = new ButtonType("Exit");
            ButtonType cancel = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(playAgain, exit, cancel);
            alert.initOwner(root.getScene().getWindow());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == playAgain) {
                switch (marupekeGrid.getMarupekeTiles().length) {
                    case 3:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(3, 2, 1, 1);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(4, 2, 3, 3);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(5, 4, 4, 4);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(6, 6, 6, 6);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(7, 8, 8, 8);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(8, 10, 10, 10);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(9, 13, 13, 13);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 10:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(10, 16, 16, 16);
                            updateGrid(gridPane);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                }
                e.consume();
            } else if (result.isPresent() && result.get() == exit) {
                primaryStage.close();
            } else if (result.isPresent() && result.get() == cancel) {
                alert.close();
                e.consume();
            }
        });
        primaryStage.show();
    }

    private void createTimeAndGeneratePane(VBox timeAndGenerate) {
        timeAndGenerate.setAlignment(Pos.CENTER_RIGHT);
        timeAndGenerate.setSpacing(1);
        timeAndGenerate.setPadding(new Insets(0, 0, 2, 0));

        Label label1 = new Label(" Generate new puzzle: ");
        label1.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        label1.setTextFill(Paint.valueOf("#FFFFFF"));
        ComboBox<Integer> validSizes = new ComboBox<>();
        validSizes.getItems().addAll(3, 4, 5, 6, 7, 8, 9, 10);
        validSizes.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        Button generateButton = new Button("GENERATE!!!");
        generateButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        generateButton.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        generateButton.setTextFill(Paint.valueOf("#000000"));
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.visibleProperty().bind(generateButton.pressedProperty());
        generateButton.setOnAction(e -> {
            if(validSizes.getSelectionModel().getSelectedItem() != null) {
                int gridSize = validSizes.getSelectionModel().getSelectedItem();
                switch (gridSize) {
                    case 3:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(3, 2, 1, 1);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(4, 2, 3, 3);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(5, 4, 4, 4);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(6, 6, 6, 6);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(7, 8, 8, 8);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(8, 10, 10, 10);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(9, 13, 13, 13);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 10:
                        try {
                            marupekeGrid = MarupekeGrid.randomPuzzle(10, 16, 16, 16);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                }
                updateGrid(gridPane);
            }
        });

        TextField elapsedSeconds = new TextField("0");
        TextField elapsedMinutes = new TextField("0");
        TextField elapsedHours = new TextField("0");
        elapsedSeconds.setMaxWidth(50);
        elapsedMinutes.setMaxWidth(50);
        elapsedHours.setMaxWidth(50);
        elapsedSeconds.setEditable(false);
        elapsedMinutes.setEditable(false);
        elapsedHours.setEditable(false);
        elapsedSeconds.setAlignment(Pos.CENTER);
        elapsedMinutes.setAlignment(Pos.CENTER);
        elapsedHours.setAlignment(Pos.CENTER);
        elapsedSeconds.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        elapsedMinutes.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        elapsedHours.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        AnimationTimer timer = new AnimationTimer() {
            private long currentTime;
            private long time = 0;
            private long leftOverTime = 0;

            @Override
            public void start() {
                currentTime = System.currentTimeMillis() - leftOverTime;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                leftOverTime = System.currentTimeMillis() - currentTime;
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (currentTime + 1000 <= newTime) {
                    long deltaT = (newTime - currentTime) / 1000;
                    time += deltaT;
                    currentTime += 1000 * deltaT;
                    elapsedSeconds.setText(Long.toString(time % 60));
                }
            }
        };
        timer.start();
        elapsedSeconds.textProperty().addListener((obs, oldValue, newValue) -> {
            if (Integer.parseInt(newValue) % 60 == 0) {
                int i = Integer.parseInt(elapsedMinutes.getText()) + 1;
                elapsedMinutes.setText(String.valueOf(i));
            }
        });
        elapsedMinutes.textProperty().addListener((obs, oldValue, newValue) -> {
            if (Integer.parseInt(newValue) % 60 == 0) {
                int i = Integer.parseInt(elapsedHours.getText()) + 1;
                elapsedHours.setText(String.valueOf(i));
            }
        });
        Label label2 = new Label(" Elapsed Time: ");
        label2.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        label2.setTextFill(Paint.valueOf("#FFFFFF"));
        Label label3 = new Label(" S");
        label3.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        label3.setTextFill(Paint.valueOf("#FFFFFF"));
        Label label4 = new Label(" M");
        label4.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        label4.setTextFill(Paint.valueOf("#FFFFFF"));
        Label label5 = new Label("H");
        label5.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        label5.setTextFill(Paint.valueOf("#FFFFFF"));
        Label space = new Label("   ");
        Button start = new Button("\u25B6");
        start.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 16));
        start.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        start.setTextFill(Paint.valueOf("#000000"));
        start.setOnAction(e -> timer.start());
        Button stop = new Button("\u23F8");
        stop.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 16));
        stop.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        stop.setTextFill(Paint.valueOf("#000000"));
        stop.setOnAction(e -> timer.stop());

        HBox hBox1 = new HBox();
        hBox1.setSpacing(3);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.getChildren().addAll(label1, validSizes, generateButton, progressIndicator);
        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.getChildren().addAll(label2, label5, elapsedHours, label4, elapsedMinutes, label3, elapsedSeconds, space, start, stop);
        timeAndGenerate.getChildren().addAll(hBox2, hBox1);
        timeAndGenerate.setStyle("-fx-background-color:#000080");
    }

    private void createVbox(VBox vBox) {
        Button eraser = new Button("\u232B");
        Button darkMode = new Button("Dark Mode");
        Button lightMode = new Button("Light Mode");
        Button helpMenu = new Button("Help");
        eraser.setStyle("-fx-background-color:#303030; -fx-border-color:#FFFFFF;");
        eraser.setTextFill(Paint.valueOf("#FFFFFF"));
        eraser.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        darkMode.setStyle("-fx-background-color:#303030; -fx-border-color:#FFFFFF;");
        darkMode.setTextFill(Paint.valueOf("#FFFFFF"));
        darkMode.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        lightMode.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        lightMode.setTextFill(Paint.valueOf("#000000"));
        lightMode.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        helpMenu.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030;");
        helpMenu.setTextFill(Paint.valueOf("#000000"));
        helpMenu.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        eraser.setOnAction(e -> unmark = true);
        darkMode.setOnAction(e -> {
            isDarkMode = true;
            updateGrid(gridPane);
        });
        lightMode.setOnAction(e -> {
            isDarkMode = false;
            updateGrid(gridPane);
        });
        helpMenu.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help Menu");
            alert.setHeaderText("How To Play:");
            alert.setContentText("How To Win:\nFill in each empty cell with either an O or an X," +
                    " so that no more than two consecutive tiles, either horizontally, vertically" +
                    " or diagonally, contain the same symbol.\n\nNon-Editable Tiles:\nThe " +
                    "Non-editable tiles are the darker tiles in each mode:\n   \u25CF Very " +
                    "dark grey in dark mode.\n   \u25CF Light grey in light mode.\n\nEditable" +
                    " Tiles:\nWithin each respective mode, the lighter-colored tiles are all" +
                    " editable tiles.\n\nControls:\n> Right click an editable tile to mark it" +
                    " with an O.\n> Left click an editable tile to mark it with an X.\n> Press" +
                    " the unmark button and choose an\n    editable tile to unmark it.\n\n");
            alert.initOwner(helpMenu.getParent().getScene().getWindow());
            alert.show();
        });

        vBox.setSpacing(2);
        vBox.getChildren().addAll(eraser, darkMode, lightMode, helpMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color:#000080");
    }

    private void updateGrid(GridPane gridPane) {
        gridPane.getChildren().clear();
        for (int i = 0; i < marupekeGrid.getMarupekeTiles().length; i++) {
            for (int j = 0; j < marupekeGrid.getMarupekeTiles().length; j++) {
                Button button;
                if (marupekeGrid.getSquareSymbol(i, j) == '_') {
                    button = new Button(" ");
                } else {
                    button = new Button(String.valueOf(marupekeGrid.getSquareSymbol(i, j)));
                }
                if (marupekeGrid.isTileEditable(i, j)) {
                    int finalI = i;
                    int finalJ = j;
                    button.setOnMouseClicked(e ->
                    {
                        changeTile = new AudioClip(getClass().getResource("changeTile.mp3").toExternalForm());
                        changeTile.setCycleCount(1);
                        changeTile.play();
                        if (unmark) {
                            button.setText(" ");
                            marupekeGrid.unMark(finalI, finalJ);
                            unmark = false;
                        } else {
                            if (e.getButton() == MouseButton.PRIMARY) {
                                button.setText("X");
                                marupekeGrid.markX(finalI, finalJ);
                            }
                            if (e.getButton() == MouseButton.SECONDARY) {
                                button.setText("O");
                                marupekeGrid.markO(finalI, finalJ);
                            }
                            if (!marupekeGrid.isLegalGrid() && !marupekeGrid.isFilled()) {
                                error = new AudioClip(getClass().getResource("error.mp3").toExternalForm());
                                error.setCycleCount(1);
                                error.play();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Illegal Grid");
                                alert.setHeaderText("A placed symbol resulted in an illegal grid!");
                                alert.setContentText("Consider changing the symbol.");
                                alert.initOwner(button.getParent().getScene().getWindow());
                                alert.showAndWait();
                            }
                        }
                        if (marupekeGrid.isFilled()) {
                            if (marupekeGrid.isPuzzleComplete()) {
                                puzzleComplete = new AudioClip(getClass().getResource("puzzleComplete.mp3").toExternalForm());
                                puzzleComplete.setCycleCount(1);
                                puzzleComplete.play();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Puzzle Complete");
                                alert.setHeaderText("Congratulations!");
                                alert.setContentText("Do you want to play again?");
                                alert.initOwner(button.getParent().getScene().getWindow());
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    switch (marupekeGrid.getMarupekeTiles().length) {
                                        case 3:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(3, 2, 1, 1);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 4:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(4, 2, 3, 3);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 5:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(5, 4, 4, 4);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 6:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(6, 6, 6, 6);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 7:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(7, 8, 8, 8);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 8:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(8, 10, 10, 10);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 9:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(9, 13, 13, 13);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 10:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(10, 16, 16, 16);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                    }
                                }
                            } else {
                                error = new AudioClip(getClass().getResource("error.mp3").toExternalForm());
                                error.setCycleCount(1);
                                error.play();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Puzzle Filled Illegally");
                                alert.setHeaderText("Better luck next time!");
                                alert.setContentText("Do you want to play again?");
                                alert.initOwner(button.getParent().getScene().getWindow());
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    switch (marupekeGrid.getMarupekeTiles().length) {
                                        case 3:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(3, 2, 1, 1);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 4:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(4, 2, 3, 3);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 5:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(5, 4, 4, 4);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 6:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(6, 6, 6, 6);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 7:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(7, 8, 8, 8);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 8:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(8, 10, 10, 10);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 9:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(9, 13, 13, 13);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                        case 10:
                                            try {
                                                marupekeGrid = MarupekeGrid.randomPuzzle(10, 16, 16, 16);
                                                updateGrid(gridPane);
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    });
                }
                if (isDarkMode) {
                    if (marupekeGrid.isTileEditable(i, j)) {
                        button.setStyle("-fx-background-color:#303030; -fx-border-color:#FFFFFF; -fx-font-size:30;");
                    } else {
                        button.setStyle("-fx-background-color:#181818; -fx-border-color:#FFFFFF; -fx-font-size:30;");
                    }
                    button.setTextFill(Paint.valueOf("#FFFFFF"));
                }
                if (!isDarkMode) {
                    if (marupekeGrid.isTileEditable(i, j)) {
                        button.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#303030; -fx-font-size:30;");
                    } else {
                        button.setStyle("-fx-background-color:#F0F0F0; -fx-border-color:#303030; -fx-font-size:30;");
                    }
                    button.setTextFill(Paint.valueOf("#000000"));
                }
                button.setMinSize(85, 85);
                gridPane.add(button, j, i);
            }
        }
        currentStage.sizeToScene();
    }
}