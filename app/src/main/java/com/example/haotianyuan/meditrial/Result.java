package com.example.haotianyuan.meditrial;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class Result extends AppCompatActivity {
    public Connection con;
    ArrayList<Button> buttonArrayList = new ArrayList<Button>();
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.err.println("here");
        username=(String)getIntent().getSerializableExtra("username");

        Button company1=(Button) findViewById(R.id.company1);
        company1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company1.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company2=(Button) findViewById(R.id.company2);
        company2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company3=(Button) findViewById(R.id.company3);
        company3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company4=(Button) findViewById(R.id.company4);
        company4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company5=(Button) findViewById(R.id.company5);
        company5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company1.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company6=(Button) findViewById(R.id.company6);
        company6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company7=(Button) findViewById(R.id.company7);
        company7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company1.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company8=(Button) findViewById(R.id.company8);
        company8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company1.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });
        Button company9=(Button) findViewById(R.id.company9);
        company9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                startIntent.putExtra("username",username);
                startActivity(startIntent);
            }
        });

        try {
            con = connectionclass();
            if (con == null) {
                System.err.println("Error: Cant connect to server");
            } else {

                String keyword = (String) getIntent().getSerializableExtra("keyword");
                String getArticles = "select * from Sheet1$";
                Statement rungetArticles = con.createStatement();
                ResultSet articles = rungetArticles.executeQuery(getArticles);
                RelativeLayout resultLayout = (RelativeLayout) findViewById(R.id.companyList);
                int marginTop=200;
                int num=0;
                while (articles.next()) {

                }
            }
        }catch (Exception ex) {
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
