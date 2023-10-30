import java.io.*;
import java.util.concurrent.*;
import java.util.zip.*;

public class DirectoryCompressor {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DirectoryCompressor <directory1> <directory2> ...");
            System.exit(1);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(NUM_THREADS);
        for (String directoryPath : args) {
            File directory = new File(directoryPath);
            if (directory.exists() && directory.isDirectory()) {
                compressDirectory(directory, threadPool);
            } else {
                System.out.println(directoryPath + " is not a valid directory.");
            }
        }

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Compression tasks did not finish within the timeout period.");
            }
        } catch (InterruptedException e) {
            System.out.println("Compression tasks interrupted.");
        }
    }

    private static void compressDirectory(File directory, ExecutorService threadPool) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    threadPool.submit(() -> compressFile(file));
                } else if (file.isDirectory()) {
                    compressDirectory(file, threadPool);
                }
            }
        }
    }

    private static void compressFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file.getPath() + ".gz");
            GZIPOutputStream gzipOS = new GZIPOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, len);
            }

            gzipOS.close();
            fos.close();
            fis.close();

            System.out.println("The file " + file.getName() + " has been compressed successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while compressing the file " + file.getName());
            e.printStackTrace();
        }
    }
}
