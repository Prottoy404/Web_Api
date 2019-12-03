package com.example.webapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String FLOWER_BASE_URL = "http://services.hanselandpetal.com/";
    private RecyclerView recyclerView;
    private  FlowerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FLOWER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowerAPIService apiService = retrofit.create(FlowerAPIService.class);
        apiService.getFlowerResponse()
                .enqueue(new Callback<List<FlowerMainResponse>>() {
                    @Override
                    public void onResponse(Call<List<FlowerMainResponse>> call, Response<List<FlowerMainResponse>> response) {
                        if(response.isSuccessful()){
                            List<FlowerMainResponse>mainResponses = response.body();
                            LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                            adapter = new FlowerAdapter(MainActivity.this, mainResponses);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter(adapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<List<FlowerMainResponse>> call, Throwable t) {

                    }
                });
    }
}
