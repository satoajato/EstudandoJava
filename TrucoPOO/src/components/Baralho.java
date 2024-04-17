package components;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    private ArrayList<Carta> cartasBaralho = new ArrayList<Carta>();

    public void criarBaralho() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                this.cartasBaralho.add(new Carta(j, i));
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(this.cartasBaralho);
    }

    public ArrayList<Carta> distribuirCartas() {
        Carta carta1 = this.cartasBaralho.remove(0);
        Carta carta2 = this.cartasBaralho.remove(0);
        Carta carta3 = this.cartasBaralho.remove(0);

        ArrayList<Carta> mao = new ArrayList<>();

        mao.add(carta1);
        mao.add(carta2);
        mao.add(carta3);

        return mao;
    }

    public Carta removerCarta() {
        return this.cartasBaralho.remove(0);
    }

}
