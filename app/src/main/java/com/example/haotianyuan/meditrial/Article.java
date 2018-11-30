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
import android.widget.TextView;

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

public class Article extends AppCompatActivity {
    public Connection con;
    String title;
    ArrayList<String> authors = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
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

                title = (String) getIntent().getSerializableExtra("title");
                String usernames = "select * from Articles";
                Statement runUsernames = con.createStatement();
                ResultSet users = runUsernames.executeQuery(usernames);
                //System.err.println(temp);
                String content = "";
                String author = "";
                String like = "";
                String dislike = "";
                while (users.next()) {
                    if (users.getString("title").equals(title)) {
                        content = users.getString("content");
                        author = users.getString("author");
                        like = users.getString("good");
                        dislike = users.getString("unlike");
                    }
                }

                String reviewQuery = "select * from comment";
                Statement reviewCommand = con.createStatement();
                ResultSet reviewResult = reviewCommand.executeQuery(reviewQuery);
                //System.err.println(temp);
                while (reviewResult.next()) {
                    if (reviewResult.getString("article").equals(title)) {
                        authors.add(reviewResult.getString("username"));
                        comments.add(reviewResult.getString("comment"));
                    }
                }

                WebView view = (WebView) findViewById(R.id.articleweb);
                String text;
                text = "<html><body> <h3>";
                text += title;
                text += "</h3>";
                text += "<p>Author: ";
                text += author;
                text += "</p>";
                text += "<p align=\"justify\">";
                text += content;
                text += "<br>";

                text += "</p></body></html>";
                view.loadData(text, "text/html", "utf-8");

                WebView Reviewview = (WebView) findViewById(R.id.revieweweb);
                String review;
                review = "<html><body> <h3>";
                review += "Comments: ";
                review += "</h3>";
                for(int i=authors.size()-1;i>-1;i--) {
                    review += "<p align=\"justify\" style=\"width: 210px; height: 60px; box-shadow: 11px 22px 6px gray; padding: 2.5em; margin: 20px; border: 2px solid ; border: 2px solid; border-radius:25px;\">From: ";
                    review += authors.get(i);
                    review += "<br>";
                    review += "Comment: ";
                    review += comments.get(i);
                    review += "</p><br>";
                }
                review+="</body></html>";
                Reviewview.loadData(review, "text/html", "utf-8");

                Button likeView = (Button) findViewById(R.id.like);
                Button dislikeView = (Button) findViewById(R.id.unlike);
                likeView.setText(like);
                dislikeView.setText(dislike);


                boolean liked = false;
                boolean disliked = false;
                String query = "select * from likernot";
                Statement query1 = con.createStatement();
                ResultSet queryResult = query1.executeQuery(query);
                //System.err.println(title+" "+(String) getIntent().getSerializableExtra("username"));
                while (queryResult.next()) {
                    //System.err.println("here!!!!!!!!!!!!!!!!");
                    //System.err.println(queryResult.getString("dislike"));
                    //System.err.println(queryResult.getString("objectname"));
                    //System.err.println(title);
                    if (queryResult.getString("objectname").equals(title)) {
                        if (queryResult.getString("username").equals((String) getIntent().getSerializableExtra("username"))) {
                            if (queryResult.getString("good").equals("true")) {
                                liked = true;
                            }
                            //if(!queryResult.getString("dislike").equals(null)) {
                            if (queryResult.getString("dislike").equals("true")) {
                                    disliked = true;
                            }
                            //}
                        }
                    }
                }

