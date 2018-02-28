package exercise.android.wku.edu.a2dgraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by michaelbutera on 2/26/18.
 */

public class MyShapeView extends View {

    protected ShapeDrawable square;
    protected ShapeDrawable circle;
    protected int squareColor = Color.BLUE;


    public MyShapeView(Context c) {
        super(c);
        init();
    }

    public MyShapeView(Context c, AttributeSet a) {
        super(c, a);
        init();
    }

    protected void init() {

        // blue 60x60 square at 80, 120
        square = new ShapeDrawable(new RectShape());

        // position it
        square.setBounds(80, 120, 80+900, 120+900);

        // greenish circle at 230, 220
        circle = new ShapeDrawable(new OvalShape());

        // set the color using opacity + RGB
        circle.getPaint().setColor(0xff74AC23);

        // give it a white shadow
        // arguments are blue radius, x-offset, y-offset
        circle.getPaint().setShadowLayer(10, 15, 15, Color.GRAY);

        // position it
        circle.setBounds(340, 340, 340+400, 340+400);
    }

    protected void onDraw(Canvas canvas) {

        // set the color
        square.getPaint().setColor(squareColor);
        // draw the square
        square.draw(canvas);
        // draw the circle
        circle.draw(canvas);

        // create Paint Object
        Paint p = new Paint();
        // set its color
        p.setColor(Color.RED);
        // set stroke width
        p.setStrokeWidth(10);

        // draw a line
        canvas.drawLine(40, 20, 60, 50, p);

        Paint p2 = new Paint();
        // set color
        p2.setColor(Color.DKGRAY);
        // align to the left
        p2.setTextAlign(Paint.Align.LEFT);
        // set type face
        p2.setTypeface(Typeface.SANS_SERIF);
        // set size
        p2.setTextSize(75);

        // draw the text
        canvas.drawText("Hello", 180, 120, p2);

    }

    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)e.getX(); int y = (int)e.getY();
            if (x > 80 && x < 140 && y > 120 && y < 180) {
                squareColor = Color.RED;
                invalidate(); // force redraw
                return true;
                }
            }
        return false;

    }


}
