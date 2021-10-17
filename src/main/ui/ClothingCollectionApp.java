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
            displayTagMenu();
            command = input.nextInt();
            if (command == 1) {
                addTags();
            } else if (command == 2) {
                viewTags();
            } else if (command == 3) {
                deleteTags();
            }
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
        int type = selectType();
        String category = getCategory(type);
        Clothes piece = selectClothing(category);
        ArrayList<String> tags = piece.getTags();
        System.out.println("tags: ");
        for (String next : tags) {
            System.out.print("#" + next + " ");
        }
        System.out.println();
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
        int type = selectType();
        String category = getCategory(type);
        Clothes piece = selectClothing(category);
        System.out.println("Enter a tag for " + piece.getName());
        Scanner scan = new Scanner(System.in);
        String tag = scan.next();
        piece.addTag(tag);
    }

    // EFFECTS: returns a specific piece of clothing
    private Clothes selectClothing(String category) {
        ArrayList<Clothes> list = closet.getCloset();
        ArrayList<Clothes> select = new ArrayList<>();
        int i = 0;
        for (Clothes item : list) {
            if (item.getClass().getName().substring(6).equals(category)) {
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
        int type = selectType();
        printClothingCategoryList(type);
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

    // EFFECTS: returns the name of the clothing category
    private String getCategory(int type) {
        if (type == 1) {
            return "Tops";
        } else if (type == 2) {
            return "Bottoms";
        } else if (type == 3) {
            return "OnePieces";
        } else if (type == 4) {
            return "Shoes";
        }
        return "Accessories";
    }

    // EFFECTS: prints all clothing in a specific category
    private void printClothingCategoryList(int type) {
        String category = getCategory(type);
        ArrayList<Clothes> list = closet.getCloset();
        for (Clothes item : list) {
            if (item.getClass().getName().substring(6).equals(category)) {
                System.out.println(item.getName());
            }
        }
    }

    // EFFECTS: lists all clothes in the closet and total number
    private void listClothing() {
        ArrayList<Clothes> list = closet.getCloset();
        for (Clothes item : list) {
            System.out.println(item.getName() + ", Category: " + item.getClass().getName().substring(6));
        }
        System.out.print("-------------------------------------"
                + "\nTotal Number of Clothes: " + closet.getTotalNumOfClothes() + "\n");
    }

    // MODIFIES: this
    // EFFECTS: adds clothing piece to collection
    private void addClothing() {
        int type = selectType();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of item");
        String name = "";
        name += scan.nextLine();

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
            System.out.println("1 -> tops");
            System.out.println("2 -> bottoms");
            System.out.println("3 -> one pieces");
            System.out.println("4 -> shoes");
            System.out.println("5 -> accessories");
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
