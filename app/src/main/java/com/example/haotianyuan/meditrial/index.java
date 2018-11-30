package com.example.haotianyuan.meditrial;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class index extends AppCompatActivity {
    public Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //System.err.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        //Log.d("likun", "sssssssssssssssssssss");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button login = (Button) findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);
                TextView result = (TextView) findViewById(R.id.result);

                try {
                    con = connectionclass();
                    if (con == null) {
                        System.err.println("Error: Cant connect to server");
                    } else {
                        boolean login_patients = false;
                        boolean login_companies = false;
                        //String query = "select * from Patients";  //SQL command
                        String usernames = "select * from Patients";
                        Statement runUsernames = con.createStatement();
                        ResultSet users = runUsernames.executeQuery(usernames);
                        while (users.next()) {
                            if(username.getText().toString().trim().equals(users.getString("Account"))){
                                if(password.getText().toString().trim().equals(users.getString("Password"))){
                                    login_patients=true;
                                    break;
                                }
                            }
                        }

                        String companynames = "select * from Companies";
                        Statement runCompanies = con.createStatement();
                        ResultSet companies = runCompanies.executeQuery(companynames);
                        while (companies.next()) {
                            if(username.getText().toString().trim().equals(companies.getString("Account"))){
                                if(password.getText().toString().trim().equals(companies.getString("Password"))){
                                    login_companies=true;
                                    break;
                                }
                            }
                        }

                        if(login_patients==true){
                            Intent startIntent=new Intent(getApplicationContext(),main.class);
                            startIntent.putExtra("accountname", username.getText().toString());
                            startActivity(startIntent);
                        }
                        else if(login_companies==true){
                            Intent startIntent=new Intent(getApplicationContext(),company.class);
                            startIntent.putExtra("accountname", username.getText().toString());
                            startActivity(startIntent);
                        }
                        else{
                            result.setText("Invalid account or password!!!");
                        }

                    }
                } catch (Exception ex) {
                    System.err.println("Error: Cant connect to server");
                    System.err.println(ex.getMessage());
                }
            }
        });

        //signup button
        Button signup=(Button)findViewById(R.id.SignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURl = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //ConnectionURl = "jdbc:jtds:sqlserver://haotianyuan.database.windows.net:1433;DatabaseName=users;user=haotianyuan@haotianyuan;password=yht6527077!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            ConnectionURl ="jdbc:jtds:sqlserver://haotianyuan.database.windows.net:1433;DatabaseName=users;user=haotianyuan@haotianyuan;password=yht6527077!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURl);
        }
        catch(ClassNotFoundException e){
            Log.e("Error: ", e.getMessage());
        }
        catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }
        return connection;
    }

}
