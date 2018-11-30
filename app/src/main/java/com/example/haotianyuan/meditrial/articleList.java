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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.DriverManager;

public class articleList extends AppCompatActivity {
    public Connection con;
    ArrayList<String> Articlelist = new ArrayList<String>();
    ArrayList<String> Articlelike = new ArrayList<String>();
    ArrayList<String> Articleunlike = new ArrayList<String>();
    ArrayList<Button> buttonList = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
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
                String getArticles = "select * from Articles";
                Statement rungetArticles = con.createStatement();
                ResultSet articles = rungetArticles.executeQuery(getArticles);
                while (articles.next()) {
                    Articlelist.add(articles.getString("title"));
                    Articlelike.add(articles.getString("good"));
                    Articleunlike.add(articles.getString("unlike"));
                }
                int marginTop=20;
                int mark=0;
                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.articleList);
               for(int i=Articlelist.size()-1;i>-1;i--){
                   String like=Articlelike.get(i);
                   String unlike=Articleunlike.get(i);
                   Button btn = new Button(this);
                   btn.setId(i);
                   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                   btn.setText(Articlelist.get(i)+"\n (like:"+ like +"  unlike:" +unlike +")\n");
                   btn.setPaintFlags(0);
                   btn.setTransformationMethod(null);
                   //btn.setTextColor(0xFFFFFFFF);
                   btn.setBackgroundColor(0xffffffff);
                   btn.setGravity(Gravity.LEFT);
                   params.addRule(RelativeLayout.BELOW, R.id.popular);
                   params.setMargins(0, marginTop, 0, 0);
                   marginTop += 200;
                   params.width = 3880;
                   btn.setLayoutParams(params);
                   buttonList.add(btn);
                   mainLayout.addView(btn);
               }

                for(int i=0;i<buttonList.size();i++){
                    final int finalI = i;
                    buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //if (finalI == 0) {
                            Intent startIntent = new Intent(getApplicationContext(), Article.class);
                            startIntent.putExtra("title", Articlelist.get(Articlelist.size() -finalI - 1));
                            startIntent.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                            //System.err.println((String)getIntent().getSerializableExtra("accountname"));
                            //System.err.println(Articlelist.get(btn.getId()));
                            startActivity(startIntent);
                            // }
                        }
                    });

                }

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
