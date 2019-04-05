package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;


public class Main extends Application {

    private File file = new File("Characters.csv");
    private BufferedWriter bw;
    private FileWriter fw;
    private BufferedReader br;
    private FileReader fr;
    private HBox hb = new HBox();

    private Scene tableScene;
    private Scene diceScene;

    private TableView<RPGCharacter> table = new TableView<RPGCharacter>();
    private ObservableList<RPGCharacter> data =
            FXCollections.observableArrayList(
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());

        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(600);

        final Label label = new Label("Dungeons and Dragons Characters");
        label.setFont(new Font("Lucida", 20));

        table.setEditable(true);

        /**
         * Until the next code block is code to create the columns
         *
         */

        TableColumn playerNameCol = new TableColumn("Player Name");
        playerNameCol.setMinWidth(115);
        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("player"));
        playerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        playerNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPlayer(t.getNewValue());
                    }
                }
        );

        TableColumn characterNameCol = new TableColumn("Character Name");
        characterNameCol.setMinWidth(115);
        characterNameCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("character"));
        characterNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        characterNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setCharacter(t.getNewValue());
                    }
                }
        );

        TableColumn raceCol = new TableColumn("Race");
        raceCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("race"));
        raceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        raceCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setRace(t.getNewValue());
                    }
                }
        );

        TableColumn subRaceCol = new TableColumn("Sub Race");
        subRaceCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("subRace"));
        subRaceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        subRaceCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setSubRace(t.getNewValue());
                    }
                }
        );

        TableColumn primaryClassCol = new TableColumn("Primary Class");
        primaryClassCol.setMinWidth(115);
        primaryClassCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("primaryClass"));
        primaryClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        primaryClassCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrimaryClass(t.getNewValue());
                    }
                }
        );

        TableColumn secondaryClassCol = new TableColumn("Secondary Class");
        secondaryClassCol.setMinWidth(115);
        secondaryClassCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("secondaryClass"));
        secondaryClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        secondaryClassCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setSecondaryClass(t.getNewValue());
                    }
                }
        );

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("status"));
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue());
                    }
                }
        );
        TableColumn locationCol = new TableColumn("Last Location");
        locationCol.setMinWidth(115);
        locationCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("lastLocation"));
        locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        locationCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastLocation(t.getNewValue());
                    }
                }
        );

        TableColumn dateCol = new TableColumn("Last Played");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("lastPlayed"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastPlayed(t.getNewValue());
                    }
                }
        );

        TableColumn levelCol = new TableColumn("Level");
        levelCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("level"));
        levelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        levelCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setLevel((t.getNewValue()));
                    }
                }
        );

        /**
         * The below code is to add ability scores into one column for characters.
         */

        TableColumn statsCol = new TableColumn("Ability Scores");

        TableColumn strCol = new TableColumn("STR");
        strCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_str"));
        strCol.setCellFactory(TextFieldTableCell.forTableColumn());
        strCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_str((t.getNewValue()));
                    }
                }
        );
        TableColumn dexCol = new TableColumn("DEX");
        dexCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_dex"));
        dexCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dexCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_dex((t.getNewValue()));
                    }
                }
        );
        TableColumn conCol = new TableColumn("STR");
        conCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_con"));
        conCol.setCellFactory(TextFieldTableCell.forTableColumn());
        conCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_con((t.getNewValue()));
                    }
                }
        );
        TableColumn intCol = new TableColumn("INT");
        intCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_int"));
        intCol.setCellFactory(TextFieldTableCell.forTableColumn());
        intCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_int((t.getNewValue()));
                    }
                }
        );
        TableColumn wisCol = new TableColumn("WIS");
        wisCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_wis"));
        wisCol.setCellFactory(TextFieldTableCell.forTableColumn());
        wisCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_wis((t.getNewValue()));
                    }
                }
        );
        TableColumn chaCol = new TableColumn("CHA");
        chaCol.setCellValueFactory(
                new PropertyValueFactory<RPGCharacter, String>("c_cha"));
        chaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        chaCol.setOnEditCommit(
                new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_cha((t.getNewValue()));
                    }
                }
        );

        statsCol.getColumns().addAll(strCol, dexCol, conCol, wisCol, intCol, chaCol);

        /**
         * The above code is to add ability scores into one column for characters.
         */