                if (liked == false) {

                    likeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                Intent startIntent = new Intent(getApplicationContext(), Article.class);
                                startIntent.putExtra("title", title);
                                startIntent.putExtra("username", (String) getIntent().getSerializableExtra("username"));
                                startActivity(startIntent);

                                String usernames = "select * from Articles";
                                Statement runUsernames = con.createStatement();
                                ResultSet users = runUsernames.executeQuery(usernames);
                                //System.err.println(title);
                                while (users.next()) {
                                    if (users.getString("title").equals(title)) {
                                        //System.err.println(title);
                                        int templike = Integer.parseInt(users.getString("good"));
                                        templike++;
                                        String templike1 = String.valueOf(templike);
                                        String usernames1 = "UPDATE Articles ";
                                        usernames1 += "SET good = '";
                                        usernames1 += templike1;
                                        usernames1 += "' ";
                                        usernames1 += "WHERE title='";
                                        usernames1 += title;
                                        usernames1 += "';";
                                        //System.err.println(usernames1);
                                        Statement runUsernames1 = con.createStatement();
                                        int users1 = runUsernames1.executeUpdate(usernames1);

                                        String username = (String) getIntent().getSerializableExtra("username");
                                        String query = "INSERT INTO likernot (username, good,objectname,dislike)";
                                        query += " VALUES ('";
                                        query += username;
                                        query += "', 'true', '";
                                        query += title;
                                        query += "','false');";
                                        //System.err.println(query);
                                        Statement query1 = con.createStatement();

                                        int resultquery = query1.executeUpdate(query);
                                    }
                                }

                            } catch (Exception ex) {
                                System.err.println("Error: Cant connect to server");
                                System.err.println(ex.getMessage());
                            }
                        }
                    });


                }
                if(disliked==false){
                    //System.err.println("here");
                    dislikeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                Intent startIntent = new Intent(getApplicationContext(), Article.class);
                                startIntent.putExtra("title", title);
                                startIntent.putExtra("username", (String) getIntent().getSerializableExtra("username"));
                                startActivity(startIntent);

                                String usernames = "select * from Articles";
                                Statement runUsernames = con.createStatement();
                                ResultSet users = runUsernames.executeQuery(usernames);
                                //System.err.println(title);
                                while (users.next()) {
                                    if (users.getString("title").equals(title)) {
                                        //System.err.println(title);
                                        int templike = Integer.parseInt(users.getString("unlike"));
                                        templike++;
                                        String templike1 = String.valueOf(templike);
                                        String usernames1 = "UPDATE Articles ";
                                        usernames1 += "SET unlike = '";
                                        usernames1 += templike1;
                                        usernames1 += "' ";
                                        usernames1 += "WHERE title='";
                                        usernames1 += title;
                                        usernames1 += "';";
                                        //System.err.println(usernames1);
                                        Statement runUsernames1 = con.createStatement();
                                        int users1 = runUsernames1.executeUpdate(usernames1);

                                        String username = (String) getIntent().getSerializableExtra("username");
                                        String query = "INSERT INTO likernot (username, dislike,objectname,good)";
                                        query += " VALUES ('";
                                        query += username;
                                        query += "', 'true', '";
                                        query += title;
                                        query += "','false');";
                                        //System.err.println(query);
                                        Statement query1 = con.createStatement();

                                        int resultquery = query1.executeUpdate(query);
                                    }
                                }

                            } catch (Exception ex) {
                                System.err.println("Error: Cant connect to server");
                                System.err.println(ex.getMessage());
                            }
                        }
                    });
                }
            }
        } catch (Exception ex) {
            System.err.println("Error: Cant connect to server");
            System.err.println(ex.getMessage());
        }

        Button back=(Button) findViewById(R.id.commentback);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),main.class);
                startIntent.putExtra("accountname", (String) getIntent().getSerializableExtra("username"));

                startActivity(startIntent);
            }
        });


        Button upload=(Button) findViewById(R.id.submitComment);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {



                    Intent startIntent = new Intent(getApplicationContext(), Article.class);
                    startIntent.putExtra("title", title);
                    startIntent.putExtra("username", (String) getIntent().getSerializableExtra("username"));
                    startActivity(startIntent);

                    EditText commentContent=(EditText) findViewById(R.id.writecomment);
                    String comment=commentContent.getText().toString();

                    String usernames = "INSERT INTO comment (username, comment, article) VALUES ('";
                    usernames+= (String) getIntent().getSerializableExtra("username");
                    usernames+= "','";
                    usernames+= comment;
                    usernames+= "','";
                    usernames+= title;
                    usernames+= "');";

                    Statement runUsernames = con.createStatement();
                    int users = runUsernames.executeUpdate(usernames);
                    //System.err.println(title);

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
