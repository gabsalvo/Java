import java.io.FileNotFoundException;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class with main method to start the thread and the other examples
public class theory {

  static void applyExample() {
    Esempio esempio = new Esempio();
    esempio.metodoPublic();
    System.out.printf("il numero di esempio pubblico è: %d\n", esempio.numeroPubblico);
  }

  static void applyMyRunnable() {
    MyRunnable runnable = new MyRunnable();
    Thread thread = new Thread(runnable);
    thread.start();
  }

  static void applyTask() {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    for (int i = 0; i < 5; i++) {
      Task task = new Task(i);
      executor.execute(task);
    }
    executor.shutdown();

  }

  static void applyCallable() throws ExecutionException, InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Future<String> futureResult = executorService.submit(new CallableExample());

    String result = futureResult.get();
    System.out.println(result);

    executorService.shutdown();
  }

  static void applyCounting(){
    Counter counter = new Counter();
    counter.increment();
    System.out.println("Current Count is:" + counter.count);
  }

  /*
   *
   * 3. Concurrent Collections*
   * In Java are thread-safe versions of standard collections that allow multiple threads to access and modify the data concurrently. For example, ConcurrentHashMap is a thread-safe version of HashMap.
   * These collections handle synchronization internally, providing optimized performance for concurrent access without the need for external synchronization by the programmer.
   *
   * Example of ConcurrentHashMap:
   *
   */

  static void applyConcurrentHashMap(){
    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    map.put("key", "value");
    String value = map.get("key");
    System.out.println(map);
    System.out.print(value);
  }

  static void applyBufferedReader() throws IOException {
    BufferReaderExample bufferReaderExample = new BufferReaderExample();
    BufferReaderExample.readMe();
  }


  public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
    applyExample();
    applyMyRunnable();
    applyTask();
    applyCallable();
    applyCounting();
    applyConcurrentHashMap();
    applyBufferedReader();
  }
  // end main
  // end theory class
}

/*
* BASICS
* We will go through some basic examples on how methods work.
* We've got to see featues like, static, private, public, final ecc ecc
* */
class Esempio {

  // Campo dati privato: visibile solo all'interno della classe 'Esempio'.
  private int numeroPrivato = 69;

  // Campo dati protetto: accessibile all'interno del pacchetto e dalle sottoclassi.
  protected int numeroProtetto = 90;

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

  // Metodo privato: accessibile solo all'interno della classe 'Esempio'
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

/*
* THREADS
*  Basic Concepts of Threads in Java
* 1. Creating Threads: There are two main ways to create a thread in Java:
* Extending the Thread class.
* Implementing the Runnable interface.
*
* 2. Thread Management: Java provides methods such as start(), run(), join(), sleep(), interrupt(), etc., to manage the life cycle of threads.
*
* 3. Synchronization: To manage access to shared resources, Java uses the concept of synchronization, which can be achieved with synchronized blocks or synchronized methods, using the synchronized keyword.
*
* 4. Locks: These are more advanced and flexible mechanisms for synchronization compared to using synchronized. Classes like ReentrantLock allow for finer control over locking.
*
* 5. Concurrent Collections: Java provides thread-safe collections such as ConcurrentHashMap, CopyOnWriteArrayList, etc., which allow safe access by multiple threads without the need for external locking.
*
* 6. Executor Framework: Introduced in Java 5, this framework abstracts the creation and management of threads through the concept of ExecutorService, which can handle pools of threads and assign tasks to them.
*
* Example of Thread with Runnable
* Here's a basic example of how to create and start a thread using the Runnable interface:
*
* */

class MyRunnable implements Runnable{
  public void run() {
    //This code will run in a new thread
    System.out.println("Hello, I'm thread work");
  }
}


// Using Runnable with an ExecutorService

class Task implements Runnable{

  private final int taskId;

    Task(int taskId) {
        this.taskId = taskId;
    }

    public void run(){
    System.out.println("Mr. Executor " + taskId + ", welcome");
    System.out.println("Task ID: " + taskId + " is running.");
  }

}

/*
 * 1. Thread Pools and Callable
 * The ExecutorService framework allows for efficient management of thread pools. While we have looked at running Runnable with an ExecutorService, there is also the Callable interface, which is similar to Runnable but can return a value and throw exceptions.
 * Callable is often used when you have tasks that can produce results, and it integrates well with the Future system provided by ExecutorService to retrieve these results.
 * Example of using Callable:
 *
 * */

class CallableExample implements Callable<String>{
  @Override
  public String call() throws Exception {
    // perform task and return result
    return "Result of the task";
  }
}

/*
 *
 * 2. Synchronization Primitives and Locks
 * Synchronization in Java is key when working with concurrency to avoid race conditions and other data access issues. The synchronized keyword can be used for methods or code blocks to ensure that only one thread at a time can execute that section of code.
 * For finer control, you can use explicit lock classes like ReentrantLock. These locks offer more features than synchronized, such as the ability to interrupt waiting for a lock, trying to get a lock without waiting indefinitely, and more.
 * Example of ReentrantLock:
 *
 *  */

class Counter {
  public int count = 0;
  private final Lock lock = new ReentrantLock();

  public void increment(){
    lock.lock();
    try{
      count++;
    } finally {
      lock.unlock();
    }
  }
}

/*
* Java Stream Types
* In Java, streams are categorized into four main types based on the data they operate on (byte or character) and their functionality (input or output):
*
* 1. Byte Streams: Work with binary data (bytes). They are useful for reading and writing binary files, such as images or any other file type where the data does not represent characters.
* Input: InputStream is the abstract base class for all byte input streams. Example implementations include FileInputStream for reading data from files.
* Output: OutputStream is the abstract base class for all byte output streams. An example implementation is FileOutputStream for writing data to files.
*
* 2. Character Streams: Designed for handling character data. They automatically handle the encoding and decoding to and from bytes according to the default charset or a specified charset.
* Input: Reader is the abstract base class for reading character streams. FileReader is a common implementation for reading character files.
* Output: Writer is the abstract base class for writing to character streams. FileWriter is used for writing characters to a file.
*
* Composing Streams
* Java allows you to "compose" or "chain" streams to combine functionality. For example, you can add buffering to a file input stream for more efficient reading, or you can chain a stream to a data stream to work directly with Java primitives or objects.
* Example 1: Adding Buffering to a File Reader
* Reading from a file one character at a time can be inefficient. Wrapping a FileReader in a BufferedReader provides a buffer to store characters and allows reading lines of text rather than individual characters, improving efficiency.
*
*
* */

class BufferReaderExample {
  public static void readMe() throws IOException {
    String filepath = "example.txt";

    try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}



















