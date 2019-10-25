package com.miniproject.kennelv2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GroceryRecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private TextView name = null;
    private TextView contact = null;
    private TextView location = null;
    private TextView comment = null;
    private TextView email = null;
    private ImageView dogImage = null;

    public GroceryRecyclerViewItemHolder(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            name = (TextView)itemView.findViewById(R.id.name);
            contact = (TextView)itemView.findViewById(R.id.contact_no);
            location = (TextView)itemView.findViewById(R.id.location);
            comment = (TextView)itemView.findViewById(R.id.comment);
            email = (TextView)itemView.findViewById(R.id.email);



            dogImage = (ImageView)itemView.findViewById(R.id.card_view_image);
        }
    }

    public TextView getName() {
        return name;
    }
    public TextView getContact() {
        return contact;
    }
    public TextView getLocation() {
        return location;
    }
    public TextView getComment() {
        return comment;
    }
    public TextView getEmail() {
        return email;
    }

    public ImageView getDogImage() {
        return getDogImage();
    }
}
