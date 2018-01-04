package com.project.roy.cobabelajar.ui.list;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.project.roy.cobabelajar.data.model.Car;
import com.project.roy.cobabelajar.data.remote.RetrofitClient;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roy on 1/2/2018.
 */

public class ListGalleryPresenter {

    private ListGalleryView listGalleryView;

    public ListGalleryPresenter(ListGalleryView listGalleryView) {
        this.listGalleryView = listGalleryView;
    }

    public void showListGallery(){
        RetrofitClient.getInstance()
                .getApi()
                .getCarAll()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()){
                            JsonObject body = response.body();
                            JsonArray data = body.get("data").getAsJsonArray();
                            Type type = new TypeToken<List<Car>>(){}.getType();

                            List<Car> carList = new Gson().fromJson(data,type);
                            listGalleryView.showData(carList);

                            Log.d("List Gallery", "OnResponse : "+ carList.toString() );
                        }
                        else {
                            listGalleryView.showError("no data");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                        listGalleryView.showError(t.getMessage());
                    }
                });
    }

    public void addCars(String year, String make, String model){
        RetrofitClient.getInstance()
                .getApi()
                .carAdd(new Car(year, make, model))
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject body = response.body();
                        String status = body.get("status").toString();
                        listGalleryView.showStatus(status);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        listGalleryView.showError("failed to add car");
                    }
                });
    }

    public void deleteCar(int id){
        RetrofitClient.getInstance()
                .getApi()
                .carDelete(id)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject obj = response.body();
                        String status = obj.get("status").toString();
                        listGalleryView.showStatus(status);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        listGalleryView.showError("failed to delete car");
                    }
                });
    }
}
