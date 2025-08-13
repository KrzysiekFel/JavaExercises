package org.coding.library;

public class ConsoleApp {
    boolean appRunning = true;
    MenuService menuService = new MenuService();

    public void run() {
        menuService.showWelcomeMenu();
        LibraryService libraryService = LibraryService.getInstance();


        while(this.appRunning) {
            String inputText = menuService.getTextInput();
            menuService.showMenuOptions();
            String chosenMenuOption = menuService.getChosenOption();
            switch (chosenMenuOption) {
                case "1":

                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    System.out.println("Bye Bye");
                    this.appRunning = false;
                default:
                    System.out.println("Wrong option, try again.");
            }

        }
    }
}
