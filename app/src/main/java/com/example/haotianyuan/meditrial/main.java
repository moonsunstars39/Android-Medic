package com.example.haotianyuan.meditrial;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.widget.SearchView.*;

public class main extends AppCompatActivity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE=1;
    private TextView mTextMessage;
    ImageView profilePicture;
    public Connection con;
    //Button btn;
    Button btn;
    ArrayList<String> Articlelist = new ArrayList<String>();
    ArrayList<String> Articletitle = new ArrayList<String>();
    ArrayList<Button> buttonList = new ArrayList<Button>();
    int tempindex = 0;
    int buttonindex=0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            SearchView search=(SearchView) findViewById(R.id.search);
            TextView popular=(TextView) findViewById(R.id.popular);
            TextView name=(TextView) findViewById(R.id.name);
            Button manage=(Button) findViewById(R.id.manageprofile);
            Button about=(Button) findViewById(R.id.about);
            Button help=(Button) findViewById(R.id.signout);
            Button contact=(Button) findViewById(R.id.contact);
            Button write=(Button) findViewById(R.id.writearticle);
            Button btn0=(Button) findViewById(0);
            Button btn1=(Button) findViewById(1);
            Button btn2=(Button) findViewById(2);
            Button btn3=(Button) findViewById(3);
            Button btn4=(Button) findViewById(4);
            Button btn5=(Button) findViewById(5);
            Button btn7=(Button) findViewById(7);
            Button btn8=(Button) findViewById(8);

            ImageView profilePicture_temp = (ImageView) findViewById(R.id.profilepicture);

            switch (item.getItemId()) {
                case R.id.navigation_search:
                    //mTextMessage.setText("Search");
                    search.setVisibility(View.VISIBLE);
                    popular.setVisibility(View.VISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    manage.setVisibility(View.INVISIBLE);
                    about.setVisibility(View.INVISIBLE);
                    help.setVisibility(View.INVISIBLE);
                    contact.setVisibility(View.INVISIBLE);
                    profilePicture_temp.setVisibility(View.INVISIBLE);
                    write.setVisibility(View.VISIBLE);
                    btn0.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    btn5.setVisibility(View.VISIBLE);
                    if(findViewById(7)!=null){
                        btn7.setVisibility(View.INVISIBLE);
                    }
                    if(findViewById(8)!=null){
                        btn8.setVisibility(View.INVISIBLE);
                    }

                    return true;
                case R.id.navigation_favorite:
                    //mTextMessage.setText("Favorites");
                    search.setVisibility(View.INVISIBLE);
                    popular.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    manage.setVisibility(View.INVISIBLE);
                    about.setVisibility(View.INVISIBLE);
                    help.setVisibility(View.INVISIBLE);
                    contact.setVisibility(View.INVISIBLE);
                    write.setVisibility(View.INVISIBLE);
                    profilePicture_temp.setVisibility(View.INVISIBLE);
                    btn0.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                    btn5.setVisibility(View.INVISIBLE);
                    if(findViewById(7)!=null){
                        btn7.setVisibility(View.VISIBLE);
                    }
                    if(findViewById(8)!=null){
                        btn8.setVisibility(View.VISIBLE);
                    }

                    return true;
                case R.id.navigation_account:
                    //mTextMessage.setText("Account");

                    search.setVisibility(View.INVISIBLE);
                    popular.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.VISIBLE);
                    manage.setVisibility(View.VISIBLE);
                    about.setVisibility(View.VISIBLE);
                    help.setVisibility(View.VISIBLE);
                    contact.setVisibility(View.VISIBLE);
                    write.setVisibility(View.INVISIBLE);
                    profilePicture_temp.setVisibility(View.VISIBLE);
                    btn0.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                    btn5.setVisibility(View.INVISIBLE);
                    if(findViewById(7)!=null){
                        btn7.setVisibility(View.INVISIBLE);
                    }
                    if(findViewById(8)!=null){
                        btn8.setVisibility(View.INVISIBLE);
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView simpleSearchView = (SearchView) findViewById(R.id.search); // inititate a search view

        String temp=(String)getIntent().getSerializableExtra("accountname");
        TextView name=(TextView) findViewById(R.id.name);
        name.setVisibility(View.INVISIBLE);
        name.setText(temp);

        Button manage=(Button) findViewById(R.id.manageprofile);
        manage.setVisibility(View.INVISIBLE);
        Button about=(Button) findViewById(R.id.about);
        about.setVisibility(View.INVISIBLE);
        Button help=(Button) findViewById(R.id.signout);
        help.setVisibility(View.INVISIBLE);
        Button contact=(Button) findViewById(R.id.contact);
        contact.setVisibility(View.INVISIBLE);
        ImageView profilePicture_temp = (ImageView) findViewById(R.id.profilepicture);
        profilePicture_temp.setVisibility(View.INVISIBLE);


        //btn8.setVisibility(View.INVISIBLE);


        simpleSearchView.setQueryHint("What are your looking for?");

        manage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String temp=(String)getIntent().getSerializableExtra("accountname");
                Intent startIntent=new Intent(getApplicationContext(),profile.class);
                startIntent.putExtra("name", temp);
                startActivity(startIntent);
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),about.class);
                startActivity(startIntent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),contract.class);
                startActivity(startIntent);
            }
        });

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),index.class);
                startActivity(startIntent);
            }
        });


        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent startIntent = new Intent(getApplicationContext(), Result.class);
                startIntent.putExtra("keyword", query);
                startIntent.putExtra("username",(String)getIntent().getSerializableExtra("accountname"));
                startActivity(startIntent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        profilePicture = (ImageView) findViewById(R.id.profilepicture);
        profilePicture.setOnClickListener(this);

        Button share=(Button) findViewById(R.id.writearticle);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent=new Intent(getApplicationContext(),writeArticle.class);
                String temp=(String)getIntent().getSerializableExtra("accountname");
                startIntent.putExtra("accountname", temp);
                startActivity(startIntent);
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
                //System.err.println(temp);
                int index=0;

                while (articles.next()) {
                    Articlelist.add(articles.getString("title"));
                }
            }
        }catch (Exception ex) {
            System.err.println("Error: Cant connect to server");
            System.err.println(ex.getMessage());
        }
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        int marginTop=50;
        for(int i=0;i<6;i++){
            if(i==5){
                Button btn= new Button(this);
                btn.setId(i);
                RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                btn.setText("Read more...");
                btn.setTransformationMethod(null);
                btn.setTextColor(0xFFFF0000);
                btn.setBackgroundColor(0xffffffff);
                params.addRule(RelativeLayout.BELOW, R.id.popular);
                params.setMargins(0,marginTop,0,0);
                btn.setLayoutParams(params);
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent startIntent=new Intent(getApplicationContext(),articleList.class);
                        startIntent.putExtra("username",(String)getIntent().getSerializableExtra("accountname"));
                        startActivity(startIntent);
                    }
                });
                mainLayout.addView(btn);
            }else {
                Button btn = new Button(this);
                btn.setId(i);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                btn.setText(Articlelist.get(Articlelist.size() - i - 1));
                btn.setTransformationMethod(null);
                btn.setTextColor(0xFF0000FF);
                btn.setBackgroundColor(0xffffffff);
                params.addRule(RelativeLayout.BELOW, R.id.popular);
                params.setMargins(0, marginTop, 0, 0);
                marginTop += 130;
                params.width = 3880;

                btn.setLayoutParams(params);
                buttonList.add(btn);
                mainLayout.addView(btn);
            }
        }

        for(int i=0;i<5;i++){
            final int finalI = i;
            buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if (finalI == 0) {
                        Intent startIntent = new Intent(getApplicationContext(), Article.class);
                        startIntent.putExtra("title", Articlelist.get(Articlelist.size() -finalI - 1));
                         startIntent.putExtra("username",(String)getIntent().getSerializableExtra("accountname"));

                        //System.err.println((String)getIntent().getSerializableExtra("accountname"));
                        startActivity(startIntent);
                   // }
                }
            });

        }

        try {
            con = connectionclass();
            if (con == null) {
                System.err.println("Error: Cant connect to server");
            } else {
                final ArrayList<Button> buttonList = new ArrayList<Button>();
                String getArticles = "select * from favorite";
                Statement rungetArticles = con.createStatement();
                ResultSet articles = rungetArticles.executeQuery(getArticles);
                RelativeLayout mainLayouttemp = (RelativeLayout) findViewById(R.id.mainLayout);
                int index=7;
                 marginTop=0;
                while (articles.next()) {
                    if(articles.getString("username").equals((String)getIntent().getSerializableExtra("accountname"))) {
                        btn = new Button(this);
                        btn.setText(articles.getString("company"));
                        btn.setId(index);
                        index++;
                        btn.setPaintFlags(0);
                        btn.setTransformationMethod(null);
                        //btn.setTextColor(0xFFFFFFFF);
                        btn.setBackgroundColor(0xffffffff);
                        btn.setGravity(Gravity.LEFT);

                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, marginTop, 0, 0);
                        marginTop += 200;
                        params.width = 3880;
                        params.addRule(RelativeLayout.BELOW, R.id.search);
                        btn.setLayoutParams(params);
                        buttonList.add(btn);
                        mainLayouttemp.addView(btn);
                    }
                }
                for(int i=0;i<buttonList.size();i++){
                    final int finalI = i;
                    buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (buttonindex == 0) {
                                Intent startIntent = new Intent(getApplicationContext(), Company1.class);
                                startIntent.putExtra("title", Articlelist.get(Articlelist.size() - finalI - 1));
                                startIntent.putExtra("username", (String) getIntent().getSerializableExtra("accountname"));
                                //System.err.println((String)getIntent().getSerializableExtra("accountname"));
                                //System.err.println(Articlelist.get(btn.getId()));
                                startActivity(startIntent);
                                buttonindex++;
                            }else{
                                Intent startIntent = new Intent(getApplicationContext(), Company2.class);
                                startIntent.putExtra("title", Articlelist.get(Articlelist.size() - finalI - 1));
                                startIntent.putExtra("username", (String) getIntent().getSerializableExtra("accountname"));
                                //System.err.println((String)getIntent().getSerializableExtra("accountname"));
                                //System.err.println(Articlelist.get(btn.getId()));
                                startActivity(startIntent);
                            }
                        }
                    });

                }


            }
        }catch (Exception ex) {
            System.err.println("Error: Cant connect to server");
            System.err.println(ex.getMessage());
        }

        Button btn7=(Button) findViewById(7);
        Button btn8=(Button) findViewById(8);
        if(findViewById(7)!=null){
            btn7.setVisibility(View.INVISIBLE);
        }
        if(findViewById(8)!=null){
            btn8.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.profilepicture:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && requestCode == RESULT_OK && data != null){
            Uri selectedImage=data.getData();
            profilePicture.setImageURI(selectedImage);
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
















































