package lld.webcrawler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manager {

    private int maxThreadPoolSize;
    private int maxThreadPerHost;
    private ExecutorService executorService;

    private List<String> seedUrls;

    public Manager(int maxThreadPoolSize, int maxThreadPerHost, List<String> seedUrls) {
        this.maxThreadPoolSize = maxThreadPoolSize;
        this.maxThreadPerHost = maxThreadPerHost;
        this.seedUrls = seedUrls;
        this.executorService = Executors.newFixedThreadPool(maxThreadPoolSize);
    }

    public void crawl() throws InterruptedException {
        for(String url : seedUrls){
            this.executorService.execute(new Crawler(url));
        }

        this.executorService.shutdown();
        this.executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }
}
