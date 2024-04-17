package components;

public class Carta {

    private int forcaValor;
    private char valor;
    private int forcaNaipe;
    private String naipe;
    private char charNaipe;

    public Carta() {
    }

    public Carta(int fv, int fn) {
        this.forcaValor = fv;
        this.forcaNaipe = fn;

        switch (this.forcaNaipe) {
            case 0:
                this.naipe = "Ouros";
                this.charNaipe = '♢';
                break;
            case 1:
                this.naipe = "Espadas";
                this.charNaipe = '♠';
                break;
            case 2:
                this.naipe = "Copas";
                this.charNaipe = '♡';
                break;
            case 3:
                this.naipe = "Paus";
                this.charNaipe = '♣';
                break;
        }

        switch (this.forcaValor) {
            case 0:
                this.valor = '4';
                break;
            case 1:
                this.valor = '5';
                break;
            case 2:
                this.valor = '6';
                break;
            case 3:
                this.valor = '7';
                break;
            case 4:
                this.valor = 'Q';
                break;
            case 5:
                this.valor = 'J';
                break;
            case 6:
                this.valor = 'K';
                break;
            case 7:
                this.valor = 'A';
                break;
            case 8:
                this.valor = '2';
                break;
            case 9:
                this.valor = '3';
                break;
        }

    }

    public int getForcaNaipe() {
        return forcaNaipe;
    }

    public int getForcaValor() {
        return forcaValor;
    }

    public String getNaipe() {
        return naipe;
    }

    public char getValor() {
        return valor;
    }

    public String cartaToString() {
        return this.getValor() + " de " + this.getNaipe();
    }

    public void setForcaValor(int forcaValor) {
        this.forcaValor = forcaValor;
    }

    public boolean compararValor(Carta compCarta) {
        if (this.forcaValor > compCarta.getForcaValor()
                || (this.forcaValor == compCarta.getForcaValor() && this.forcaNaipe > compCarta.getForcaNaipe()))
            return true;
        return false;
    }
}
