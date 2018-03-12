// Michael Butera

package lab6.android.wku.edu.contactmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;

/**
 * Created by michaelbutera on 3/9/18.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "ContactManager.db";

    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_COUNTRY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_USERNAME = "username";
    public static final String USERS_COLUMN_PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create contacts table
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key autoincrement, userID integer, name text,phone text,email text, street text,place text)"
        );

        // create users table
        db.execSQL(
                "create table users " +
                        "(id integer primary key autoincrement, username text, password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // insert contact to DB
    public boolean insertContact(int userID, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userID", userID);
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("street", street);
        cv.put("place", place);

        db.insert("contacts", null, cv);

        return true;
    }

    // updates row in table at the specific id
    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("street", street);
        cv.put("place", place);
        db.update("contacts", cv, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    // insert user to DB
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);
        if(c.getCount() > 0) {
            c.close();
            return false;
        }

        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        db.insert("users", null, cv);

        return true;
    }

    // check if username is already registered when a user is trying to register
    public boolean isRegisteredUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'", null);
        if(c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    // returns the names of all contacts for a specific user with userID
    public ArrayList<String> getAllContacts(int userID) {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM contacts WHERE userID = " + userID, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    // method returns userID of a user with given username
    public int getUserID(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT id FROM users WHERE username = '" + username + "'", null);
        res.moveToFirst();
        return res.getInt(res.getColumnIndex(USERS_COLUMN_ID));

    }

    // method returns the id for a specific contact given the userID and the name of the contact
    public int getContactID(int id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT id FROM contacts WHERE userID = " + id + " AND name = '" + name + "'", null);
        res.moveToFirst();
        return res.getInt(res.getColumnIndex(CONTACTS_COLUMN_ID));
    }

    // returns all contact information for every contact of user with userID
    public Cursor getContactData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    // removes row of the database where the id of the contact is the passed id
    public int deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "id = ? ", new String[] { Integer.toString(id) });
    }



}
