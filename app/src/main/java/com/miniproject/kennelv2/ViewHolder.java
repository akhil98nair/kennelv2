package com.miniproject.kennelv2;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context ctx, String name, String comment, String email, String image_path, String mob_no, String loc){
        TextView person_name = mView.findViewById(R.id.name);
        TextView person_comment = mView.findViewById(R.id.comment);
        TextView person_email = mView.findViewById(R.id.email);
        ImageView person_imagepath = mView.findViewById(R.id.card_view_image);
        TextView person_mobno = mView.findViewById(R.id.contact_no);
        TextView person_loc = mView.findViewById(R.id.location);
        person_name.setText(name);
        person_comment.setText(comment);
        person_email.setText(email);
        person_mobno.setText(mob_no);
        person_loc.setText(loc);

        Picasso.get().load(image_path).into(person_imagepath);



    }
}
