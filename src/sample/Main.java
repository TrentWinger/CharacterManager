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
        diceButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
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

        for(int i = 0; i < data.size(); i++){
            for(int j = 0; j < data.size(); j++){
                if((i != j) &&
                        data.get(i).getCharacter().equals(data.get(j).getCharacter()) &&
                        data.get(i).getPlayer().equals(data.get(j).getPlayer())){
                    data.remove(i);
                }
            }
        }
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getCharacter().equals("")){
                data.remove(i);
            }

        }

    }

    private void toTableScene(Stage primaryStage){

        primaryStage.setScene(tableScene);
        primaryStage.show();

    }

    private void toDiceScene(Stage primaryStage){

        if(diceScene == null){
            createDice(primaryStage);
        }

        primaryStage.setScene(diceScene);
        primaryStage.show();
    }

    private void createDice(Stage primaryStage){
        diceScene = new Scene(new Group());

        final int buttonSize = 60;

        TextField d20 = new TextField();
        d20.setEditable(false);
        d20.setFont(new Font("Arial", 15));
        d20.setMaxWidth(buttonSize);
        d20.setMaxHeight(buttonSize);

        TextField d20count = new TextField();
        d20count.setEditable(true);
        d20count.setFont(new Font("Arial", 15));
        d20count.setMaxWidth(buttonSize);
        d20count.setMaxHeight(buttonSize);
        d20count.setText("1");

        TextField d20add = new TextField();
        d20add.setEditable(true);
        d20add.setFont(new Font("Arial", 15));
        d20add.setMaxWidth(buttonSize);
        d20add.setMaxHeight(buttonSize);
        d20add.setText("0");


        Button roll20 = new Button("d20");
        roll20.setPrefWidth(buttonSize);
        roll20.setMinHeight(buttonSize);
        roll20.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               Random rand = new Random();
               d20.setText(Integer.toString((rand.nextInt(20)+1)*Integer.parseInt(d20count.getText())+Integer.parseInt(d20add.getText())));
           }
        });

        TextField d12 = new TextField();
        d12.setEditable(false);
        d12.setFont(new Font("Arial", 15));
        d12.setMaxWidth(buttonSize);
        d12.setMaxHeight(buttonSize);

        TextField d12count = new TextField();
        d12count.setEditable(true);
        d12count.setFont(new Font("Arial", 15));
        d12count.setMaxWidth(buttonSize);
        d12count.setMaxHeight(buttonSize);
        d12count.setText("1");

        TextField d12add = new TextField();
        d12add.setEditable(true);
        d12add.setFont(new Font("Arial", 15));
        d12add.setMaxWidth(buttonSize);
        d12add.setMaxHeight(buttonSize);
        d12add.setText("0");

        Button roll12 = new Button("d12");
        roll12.setPrefWidth(buttonSize);
        roll12.setMinHeight(buttonSize);
        roll12.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                d12.setText(Integer.toString((rand.nextInt(12)+1)*Integer.parseInt(d12count.getText())+Integer.parseInt(d12add.getText())));
            }
        });



        TextField d10 = new TextField();
        d10.setEditable(false);
        d10.setFont(new Font("Arial", 15));
        d10.setMaxWidth(buttonSize);
        d10.setMaxHeight(buttonSize);

        TextField d10count = new TextField();
        d10count.setEditable(true);
        d10count.setFont(new Font("Arial", 15));
        d10count.setMaxWidth(buttonSize);
        d10count.setMaxHeight(buttonSize);
        d10count.setText("1");

        TextField d10add = new TextField();
        d10add.setEditable(true);
        d10add.setFont(new Font("Arial", 15));
        d10add.setMaxWidth(buttonSize);
        d10add.setMaxHeight(buttonSize);
        d10add.setText("0");

        Button roll10 = new Button("d10");
        roll10.setPrefWidth(buttonSize);
        roll10.setMinHeight(buttonSize);
        roll10.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                d10.setText(Integer.toString((rand.nextInt(10)+1)*Integer.parseInt(d10count.getText())+Integer.parseInt(d10add.getText())));
            }
        });



        TextField d8 = new TextField();
        d8.setEditable(false);
        d8.setFont(new Font("Arial", 15));
        d8.setMaxWidth(buttonSize);
        d8.setMaxHeight(buttonSize);

        TextField d8count = new TextField();
        d8count.setEditable(true);
        d8count.setFont(new Font("Arial", 15));
        d8count.setMaxWidth(buttonSize);
        d8count.setMaxHeight(buttonSize);
        d8count.setText("1");

        TextField d8add = new TextField();
        d8add.setEditable(true);
        d8add.setFont(new Font("Arial", 15));
        d8add.setMaxWidth(buttonSize);
        d8add.setMaxHeight(buttonSize);
        d8add.setText("0");

        Button roll8 = new Button("d8");
        roll8.setPrefWidth(buttonSize);
        roll8.setMinHeight(buttonSize);
        roll8.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                d8.setText(Integer.toString((rand.nextInt(8)+1)*Integer.parseInt(d8count.getText())+Integer.parseInt(d8add.getText())));
            }
        });



        TextField d6 = new TextField();
        d6.setEditable(false);
        d6.setFont(new Font("Arial", 15));
        d6.setMaxWidth(buttonSize);
        d6.setMaxHeight(buttonSize);

        TextField d6count = new TextField();
        d6count.setEditable(true);
        d6count.setFont(new Font("Arial", 15));
        d6count.setMaxWidth(buttonSize);
        d6count.setMaxHeight(buttonSize);
        d6count.setText("1");

        TextField d6add = new TextField();
        d6add.setEditable(true);
        d6add.setFont(new Font("Arial", 15));
        d6add.setMaxWidth(buttonSize);
        d6add.setMaxHeight(buttonSize);
        d6add.setText("0");

        Button roll6 = new Button("d6");
        roll6.setPrefWidth(buttonSize);
        roll6.setMinHeight(buttonSize);
        roll6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                d6.setText(Integer.toString((rand.nextInt(6)+1)*Integer.parseInt(d6count.getText())+Integer.parseInt(d6add.getText())));
            }
        });



        TextField d4 = new TextField();
        d4.setEditable(false);
        d4.setFont(new Font("Arial", 15));
        d4.setMaxWidth(buttonSize);
        d4.setMaxHeight(buttonSize);

        TextField d4count = new TextField();
        d4count.setEditable(true);
        d4count.setFont(new Font("Arial", 15));
        d4count.setMaxWidth(buttonSize);
        d4count.setMaxHeight(buttonSize);
        d4count.setText("1");

        TextField d4add = new TextField();
        d4add.setEditable(true);
        d4add.setFont(new Font("Arial", 15));
        d4add.setMaxWidth(buttonSize);
        d4add.setMaxHeight(buttonSize);
        d4add.setText("0");

        Button roll4 = new Button("d4");
        roll4.setPrefWidth(buttonSize);
        roll4.setMinHeight(buttonSize);
        roll4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                d4.setText(Integer.toString((rand.nextInt(4)+1)*Integer.parseInt(d4count.getText())+Integer.parseInt(d4add.getText())));
            }
        });

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
        customBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Random rand = new Random();
                dCustom.setText(Integer.toString((rand.nextInt(Integer.parseInt(customNum.getText()))+1)*Integer.parseInt(customCount.getText())+Integer.parseInt(customAdd.getText())));
            }
        });






        Button changeScene = new Button("Character Table");
        changeScene.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
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

        HBox dice20 = new HBox();
        dice20.getChildren().addAll(d20count, roll20, d20add, d20);
        dice20.setSpacing(3);
        dice20.setAlignment(Pos.CENTER);


        HBox dice12 = new HBox();
        dice12.getChildren().addAll(d12count, roll12, d12add, d12);
        dice12.setSpacing(3);
        dice12.setAlignment(Pos.CENTER);

        HBox dice10 = new HBox();
        dice10.getChildren().addAll(d10count, roll10, d10add, d10);
        dice10.setSpacing(3);
        dice10.setAlignment(Pos.CENTER);

        HBox dice8 = new HBox();
        dice8.getChildren().addAll(d8count, roll8, d8add, d8);
        dice8.setSpacing(3);
        dice8.setAlignment(Pos.CENTER);

        HBox dice6 = new HBox();
        dice6.getChildren().addAll(d6count, roll6, d6add, d6);
        dice6.setSpacing(3);
        dice6.setAlignment(Pos.CENTER);


        HBox dice4 = new HBox();
        dice4.getChildren().addAll(d4count, roll4, d4add, d4);
        dice4.setSpacing(3);
        dice4.setAlignment(Pos.CENTER);

        HBox customDice = new HBox();
        customDice.getChildren().addAll(customBut, customCount, customNum, customAdd, dCustom);
        customDice.setSpacing(3);
        customDice.setAlignment(Pos.CENTER);


        Label title = new Label("Dice Roller");
        title.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(title, hbox, labels, dice20, dice12, dice10, dice8, dice6, dice4, customDice);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 0, 0, 550));

        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) diceScene.getRoot()).getChildren().addAll(iv, vbox);

    }
}


