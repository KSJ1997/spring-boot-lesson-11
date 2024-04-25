package ru.gb.springbootlesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson11.metric.MyMetric;



@Service
public class BookIssuingService {

    private final MyMetric myMetric;

    @Autowired
    public BookIssuingService(MyMetric myMetric) {
        this.myMetric = myMetric;
    }


    public void issueBook(String bookId, String readerId) {


        boolean isBookAvailable = true;
        boolean isReaderEligible = true;

        if (isBookAvailable && isReaderEligible) {

            myMetric.incrementBooksIssued();

            System.out.println("Книга выдана: " + bookId + " для читателя: " + readerId);
        } else {

            myMetric.incrementRequestsRejected();
            System.out.println("Выдача книги отклонена: " + bookId + " для читателя: " + readerId);
        }
    }
}
