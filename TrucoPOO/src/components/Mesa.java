package components;

import java.util.ArrayList;
import java.util.Stack;

public class Mesa {

    private Baralho baralho;
    private int pontosRodada;
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean vezAcao;
    private Carta vira;
    private int turno;

    private Jogada[] cartasJogadas;

    private Stack<Integer> pilhaAcoes;
    private ArrayList<Integer> vencedorTurnos;

    public Mesa(int jogQCmc, Jogador j1, Jogador j2) {
        this.turno = 0;
        this.pontosRodada = 1;
        this.baralho = new Baralho();
        this.jogador1 = j1;
        this.jogador2 = j2;
        if (jogQCmc == 1) {
            this.vezAcao = false;
        } else
            this.vezAcao = true;

        this.pilhaAcoes = new Stack<>();

        this.vencedorTurnos = new ArrayList<>();

        this.cartasJogadas = new Jogada[2];
    }

    public int realizarRodada() {
        inicializarRodada();

        while (checarVencedorRodada(this.vencedorTurnos) == 0) {
            this.turno++;
            if (this.cartasJogadas[0] == null && this.cartasJogadas[1] == null) {
                System.out.println(
                        "\nEsse é o turno: " + (this.turno / 2 + 1) + "\nEssa é a vira: "
                                + this.vira.cartaToString() + "\nA rodada está valendo: " + this.pontosRodada + "\n");
            }

            while (this.pilhaAcoes.empty() || this.pilhaAcoes.peek().equals(3)) {
                if (this.vezAcao == false) {
                    if (this.jogador1.getCartas().size() > 0)
                        System.out.println(this.jogador1.cartasToString());
                    if (fazerAcao(this.jogador1, this.jogador2) == 1)
                        break;
                } else {
                    if (fazerAcao(this.jogador2, this.jogador1) == 1)
                        break;
                }
                this.vezAcao = !this.vezAcao;
            }
            if (this.pilhaAcoes.peek().equals(1)) { // quer dizer que vai jogar ou aceitou o truco/6/9/12
                this.pilhaAcoes.pop();
                while (!this.pilhaAcoes.isEmpty()) {
                    if (this.pilhaAcoes.pop().equals(3))
                        aumentarPontos();
                }
                this.pilhaAcoes.clear();
                if (this.vezAcao == false) {
                    Carta cartaJ1 = this.jogador1.jogarCarta(this.cartasJogadas);
                    Jogada jogada1 = new Jogada(this.jogador1, cartaJ1);
                    this.cartasJogadas[0] = jogada1;
                } else {
                    Carta cartaJ2 = this.jogador2.jogarCarta(this.cartasJogadas);
                    Jogada jogada2 = new Jogada(this.jogador2, cartaJ2);
                    this.cartasJogadas[1] = jogada2;

                }
                this.vezAcao = !this.vezAcao;
            } else { // quer dizer que correu
                this.pilhaAcoes.clear();
                this.vencedorTurnos.clear();
                if (this.vezAcao == false) {
                    return declararVencedor(this.jogador2);
                } else {
                    return declararVencedor(this.jogador1);
                }
            }

            if (this.cartasJogadas[0] != null && this.cartasJogadas[1] != null) { // this.listaCartasJogadas.size() % 2
                                                                                  // == 0
                Carta carta1 = this.cartasJogadas[0].getCartaJogada();
                String jogNome1 = this.cartasJogadas[0].getJogador().getNome();
                Carta carta2 = this.cartasJogadas[1].getCartaJogada();
                String jogNome2 = this.cartasJogadas[1].getJogador().getNome();

                if (carta1.compararValor(carta2) && jogNome1.equals(this.jogador1.getNome())) {
                    System.out.println(jogNome1 + " GANHOU O TURNO\n");
                    this.vencedorTurnos.add(1);
                    this.vezAcao = false;
                } else if (carta2.compararValor(carta1) && jogNome2.equals(this.jogador2.getNome())) {
                    System.out.println(jogNome2 + " GANHOU O TURNO\n");
                    this.vencedorTurnos.add(2);
                    this.vezAcao = true;
                }
                this.cartasJogadas = new Jogada[2];
            }
        }
        int jogadorNum = checarVencedorRodada(this.vencedorTurnos);
        System.out.println("\n");
        if (jogadorNum == 1) {
            return declararVencedor(this.jogador1);

        } else if (jogadorNum == 2) {
            return declararVencedor(this.jogador2);
        }
        return jogadorNum;
    }

    private String trucoToString() {
        switch (this.pontosRodada) {
            case 1:
                return "TRUCO";
            case 3:
                return "SEIS";
            case 6:
                return "SEIS";
            case 9:
                return "DOZE";
        }
        return "TRUCO";
    }

    private int fazerAcao(Jogador j1, Jogador j2) {
        this.pilhaAcoes.push(j1.escolhaAcao(this.pontosRodada));
        if (this.pilhaAcoes.peek().equals(1) && this.pilhaAcoes.size() % 2 == 0) {
            System.out.println(" - - - \t" + j1.getNome() + " PEDIU PRA DESCER \t - - - \n");
            this.vezAcao = !this.vezAcao;
            return 1;
        } else if (this.pilhaAcoes.peek().equals(3)) {
            j1.setPediuTruco(true);
            j2.setPediuTruco(false);

            this.pilhaAcoes.pop().equals(3);
            if (this.pilhaAcoes.size() > 0 && this.pilhaAcoes.peek().equals(3))
                aumentarPontos();
            System.out.println(" - - - \t" + j1.getNome() + " PEDIU " + trucoToString() + "\t - - - ");
            this.pilhaAcoes.push(3).equals(3);
            return 0;
        } else if (this.pilhaAcoes.peek().equals(1)) {
            System.out.println(" - - - \t" + j1.getNome() + " VAI JOGAR \t - - - ");
            return 1;
        } else {
            System.out.println(" - - - \t" + j1.getNome() + " CORREU! \t - - - ");
            return 1;
        }
    }

    private int declararVencedor(Jogador j1) {
        System.out.println(
                " *¨*¨* \t" + j1.getNome() + " GANHOU " + this.pontosRodada + " PONTOS! \t*¨*¨* ");
        atualizarPontos(j1);
        return j1.getNumJogador();
    }

    private int checarVencedorRodada(ArrayList<Integer> lista) {
        if (lista.size() < 2)
            return 0;
        else if (lista.size() == 2 && lista.get(0).equals(lista.get(1)))
            return lista.get(lista.size() - 1);
        else if (lista.size() == 3)
            return lista.get(lista.size() - 1);
        else
            return 0;
    }

    private void inicializarRodada() {
        this.baralho.criarBaralho();
        this.baralho.embaralhar();
        this.jogador1.setCartas(this.baralho.distribuirCartas());
        this.jogador2.setCartas(this.baralho.distribuirCartas());
        this.vira = this.baralho.removerCarta();
        this.jogador1.setManilha(vira);
        this.jogador2.setManilha(vira);
        this.jogador1.setPediuTruco(false);
        this.jogador2.setPediuTruco(false);
    }

    public void aumentarPontos() {
        switch (this.pontosRodada) {
            case 1:
                this.pontosRodada = 3;
                break;
            case 3:
                this.pontosRodada = 6;
                break;
            case 6:
                this.pontosRodada = 9;
                break;
            case 9:
                this.pontosRodada = 12;
                break;
        }
    }

    public void atualizarPontos(Jogador player) {
        int novaPontuacao = player.getPontos() + this.pontosRodada;
        player.setPontos(novaPontuacao);
    }

}
