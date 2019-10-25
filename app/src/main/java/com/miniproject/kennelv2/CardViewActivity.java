package com.miniproject.kennelv2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardViewActivity extends AppCompatActivity {
    private static final String TAG = "hello";
    private static final String CHANNEL_ID = "2";
    DatabaseReference demoRef, rootRef;
    private StorageReference mStorageRef;


    private List<GroceryRecyclerViewItem> carItemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
//        ViewGroup content = (ViewGroup) findViewById(R.id.display);
//        getLayoutInflater().inflate(R.layout.activity_card_view, content, true);

//        initializeCarItemList();

        // Create the recyclerview.

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();



        DatabaseReference table_user = FirebaseDatabase.getInstance().getReference("details");
        table_user.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carItemList = new ArrayList<GroceryRecyclerViewItem>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    RecyclerView carRecyclerView = (RecyclerView)findViewById(R.id.card_view_recycler_list1);
                    // Create the grid layout manager with 2 columns.
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(CardViewActivity.this, 1);
                    // Set layout manager.
                    carRecyclerView.setLayoutManager(gridLayoutManager);
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    Log.d(TAG, "val "+map);
                    String name = ds.child("name").getValue(String.class);
                    String contact = ds.child("mob_no").getValue(String.class);
                    String location = ds.child("loc").getValue(String.class);
                    String comment = ds.child("comment").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String path = ds.child("image_path").getValue(String.class);
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/"+path+".jpg");


                    Log.d(TAG, name);
                    carItemList.add(new GroceryRecyclerViewItem(name, contact, location, comment, email, R.drawable.dog));
                    CarRecyclerViewDataAdapter carDataAdapter = new CarRecyclerViewDataAdapter(carItemList);
                    // Set data adapter.
                    carRecyclerView.setAdapter(carDataAdapter);
//                        emergency_no1.setText(ds.child("emergency_no1").getValue(String.class));
//                        emergency_no2.setText(ds.child("emergency_no2").getValue(String.class));
////                    Log.d("myTag", ds.child("name").getValue(String.class) );
//                        mobile.setText(ds.child("mobile").getValue(String.class));
//                        lpg_service_no.setText(ds.child("lpg_service_no").getValue(String.class));
//                        address.setText(ds.child("address").getValue(String.class));
//                        edittext.setText(ds.child("DOB").getValue(String.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Create car recycler view data adapter with car item list.


    }

    /* Initialise car items in list. */

    private void initializeCarItemList()
    {
        if(carItemList == null)
        {

//            carItemList = new ArrayList<CarRecyclerViewItem>();
//            carItemList.add(new CarRecyclerViewItem("Onion", R.drawable.jar));
//            carItemList.add(new CarRecyclerViewItem("Tomato", R.drawable.jar));
//            carItemList.add(new CarRecyclerViewItem("Potato", R.drawable.jar));
//            carItemList.add(new CarRecyclerViewItem("Apple", R.drawable.jar));

        }
    }


}
