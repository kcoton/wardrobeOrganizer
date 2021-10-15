package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

// Clothing Collection Application, references teller application given in CPSC 210
public class ClothingCollectionApp {
    private Scanner input;
    private Closet closet;

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
            addTags();
        } else {
            System.out.println("Not valid option");
        }
    }

    // EFFECTS: lists num of clothes in a specific clothing category
    private void listClothingCategory() {
        int type = selectType();
        if (type == 1) {
            System.out.println("Total Number of Tops: " + closet.getNumOfTops());
        } else if (type == 2) {
            System.out.println("Total Number of Bottoms: " + closet.getNumOfBottoms());
        } else if (type == 3) {
            System.out.println("Total Number of One Pieces: " + closet.getNumOfOnePieces());
        } else if (type == 4) {
            System.out.println("Total Number of Shoes: " + closet.getNumOfShoes());
        } else if (type == 5) {
            System.out.println("Total Number of Accessories: " + closet.getNumOfAccessories());
        }
    }

    // EFFECTS: lists all clothes in the closet and total number
    private void listClothing() {
        ArrayList<Clothes> list = closet.getCloset();
        for (Clothes item : list) {
            System.out.println(item + "\n");
        }
        System.out.println("Total Number of Clothes: " + closet.getTotalNumOfClothes());
    }

    // MODIFIES: this
    // EFFECTS: adds clothing piece to collection
    private void addClothing() {
        int type = selectType();
        System.out.println("Enter name of item");
        String name = input.next();
        if (type == 1) {
            Clothes top = new Tops(name);
            closet.addClothes(top);
        } else if (type == 2) {
            Clothes bottom = new Bottoms(name);
            closet.addClothes(bottom);
        } else if (type == 3) {
            Clothes onePiece = new OnePiece(name);
            closet.addClothes(onePiece);
        } else if (type == 4) {
            Clothes shoes = new Shoes(name);
            closet.addClothes(shoes);
        } else if (type == 5) {
            Clothes accessory = new Accessories(name);
            closet.addClothes(accessory);
        }
    }

    // EFFECTS: prompts user to select type of clothing and returns it
    private int selectType() {
        int select = 0;
        while (!(select == 1 || select == 2 || select == 3 || select == 4 || select == 5)) {
            System.out.println("1 for Tops");
            System.out.println("2 for Bottoms");
            System.out.println("3 for One Pieces");
            System.out.println("4 for Shoes");
            System.out.println("5 for Accessories");
            select = input.nextInt();
        }
        return select;
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
