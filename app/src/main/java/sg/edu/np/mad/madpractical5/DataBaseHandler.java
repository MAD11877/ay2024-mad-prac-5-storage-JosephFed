package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DataBaseHandler extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Database Operations","Creating a Table.");
        try{
            String CREATE_USERS_TABLE = "CREATE TABLE " +
                    TABLE_USERS +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_FOLLOWED + " BOOLEAN" +")";
            db.execSQL(CREATE_USERS_TABLE);

            //Create 20 random user
            for (int i = 0; i<20; i++){
                int name = new Random().nextInt(999999999);
                int description = new Random().nextInt(999999999);
                boolean followed = new Random().nextBoolean();

                //Creating user
                User user = new User("John Doe","MAD Developer", 1, false);
                user.setName("Name" + String.valueOf(name));
                user.setDescription("Description" + String.valueOf(description));
                user.setFollowed(followed);
//                userList.add(user);
                //Store new User in database
                ContentValues values = new ContentValues();
                values.put(COLUMN_USERNAME, "name"+name);
                values.put(COLUMN_DESCRIPTION, description);
                values.put(COLUMN_FOLLOWED, followed);
                db.insert(TABLE_USERS, null, values);
            }
            Log.i("Database Operations", "Table created successfully.");
        }
        catch (SQLiteException e){
            Log.i("Database Operations","Error creating table.",e);
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";
    public DataBaseHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }
    public User getUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User("", "", 1,true);
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(Integer.parseInt(cursor.getString(2)));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }
//      db.close();
        return user;
    }
    public ArrayList<User> getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt((int)cursor.getColumnIndex("id"));
            String name = cursor.getString((int)cursor.getColumnIndex("name"));
            String password = cursor.getString((int)cursor.getColumnIndex("description"));
            boolean followed = Boolean.parseBoolean(cursor.getString((int)cursor.getColumnIndex("followed")));
            User user = new User(name, password, id, followed);
            userList.add(user);
        }
        cursor.close();
        return userList;
    }
    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, String.valueOf(user.getId()));
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        String clause = "id=?";
        String[] args = {String.valueOf(user.getId())};
        db.update(TABLE_USERS, values, clause, args);
    }
    @Override
    public void close() {
        Log.i("Database Operations", "Database is closed.");
        super.close();
    }
}

