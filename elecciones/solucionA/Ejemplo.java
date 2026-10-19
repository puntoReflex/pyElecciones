package elecciones.solucionA;

class Ejemplo {
    public static void main(String[] args) {

        List presentes = new List();
        presentes.insertEnd("Juanito");
        presentes.insertEnd("Pepito");
        presentes.insertEnd("Anita");
        presentes.insertEnd("Luisito");
        presentes.insertEnd("Marta");
        presentes.insertEnd("Rosa");

        presentes.delete("Luisito");
        presentes.insertEnd("Carlos");
        viewLine("PRESENTES", presentes.listAll());

        List candidaturas = new List();
        proponer(candidaturas, presentes, "Anita");
        proponer(candidaturas, presentes, "Pepito");
        proponer(candidaturas, presentes, "Luisito");
        proponer(candidaturas, presentes, "Marta");
        candidaturas.delete("Marta");

        String[] candidatos = candidaturas.listAll();
        viewLine("CANDIDATOS", candidatos);

        Pizarra pizarra = new Pizarra(candidatos);
        Queue urna = new Queue();

        votar(urna, presentes, "Juanito", "Anita");
        votar(urna, presentes, "Pepito", "Pepito");
        votar(urna, presentes, "Anita", "Anita");
        votar(urna, presentes, "Marta", "");
        votar(urna, presentes, "Rosa", "Anita");
        votar(urna, presentes, "Carlos", "Batman");
        votar(urna, presentes, "Luisito", "Anita");

        System.out.println("Las papeletas se leen en el orden en que se recogieron");
        while (!urna.isEmpty()) {
            String voto = urna.dequeue();
            System.out.println("Secretario: " + (voto.isEmpty() ? "en blanco" : voto));
            pizarra.anotar(voto);
        }

        pizarra.mostrar();
        pizarra.proclamar();
    }

    static void proponer(List candidaturas, List presentes, String alumno) {
        if (presentes.contains(alumno) && !candidaturas.contains(alumno)) {
            candidaturas.insertEnd(alumno);
        } else {
            System.out.println("Candidatura rechazada: " + alumno);
        }
    }

    static void votar(Queue urna, List presentes, String alumno, String papeleta) {
        if (presentes.contains(alumno)) {
            urna.enqueue(papeleta);
        } else {
            System.out.println("Papeleta rechazada: " + alumno + " no consta como presente");
        }
    }

    static void viewLine(String titulo, String[] elementos) {
        System.out.println(titulo + "-".repeat(25 - titulo.length()));
        for (String elemento : elementos) {
            System.out.println(elemento);
        }
        System.out.println("-".repeat(25));
    }
}
