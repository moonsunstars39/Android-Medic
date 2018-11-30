package com.example.haotianyuan.meditrial;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profile extends AppCompatActivity {
    public Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            con = connectionclass();
            if (con == null) {
                System.err.println("Error: Cant connect to server");
            } else {
                String temp = (String) getIntent().getSerializableExtra("name");
                String usernames = "select * from Patients";
                Statement runUsernames = con.createStatement();
                ResultSet users = runUsernames.executeQuery(usernames);
                //System.err.println(temp);
                while (users.next()) {
                    if (users.getString("Account").equals(temp)) {
                        //System.err.println(users.getString("Email").equals(null));
                        //if (users.getString("LastName").equals(null)==false) {
                        EditText fname = (EditText) findViewById(R.id.fnameInput);
                        fname.setText(users.getString("FirstName"));
                        EditText lname = (EditText) findViewById(R.id.lastnameInput);
                        lname.setText(users.getString("LastName"));
                        EditText age = (EditText) findViewById(R.id.ageInput);
                        age.setText(users.getString("Age"));
                        EditText phone = (EditText) findViewById(R.id.phoneInput);
                        phone.setText(users.getString("Phone"));
                        EditText Address = (EditText) findViewById(R.id.addressInput);
                        Address.setText(users.getString("Address"));
                        EditText city = (EditText) findViewById(R.id.cityInput);
                        city.setText(users.getString("City"));
                        EditText State = (EditText) findViewById(R.id.stateInput);
                        State.setText(users.getString("State"));
                        EditText zip = (EditText) findViewById(R.id.zipInput);
                        zip.setText(users.getString("Zip"));
                        EditText email = (EditText) findViewById(R.id.emailInput);
                        email.setText(users.getString("Email"));

                        RadioButton female = (RadioButton) findViewById(R.id.female);
                        if (users.getString("Gender").equals("female")) {
                            female.setChecked(true);
                        }
                    }
                }

                Button save = (Button) findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent startIntent=new Intent(getApplicationContext(),main.class);
                        startIntent.putExtra("accountname", (String) getIntent().getSerializableExtra("name"));
                        startActivity(startIntent);



                        EditText fname = (EditText) findViewById(R.id.fnameInput);
                        String fname1 = fname.getText().toString();
                        EditText lname = (EditText) findViewById(R.id.lastnameInput);
                        String lname1 = lname.getText().toString();
                        EditText email = (EditText) findViewById(R.id.emailInput);
                        String email1 = email.getText().toString();
                        EditText age = (EditText) findViewById(R.id.ageInput);
                        String age1 = age.getText().toString();
                        EditText phone = (EditText) findViewById(R.id.phoneInput);
                        String phone1 = phone.getText().toString();
                        EditText address = (EditText) findViewById(R.id.addressInput);
                        String address1 = address.getText().toString();
                        EditText city = (EditText) findViewById(R.id.cityInput);
                        String city1 = city.getText().toString();
                        EditText state = (EditText) findViewById(R.id.stateInput);
                        String state1 = state.getText().toString();
                        EditText zip = (EditText) findViewById(R.id.zipInput);
                        String zip1 = zip.getText().toString();
                        String gender = "";
                        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderRadiogroup);
                        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                        RadioButton temo = (RadioButton) findViewById(checkedRadioButtonId);
                        if (temo.getText().toString().trim().equals("Male")) {
                            gender = "male";
                        } else {
                            gender = "female";
                        }
                        try {
                            String temp = (String) getIntent().getSerializableExtra("name");
                            String query = "UPDATE Patients ";
                            query += "SET FirstName = '";
                            query += fname1;
                            query += "', LastName='";
                            query += lname1;
                            query += "', Email='";
                            query += email1;
                            query += "', Age='";
                            query += age1;
                            query += "', Phone='";
                            query += phone1;
                            query += "', Address='";
                            query += address1;
                            query += "', City='";
                            query += city1;
                            query += "', State='";
                            query += state1;
                            query += "', Zip='";
                            query += zip1;
                            query += "', Gender='";
                            query += gender;
                            query += "' ";


                            query += "WHERE Account ='"+ temp+"';";

                            //System.err.println(query);


                            Statement runUsernames = con.createStatement();
                            ResultSet users = runUsernames.executeQuery(query);
                            while (users.next()) {

                            }



                        }catch (Exception ex) {
                            System.err.println("Error: Cant connect to server");
                            System.err.println(ex.getMessage());
                        }

                    }
                });

            }
        } catch (Exception ex) {
            System.err.println("Error: Cant connect to server");
            System.err.println(ex.getMessage());
        }
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
