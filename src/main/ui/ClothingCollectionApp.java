package ui;

import model.*;

import java.util.Scanner;

// Clothing Collection Application, references teller application given in CPSC 210
public class ClothingCollectionApp {
    private Scanner input;
    private Closet closet;
    private Tops top;
    private Bottoms bottom;
    private OnePiece onePiece;
    private Shoes shoe;
    private Accessories accessory;

    // EFFECTS: runs the collection application
    public ClothingCollectionApp() {
        runCollection();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCollection() {
        boolean keepGoing = true;
        int command;

        init();
        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 0) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Exiting Clothing Collection...");
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
            modifyTags();
        } else {
            System.out.println("Not valid option");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds clothing piece to collection
    private void addClothing() {
        Clothes type = selectType();
        System.out.println("Enter name of item");
        String name = input.next();
        type = new type(name);
    }

    // EFFECTS: prompts user to select type of clothing and returns it
    private Clothes selectType() {
        int select = 0;
        while (!(select == 1 || select == 2 || select == 3 || select == 4 || select == 5)) {
            System.out.println("1 for Tops");
            System.out.println("2 for Bottoms");
            System.out.println("3 for One Pieces");
            System.out.println("4 for Shoes");
            System.out.println("5 for Accessories");
            select = input.nextInt();
        }
        if (select == 1) {
            return top;
        } else if (select == 2) {
            return bottom;
        } else if (select == 3) {
            return onePiece;
        } else if (select == 4) {
            return shoe;
        } else {
            return accessory;
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
                + "\n\t0 -> quit");
    }
}
