package com.example.goa360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class home extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<placeModel> PlaceModels = new ArrayList<>();

    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,};

    FirebaseAuth auth;
    TextView useremail;
    Button logout;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //to set the user email in welcome text
        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logoutbtn);
        useremail = findViewById(R.id.user_detail);
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }
        else{
            useremail.setText(user.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpPlaceModels();
        //Always create the adapter after setUp of your model
        Place_RecyclerViewAdapter adapter = new Place_RecyclerViewAdapter(this , this ,PlaceModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpPlaceModels(){
        String[] placeNames = getResources().getStringArray(R.array.location_text);
        String[] description = getResources().getStringArray(R.array.description_text);
        String[] links = getResources().getStringArray(R.array.links);

        for(int i=0 ; i<placeNames.length; i++){
            PlaceModels.add(new placeModel(
                    placeNames[i],
                    description[i],
                    links[i],
                    images[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(home.this, info_page.class);
        intent.putExtra("NAME" , PlaceModels.get(position).getPlaceName());
        intent.putExtra("DESCRIPTION" , PlaceModels.get(position).getDescription());
        intent.putExtra("LINKS" , PlaceModels.get(position).getLinks());
        intent.putExtra("IMAGE" , PlaceModels.get(position).getImage());
        startActivity(intent);
    }
}