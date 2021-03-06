package com.tareksaidee.newyorkgo.iceskating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.tareksaidee.newyorkgo.DTO.IceSkating;
import com.tareksaidee.newyorkgo.R;
import com.tareksaidee.newyorkgo.parser.JsonParser;

import java.util.ArrayList;

public class IceSkatingActivity extends AppCompatActivity {
    //private ArrayList<BBQ> bbqs;
    private ArrayList<IceSkating> I;
    //private RecyclerView bbqsView;
    private RecyclerView iceSkatingView;
    //private BBQAdapter bbqAdapter;
    private IceSkatingAdapter iceSkatingAdapter;
    private JsonParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_skating);
        parser = new JsonParser();
        if (getIntent().getStringExtra("object") != null) {
            I = new ArrayList<>();
            I.add(new Gson().fromJson(getIntent().getStringExtra("object"), IceSkating.class));
        } else {
            parser = new JsonParser();
            try {
                I = parser.getIceSkating(getAssets());
            } catch (Exception e) {
                Log.e("Art gallery", e.getMessage());
            }
        }
        iceSkatingView = findViewById(R.id.iceskatingRecyclerView);
        iceSkatingAdapter = new IceSkatingAdapter(this, I);
        iceSkatingView.setAdapter(iceSkatingAdapter);
        iceSkatingView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
