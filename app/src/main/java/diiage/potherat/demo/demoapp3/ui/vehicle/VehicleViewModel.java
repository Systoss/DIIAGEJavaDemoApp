package diiage.potherat.demo.demoapp3.ui.vehicle;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class VehicleViewModel extends ViewModel {
    private SWRepository _repository;

    private LiveData<Vehicle> _vehicle;

    @Inject
    @ViewModelInject
    public VehicleViewModel(SWRepository repository) {
        _repository = repository;

        _vehicle = new MutableLiveData<>();
    }

    public LiveData<Vehicle> getVehicle() { return _vehicle; }

    public void loadVehicle(Integer id) {
        _vehicle = Transformations.map(
                _repository.getVehicle(id),input -> {
                    if(input instanceof ApiSuccessResponse) {
                        return ((ApiSuccessResponse<Vehicle>) input).getBody();
                    } else if (input instanceof ApiErrorResponse){
                        Log.e("debug",((ApiErrorResponse<Vehicle>) input).getErrorMessage()+"");
                    }
                    return null;
                }
        );
    }
}