import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class UfficioPostale {
    private static final int NUMERO_SPORTELLI = 4;
    private static final int K_PERSONE = 3;
    private static final int TEMPO_CHIUSURA_SPORTELLO = 5; // tempo in secondi
    
    private int activeSportelli = NUMERO_SPORTELLI; 
    private Queue<Cliente> salaAttesa = new LinkedList<>();
    private ExecutorService sportelli = Executors.newFixedThreadPool(NUMERO_SPORTELLI);
    private ScheduledExecutorService controlloSportelli = Executors.newScheduledThreadPool(1);

    public UfficioPostale() {
        controlloSportelli.scheduleAtFixedRate(() -> {
            // Check if all sportelli are inactive (inactiveCount == NUMERO_SPORTELLI)
            ThreadPoolExecutor executor = (ThreadPoolExecutor) sportelli;
            if (executor.getActiveCount() == 0 && activeSportelli > 0) {
                System.out.println("Nessun cliente da un po'. Chiusura di uno sportello.");
                activeSportelli--;
                sportelli.shutdown(); // Shutdown gracefully
        
                // Wait for termination
                try {
                    sportelli.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        
                // Now create a new ThreadPool with reduced sportelli count
                sportelli = Executors.newFixedThreadPool(activeSportelli);
            }
        }, 0, TEMPO_CHIUSURA_SPORTELLO, TimeUnit.SECONDS);
    }

    public void gestisciClienti() {
        // Thread per aggiungere clienti continuamente
        new Thread(() -> {
            int numeroCliente = 1;
            while (true) {
                salaAttesa.add(new Cliente(numeroCliente++));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Main thread per gestire clienti ai sportelli
        while (true) {
            for (int i = 0; i < K_PERSONE && !salaAttesa.isEmpty(); i++) {
                sportelli.execute(salaAttesa.poll());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Cliente implements Runnable {
        private int numero;

        Cliente(int numero) {
            this.numero = numero;
        }

        @Override
        public void run() {
            System.out.println("Il cliente " + numero + " è allo sportello.");
            try {
                TimeUnit.SECONDS.sleep((int) (Math.random() * 5 + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Il cliente " + numero + " ha finito.");
        }
    }

    public static void main(String[] args) {
        UfficioPostale ufficio = new UfficioPostale();
        ufficio.gestisciClienti();
    }
}
