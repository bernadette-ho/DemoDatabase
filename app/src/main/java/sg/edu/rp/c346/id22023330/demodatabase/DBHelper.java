package sg.edu.rp.c346.id22023330.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //start version with 1
    //increment by 1 whenever db schema changes
    private static final int DATABASE_VER = 1;
    //filename of the database
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        //create table(s) again
        onCreate(db);
    }

    public void insertTask(String description, String date){
        //get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        //we use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        //store the column name as key and the description as value
        values.put(COLUMN_DESCRIPTION, description);
        //store the column name as key and the date as value
        values.put(COLUMN_DATE, date);
        //insert the row into the TABLE_TASK
        db.insert(TABLE_TASK, null, values);
        //close the database connection
        db.close();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DATE};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Task obj = new Task(id, description, date);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<Task> getTasksAsc() {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DATE};
        //modifies the SQL query to include ORDER BY clause
        String sortOrder = COLUMN_DESCRIPTION + " ASC";
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, sortOrder, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Task obj = new Task(id, description, date);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<Task> getTasksDesc() {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DATE};
        //modifies the SQL query to include ORDER BY clause
        String sortOrder = COLUMN_DESCRIPTION + " DESC";
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, sortOrder, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Task obj = new Task(id, description, date);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<Task> getTasksContent() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DATE};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Task obj = new Task(id, description, date);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }


}
