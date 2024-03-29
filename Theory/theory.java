public class theory {
   public static void main(String[] args) {
     Esempio esempio = new Esempio();
     esempio.metodoPublic();
     System.out.printf("il numero di esempio pubblico è: %d\n", esempio.numeroPubblico);
   }
}

class Esempio {

  // Campo dati privato: visibile solo all'interno della classe 'Esempio'.
  private int numeroPrivato = 69;

  // Campo dati protetto: accessibile all'interno del pacchetto e dalle sottoclassi.
  protected int numeroProgetto = 90;

  // Campo dati pubblico: accessibile da qualsiasi classe.
  public int numeroPubblico = 80;

  // Metodo statico: appartiene alla classe 'Esempio', non a un'istanza specifica.
  public static void metodoStatico(){
    System.out.println("Sono un metodo statico");
  }

  // Metodo finale: non può essere sovrascritto da sottoclassi.
  public final void metodoFinal(){
    System.out.println("This is my final form");
  }

  // Metodo privato: accessibile solo all'interno della classe 'Esempio'.
  private void metodoPrivate(){
    System.out.println("Sono un metodo privato privatissim");
  }

  // Metodo pubblico: accessibile da qualsiasi altra classe.
  public void metodoPublic(){
    System.out.println("Ehi io sono pubblic");
  }

  public int getNumeroPrivato() {
    return numeroPrivato;
  }

  public void setNumeroPrivato(int numeroPrivato) {
    this.numeroPrivato = numeroPrivato;
  }
}


