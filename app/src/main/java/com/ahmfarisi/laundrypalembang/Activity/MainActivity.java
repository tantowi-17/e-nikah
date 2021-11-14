package com.ahmfarisi.laundrypalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ahmfarisi.laundrypalembang.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnCreate, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = (ImageButton) findViewById(R.id.btn_create);
        btnView = (ImageButton) findViewById(R.id.btn_view);

        // button create
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

        // button view
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LihatDataNikah.class));
            }
        });
    }
}
