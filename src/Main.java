import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe game");
        Scanner scanner = new Scanner(System.in);
        UIController uiController = new UIController(scanner);
        uiController.start();
    }
}