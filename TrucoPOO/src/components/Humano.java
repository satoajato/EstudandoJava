package components;

import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jogador {

    public Scanner entrada = new Scanner(System.in);

    public Humano(String nomeJog, int numJog) {
        super(nomeJog, numJog);
        this.nome = nomeJog;
        this.numJogador = numJog;
        this.pontos = 0;
    }

    public int fazerTentativa(ArrayList<Carta> listaMao) {
        do {
            System.out.println("\n" + this.cartasToString());
            System.out.print("Carta: ");
            jogada = entrada.nextInt();
            if (jogada > listaMao.size() || jogada < 1) {
                System.out.println("Número inválido. É 1, 2 ou 3 (de acordo com as cartas na mão)");
            }
        } while (jogada > listaMao.size() || jogada < 1);

        return jogada - 1;
    }

    @Override
    public Carta jogarCarta(Jogada[] arrayJogadas) {
        int cardJogado = fazerTentativa(this.cartas);
        Carta cartaJogada = this.cartas.remove(cardJogado);
        System.out.println(this.nome + " jogou a carta: " + cartaJogada.cartaToString());
        if (arrayJogadas[1] != null) {
            System.out.println();
            System.out
                    .println(cartaJogada.cartaToString() + " X " + arrayJogadas[1].getCartaJogada().cartaToString());
        }
        return cartaJogada;
    }

    @Override
    public int escolhaAcao(int points) {
        int acao = 0;
        System.out.println("\n\n-------------------------------------------------------------");

        if (points >= 12 || this.pediuTruco) {
            do {
                System.out.println("FAÇA SUA AÇÃO: 1 - JOGAR (ACEITAR) | 2 - CORRER");
                System.out.print("Opção: ");
                acao = entrada.nextInt();
                if (acao != 1 && acao != 2) {
                    System.out.println("Opção inválida! Tente novamente");
                }
            } while (acao != 1 && acao != 2);
        } else {
            do {
                System.out.println("FAÇA SUA AÇÃO: 1 - JOGAR (ACEITAR) | 2 - CORRER | 3- TRUCAR (6,9,12)");
                System.out.print("Opção: ");
                acao = entrada.nextInt();
                if (acao != 1 && acao != 2 && acao != 3) {
                    System.out.println("Opção inválida! Tente novamente");
                }
            } while (acao != 1 && acao != 2 && acao != 3);
        }
        return acao;
    }

}
