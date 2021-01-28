package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    QuoteRepository _quoteRepository;

    private MutableLiveData<String> mText;

    private MutableLiveData<String> mCountQuoteText;

    private MutableLiveData<String> mCountQuoteDistinctText;
    private MutableLiveData<Quote> mLastQuote;

    @Inject
    @ViewModelInject
    public HomeViewModel(QuoteRepository quoteRepository) {
        mText = new MutableLiveData<>();
        mCountQuoteText = new MutableLiveData<>();
        mCountQuoteDistinctText = new MutableLiveData<>();
        mLastQuote = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mCountQuoteText.setValue("Nombre de quote : 0");
        mCountQuoteDistinctText.setValue("Nombre de quote distinct : 0");
        mLastQuote.setValue(new Quote());

        _quoteRepository = quoteRepository;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getQuotesCountText() { return mCountQuoteText; }
    public LiveData<String> getQuotesCountDistincText() { return mCountQuoteDistinctText; }
    public LiveData<Quote> getLastQuote() { return mLastQuote; }

    public void loadCountQuotes(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer count = _quoteRepository.count();

                mCountQuoteText.postValue("Nombre de quote : " + count.toString());
            }
        });

        thread.start();
    }

    public void loadCountQuotesDisctint(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer count = _quoteRepository.countDistinct();

                mCountQuoteDistinctText.postValue("Nombre de quote distinct : " + count.toString());
            }
        });

        thread.start();
    }

    public void loadLastQuote(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Quote quote = _quoteRepository.getLastQuote();

                mLastQuote.postValue(quote);
            }
        });

        thread.start();
    }

}