/**
 * From here and up is code that organizes the table columns.
 *
 */

        table.setItems(data);

        table.getColumns().addAll(playerNameCol, characterNameCol, raceCol, subRaceCol, primaryClassCol,
                secondaryClassCol, statusCol, locationCol, dateCol, levelCol, statsCol);

        final TextField addPlayerName = new TextField();
        addPlayerName.setPromptText("Player Name");
        addPlayerName.setMaxWidth(playerNameCol.getPrefWidth());

        final TextField addCharacterName = new TextField();
        addCharacterName.setMaxWidth(characterNameCol.getPrefWidth());
        addCharacterName.setPromptText("Character Name");

        final TextField addSubRace = new TextField();
        addSubRace.setMaxWidth(subRaceCol.getPrefWidth());
        addSubRace.setPromptText("Sub-Race");

        final TextField addRace = new TextField();
        addRace.setMaxWidth(raceCol.getPrefWidth());
        addRace.setPromptText("Race");

        final TextField addPrimary = new TextField();
        addPrimary.setMaxWidth(primaryClassCol.getPrefWidth());
        addPrimary.setPromptText("Primary Class");

        final TextField addSecondary = new TextField();
        addSecondary.setMaxWidth(secondaryClassCol.getPrefWidth());
        addSecondary.setPromptText("Secondary Class");

        final TextField addStatus = new TextField();
        addStatus.setMaxWidth(statusCol.getPrefWidth());
        addStatus.setPromptText("Status");

        final TextField addLocation = new TextField();
        addLocation.setMaxWidth(locationCol.getPrefWidth());
        addLocation.setPromptText("Last Location");

        final TextField addDate = new TextField();
        addDate.setMaxWidth(dateCol.getPrefWidth());
        addDate.setPromptText("Last Played");

        final TextField addLevel = new TextField();
        addLevel.setMaxWidth(levelCol.getPrefWidth());
        addLevel.setPromptText("Level");

        final TextField addStr = new TextField();
        addStr.setMaxWidth(strCol.getPrefWidth());
        addStr.setPromptText("Strength");

        final TextField addDex = new TextField();
        addDex.setMaxWidth(dexCol.getPrefWidth());
        addDex.setPromptText("Dexterity");

        final TextField addCon = new TextField();
        addCon.setMaxWidth(conCol.getPrefWidth());
        addCon.setPromptText("Constitution");

        final TextField addInt = new TextField();
        addInt.setMaxWidth(intCol.getPrefWidth());
        addInt.setPromptText("Intelligence");

        final TextField addWis = new TextField();
        addWis.setMaxWidth(wisCol.getPrefWidth());
        addWis.setPromptText("Wisdom");

        final TextField addCha = new TextField();
        addCha.setMaxWidth(chaCol.getPrefWidth());
        addCha.setPromptText("Charisma");


        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!addCharacterName.getText().equals("") &&
                        !addPlayerName.getText().equals("") &&
                        !addRace.getText().equals("") &&
                        !addSubRace.getText().equals("") &&
                        !addPrimary.getText().equals("") &&
                        !addSecondary.getText().equals("") &&
                        !addStatus.getText().equals("") &&
                        !addLocation.getText().equals("") &&
                        !addDate.getText().equals("") &&
                        !addLevel.getText().equals("")) {
                    data.add(new RPGCharacter(
                            addCharacterName.getText(),
                            addPlayerName.getText(),
                            addRace.getText(),
                            addSubRace.getText(),
                            addPrimary.getText(),
                            addSecondary.getText(),
                            addStatus.getText(),
                            addLocation.getText(),
                            addDate.getText(),
                            addLevel.getText(),
                            addStr.getText(),
                            addDex.getText(),
                            addCon.getText(),
                            addInt.getText(),
                            addWis.getText(),
                            addCha.getText()
                    ));
                    addPlayerName.clear();
                    addCharacterName.clear();
                    addRace.clear();
                    addSubRace.clear();
                    addPrimary.clear();
                    addSecondary.clear();
                    addStatus.clear();
                    addLocation.clear();
                    addDate.clear();
                    addLevel.clear();
                    addStr.clear();
                    addDex.clear();
                    addCon.clear();
                    addInt.clear();
                    addWis.clear();
                    addCha.clear();
                }
            }
        });

        final Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                save();
            }

        });

        final Button loadButton = new Button("Load");
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                load();
            }
        });

        final Button diceButton = new Button("Dice");
        diceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toDiceScene(primaryStage);
            }
        });

        hb.getChildren().addAll(addPlayerName, addCharacterName, addRace, addSubRace, addPrimary,
                addSecondary, addStatus, addLocation, addDate, addLevel, addStr,
                addDex, addCon, addInt, addWis, addCha, addButton, saveButton,
                loadButton);
        hb.setSpacing(3);


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb, diceButton);

        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);


        ((Group) scene.getRoot()).getChildren().addAll(iv, vbox);

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("D&D");
        primaryStage.setScene(scene);
        primaryStage.show();
        load();
        tableScene = scene;
    }

    private void save() {
        try {
            fw = new FileWriter(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        bw = new BufferedWriter(fw);

        try {

            for (int i = 0; i < data.size(); i++) {

                bw.write(data.get(i).getCharacter() + ",");
                bw.write(data.get(i).getPlayer() + ",");
                bw.write(data.get(i).getRace() + ",");
                bw.write(data.get(i).getSubRace() + ",");
                bw.write(data.get(i).getPrimaryClass() + ",");
                bw.write(data.get(i).getSecondaryClass() + ",");
                bw.write(data.get(i).getStatus() + ",");
                bw.write(data.get(i).getLastLocation() + ",");
                bw.write(data.get(i).getLastPlayed() + ",");
                bw.write(data.get(i).getLevel() + ",");
                bw.write(data.get(i).getC_str() + ",");
                bw.write(data.get(i).getC_dex() + ",");
                bw.write(data.get(i).getC_con() + ",");
                bw.write(data.get(i).getC_int() + ",");
                bw.write(data.get(i).getC_wis() + ",");
                bw.write(data.get(i).getC_cha());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void load() {
        try {
            fr = new FileReader("Characters.csv");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");

                RPGCharacter character = new RPGCharacter(attributes[0], attributes[1],
                        attributes[2], attributes[3], attributes[4], attributes[5], attributes[6],
                        attributes[7], attributes[8], attributes[9], attributes[10], attributes[11],
                        attributes[12], attributes[13], attributes[14], attributes[15]);

                data.add(character);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if ((i != j) &&
                        data.get(i).getCharacter().equals(data.get(j).getCharacter()) &&
                        data.get(i).getPlayer().equals(data.get(j).getPlayer())) {
                    data.remove(i);
                }
            }
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCharacter().equals("")) {
                data.remove(i);
            }

        }

    }

    private void toTableScene(Stage primaryStage) {

        primaryStage.setScene(tableScene);
        primaryStage.show();

    }

    private void toDiceScene(Stage primaryStage) {

        if (diceScene == null) {
            createDice(primaryStage);
        }

        primaryStage.setScene(diceScene);
        primaryStage.show();
    }


    private void createDice(Stage primaryStage) {
        diceScene = new Scene(new Group());

        final int buttonSize = 60;

        HBox[] diceBoxes = new HBox[6];

        for (int i = 0; i <= 5; i++) {

            int dNumber = 0;

            switch (i) {
                case 0:
                    dNumber = 20;
                    break;
                case 1:
                    dNumber = 12;
                    break;
                case 2:
                    dNumber = 10;
                    break;
                case 3:
                    dNumber = 8;
                    break;
                case 4:
                    dNumber = 6;
                    break;
                case 5:
                    dNumber = 4;
                    break;
            }

            final int diceFinal = dNumber;

            TextField text = new TextField();
            text.setEditable(false);
            text.setFont(new Font("Arial", 15));
            text.setMaxWidth(buttonSize);
            text.setMaxHeight(buttonSize);

            TextField textCount = new TextField();
            textCount.setEditable(true);
            textCount.setFont(new Font("Arial", 15));
            textCount.setMaxWidth(buttonSize);
            textCount.setMaxHeight(buttonSize);
            textCount.setText("1");

            TextField textAdd = new TextField();
            textAdd.setEditable(true);
            textAdd.setFont(new Font("Arial", 15));
            textAdd.setMaxWidth(buttonSize);
            textAdd.setMaxHeight(buttonSize);
            textAdd.setText("0");


            Button rollButton = new Button("d" + dNumber);
            rollButton.setPrefWidth(buttonSize);
            rollButton.setMinHeight(buttonSize);
            rollButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Random rand = new Random();


                    int temp = 0;

                    for (int i = 0; i < Integer.parseInt(textCount.getText()); i++) {
                        temp += rand.nextInt(diceFinal) + 1;
                    }

                    temp += Integer.parseInt(textAdd.getText());

                    text.setText(Integer.toString(temp));
                }
            });

            System.out.println(diceFinal);
            System.out.println(i);
            diceBoxes[i] = new HBox();
            diceBoxes[i].getChildren().addAll(textCount, rollButton, textAdd, text);
            diceBoxes[i].setSpacing(3);
            diceBoxes[i].setAlignment(Pos.CENTER);

        }

        TextField dCustom = new TextField();
        dCustom.setEditable(false);
        dCustom.setFont(new Font("Arial", 15));
        dCustom.setMaxWidth(buttonSize);
        dCustom.setMaxHeight(buttonSize);

        TextField customCount = new TextField();
        customCount.setEditable(true);
        customCount.setFont(new Font("Arial", 15));
        customCount.setMaxWidth(buttonSize);
        customCount.setMaxHeight(buttonSize);
        customCount.setText("1");

        TextField customAdd = new TextField();
        customAdd.setEditable(true);
        customAdd.setFont(new Font("Arial", 15));
        customAdd.setMaxWidth(buttonSize);
        customAdd.setMaxHeight(buttonSize);
        customAdd.setText("0");

        TextField customNum = new TextField();
        customNum.setEditable(true);
        customNum.setFont(new Font("Arial", 15));
        customNum.setMaxWidth(buttonSize);
        customNum.setMaxHeight(buttonSize);
        customNum.setText("20");

        Button customBut = new Button("Custom");
        customBut.setPrefWidth(buttonSize);
        customBut.setMinHeight(buttonSize);
        customBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Random rand = new Random();
                int temp = 0;

                for (int i = 0; i < Integer.parseInt(customCount.getText()); i++) {
                    temp += rand.nextInt(Integer.parseInt(customNum.getText())) + 1;
                }

                temp += Integer.parseInt(customAdd.getText());

                dCustom.setText(Integer.toString(temp));
            }
        });

        Button changeScene = new Button("Character Table");
        changeScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toTableScene(primaryStage);
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(changeScene);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Label diceNo = new Label("# of Dice");
        diceNo.setFont(new Font("Arial", 12));

        Label diceType = new Label("Dice Type");
        diceType.setFont(new Font("Arial", 12));

        Label diceTotal = new Label("Total");
        diceTotal.setFont(new Font("Arial", 12));

        Label diceAdd = new Label("Add");
        diceAdd.setFont(new Font("Arial", 12));

        HBox labels = new HBox();
        labels.getChildren().addAll(diceNo, diceType, diceAdd, diceTotal);
        labels.setSpacing(20);
        labels.setAlignment(Pos.CENTER);

        HBox customDice = new HBox();
        customDice.getChildren().addAll(customBut, customCount, customNum, customAdd, dCustom);
        customDice.setSpacing(3);
        customDice.setAlignment(Pos.CENTER);


        Label title = new Label("Dice Roller");
        title.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(title, hbox, labels, diceBoxes[0], diceBoxes[1],
                diceBoxes[2], diceBoxes[3], diceBoxes[4], diceBoxes[5], customDice);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 0, 0, 550));

        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) diceScene.getRoot()).getChildren().addAll(iv, vbox);

    }
}


