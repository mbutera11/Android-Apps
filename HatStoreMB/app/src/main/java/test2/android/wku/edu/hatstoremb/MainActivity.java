// Michael Butera

package test2.android.wku.edu.hatstoremb;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView hat1;
    private ImageView hat2;
    private ImageView hat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Hat Store");

        hat1 = findViewById(R.id.hat1);
        hat2 = findViewById(R.id.hat2);
        hat3 = findViewById(R.id.hat3);

        hat1.setTag("Hat One");
        hat2.setTag("Hat Two");
        hat3.setTag("Hat Three");

        // set on long click listeners for each hat
        hat1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // create it from the object's tag
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag( data, shadowBuilder, v, 0);

                return true;
            }
        });
        hat2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // create it from the object's tag
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag( data, shadowBuilder, v, 0);

                return true;
            }
        });
        hat3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // create it from the object's tag
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag( data, shadowBuilder, v, 0);

                return true;
            }
        });

        // set on click listeners for each hat to go to another page.
        // send its drawable tag
        hat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Zoom.class);
                in.putExtra("DRAWABLE", R.drawable.hat1);
                startActivity(in);
            }
        });
        hat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Zoom.class);
                in.putExtra("DRAWABLE", R.drawable.hat2);
                startActivity(in);
            }
        });
        hat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Zoom.class);
                in.putExtra("DRAWABLE", R.drawable.hat3);
                startActivity(in);
            }
        });

        // set listeners for the top and bottom layouts
        findViewById(R.id.topRelative).setOnDragListener(new ImageDragListener());
        findViewById(R.id.bottomRelative).setOnDragListener(new ImageDragListener());



    }


    class ImageDragListener implements View.OnDragListener {

        // drawables that are just background colors for a better UI when the user drags and drops the images
        Drawable normalShape = getResources().getDrawable(R.drawable.normal_shape);
        Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            // get x and y for position when user lets go of the image
            float x = event.getX();
            float y = event.getY();

            // when the drag is started, dont do anything particular
            switch (event.getAction()) {

                // when the event enters a view, change background to dark drawable
                case DragEvent.ACTION_DRAG_STARTED:
                    break;

                // when the event enters a view, change background to dark drawable
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(targetShape);
                    break;

                // when the event exits a view, set background to normal drawable
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);
                    break;

                // when the event is let go inside a view
                case DragEvent.ACTION_DROP:

                    // get current view group and remove the view from it so it can be put in the other view
                    View view = (View) event.getLocalState();
                    ViewGroup viewgroup = (ViewGroup) view.getParent();
                    viewgroup.removeView(view);

                    // add the view that has been dropped to a relative layout
                    RelativeLayout containView = (RelativeLayout) v;
                    containView.addView(view);

                    // set position of the image that was dropped to where the user let go
                    view.setX(x-view.getWidth()/2);
                    view.setY(y-view.getHeight()/2);

                    view.setVisibility(View.VISIBLE);

                    break;

                // when drag and drop has ended, set to normal drawable
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);

                default:
                    break;
            }
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }
}