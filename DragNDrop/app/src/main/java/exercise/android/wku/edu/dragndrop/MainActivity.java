package exercise.android.wku.edu.dragndrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView myImage;
    private static final String IMAGEVIEW_TAG = "The Android Logo";

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myImage = findViewById(R.id.image);
        // Sets the tag
        myImage.setTag(IMAGEVIEW_TAG);

        // set the listener to the dragging data
        myImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // create it from the object's tag
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag( data, //data to be dragged
                        shadowBuilder, //drag shadow
                        v, //local data about the drag and drop operation
                        0   //no needed flags
                );


                //v.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        findViewById(R.id.toplinear).setOnDragListener(new MyDragListener());

        findViewById(R.id.bottomlinear).setOnDragListener(new MyDragListener());

    }

    class MyDragListener implements OnDragListener {
        Drawable normalShape = getResources().getDrawable(R.drawable.normal_shape);
        Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {

                // when the drag is started, dont do anything particular
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

//                        //change the text
//                        TextView text = (TextView) v.findViewById(R.id.text);
//                        text.setText("The item is dropped");

                    LinearLayout containView = (LinearLayout) v;
                    containView.addView(view);
                    view.setVisibility(View.VISIBLE);

                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);	//go back to normal shape

                default:
                    break;
            }
            return true;
        }
    }
}