package elecciones.solucionB;

public class Pizarra {

    private static final String EN_BLANCO = "En blanco";
    private static final String NULO = "Nulo";

    private String[] nombres;
    private int[] votos;
    private int candidatos;

    public Pizarra(String[] candidatos) {
        this.candidatos = candidatos.length;
        this.nombres = new String[this.candidatos + 2];
        for (int i = 0; i < this.candidatos; i++) {
            this.nombres[i] = candidatos[i];
        }
        this.nombres[this.candidatos] = EN_BLANCO;
        this.nombres[this.candidatos + 1] = NULO;
        this.votos = new int[this.nombres.length];
    }

    public void anotar(String voto) {
        this.votos[this.posicion(voto)]++;
    }

    public void corregir(String voto) {
        int i = this.posicion(voto);
        if (this.votos[i] > 0) {
            this.votos[i]--;
        }
    }

    private int posicion(String voto) {
        if (voto.isEmpty()) {
            return this.candidatos;
        }
        for (int i = 0; i < this.candidatos; i++) {
            if (this.nombres[i].equals(voto)) {
                return i;
            }
        }
        return this.candidatos + 1;
    }

    public void mostrar() {
        System.out.println("PIZARRA" + "-".repeat(18));
        for (int i = 0; i < this.nombres.length; i++) {
            System.out.println(String.format("%-10s", this.nombres[i]) + " " + "|".repeat(this.votos[i]) + " (" + this.votos[i] + ")");
        }
        System.out.println("-".repeat(25));
    }

    public void proclamar() {
        int maximo = 0;
        int ganador = -1;
        boolean empate = false;
        for (int i = 0; i < this.candidatos; i++) {
            if (this.votos[i] > maximo) {
                maximo = this.votos[i];
                ganador = i;
                empate = false;
            } else if (this.votos[i] == maximo && maximo > 0) {
                empate = true;
            }
        }
        if (ganador == -1) {
            System.out.println("Ningún candidato ha recibido votos");
        } else if (empate) {
            System.out.println("Empate a " + maximo + " votos: hay que repetir la votación");
        } else {
            System.out.println("Delegado electo: " + this.nombres[ganador] + " con " + maximo + " votos");
        }
    }
}
