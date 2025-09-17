package src;

import src.ui.MainConsole;

public class Main {

    public static void main(String[] args) {
        Bootstrap.run();

        MainConsole app = new MainConsole();

        app.offMenu();
    }

}
