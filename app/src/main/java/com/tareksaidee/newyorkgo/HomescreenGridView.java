package com.tareksaidee.newyorkgo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.tareksaidee.newyorkgo.DTO.Category;
import com.tareksaidee.newyorkgo.artgallery.ArtGalleryActivity;
import com.tareksaidee.newyorkgo.bball.BasketballCourtActivity;
import com.tareksaidee.newyorkgo.bbq.BBQActivity;
import com.tareksaidee.newyorkgo.beaches.BeachesActivity;

import java.util.ArrayList;

public class HomescreenGridView extends AppCompatActivity {

    private ArrayList<Category> categories;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        categories = new ArrayList<>();
        getData();
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new HomescreenGridAdapter(this, categories));
        //gridview.setLayoutParams(new RelativeLayout.LayoutParams(GridView.AUTO_FIT,GridView.AUTO_FIT));
    }

    private void getData() {
        Category category = new Category("Art Galleries", ContextCompat.getDrawable(this, R.drawable.artgallery), ArtGalleryActivity.class);
        categories.add(category);
        category = new Category("Basketball Courts", ContextCompat.getDrawable(this, R.drawable.basketball), BasketballCourtActivity.class);
        categories.add(category);
        category = new Category("BBQ Spots", ContextCompat.getDrawable(this, R.drawable.bbqimage), BBQActivity.class);
        categories.add(category);
        category = new Category("Beaches", ContextCompat.getDrawable(this, R.drawable.beaches), BeachesActivity.class);
        categories.add(category);
        /*category = new Category("Dogs", ContextCompat.getDrawable(this, R.drawable.dogs), BeachesActivity.class);
        categories.add(category);*/

//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);
//        category = new Category("Art Gallery", ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), ArtGalleryActivity.class);
//        categories.add(category);

    }


}
