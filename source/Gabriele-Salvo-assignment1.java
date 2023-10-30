class prime10Mil implements Runnable {
     private boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
        if (n % i == 0) return false;
    }
    return true;
}

    @Override
    public void run() {
        long startTime = System.nanoTime();
        int count = 0;
        for (int i = 2; i < 10_000_000; i++) {
            if (isPrime(i)) count++;
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Thread " + Thread.currentThread().getName() + " finished. Prime count: " + count);
        System.out.println("Execution Time: " + elapsedTime / 1_000_000 + " ms");

    
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please specify the number of threads as a command-line argument.");
            return;
        }

        int numOfThreads;
        try {
            numOfThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of threads specified.");
            return;
        }

        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(new prime10Mil());
            thread.start();
        }
    }
}
