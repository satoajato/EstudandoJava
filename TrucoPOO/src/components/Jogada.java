package components;

public class Jogada {
    private Jogador jogador;
    private Carta cartaJogada;

    public Jogada(Jogador j, Carta cj) {
        this.jogador = j;
        this.cartaJogada = cj;
    }

    public Carta getCartaJogada() {
        return cartaJogada;
    }

    public Jogador getJogador() {
        return jogador;
    }
}
