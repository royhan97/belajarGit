package com.project.roy.cobabelajar.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.roy.cobabelajar.R;
import com.project.roy.cobabelajar.data.model.Car;

import java.util.List;

/**
 * Created by roy on 1/2/2018.
 */

public class ListGalleryActivity extends AppCompatActivity implements ListGalleryView, View.OnClickListener {

    private RecyclerView recyclerView;
    private GalleryAdapter adapter;
    private ListGalleryPresenter presenter;
    private AlertDialog dialogAddCar;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ListGalleryPresenter(this);
        recyclerView = findViewById(R.id.rv_content);
        fab_add = findViewById(R.id.btn_add);
        fab_add.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initView();
    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new GalleryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        showList();
    }

    @Override
    public void showData(List<Car> car) {
        adapter.setData(car);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogBuilder.setCancelable(false);
            final View dialogView = inflater.inflate(R.layout.dialog_car_add, null);
            dialogBuilder.setView(dialogView);

            final EditText et_year, et_make, et_model;
            Button btn_batal, btn_add;

            et_year = dialogView.findViewById(R.id.year);
            et_make = dialogView.findViewById(R.id.make);
            et_model = dialogView.findViewById(R.id.model);
            btn_batal = dialogView.findViewById(R.id.btn_cancel);
            btn_add = dialogView.findViewById(R.id.btn_submit);

            btn_batal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogAddCar.dismiss();
                }
            });

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.addCars(et_year.getText().toString().trim(),
                            et_make.getText().toString().trim(),
                            et_model.getText().toString().trim());
                    dialogAddCar.dismiss();
                    showList();
                }
            });

            dialogAddCar = dialogBuilder.create();
            dialogAddCar.show();
        }
    }

    @Override
    public void showStatus(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    public void showList(){
        presenter.showListGallery();
    }
}
