package components;

import java.util.Scanner;

public class Game {

    private Mesa mesa;
    private int rodada = 1;
    private Jogador jogador1;
    private Jogador jogador2;
    public Scanner entrada = new Scanner(System.in);
    private int ultimoGanhador = 1;

    public Game() {
        iniciarPartida();
        while (Jogar())
            ;
    }

    public void iniciarPartida() {
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Você é o Jogador 1!");
        System.out.print("Digite seu nome: ");

        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();

        this.jogador1 = new Humano(nome, 1);
        System.out.println("---------------------------------------------------");
        System.out.println("O Jogador 2 vai ser o PC");
        this.jogador2 = new Computador("PC", 2);

        // System.out.println("Quem vai ser o Jogador 2?");
        // int numPlayer = escolherJogador();
        // if (numPlayer == 1) {
        // this.jogador2 = new Computador("PC", 2);
        // } else if (numPlayer == 2) {
        // System.out.println("Você é o Jogador 2!");
        // System.out.print("Digite seu nome: ");
        // String nome2 = sc.nextLine();
        // this.jogador1 = new Humano(nome2, 2);
        // }
    }

    public int escolherJogador() {
        int opcao = 0;
        do {
            System.out.println("1. PC");
            System.out.println("2. Humano\n");
            System.out.print("Opção: ");
            opcao = entrada.nextInt();
            if (opcao != 1 && opcao != 2) {
                System.out.println("Opção inválida! Tente novamente");
            }
        } while (opcao != 1 && opcao != 2);
        return opcao;
    }

    public boolean Jogar() {
        if (verificaGanhou() == 0) {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("\nRodada " + rodada);
            System.out.println(this.jogador1.getNome() + " " + this.jogador1.getPontos() + " x "
                    + this.jogador2.getPontos() + " " + this.jogador2.getNome());
            this.mesa = new Mesa(this.ultimoGanhador, jogador1, jogador2);
            this.ultimoGanhador = this.mesa.realizarRodada();
            this.jogador2.setMediaCartas(0);
            rodada++;
        } else {
            if (verificaGanhou() == 1) {
                System.out.println("=-=-=-= " + jogador1.getNome() + " VENCEU O JOGO!!! =-=-=-=");
                return false;
            } else if (verificaGanhou() == 2) {
                System.out.println("=-=-=-= " + jogador2.getNome() + " VENCEU O JOGO!!! =-=-=-=");
                return false;
            }
        }
        return true;
    }

    public int verificaGanhou() {
        if (jogador1.getPontos() >= 12) {
            return 1;
        } else if (jogador2.getPontos() >= 12) {
            return 2;
        }
        return 0;
    }
}