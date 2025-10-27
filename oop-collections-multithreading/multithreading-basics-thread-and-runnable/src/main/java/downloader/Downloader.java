package downloader;

public class Downloader implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.printf("Скачали файл в потоке %s%n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
