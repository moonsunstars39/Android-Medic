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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.sql.DriverManager;

public class writeArticle extends AppCompatActivity {
    public Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_article);
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

        Button upload = (Button) findViewById(R.id.articleSubmit);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    con = connectionclass();
                    if (con == null) {
                        System.err.println("Error: Cant connect to server");
                    } else {
                        EditText title = (EditText) findViewById(R.id.articleTitle);
                        EditText content = (EditText) findViewById(R.id.articleContent);
                        String author=(String)getIntent().getSerializableExtra("accountname");

                        Intent startIntent=new Intent(getApplicationContext(),main.class);
                        startIntent.putExtra("accountname", (String) getIntent().getSerializableExtra("accountname"));
                        startActivity(startIntent);

                        String query = "INSERT INTO Articles (title,content,author,unlike,good) VALUES ('";
                        query += title.getText().toString();
                        query += "','";
                        query += content.getText().toString();
                        query += "','";
                        query += author;
                        query += "','0','0');";

                        Statement runArticles = con.createStatement();
                        ResultSet articles = runArticles.executeQuery(query);
                        while (articles.next()) {
                            //do nothing
                        }
                    }
                } catch (Exception ex) {
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
