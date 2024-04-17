import java.util.Scanner;

import components.Game;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("|                                                 |");
        System.out.println("|   Olá bem vindo ao TRUCO ORIENTADO A OBJETO!    |");
        System.out.println("|                                                 |");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        System.out.println("\n\nDESEJA JOGAR?   1-SIM   ||   2-NÃO");
        int x = sc.nextInt();
        do {
            Game p = new Game();

            System.out.println("\n\nJOGAR NOVAMENTE?   1-SIM   ||   2-NÃO");
            x = sc.nextInt();
        } while (x == 1);
        sc.close();
    }
}
