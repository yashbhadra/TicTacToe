import java.util.Scanner;

class UIController {

    public boolean gameOver;
    public Scanner scanner;
    public GameController gameController;

    private int counter;


    UIController(Scanner scanner) {
        gameOver = false;
        this.scanner = scanner;
        this.gameController = GameController.getInstance();
    }

    public void start() {
        counter = 0;
        this.initialisePlayers();
        Player currentPlayer = gameController.getPlayer1();
        while (!gameOver && counter <= 9) {
            System.out.println(currentPlayer.getName() + "'s turn  - (Please enter x and y co-ordinate)");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            try {
                gameController.mark(currentPlayer, x, y);
            } catch (Exception e) {
                System.out.println("Wrong input");
                continue;
            }


            Player winner = gameController.checkWinner();

            if (winner != null) {
                System.out.println(winner.getName() + " wins!");
                gameOver = true;
            }

            if (currentPlayer == gameController.getPlayer1()) {
                currentPlayer = gameController.getPlayer2();
            } else {
                currentPlayer = gameController.getPlayer1();
            }
            this.gameController.printBoard();
        }
        if (counter >= 9) {
            System.out.println("Its a Draw!");
        }
    }

    public void initialisePlayers() {
        System.out.println("Enter the player's name for X");
        String name1 = scanner.next();
        System.out.println("Enter the player's name for O");
        String name2 = scanner.next();
        this.gameController.setPlayers(new Player[]{new Player(1, name1), new Player(2, name2)});
    }
}
