package com.example.milestone2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {
    private Context context;

    public static final String CREATE_BLOODTABLE =
            "CREATE TABLE "+
                    Constants.BLOODTABLE_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.PATIENTNAME + " TEXT, " +
                    Constants.DATE + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    Constants.BLOODTYPE + " TEXT, " +
                    Constants.WBC + " TEXT, " +
                    Constants.RBC + " TEXT, " +
                    Constants.HEPATITISA + " BOOLEAN, " +
                    Constants.HEPATITISB + " BOOLEAN);" ;

    public static final String CREATE_USERTABLE =
            "CREATE TABLE "+
                    Constants.USERTABLE_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.PATIENTNAME + " TEXT, " +
                    Constants.PASSWORD + " TEXT, " +
                    Constants.CARDNUM + " TEXT, " +
                    Constants.SEX + " TEXT, " +
                    Constants.AGE + " TEXT, " +
                    Constants.PHONE + " TEXT);" ;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + Constants.BLOODTABLE_NAME;

    public MyHelper(Context context){
        super (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_BLOODTABLE);
            db.execSQL(CREATE_USERTABLE);
            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
            Log.d("wow", "table create attempted");

        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() db", Toast.LENGTH_LONG).show();
            Log.d("wow", "table create failed: " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_LONG).show();
            Log.d("wow", "table updated");
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_LONG).show();
            Log.d("wow", "table update failed");
        }
    }
}
