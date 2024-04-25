package ru.gb.springbootlesson11.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyMetric {

    private final Counter booksIssuedCounter;
    private final Counter requestsRejectedCounter;

    public MyMetric(MeterRegistry meterRegistry) {
        this.booksIssuedCounter = meterRegistry.counter("books_issued_counter");
        this.requestsRejectedCounter = meterRegistry.counter("requests_rejected_counter");
    }

    public void incrementBooksIssued() {
        this.booksIssuedCounter.increment();
    }

    public void incrementRequestsRejected() {
        this.requestsRejectedCounter.increment();
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void schedulingTask() {

        boolean isBookIssued = Math.random() < 0.8; 
        if (isBookIssued) {
            incrementBooksIssued(); 
            System.out.println("Книга выдана");
        } else {
            incrementRequestsRejected(); 
            System.out.println("Отказ в выдаче книги");
        }
    }
}
