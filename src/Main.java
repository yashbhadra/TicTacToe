import java.util.Scanner;


class Player{
        int id;
        String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Player(int id, String name){
            this.id= id ;
            this.name = name;
        }
}
class GameController{

    private static GameController  gameController;
    private char[][] board;

    public Player[] players;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
    public Player getPlayer1() {
        return this.players[0];
    }

    public Player getPlayer2() {
        return this.players[1];
    }

    private GameController(char[][] board){
        this.board = board;
    }

    public static GameController getInstance(){
        if(gameController==null){

            gameController = new GameController(new char[3][3]);
        }
        return gameController;
    }
    public void mark(Player player, int x, int y) throws Exception {

        if(player.getId()==1 && board[x][y]=='\0'){
            board[x][y]='X';
        }
        else if(player.getId()==2 && board[x][y]=='\0'){
            board[x][y]='O';
        }else{
            throw new Exception("Wrong input!");
        }
    }
    public Player checkWinner(){



      //horizontal
        for(int i=0;i<3;i++){
            if(board[i][0]!='\0' && board[i][0] == board[i][1] && board[i][0]==board[i][2]){
                return this.getPlayerByMark(board[i][0]);

            }
        }
        //vertical
        for(int i=0;i<3;i++){
            if(board[0][i]!='\0' && board[0][i] == board[1][i] && board[0][i]==board[2][0]){
                return this.getPlayerByMark(board[0][i]);
            }
        }
        //Diagonal

        if(board[0][0]!='\0' && board[0][0] == board[1][1] && board[2][2]==board[2][2]){
            return this.getPlayerByMark(board[0][0]);
        }

        if(board[0][2]!='\0' && board[0][2] == board[1][1] && board[0][2]==board[2][0]){
            return this.getPlayerByMark(board[0][2]);
        }

        return null;

    }

    private Player getPlayerByMark(char mark) {
        if(mark=='X') {
            return this.players[0];
        }else{
            return this.players[1];
        }
    }
    public void printBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(this.board[i][j]+" ");
            }
            System.out.println();
        }
    }
}

class UIController{

    public boolean gameOver;
    public Scanner scanner;
    public GameController gameController;

    private int counter;


    UIController(Scanner scanner){
        gameOver=false;
       this.scanner = scanner;
       this.gameController = GameController.getInstance();
    }
    public void start(){
        counter=0;
        this.initialisePlayers();
        Player currentPlayer = gameController.getPlayer1();
        while(!gameOver && counter<=9){
            System.out.println(currentPlayer.getName()+"'s turn  - (Please enter x and y co-ordinate)");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            try{
                gameController.mark(currentPlayer,x,y);
            } catch (Exception e) {
                System.out.println("Wrong input");
                continue;
            }


            Player winner = gameController.checkWinner();

            if(winner!=null){
                System.out.println(winner.getName()+" wins!");
                gameOver=true;
            }

            if(currentPlayer==gameController.getPlayer1()) {
                currentPlayer = gameController.getPlayer2();
            }else{
                currentPlayer=gameController.getPlayer1();
            }
            this.gameController.printBoard();
        }
        if(counter>=9){
            System.out.println("Its a Draw!");
        }
    }
    public void initialisePlayers(){
        System.out.println("Enter the player's name for X");
        String name1 = scanner.next();
        System.out.println("Enter the player's name for O");
        String name2 = scanner.next();
        this.gameController.setPlayers(new Player[]{ new Player(1,name1), new Player(2,name2)});
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe game");
        Scanner scanner = new Scanner(System.in);
        UIController uiController = new UIController(scanner);
        uiController.start();
    }
}