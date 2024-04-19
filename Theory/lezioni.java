/*

Lezione 1

JAVA: CREAZIONE ED ATTIVAZIONE DI THREAD
• quando si manda in esecuzione un programma JAVA
● la JVM crea un thread che invoca il metodo main del programma
● quindi esiste sempre almeno un thread per ogni programma, il main
• in seguito...
● altri thread sono attivati automaticamente da JAVA (gestore eventi,
interfaccia, garbage collector,...).
● ogni thread durante la sua esecuzione può creare ed attivare altri threads.
• due modalità per creare ed attivare esplicitamente un thread

*/

// Creare un thread soluzione 1

/*
definire un task
• creare un oggetto thread e passargli il
task definito che contiene il codice da
eseguire
• attivare il thread con una start()
per definire un task
• definire una classe che implementi
l'interfaccia Runnable
• creare un'istanza R di questa classe,
Questo è il task da passare al thread
*/

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/*public class lezioni {

  static class MyRunnable implements Runnable {

  public void run() {
	System.out.println("MyRunnable running");
	System.out.println("MyRunnable finished");
    }
}

  public static void main(String [] args) {
	Thread thread = new Thread (new MyRunnable());
	thread.start();
    } 
}

*/
/*
Runnable:
appartiene al package java.language
• contiene solo la segnatura del metodo void run(), che deve essere
implementato
• un'istanza della classe che implementa Runnable è un task
● un fragmento di codice che può essere eseguito in un thread
● la creazione del task non implica la creazione di un thread per lo esegua.
● lo stesso task può essere eseguito da più threads: un solo codice, più
esecutori
● il task viene passato al Thread che deve eseguirlo
*/

// Task definito con classe anonima

/*public class lezioni {
	public static void main(String[] args){
		Runnable runnable = new Runnable(){
			public void run(){
				System.out.println("Runnble running, nice");
				System.out.println("Runnable finished");
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
}
*/

// Creazione-attivazione di thread: soluzione 2

/*
creare una classe C che estenda
la classe predefinita Thread
● effettuare l'overriding del metodo
run()
● istanziare un oggetto di quella
classe
● questo oggetto è un thread
● il suo comportamento è quello
definito nel metodo run
ridefinito
● invocare il metodo start()
sull'oggetto istanziato.

Overriding:
● metodo in una sottoclasse
con lo stesso nome e
segnatura del metodo della
superclasse
● decisione a run-time su
quale metodo viene
invocare in base all'istanza
su cui si invoca il metodo
*/

public class lezioni implements Runnable {
	/*public static class MyThread extends Thread {
		public void run(){
			System.out.println("MyThread running");
            System.out.println("MyThread finished");
		}
	}

	public static void main(String [] args){
		MyThread myThread = new MyThread();
		myThread.start();
	}*/

	/*private int number;
	public lezioni(int number) {
		this.number = number;
	}

	public void run(){
		for (int i = 1; i <= 10; i++){
			System.out.printf("%s: %d * %d = %d\n", 
				Thread.currentThread().getName(), number, i, i*number);
		}
	}

	public static void main(String[] args){
	for (int i=1; i<=10; i++){
		lezioni lezioni = new lezioni(i);
		Thread thread = new Thread(lezioni);
		thread.start();
	}
	System.out.println("Avviato Calcolo Tabelline");
}*/

private int name;
public lezioni(int name) {this.name=name;}
public void run(){
	try {
		Long duration=(long)(Math.random()*10);
		System.out.printf("%s: Task %s: Starting a task during %d seconds\n",
			Thread.currentThread().getName(),name,duration);
		Thread.sleep(duration);
	}
	catch(InterruptedException e) {e.printStackTrace();}
	System.out.printf("%s: Task Finished %s \n",
		Thread.currentThread().getName(),name);}

public static void main(String[] args) {
// create the pool
ExecutorService service = Executors.newFixedThreadPool(10);
//submit the task for execution
for (int i =0; i<100; i++) {
service.execute(new lezioni(i)); }
System.out.println("Thread Name:"+
Thread.currentThread().getName());
}

}


/*
U Università degli Studi di Pisa
Dipartimento di Informatica
JAVA multithreading
ThreadPool
31Laura Ricci
LA CLASSE THREAD
• memorizza un riferimento all'oggetto Runnable, eventualmente
passato come parametro, nella variabile runnable
• definisce il metodo run( ) come segue
public void run( )
{ if (runnable != null)
runnable.run( ); }
*/