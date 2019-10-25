package com.miniproject.kennelv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class fill_details extends AppCompatActivity {
    EditText name, mobile, location, email, comment;
    ImageView imageView;
    Button camera,submit_button;
    FirebaseStorage storage;
    StorageReference storageReference;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details);
        camera = (Button) findViewById(R.id.camerabutton);
        name = (EditText)findViewById(R.id.name);
        comment = (EditText) findViewById(R.id.comment);
        mobile = (EditText)findViewById(R.id.number);
        location = (EditText)findViewById(R.id.location);
        email = (EditText)findViewById(R.id.email);
        submit_button = (Button) findViewById(R.id.submit);
        imageView = (ImageView) findViewById(R.id.dogimage);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    DatabaseReference myRef = database.getReference("details").push();
                    String user_name = name.getText().toString();
                    String mob_no = mobile.getText().toString();
                    String loc = location.getText().toString();
                    String email_id = email.getText().toString();
                    String get_comment = comment.getText().toString();
                    myRef.child("name").setValue(user_name);
                    myRef.child("mob_no").setValue(mob_no);
                    myRef.child("comment").setValue(get_comment);
                    myRef.child("loc").setValue(loc);
                    myRef.child("email").setValue(email_id);
                    myRef.child("image_path").setValue(uploadImage());
                    name.setText("");
                    mobile.setText("");
                    location.setText("");
                    email.setText("");
                    comment.setText("");
                    imageView.setImageBitmap(null);

                }
                catch (Exception e)
                {
                    Log.d(TAG, "onClick");
                    e.printStackTrace();
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    chooseImage();
                }
                catch (Exception e)
                {
                    Log.d(TAG, "onClick");
                    e.printStackTrace();
                }
            }
        });

    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private String uploadImage() {
        String rand = UUID.randomUUID().toString();
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ rand);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(fill_details.this, "Uploaded", Toast.LENGTH_SHORT).show();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(fill_details.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });

        }
        return rand;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);



            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

