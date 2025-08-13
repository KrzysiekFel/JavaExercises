package org.coding.library;

import java.util.Scanner;

public class MenuService {
    Scanner scanner = new Scanner(System.in);

    public void showWelcomeMenu(){
        System.out.println("Hello!");
    }

    public String getTextInput() {
        System.out.print("\nPlease provide input: ");
        String inputText = this.scanner.nextLine();
        return inputText;
    }

    public String getChosenOption() {
        System.out.print("Choose option: ");
        String inputText = this.scanner.nextLine();
        return inputText.replaceAll(" ", "");
    }

    public void showMenuOptions(){
        System.out.println();
        System.out.println("Choose what do you want to do in Library (write a number):");
        System.out.println("1. Add User to Library.");
        System.out.println("2. Add book to Library.");
        System.out.println("3. Show Users.");
        System.out.println("4. Borrow a book for User.");
        System.out.println("5. Return book for User.");
        System.out.println("6. Show available books.");
        System.out.println("7. End");
    }
}

