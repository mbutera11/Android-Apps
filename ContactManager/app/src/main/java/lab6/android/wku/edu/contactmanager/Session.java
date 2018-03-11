package lab6.android.wku.edu.contactmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by michaelbutera on 3/11/18.
 * Used to keep track of the logged in user
 *
 * setUsername: stores the logged in user's username
 * getUsername: returns logged in user's username
 *
 * setUserID: stores the logged in user's userID
 * getUserID: returns logged in user's userID
 *
 * destroy: clears shared preference of all stored user information
 *
 */

public class Session {

    private SharedPreferences pref;

    public Session(Context c) {
        pref = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public void setUsername(String username) {
        pref.edit().putString("Username", username).commit();
    }

    public String getUserName() {
        String username = pref.getString("Username", "");
        return username;
    }

    public void setUserID(int userID) {
        pref.edit().putInt("UserID", userID).commit();
    }

    public int getUserID() {
        int userID = pref.getInt("UserID", -1);
        return userID;
    }

    public void destroy() {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
