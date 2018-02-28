package exercise.android.wku.edu.animations;

/**
 * Created by michaelbutera on 2/28/18.
 */

public class Gear {
    private int mStartDegree;
    private int mEndDegree;

    public Gear() {
        mStartDegree = 0;
        mEndDegree = 0;
    }

    public void setStartDegree(int startDegree) {
        mStartDegree = startDegree;
    }

    public int getStartDegree() {
        return mStartDegree;
    }

    public void setEndDegree(int endDegree) {
        mEndDegree = endDegree;
    }

    public int getEndDegree() {
        return mEndDegree;
    }
}