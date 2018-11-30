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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Company2 extends AppCompatActivity {
    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String username=(String)getIntent().getSerializableExtra("username");
        final Button favorite=(Button) findViewById(R.id.favorite2);
        boolean if_favorite=false;

        Button back=(Button) findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),main.class);
                startIntent.putExtra("accountname",username);
                startActivity(startIntent);
            }
        });

        try {
            con = connectionclass();
            if (con == null) {
                System.err.println("Error: Cant connect to server");
            } else {
                String getArticles1 = "select * from favorite";
                Statement rungetArticles1 = con.createStatement();
                ResultSet result = rungetArticles1.executeQuery(getArticles1);
                while (result.next()) {
                    if (result.getString("username").equals(username)) {
                        if (result.getString("company").equals("Memorial Sloan Kettering Cancer Center")) {
                            if_favorite = true;
                            break;
                        }
                    }
                }
                if (if_favorite == true) {
                    favorite.setText("Saved");
                }
            }}catch(Exception ex){
            System.err.println("Error: Cant connect to server");
            System.err.println(ex.getMessage());
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        WebView view = (WebView) findViewById(R.id.company2web);
        String text;
        text = "<html><body> <h3>Memorial Sloan Kettering Cancer Center</h3> <p align=\"justify\">";
        text+= "Phone: (212) 639 2000</p>";
        text+= "<p align=\"justify\">Website: www.mskcc.org</p>";
        text+= "<p align=\"justify\">Address: 1275 York Avenue New York, NY 10065-6007</p>";
        text+= "<p align=\"justify\">Diseases: Cancer</p>";
        text+= "<p align=\"justify\">Description: Cancer, Gynecology, Urology Memorial Sloan-Kettering Cancer Center in New York, N.Y. is nationally ranked in 4 adult specialties and 1 pediatric specialty and rated high performing in 2 adult procedures and conditions. It is a cancer facility. It is a teaching hospital.</p>";
        text+= "</body></html>";

        Log.d("likun",text);

        view.loadData(text, "text/html", "utf-8");


        favorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                try {
                    con = connectionclass();
                    if (con == null) {
                        System.err.println("Error: Cant connect to server");
                    } else {
                        boolean if_favorite=false;

                        String getArticles1 = "select * from favorite";
                        Statement rungetArticles1 = con.createStatement();
                        ResultSet result=rungetArticles1.executeQuery(getArticles1);
                        while(result.next()){
                            if(result.getString("username").equals(username)){
                                if(result.getString("company").equals("Memorial Sloan Kettering Cancer Center")){
                                    if_favorite=true;
                                    break;
                                }
                            }

                        }
                        if(if_favorite==true){
                            favorite.setText("Saved");

                        }
                        else {
                            String getArticles = "INSERT INTO favorite (username,company) VALUES ('";
                            getArticles += username;
                            getArticles += "','";
                            getArticles += "Memorial Sloan Kettering Cancer Center";
                            getArticles += "');";
                            Statement rungetArticles = con.createStatement();
                            int articles = rungetArticles.executeUpdate(getArticles);
                            Intent startIntent=new Intent(getApplicationContext(),Company2.class);
                            startIntent.putExtra("username",username);
                            startActivity(startIntent);
                        }
                        //System.err.println(temp);

                    }
                }catch (Exception ex) {
                    System.err.println("Error: Cant connect to server");
                    System.err.println(ex.getMessage());
                }
            }
        });

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

