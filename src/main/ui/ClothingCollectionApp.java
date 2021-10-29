package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clothing Collection Application, references teller application given in CPSC 210
public class ClothingCollectionApp {
    private static final String JSON_STORE = "./data/closet.json";
    private Scanner input;
    private Closet closet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the collection application
    public ClothingCollectionApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCollection();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCollection() {
        boolean keepGoing = true;
        int command;

        init();
        welcomeScreen();

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 0) {
                System.out.println("Would you like to save your closet before quitting? Type 'Y' or 'N'");
                String yesOrNo = input.next();
                if (yesOrNo.equalsIgnoreCase("Y")) {
                    saveCloset();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Exiting Clothing Collection...");
    }

    // MODIFIES: this
    // EFFECTS: asks user to load previous closet, otherwise sets name for new closet
    private void welcomeScreen() {
        System.out.println("Would you like to load a previous closet? Type 'Y' or 'N'");
        String yesOrNo = input.next();
        if (yesOrNo.equalsIgnoreCase("Y")) {
            loadCloset();
            System.out.println("Welcome " + closet.getName() + "!");
        } else {
            System.out.println("Enter name of closet");
            String name = input.nextLine();
            closet.setName(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(int command) {
        if (command == 1) {
            addClothing();
        } else if (command == 2) {
            listClothing();
        } else if (command == 3) {
            listClothingCategory();
        } else if (command == 4) {
            displayTagMenu();
            command = input.nextInt();
            if (command == 1) {
                addTags();
            } else if (command == 2) {
                viewTags();
            } else if (command == 3) {
                deleteTags();
            }
        } else if (command == 5) {
            saveCloset();
        } else if (command == 6) {
            loadCloset();
        } else {
            System.out.println("Not a valid option");
        }
    }

    // REQUIRES: an existing tag, non-null input
    // EFFECTS: deletes an existing tag on a specific clothing piece
    // MODIFIES: clothes
    private void deleteTags() {
        Clothes piece = viewTags();
        System.out.println("Enter tag to delete (exclude the #)");
        String tag = input.next().toLowerCase();
        piece.deleteTag(tag);
    }

    // EFFECTS: views tags on a specific clothing piece
    private Clothes viewTags() {
        Type type = selectType();
        Clothes piece = selectClothing(type);
        piece.printTags();
        return piece;
    }

    // EFFECTS: displays the menu option to add/delete/view tags on piece of clothing
    private void displayTagMenu() {
        System.out.println("Select from the following options:"
                + "\n\t1 -> add tags"
                + "\n\t2 -> view tags"
                + "\n\t3 -> delete tags"
                + "\n\t0 -> quit");
    }

    // REQUIRES: tag inputted must be a single word, not null value
    // EFFECTS: adds tags to a specific clothing piece
    // MODIFIES: clothes
    private void addTags() {
        Type type = selectType();
        Clothes piece = selectClothing(type);
        System.out.println("Enter a tag for " + piece.getName());
        Scanner scan = new Scanner(System.in);
        String tag = scan.next();
        piece.addTag(tag);
    }

    // REQUIRES: type from enum list
    // EFFECTS: returns a specific piece of clothing
    private Clothes selectClothing(Type type) {
        List<Clothes> list = closet.getClothes();
        List<Clothes> select = new ArrayList<>();
        int i = 0;
        for (Clothes item : list) {
            if (item.getType().equals(type)) {
                System.out.println(item.getName() + " -> enter " + i);
                select.add(item);
                i++;
            }
        }
        Scanner scan = new Scanner(System.in);
        int selectItem = scan.nextInt();
        return select.get(selectItem);
    }

    // EFFECTS: lists number of clothes in a specific clothing category
    private void listClothingCategory() {
        Type type = selectType();
        printClothingCategoryList(type);
        System.out.println("total number [" + type + "] : ");
        if (type.equals(Type.TOP)) {
            System.out.println(closet.getNumOfTops());
        } else if (type.equals(Type.BOTTOM)) {
            System.out.println(closet.getNumOfBottoms());
        } else if (type.equals(Type.ONEPIECE)) {
            System.out.println(closet.getNumOfOnePieces());
        } else if (type.equals(Type.SHOE)) {
            System.out.println(closet.getNumOfShoes());
        } else if (type.equals(Type.ACCESSORY)) {
            System.out.println(closet.getNumOfAccessories());
        }
    }

    // EFFECTS: prints all clothing in a specific category
    private void printClothingCategoryList(Type type) {
        List<Clothes> list = closet.getClothes();
        for (Clothes item : list) {
            if (item.getType().equals(type)) {
                System.out.println(item.getName());
            }
        }
    }

    // EFFECTS: lists all clothes in the closet and total number
    private void listClothing() {
        List<Clothes> list = closet.getClothes();
        for (Clothes item : list) {
            System.out.println(item.toString());
        }
        System.out.print("-------------------------------------"
                + "\ntotal number of clothes: " + closet.getTotalNumOfClothes() + "\n");
    }

    // MODIFIES: this
    // EFFECTS: adds clothing piece to collection
    private void addClothing() {
        Type type = selectType();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter item name");
        String name = "";
        name += scan.nextLine();

        if (type.equals(Type.TOP)) {
            Clothes top = new Tops(name);
            closet.addClothes(top);
        } else if (type.equals(Type.BOTTOM)) {
            Clothes bottom = new Bottoms(name);
            closet.addClothes(bottom);
        } else if (type.equals(Type.ONEPIECE)) {
            Clothes onePiece = new OnePiece(name);
            closet.addClothes(onePiece);
        } else if (type.equals(Type.SHOE)) {
            Clothes shoes = new Shoes(name);
            closet.addClothes(shoes);
        } else if (type.equals(Type.ACCESSORY)) {
            Clothes accessory = new Accessories(name);
            closet.addClothes(accessory);
        }
    }

    // EFFECTS: prompts user to select type of clothing and returns it
    private Type selectType() {
        System.out.println("Select a category for your clothing piece");
        int menuType = 1;
        for (Type type : Type.values()) {
            System.out.println(menuType + ": " + type);
            menuType++;
        }
        int select = input.nextInt();
        return Type.values()[select - 1];
    }

    // MODIFIES: this
    // EFFECTS: initializes closet and scanner
    private void init() {
        closet = new Closet();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of items for user to choose from
    private void displayMenu() {
        System.out.println("Select from the following options:"
                + "\n\t1 -> add clothing"
                + "\n\t2 -> see list of clothing and total number of pieces"
                + "\n\t3 -> see total number of pieces in specific category"
                + "\n\t4 -> add/delete/view tags for specific piece of clothing"
                + "\n\t5 -> save closet list"
                + "\n\t6 -> load closet list"
                + "\n\t0 -> quit");
    }

    // EFFECTS: saves closet to file
    private void saveCloset() {
        try {
            jsonWriter.open();
            jsonWriter.write(closet);
            jsonWriter.close();
            System.out.println("saved " + closet.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.print("error: cannot write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads closet from file
    private void loadCloset() {
        try {
            closet = jsonReader.read();
            System.out.println("loaded " + closet.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("cannot read from file: " + JSON_STORE);
        }
    }
}
