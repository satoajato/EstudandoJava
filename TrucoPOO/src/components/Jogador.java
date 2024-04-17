package components;

import java.util.ArrayList;

public abstract class Jogador {

    protected String nome;
    protected int numJogador;
    protected ArrayList<Carta> cartas;
    protected int pontos;
    protected int jogada;
    protected int mediaCartas;
    protected boolean pediuTruco;

    public Jogador(String nomeJog, int numJog) {
        this.nome = nomeJog;
        this.numJogador = numJog;
        this.pontos = 0;
        this.pediuTruco = false;
    }

    public abstract int escolhaAcao(int vAcao);

    public abstract Carta jogarCarta(Jogada[] arrayJogadas);

    public void setMediaCartas(int mediaCartas) {
        this.mediaCartas = mediaCartas;
    }

    public int getMediaCartas() {
        return mediaCartas;
    }

    public void setCartas(ArrayList<Carta> cm) {
        this.cartas = cm;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public boolean getPediuTruco() {
        return pediuTruco;
    }

    public void setPediuTruco(boolean pediuTruco) {
        this.pediuTruco = pediuTruco;
    }

    public String cartasToString() {
        String cartasNaMao = "Você tem " + this.cartas.size() + " carta(s) na mão: ";
        cartasNaMao += "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

        for (Carta card : this.cartas) {
            cartasNaMao += "\n| \t   " + (this.cartas.indexOf(card) + 1) + " - " + card.getValor() + " de "
                    + card.getNaipe()
                    + "\t    | ";
        }
        cartasNaMao += "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

        return cartasNaMao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setManilha(Carta vira) {
        for (Carta manilha : this.cartas) {
            if ((vira.getForcaValor() + 1) % 10 == manilha.getForcaValor()) {
                manilha.setForcaValor(10);
            }
        }
    }

    public int getNumJogador() {
        return numJogador;
    }

    public void setNumJogador(int numJogador) {
        this.numJogador = numJogador;
    }
}
