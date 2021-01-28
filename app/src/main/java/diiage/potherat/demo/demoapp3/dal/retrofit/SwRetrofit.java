package diiage.potherat.demo.demoapp3.dal.retrofit;

import androidx.lifecycle.LiveData;

import java.util.List;

import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiResponse;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwRetrofit extends SWRepository {
    @GET("people/")
    LiveData<ApiResponse<SWModelList<People>>> getPeoples();
    @GET("people/{id}/")
    LiveData<ApiResponse<People>> getPeople(@Path("id") Integer id);
    @GET("vehicles/{id}/")
    LiveData<ApiResponse<Vehicle>> getVehicle(@Path("id") Integer id);
}
