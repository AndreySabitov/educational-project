import downloader.Downloader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Downloader> downloaderList = List.of(
                new Downloader(), new Downloader(), new Downloader(), new Downloader(), new Downloader()
        );
        long time1 = System.nanoTime();
        for (Downloader downloader: downloaderList) {
            downloader.run();
        }
        long time2 = System.nanoTime();
        System.out.printf("Время последовательного выполнения = %d%n", Duration.ofNanos(time2 - time1).toMillis());

        List<Thread> threads = new ArrayList<>();
        long time3 = System.nanoTime();
        for (Downloader downloader: downloaderList) {
            Thread thread = new Thread(downloader);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread: threads) {
            thread.join();
        }
        long time4 = System.nanoTime();
        System.out.printf("Время параллельного выполнения = %d%n", Duration.ofNanos(time4 - time3).toMillis());
    }
}
