// Michael Butera

package test2.android.wku.edu.hatstoremb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Zoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("DRAWABLE",0);

        // parent relative layout
        RelativeLayout parent = new RelativeLayout(this);

        // first child of relative layout
        RelativeLayout sub1 = new RelativeLayout(this);
        // create customer imagezoom view, pass id from clicked hat
        ImageViewWithZoom imageViewWithZoom = new ImageViewWithZoom(this, id);
        // add sub to parent view
        sub1.addView(imageViewWithZoom);

        // second child of relative layout
        RelativeLayout sub2 = new RelativeLayout(this);
        // image view that is the dog
        ImageView dog = new ImageView(this);
        // set dog as image
        dog.setImageResource(R.drawable.dog);
        // add image to relative layout
        sub2.addView(dog);

        // add both childrent to parent
        parent.addView(sub2);
        parent.addView(sub1);

        // set parent as content view
        setContentView(parent);

        getSupportActionBar().setTitle("Zoom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


