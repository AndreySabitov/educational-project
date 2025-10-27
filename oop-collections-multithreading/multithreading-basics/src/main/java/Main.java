import downloader.Downloader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Downloader> downloaderList = List.of(
                new Downloader(), new Downloader(), new Downloader(), new Downloader(), new Downloader()
        );

        testThreadAndRunnable(downloaderList);

        testExecutorService(downloaderList);
    }

    public static void testThreadAndRunnable(List<Downloader> downloaderList) throws InterruptedException {
        long time1 = System.nanoTime();
        for (Downloader downloader : downloaderList) {
            downloader.run();
        }
        long time2 = System.nanoTime();
        System.out.printf("Время последовательного выполнения = %d%n", Duration.ofNanos(time2 - time1).toMillis());

        List<Thread> threads = new ArrayList<>();
        long time3 = System.nanoTime();
        for (Downloader downloader : downloaderList) {
            Thread thread = new Thread(downloader);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long time4 = System.nanoTime();
        System.out.printf("Время параллельного выполнения = %d%n", Duration.ofNanos(time4 - time3).toMillis());
    }

    public static void testExecutorService(List<Downloader> downloaderList) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (Downloader downloader: downloaderList) {
                executorService.submit(downloader);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
