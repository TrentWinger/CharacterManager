package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class SceneCreator {

    //Readers and writers for file management
    private static BufferedWriter bw;
    private static FileWriter fw;
    static final HBox hb = new HBox();

    //This method creates the dice scene the first time Main.toDiceScene is called
    static void createDice(Stage primaryStage) {
        Main.diceScene = new Scene(new Group());

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
        customBut.setOnAction(e -> {
            Random rand = new Random();
            int temp = 0;

            for (int i = 0; i < Integer.parseInt(customCount.getText()); i++) {
                temp += rand.nextInt(Integer.parseInt(customNum.getText())) + 1;
            }

            temp += Integer.parseInt(customAdd.getText());

            dCustom.setText(Integer.toString(temp));
        });

        Button changeScene = new Button("Character Table");
        changeScene.setOnAction(e -> Main.toTableScene(primaryStage));

        final Button initButton = new Button("Initiatives");
        initButton.setOnAction(e -> Main.toInitScene(primaryStage));

        final Button timeButton = new Button("Time/Weather");
        timeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Main.toTimeScene(primaryStage);
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(changeScene, initButton, timeButton);
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

        VBox dice = new VBox();
        dice.getChildren().addAll(title, hbox, labels, diceBoxes[0], diceBoxes[1],
                diceBoxes[2], diceBoxes[3], diceBoxes[4], diceBoxes[5], customDice);
        dice.setSpacing(5);
        dice.setAlignment(Pos.CENTER);
        dice.setPadding(new Insets(10, 0, 0, 550));

        TextField[] dropLowestArr = new TextField[7];

        for (int i = 0; i < 7; i++) {
            TextField resultsBox = new TextField();
            resultsBox.setEditable(false);
            resultsBox.setFont(new Font("Arial", 15));
            resultsBox.setMaxWidth(buttonSize);
            resultsBox.setMinHeight(buttonSize);

            dropLowestArr[i] = resultsBox;
        }

        final Button rollDrop = new Button("Roll 4d6, drop lowest.");
        rollDrop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < 7; i++) {

                    int[] temp = new int[4];
                    Random rand = new Random();
                    for (int j = 0; j < temp.length; j++) {
                        temp[j] = (rand.nextInt(6) + 1);
                    }

                    Arrays.sort(temp);

                    int resultNum = temp[1] + temp[2] + temp[3];

                    dropLowestArr[i].setText(Integer.toString(resultNum));


                }
            }
        });


        HBox scoreResults = new HBox();
        scoreResults.getChildren().addAll(dropLowestArr[0], dropLowestArr[1], dropLowestArr[2],
                dropLowestArr[3], dropLowestArr[4], dropLowestArr[5]);
        scoreResults.setAlignment(Pos.CENTER);


        VBox dropLowest = new VBox();
        dropLowest.getChildren().addAll(rollDrop, scoreResults);
        dropLowest.setSpacing(5);
        dropLowest.setAlignment(Pos.CENTER);
        dropLowest.setPadding(new Insets(200, 0, 0, 125));


        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) Main.diceScene.getRoot()).getChildren().addAll(iv, dice, dropLowest);

    }

    //This method creates the initiatives scene the first time Main.toInitScene is called
    static void createInit(Stage primaryStage) {

        Main.initScene = new Scene(new Group());
        Main.initTable.setEditable(true);

        TableColumn<InitiativeEntry, String> charName = new TableColumn<>("Character Name");
        charName.setMinWidth(300);
        charName.setCellValueFactory(
                new PropertyValueFactory<>("initName")
        );

        TableColumn<InitiativeEntry, String> charInit = new TableColumn<>("Initiative Score");
        charInit.setMinWidth(100);
        charInit.setCellValueFactory(
                new PropertyValueFactory<>("initScore")
        );

        TableColumn<InitiativeEntry, String> charHealth = new TableColumn<>("Health");
        charHealth.setMinWidth(100);
        charHealth.setCellValueFactory(
                new PropertyValueFactory<>("health")
        );
        charHealth.setCellFactory(TextFieldTableCell.forTableColumn());
        charHealth.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setHealth(((t.getNewValue())))
        );


        final TextField addName = new TextField();
        addName.setPromptText("Character/Enemy Name");

        final TextField addInit = new TextField();
        addInit.setPromptText("Initiative Score");


        final TextField addHealth = new TextField();
        addHealth.setPromptText("Character/Enemy Health");
        addHealth.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                Main.initData.add(new InitiativeEntry(
                        addName.getText(),
                        Integer.parseInt(addInit.getText()),
                        addHealth.getText()
                ));
                addName.clear();
                addInit.clear();
                addHealth.clear();
                Main.initTable.getSortOrder().add(charInit);
                addName.requestFocus();
            }
        });

        addInit.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if (addHealth.getText().equals("")) {
                    Main.initData.add(new InitiativeEntry(
                            addName.getText(),
                            Integer.parseInt(addInit.getText()),
                            "-"
                    ));
                } else {
                    Main.initData.add(new InitiativeEntry(
                            addName.getText(),
                            Integer.parseInt(addInit.getText()),
                            addHealth.getText()
                    ));
                }
                addName.clear();
                addInit.clear();
                addHealth.clear();
                Main.initTable.getSortOrder().add(charInit);
                addName.requestFocus();

            }
        });


        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Main.initData.add(new InitiativeEntry(
                            addName.getText(),
                            Integer.parseInt(addInit.getText()),
                            addHealth.getText()
                    )
            );
            addName.clear();
            addInit.clear();
            Main.initTable.getSortOrder().add(charInit);
        });


        Main.initTable.setItems(Main.initData);
        Main.initTable.getColumns().addAll(charName, charInit, charHealth);


        Button changeScene = new Button("Character Table");
        changeScene.setOnAction(e -> Main.toTableScene(primaryStage));

        Button toDice = new Button("Dice");
        toDice.setOnAction(e -> Main.toDiceScene(primaryStage));

        final Button timeButton = new Button("Time/Weather");
        timeButton.setOnAction(e -> Main.toTimeScene(primaryStage));

        final Button clearTable = new Button("Clear Table");
        clearTable.setOnAction(e -> {
            Main.initData.clear();
            addName.clear();
            addInit.clear();
            addHealth.clear();
        });

        final Button deleteEntry = new Button("Remove Selected");
        deleteEntry.setOnAction(e -> {
            InitiativeEntry selectedEntry = Main.initTable.getSelectionModel().getSelectedItem();
            Main.initData.remove(selectedEntry);
            Main.initTable.getItems().remove(selectedEntry);
        });

        HBox navigation = new HBox();
        navigation.setSpacing(5);
        navigation.getChildren().addAll(changeScene, toDice, timeButton);
        navigation.setAlignment(Pos.CENTER);

        HBox additions = new HBox();
        additions.setSpacing(5);
        additions.getChildren().addAll(addName, addInit, addHealth, addButton);
        additions.setAlignment(Pos.CENTER);

        Label title = new Label("Initiative Tracker");
        title.setFont(new Font("Arial", 20));

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(title, navigation, Main.initTable, additions, deleteEntry, clearTable);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 0, 0, 550));

        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) Main.initScene.getRoot()).getChildren().addAll(iv, vbox);

    }

    //This method creates the timer scene the first time Main.toTimeScene is called
    static void createTime(Stage primaryStage) {
        Main.timeScene = new Scene(new Group());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy M dd");
        SimpleDateFormat displayFormat = new SimpleDateFormat("MMM dd yyy");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Campaign");

        File currentDirFile = new File("savedcampaigns/");
        String helper = currentDirFile.getAbsolutePath();
        System.out.println(helper);


        fileChooser.setInitialDirectory(currentDirFile);

        File file = fileChooser.showOpenDialog(primaryStage);
        String date = "";
        FileReader fr;
        BufferedReader br;
        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);
            date = br.readLine();
            System.out.println(date);
            br.close();

        } catch (IOException e1) {
            e1.printStackTrace();
            try {
                fr = new FileReader("Date.txt");
                br = new BufferedReader(fr);
                date = br.readLine();
                System.out.println(date);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        String[] campaignArray = date.split(" ");
        GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(campaignArray[0]),
                Integer.parseInt(campaignArray[1]),
                Integer.parseInt(campaignArray[2]));

        campaignTrack thisCampaign = new campaignTrack(campaignArray[3], calendar, Integer.parseInt(campaignArray[4]), campaignArray[5]);

        Label name = new Label(thisCampaign.getCampaignName());
        name.setFont(new Font("Arial", 30));

        Label description = new Label("The Date Is:");
        description.setFont(new Font("Arial", 10));

        Label dateLabel = new Label(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        dateLabel.setFont(new Font("Arial", 50));


        final Button loadButton = new Button("Open Campaign");
        loadButton.setOnAction(e -> {
            createTime(primaryStage);
            Main.toTimeScene(primaryStage);
        });

        final Button incDay = new Button("Next Day");
        incDay.setOnAction(e -> {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button incWeek = new Button("Next Week");
        incWeek.setOnAction(e -> {
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button incMonth = new Button("Next Month");
        incMonth.setOnAction(e -> {
            calendar.add(Calendar.MONTH, 1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button incYear = new Button("Next Year");
        incYear.setOnAction(e -> {
            calendar.add(Calendar.YEAR, 1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button incEra = new Button("Next Era");
        incEra.setOnAction(e -> {
            thisCampaign.setEra(thisCampaign.getEra() + 1);
            System.out.println(thisCampaign.getEra());
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button decDay = new Button("Previous Day");
        decDay.setOnAction(e -> {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button decWeek = new Button("Previous Week");
        decWeek.setOnAction(e -> {
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button decMonth = new Button("Previous Month");
        decMonth.setOnAction(e -> {
            calendar.add(Calendar.MONTH, -1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button decYear = new Button("Previous Year");
        decYear.setOnAction(e -> {
            calendar.add(Calendar.YEAR, -1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        final Button decEra = new Button("Previous Era");
        decEra.setOnAction(e -> {
            thisCampaign.setEra(thisCampaign.getEra() - 1);
            dateLabel.setText(displayFormat.format(calendar.getTime()) + ", Era " + thisCampaign.getEra());
        });

        Button saveTime = new Button("Save");
        saveTime.setOnAction(e -> {
            try {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                calendar.add(Calendar.MONTH, -1);
                bw.write(sdf.format(calendar.getTime())
                        + " " + thisCampaign.getCampaignName()
                        + " " + thisCampaign.getEra()
                        + " " + thisCampaign.getTimeOfDay());
                bw.newLine();
                bw.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Button saveAs = new Button("Save As...");
        saveAs.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            chooser.setInitialDirectory(currentDirFile);
            chooser.getExtensionFilters().add(filter);
            chooser.setTitle("Save Campaign");
            File saveFile = chooser.showSaveDialog(primaryStage);
            try {
                FileWriter fw = new FileWriter(saveFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(sdf.format(calendar.getTime())
                        + " " + thisCampaign.getCampaignName()
                        + " " + thisCampaign.getEra()
                        + " " + thisCampaign.getTimeOfDay());
                bw.newLine();
                bw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        Label title = new Label("Time and Weather");
        title.setFont(new Font("Arial", 20));

        TextField nameCampaign = new TextField();
        nameCampaign.setPromptText("Set Campaign Name");
        nameCampaign.setEditable(true);
        nameCampaign.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                thisCampaign.setCampaignName(nameCampaign.getText());
                name.setText(thisCampaign.getCampaignName());
            }
        });


        Button changeScene = new Button("Character Table");
        changeScene.setOnAction(e -> Main.toTableScene(primaryStage));

        Button toDice = new Button("Dice");
        toDice.setOnAction(e -> Main.toDiceScene(primaryStage));

        final Button initButton = new Button("Initiatives");
        initButton.setOnAction(e -> Main.toInitScene(primaryStage));

        HBox navigation = new HBox();
        navigation.setSpacing(5);
        navigation.getChildren().addAll(changeScene, toDice, initButton);
        navigation.setAlignment(Pos.CENTER);

        VBox passTime = new VBox();
        passTime.setSpacing(5);
        passTime.getChildren().addAll(incDay, incWeek, incMonth, incYear, incEra);
        passTime.setAlignment(Pos.CENTER);

        VBox backTime = new VBox();
        backTime.setSpacing(5);
        backTime.getChildren().addAll(decDay, decWeek, decMonth, decYear, decEra);
        backTime.setAlignment(Pos.CENTER);

        HBox time = new HBox();
        time.setSpacing(5);
        time.getChildren().addAll(backTime, passTime);
        time.setAlignment(Pos.CENTER);
        time.setPadding(new Insets(20, 20, 20, 20));

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(name, description, dateLabel, saveTime, saveAs, nameCampaign, loadButton);
        vbox.setAlignment(Pos.CENTER);

        HBox display = new HBox();
        display.setSpacing(5);
        display.getChildren().addAll(time, vbox);
        display.setAlignment(Pos.CENTER);
        display.setPadding(new Insets(50, 0, 0, 0));

        VBox alignment = new VBox();
        alignment.setSpacing(5);
        alignment.getChildren().addAll(title, navigation, display);
        alignment.setAlignment(Pos.CENTER);
        alignment.setPadding(new Insets(0, 0, 0, 400));


        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) Main.timeScene.getRoot()).getChildren().addAll(iv, alignment);

    }

    //This method creates the initial table scene displaying character information.
    static void createTable(Stage primaryStage) {
        Scene scene = new Scene(new Group());

        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(1500);
        primaryStage.setHeight(600);

        final Label label = new Label("Dungeons & Dragons Characters");
        label.setFont(new Font("Arial", 20));

        Main.table.setEditable(true);

        /*
         * Until the next code block is code to create the columns
         */

        TableColumn<RPGCharacter, String> playerNameCol = new TableColumn<>("Player Name");
        playerNameCol.setMinWidth(115);
        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("player"));
        playerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        playerNameCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPlayer(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> characterNameCol = new TableColumn<>("Character Name");
        characterNameCol.setMinWidth(115);
        characterNameCol.setCellValueFactory(
                new PropertyValueFactory<>("character"));
        characterNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        characterNameCol.setOnEditCommit(

                /*
                The below lambda function is equivalent to the enclosed code block in this comment
                -----------------------------------------------------------------------
                 new EventHandler<CellEditEvent<RPGCharacter, String>>() {
                    @Override
                    public void handle(CellEditEvent<RPGCharacter, String> t) {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setCharacter(t.getNewValue());
                    }
                }
                -----------------------------------------------------------------------
                 */

                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCharacter(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> raceCol = new TableColumn<>("Race");
        raceCol.setCellValueFactory(
                new PropertyValueFactory<>("race"));
        raceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        raceCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setRace(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> subRaceCol = new TableColumn<>("Sub Race");
        subRaceCol.setCellValueFactory(
                new PropertyValueFactory<>("subRace"));
        subRaceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        subRaceCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSubRace(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> primaryClassCol = new TableColumn<>("Primary Class");
        primaryClassCol.setMinWidth(115);
        primaryClassCol.setCellValueFactory(
                new PropertyValueFactory<>("primaryClass"));
        primaryClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        primaryClassCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrimaryClass(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> secondaryClassCol = new TableColumn<>("Secondary Class");
        secondaryClassCol.setMinWidth(115);
        secondaryClassCol.setCellValueFactory(
                new PropertyValueFactory<>("secondaryClass"));
        secondaryClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        secondaryClassCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSecondaryClass(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue())
        );
        TableColumn<RPGCharacter, String> locationCol = new TableColumn<>("Last Location");
        locationCol.setMinWidth(115);
        locationCol.setCellValueFactory(
                new PropertyValueFactory<>("lastLocation"));
        locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        locationCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastLocation(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> dateCol = new TableColumn<>("Last Played");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("lastPlayed"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastPlayed(t.getNewValue())
        );

        TableColumn<RPGCharacter, String> levelCol = new TableColumn<>("Level");
        levelCol.setCellValueFactory(
                new PropertyValueFactory<>("level"));
        levelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        levelCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setLevel((t.getNewValue()))
        );

        /*
         * The code below this comment is used to create sub-tables to display
         * an individual entry's stat scores.
         */

        TableColumn<RPGCharacter, String> statsCol = new TableColumn<>("Ability Scores");

        TableColumn<RPGCharacter, String> strCol = new TableColumn<>("STR");
        strCol.setCellValueFactory(
                new PropertyValueFactory<>("c_str"));
        strCol.setCellFactory(TextFieldTableCell.forTableColumn());
        strCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_str((t.getNewValue()))
        );
        TableColumn<RPGCharacter, String> dexCol = new TableColumn<>("DEX");
        dexCol.setCellValueFactory(
                new PropertyValueFactory<>("c_dex"));
        dexCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dexCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_dex((t.getNewValue()))
        );
        TableColumn<RPGCharacter, String> conCol = new TableColumn<>("CON");
        conCol.setCellValueFactory(
                new PropertyValueFactory<>("c_con"));
        conCol.setCellFactory(TextFieldTableCell.forTableColumn());
        conCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_con((t.getNewValue()))
        );
        TableColumn<RPGCharacter, String> intCol = new TableColumn<>("INT");
        intCol.setCellValueFactory(
                new PropertyValueFactory<>("c_int"));
        intCol.setCellFactory(TextFieldTableCell.forTableColumn());
        intCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_int((t.getNewValue()))
        );
        TableColumn<RPGCharacter, String> wisCol = new TableColumn<>("WIS");
        wisCol.setCellValueFactory(
                new PropertyValueFactory<>("c_wis"));
        wisCol.setCellFactory(TextFieldTableCell.forTableColumn());
        wisCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_wis((t.getNewValue()))
        );
        TableColumn<RPGCharacter, String> chaCol = new TableColumn<>("CHA");
        chaCol.setCellValueFactory(
                new PropertyValueFactory<>("c_cha"));
        chaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        chaCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setC_cha((t.getNewValue()))
        );

        statsCol.getColumns().addAll(strCol, dexCol, conCol, intCol, wisCol, chaCol);

        /*
         * The code above this comment is used to create sub-tables to display
         * an individual entry's stat scores.
         */


        Main.table.setItems(Main.data);

        Main.table.getColumns().addAll(playerNameCol, characterNameCol, raceCol, subRaceCol, primaryClassCol,
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
        addButton.setOnAction(e -> {
            //This checks to ensure that there is actually a character to create
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
                Main.data.add(new RPGCharacter(
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
            Main.save();
        });

        final Button deleteEntry = new Button("Remove Selected");
        deleteEntry.setOnAction(e -> {
            RPGCharacter selectedEntry = Main.table.getSelectionModel().getSelectedItem();

            Alert warning = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete " + selectedEntry.getCharacter() + "?",
                    ButtonType.YES, ButtonType.NO);

            warning.showAndWait();

            if (warning.getResult() == ButtonType.YES) {
                Main.data.remove(selectedEntry);
                Main.table.getItems().remove(selectedEntry);
                Main.save();
            }
        });

        final Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> Main.save());

        final Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            Main.load();
            Main.table.getSortOrder().add(dateCol);
        });

        final Button diceButton = new Button("Dice");
        diceButton.setOnAction(e -> Main.toDiceScene(primaryStage));

        final Button initButton = new Button("Initiatives");
        initButton.setOnAction(e -> Main.toInitScene(primaryStage));

        final Button timeButton = new Button("Time/Weather");
        timeButton.setOnAction(e -> Main.toTimeScene(primaryStage));

        hb.getChildren().addAll(addPlayerName, addCharacterName, addRace, addSubRace, addPrimary,
                addSecondary, addStatus, addLocation, addDate, addLevel, addStr,
                addDex, addCon, addWis, addInt, addCha, addButton, saveButton,
                loadButton);
        hb.setSpacing(3);

        HBox navigation = new HBox();
        navigation.setSpacing(5);
        navigation.getChildren().addAll(diceButton, initButton, timeButton);
        navigation.setAlignment(Pos.CENTER);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, navigation, Main.table, hb, deleteEntry);
        vbox.setAlignment(Pos.CENTER);

        Image background = new Image("File:images/parchment.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);

        ((Group) scene.getRoot()).getChildren().addAll(iv, vbox);

        primaryStage.setTitle("D&D");
        primaryStage.setScene(scene);
        primaryStage.show();

        Main.load();
        Main.table.getSortOrder().add(dateCol);
        Main.tableScene = scene;
    }
}
