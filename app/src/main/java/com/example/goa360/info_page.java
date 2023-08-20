package com.example.goa360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class info_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String links = getIntent().getStringExtra("LINKS");
        int image = getIntent().getIntExtra("IMAGE" , 0);

        TextView nametextView = findViewById(R.id.textView);
        TextView descriptiontextview = findViewById(R.id.textView2);
        ImageView imageView = findViewById(R.id.imageView2);
        Button linkbutton = findViewById(R.id.mapbtn);

        nametextView.setText(name);
        descriptiontextview.setText(description);
        imageView.setImageResource(image);

        linkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(links);
                Intent intent = new Intent(Intent.ACTION_VIEW , link);
                startActivity(intent);
                finish();
            }
        });
    }
}