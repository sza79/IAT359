package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //create text view
    TextView user, pass;

    //Declaration of database
    private MyUserDatabase userDb;

    public static final String DEFAULT = "not available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //initialize the variable for username
        user = findViewById( R.id.name );

        //initialize the variable for password
        pass = findViewById( R.id.password );

        //Instantiate user database
        userDb = new MyUserDatabase(this);
    }

    //create log in button, when user sign in username and password is correct, the program will execute order to next interface
    public void Login(View view) {
        //Get SharedPreferences
        SharedPreferences prefs = getSharedPreferences( "Save Info", Context.MODE_PRIVATE );// initialize the shared preference

        String enteredUsername = user.getText().toString();
        String enteredPassword = pass.getText().toString();
        String userName = "";
        String password = "";

        //Retrieve login Credentials from
        Cursor cursor = userDb.getDataByName(user.getText().toString());
        int index1 = cursor.getColumnIndex(Constants.PATIENTNAME);
        int index2 = cursor.getColumnIndex(Constants.PASSWORD);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("wow", "stuff changing, index 1 = " + cursor.getString(index1));
            userName = cursor.getString(index1);
            password = cursor.getString(index2);
            cursor.moveToNext();
        }

        //Legacy Login Credential Retrieval Code
//        String userName = prefs.getString( enteredUsername + "_username",DEFAULT );
//        String password = prefs.getString( enteredPassword + "_password",DEFAULT );

        /*if user sign in user name and password equal register information, the program will execute order to next interface.
        otherwise, the program will back to register interface.*/


        //If user name and password matches, go to home page
        if (userName.equals(enteredUsername) && !userName.isEmpty()){
            if (password.equals(enteredPassword)) {
                //If login successful, set current session username so that we can access username in other activities
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("CurrentSessionUsername", enteredUsername);
                editor.commit();

                //Lead user to Home Page
                Toast.makeText(this, "Login Successful ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, HomePage.class);
                startActivity(i);
            } else {
                //Username correct Password wrong
                Toast.makeText(this, "Password incorrect, please try again ", Toast.LENGTH_SHORT).show();
            }
        }else{
            //else return to the login page,(main activity)
            Toast.makeText( this,"User not registered, please register", Toast.LENGTH_SHORT).show();
            Intent i = new Intent( this,MainActivity.class );
            startActivity( i );
        }

//        //If user name and password matches, go to home page
//        if (userName.equals( user.getText().toString() ) && password.equals( pass.getText().toString() )){
//            Toast.makeText( this,"go to homepage ", Toast.LENGTH_LONG ).show();
//            Intent i = new Intent( this, HomePage.class );
//            startActivity( i );
//        }else{
//
//            //else return to the login page,(main activity)
//            Toast.makeText( this,"go back to register interface", Toast.LENGTH_LONG ).show();
//            Intent i = new Intent( this,MainActivity.class );
//            startActivity( i );
//        }
    }
}

