package components;

import java.util.ArrayList;
import java.util.Random;

// import java.util.Random;

public class Computador extends Jogador {

    public Computador(String nomeJog, int numJog) {
        super(nomeJog, numJog);
        this.nome = nomeJog;
        this.numJogador = numJog;
        this.pontos = 0;
    }

    @Override
    public int escolhaAcao(int points) {

        Random rand = new Random();
        int chooseAction = 0;
        if (rand.nextInt(100) < 16) { // 16% do computador fazer uma ação aleatória para o jogo ter mais graça
            chooseAction = rand.nextInt(10) + 1;
            System.out.println("***** Jogada Aleatória *****");
        } else {
            int somaForca = 0;
            for (Carta carta : this.cartas) {
                somaForca += carta.getForcaValor();
            }
            if (this.getMediaCartas() < somaForca / this.cartas.size()) {
                setMediaCartas(somaForca / this.cartas.size());
            }
            chooseAction = getMediaCartas();
        }

        if (points == 12 || this.pediuTruco) {
            if (chooseAction > 4) {
                return 1;
            } else if (chooseAction == 4) {
                if (rand.nextInt(2) == 1) // quando a media for ruim tem 50% de chance dele correr ou jogar
                    return 1;
                else
                    return 2;
            } else
                return 2;
        } else if (points < 12) {
            if (chooseAction > 8) {
                return 3;
            } else if (chooseAction > 4) {
                return 1;
            } else if (chooseAction == 4) {
                if (rand.nextInt(2) == 1) // quando a media for ruim tem 50% de chance dele correr ou jogar
                    return 1;
                else
                    return 2;
            } else
                return 2;
        }
        return 1;

    }

    @Override
    public Carta jogarCarta(Jogada[] arrayJogadas) {
        int max = 0;
        int min = 11;
        Carta indexJogada = new Carta();

        // Random rand = new Random();
        if (arrayJogadas[0] != null) {
            Carta cartaOponente = arrayJogadas[0].getCartaJogada();
            ArrayList<Carta> opcoes = new ArrayList<>();
            Carta pesquisaJogada = new Carta(11, 5);

            // escolhe a menor carta que ganha da carta jogada do oponente
            for (Carta opt : this.cartas) {
                int forcaTotalOpcao = opt.getForcaValor() * 10 + opt.getForcaNaipe();
                int forcaTotalAdver = cartaOponente.getForcaValor() * 10 + cartaOponente.getForcaNaipe();

                if (forcaTotalOpcao > forcaTotalAdver) {
                    opcoes.add(opt);
                    for (Carta escolha : opcoes) {
                        if (escolha.getForcaValor() < pesquisaJogada.getForcaValor()) {
                            pesquisaJogada = escolha;
                        }
                    }
                }
            }

            // nao tem carta mais forte que o oponente
            // -> descarta a mais fraca
            if (pesquisaJogada.getForcaValor() == 11) {
                for (Carta escolha : this.cartas) {
                    if (escolha.getForcaValor() < min) {
                        pesquisaJogada = escolha;
                        min = escolha.getForcaValor();
                    }
                }
            }
            indexJogada = pesquisaJogada;
        } else {
            for (Carta escolha : this.cartas) {
                if (escolha.getForcaValor() > max) {
                    indexJogada = escolha;
                    max = escolha.getForcaValor();
                }
            }
        }
        int cardJogado = this.cartas.indexOf(indexJogada);
        Carta cartaJogada = this.cartas.remove(cardJogado);
        System.out.println(this.nome + " jogou a carta: " + cartaJogada.cartaToString());

        if (arrayJogadas[0] != null) {
            System.out.println();
            System.out
                    .println(
                            arrayJogadas[0].getCartaJogada().cartaToString() + "   X   " + cartaJogada.cartaToString());
        }
        return cartaJogada;
    }

}
