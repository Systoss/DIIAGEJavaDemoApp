package diiage.potherat.demo.demoapp3.dal.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;

import diiage.potherat.demo.demoapp3.model.Quote;

public interface QuoteRepository {
    PagingSource<Integer, Quote> getAll();
    LiveData<Quote> getById(Long id);
    Integer count();
    Integer countDistinct();
    Quote getLastQuote();

    Long create(Quote quote);
    void update(Quote quote);
    void delete(Quote quote);
}
