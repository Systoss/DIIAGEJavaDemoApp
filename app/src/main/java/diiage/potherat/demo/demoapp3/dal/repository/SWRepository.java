package diiage.potherat.demo.demoapp3.dal.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiResponse;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;

public interface SWRepository {

    LiveData<ApiResponse<SWModelList<People>>> getPeoples();
    LiveData<ApiResponse<People>> getPeople(Integer id);
    LiveData<ApiResponse<Vehicle>> getVehicle(Integer id);

}
