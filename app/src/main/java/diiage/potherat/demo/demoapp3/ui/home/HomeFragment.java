package diiage.potherat.demo.demoapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.common.EventObserver;
import diiage.potherat.demo.demoapp3.databinding.FragmentHomeBinding;
import diiage.potherat.demo.demoapp3.databinding.FragmentQuoteEditBinding;
import diiage.potherat.demo.demoapp3.ui.edit.QuoteEditFragmentDirections;
import diiage.potherat.demo.demoapp3.ui.edit.QuoteEditViewModel;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    @Inject
    HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    private TextView numberOfQuotes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        ready();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(HomeViewModel.class);
        viewModel.loadCountQuotes();
        viewModel.loadCountQuotesDisctint();
        viewModel.loadLastQuote();


        ready();
    }

    private void ready(){
        if (binding != null && viewModel != null){
            binding.setLifecycleOwner(this);
            binding.setViewModel(viewModel);
        }
    }
}