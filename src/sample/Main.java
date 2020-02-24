package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.*;


public class Main extends Application {

    private static File characterSheet = new File("Characters.csv");

    private static FileWriter fw;

    //Scenes to switch between
    public static Scene tableScene;
    public static Scene diceScene;
    public static Scene initScene;
    public static Scene timeScene;

    public static TableView<RPGCharacter> table = new TableView<>();
    public static ObservableList<RPGCharacter> data =
            FXCollections.observableArrayList();

    static TableView<InitiativeEntry> initTable = new TableView<>();
    static ObservableList<InitiativeEntry> initData =
            FXCollections.observableArrayList();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneCreator.createTable(primaryStage);
    }

    //This method writes data from the application tables into a csv file
    static void save() {
        try {
            fw = new FileWriter(characterSheet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Readers and writers for file management
        BufferedWriter bw = new BufferedWriter(fw);

        try {

            for (RPGCharacter datum : data) {
                bw.write(datum.getCharacter() + ",");
                bw.write(datum.getPlayer() + ",");
                bw.write(datum.getRace() + ",");
                bw.write(datum.getSubRace() + ",");
                bw.write(datum.getPrimaryClass() + ",");
                bw.write(datum.getSecondaryClass() + ",");
                bw.write(datum.getStatus() + ",");
                bw.write(datum.getLastLocation() + ",");
                bw.write(datum.getLastPlayed() + ",");
                bw.write(datum.getLevel() + ",");
                bw.write(datum.getC_str() + ",");
                bw.write(datum.getC_dex() + ",");
                bw.write(datum.getC_con() + ",");
                bw.write(datum.getC_int() + ",");
                bw.write(datum.getC_wis() + ",");
                bw.write(datum.getC_cha());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*This method reads data from the application tables from a csv file
    This method could potentially be altered to allow the user to select from multiple csv files*/
    static void load() {
        try {
            FileReader fr = new FileReader("Characters.csv");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");

                RPGCharacter character = new RPGCharacter(attributes[0], attributes[1],
                        attributes[2], attributes[3], attributes[4], attributes[5], attributes[6],
                        attributes[7], attributes[8], attributes[9], attributes[10], attributes[11],
                        attributes[12], attributes[13], attributes[14], attributes[15]);

                data.add(character);
            }

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

    //This method changes the focus of the application to show the character table
    static void toTableScene(Stage primaryStage) {

        primaryStage.setScene(tableScene);
        primaryStage.show();

    }

    //This method changes the focus of the application to show the "dice"
    static void toDiceScene(Stage primaryStage) {

        if (diceScene == null) {
            SceneCreator.createDice(primaryStage);
        }

        primaryStage.setScene(diceScene);
        primaryStage.show();
    }

    //This method changes the focus of the application to show the initiatives table
    static void toInitScene(Stage primaryStage) {
        if (initScene == null) {
            SceneCreator.createInit(primaryStage);
        }
        primaryStage.setScene(initScene);
        primaryStage.show();

    }

    //This method changes the focus of the application to show the time scene.
    static void toTimeScene(Stage primaryStage) {

        if (timeScene == null) {
            SceneCreator.createTime(primaryStage);
        }

        primaryStage.setScene(timeScene);
        primaryStage.show();

    }
}