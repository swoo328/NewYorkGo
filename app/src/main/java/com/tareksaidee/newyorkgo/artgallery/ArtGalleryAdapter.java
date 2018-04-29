package com.tareksaidee.newyorkgo.artgallery;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tareksaidee.newyorkgo.DTO.ArtGallery;
import com.tareksaidee.newyorkgo.DTO.Bookmark;
import com.tareksaidee.newyorkgo.R;
import com.tareksaidee.newyorkgo.ShowAddressActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tarek on 4/28/2018.
 */

public class ArtGalleryAdapter extends RecyclerView.Adapter<ArtGalleryAdapter.ArtGalleryViewHolder> {

    ArrayList<ArtGallery> artGalleries;
    private Context mContext;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mBookmarksDatabaseReferenceFull;
    private DatabaseReference mBookmarksDatabaseReferencePart;
    private FirebaseAuth mFirebaseAuth;


    ArtGalleryAdapter(@NonNull Context context, ArrayList<ArtGallery> artGalleries) {
        mContext = context;
        this.artGalleries = artGalleries;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mBookmarksDatabaseReferenceFull = mFirebaseDatabase.getReference();
        mBookmarksDatabaseReferencePart = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public ArtGalleryAdapter.ArtGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.art_gallery_card, parent, false);
        return new ArtGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtGalleryAdapter.ArtGalleryViewHolder holder, int position) {
        final ArtGallery gallery = artGalleries.get(position);
        holder.name.setText(gallery.getName());
        holder.telephone.setText(gallery.getTel());
        holder.url.setText(gallery.getUrl());
        holder.address.setText(gallery.getAddress1() + "\n" + gallery.getAddress2());
        holder.city.setText(gallery.getCity());
        holder.zipcode.setText(gallery.getZipCode());
        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFirebaseAuth.getCurrentUser() != null) {
                    String key = (new Random().nextInt(100000000)) + "";
                    mBookmarksDatabaseReferenceFull.child(mFirebaseAuth.getCurrentUser().getUid() + "/full/" + key)
                            .push().setValue(gallery);
                    Bookmark bookmark = new Bookmark(gallery.getName(), key);
                    mBookmarksDatabaseReferencePart.child(mFirebaseAuth.getCurrentUser().getUid() + "/part/")
                            .push().setValue(bookmark);
                    Toast.makeText(mContext, "Bookmarked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Not logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowAddressActivity.class);
                intent.putExtra("address", gallery.getAddress1());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artGalleries.size();
    }

    void clear() {
        artGalleries.clear();
    }

    class ArtGalleryViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView telephone;
        TextView url;
        TextView address;
        TextView city;
        TextView zipcode;
        Button mapButton;
        Button bookmarkButton;

        ArtGalleryViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            telephone = (TextView) view.findViewById(R.id.phone);
            url = (TextView) view.findViewById(R.id.url);
            address = (TextView) view.findViewById(R.id.address);
            city = (TextView) view.findViewById(R.id.city);
            zipcode = (TextView) view.findViewById(R.id.zip);
            mapButton = (Button) view.findViewById(R.id.map);
            bookmarkButton = (Button) view.findViewById(R.id.bookmark);
        }
    }

}
