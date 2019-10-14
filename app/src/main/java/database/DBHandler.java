package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Employee.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EmployeeClassInfo.EmployeeInfo.TABLE_NAME + " (" +
                    EmployeeClassInfo.EmployeeInfo._ID + " INTEGER PRIMARY KEY," +
                    EmployeeClassInfo.EmployeeInfo.COLUMN_NAME + " TEXT," +
                    EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE + " INT," +
                    EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL + " TEXT," +
                    EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER + " TEXT," +
                    EmployeeClassInfo.EmployeeInfo.COLUMN_Type + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + EmployeeClassInfo.EmployeeInfo.TABLE_NAME;



    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean addEmployee(String name , int phone , String email , String gender , String type){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_NAME, name);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE, phone);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL, email);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER, gender);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_Type, type);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(EmployeeClassInfo.EmployeeInfo.TABLE_NAME, null, values);
        if(newRowId> 0){
            return true;
        }
        return false;
    }

    public  boolean updateEmployee(String id , String name , int phone , String email , String gender , String type){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_NAME, name);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE, phone);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL, email);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER, gender);
        values.put(EmployeeClassInfo.EmployeeInfo.COLUMN_Type, type);

        String selection = EmployeeClassInfo.EmployeeInfo._ID + "=?";
        String[] selectionArgs = { id };

        int count = db.update(
                EmployeeClassInfo.EmployeeInfo.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count > 0){
            return true;
        }

        return false;
    }

    public Cursor search(){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                EmployeeClassInfo.EmployeeInfo.COLUMN_NAME,
                EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE,
                EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL,

                EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER,

                EmployeeClassInfo.EmployeeInfo.COLUMN_Type
        };

// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor
        String sortOrder = EmployeeClassInfo.EmployeeInfo._ID + " DESC";

        Cursor cursor = db.query(
                EmployeeClassInfo.EmployeeInfo.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }

    public Cursor search(String column , String columnValue){


        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                EmployeeClassInfo.EmployeeInfo.COLUMN_NAME,
                EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE,
                EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL,

                EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER,

                EmployeeClassInfo.EmployeeInfo.COLUMN_Type
        };
        String selection;

// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor
        String sortOrder = EmployeeClassInfo.EmployeeInfo._ID + " DESC";

        if(column.equals(EmployeeClassInfo.EmployeeInfo.COLUMN_Type)){
            selection = EmployeeClassInfo.EmployeeInfo.COLUMN_Type + " = ?";
            String[] selectionArgs = {columnValue};
            System.out.println("inside"+columnValue + " " + column);
            Cursor cursor = db.query(
                    EmployeeClassInfo.EmployeeInfo.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            return cursor;

        }else{
            selection = EmployeeClassInfo.EmployeeInfo._ID + " = ?";
            String[] selectionArgs = {columnValue};
            Cursor cursor = db.query(
                    EmployeeClassInfo.EmployeeInfo.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            return cursor;
        }

    }
}
