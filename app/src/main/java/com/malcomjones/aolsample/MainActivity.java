package com.malcomjones.aolsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ad_type_list = (ListView) findViewById(R.id.lv);
        ad_type_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.array_btns)));

        ad_type_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if(pos == 0){
                    Intent intent = new Intent(MainActivity.this, BannerActivity.class);
                    startActivity(intent);
                }
                if(pos == 1){
                    Toast.makeText(MainActivity.this, "MREC", Toast.LENGTH_SHORT).show();
                }
                if(pos == 2){
                    Intent intent = new Intent(MainActivity.this, InterstitialActivity.class);
                    startActivity(intent);
                }
                if(pos == 3){
                    Intent intent = new Intent(MainActivity.this, NativeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
