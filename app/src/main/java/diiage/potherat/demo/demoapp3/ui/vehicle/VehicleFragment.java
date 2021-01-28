package diiage.potherat.demo.demoapp3.ui.vehicle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.FragmentVehicleBinding;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

@AndroidEntryPoint
public class VehicleFragment extends Fragment {

    @Inject
    VehicleViewModel viewModel;

    private FragmentVehicleBinding binding;

    private EditText input;
    private TextView textNom;
    private TextView textModele;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVehicleBinding.inflate(inflater,container,false);

        ready();

        View root = binding.getRoot();

        input = root.findViewById(R.id.input);
        textNom = root.findViewById(R.id.text_name);
        textModele = root.findViewById(R.id.text_modele);
        button = root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.getText().toString().equals("") && Integer.parseInt(input.getText().toString()) > 0 ) {
                    viewModel.loadVehicle(Integer.parseInt(input.getText().toString()));
                    viewModel.getVehicle().observe(getViewLifecycleOwner(), new Observer<Vehicle>() {
                        @Override
                        public void onChanged(Vehicle vehicle) {
                            if (vehicle == null ) {
                                textNom.setText("Aucun vehicule");
                                textModele.setText("");
                            } else {
                                textNom.setText("Nom du vehicule : " + vehicle.name);
                                textModele.setText("Modèle du vehicule : " + vehicle.model);
                            }
                        }
                    });
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    }

    private void ready(){
        if (binding != null && viewModel != null){
            binding.setLifecycleOwner(this);
            binding.setViewModel(viewModel);
        }
    }
}