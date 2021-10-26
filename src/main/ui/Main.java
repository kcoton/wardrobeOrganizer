package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ClothingCollectionApp();
        } catch (FileNotFoundException e) {
            System.out.println("error: file not found");
        }
    }
}
