package com.project.roy.cobabelajar.ui.list;

import com.project.roy.cobabelajar.data.model.Car;

import java.util.List;

/**
 * Created by roy on 1/2/2018.
 */

public interface ListGalleryView {

    void showData(List<Car> car);

    void showError(String errorMessage);

    void showStatus(String status);

}
