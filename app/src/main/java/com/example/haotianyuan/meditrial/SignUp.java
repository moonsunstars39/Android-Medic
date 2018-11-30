package com.example.haotianyuan.meditrial;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    public Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        Button signup = (Button) findViewById(R.id.Signup);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.passwordSignup);
                EditText passwordConfirm = (EditText) findViewById(R.id.confirmpassword);
                EditText email = (EditText) findViewById(R.id.email);

                if(username.getText().toString().trim().equals("")) {
                    username.setError("User name is required.");
                }
                else if(password.getText().toString().trim().equals("")) {
                    password.setError("Password is required.");
                }
                else if(passwordConfirm.getText().toString().trim().equals("")) {
                    passwordConfirm.setError("Password confirmation is required.");
                }
                else if(passwordConfirm.getText().toString().trim().equals(password.getText().toString().trim())==false) {
                    passwordConfirm.setError("Password confirmation must be the same as your password.");
                }
                else if(isEmailValid(email.getText().toString().trim())==false) {
                    email.setError("Invalid email address.");
                }
                else {
                    try {
                        con = connectionclass();
                        if (con == null) {
                            System.err.println("Error: Cant connect to server");
                        } else {
                            //String query = "select * from Patients";  //SQL command
                            String usernames = "select * from Patients";
                            Statement runUsernames = con.createStatement();
                            ResultSet result = runUsernames.executeQuery(usernames);
                            boolean isUserExisting = false;
                            while (result.next()) {
                                if (username.getText().toString().equals(result.getString("Account"))) {
                                    isUserExisting = true;
                                }
                            }

                            String companies = "select * from Companies";
                            Statement runCompanies = con.createStatement();
                            ResultSet result1 = runCompanies.executeQuery(companies);
                            while (result1.next()) {
                                System.err.println(result1.getString("Account"));
                                if (username.getText().toString().equals(result1.getString("Account"))) {
                                    isUserExisting = true;
                                }
                            }

                            if (isUserExisting == true) {
                                username.setError("User name is existing.");

                            } else {
                                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
                                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                                RadioButton temo= (RadioButton) findViewById(checkedRadioButtonId);
                                //System.err.println(temo.getText().toString());

                                Intent startIntent = new Intent(getApplicationContext(), signupsuccessfully.class);
                                startActivity(startIntent);

                                if (temo.getText().toString().trim().equals("No")) {
                                    // patients register

                                    String query = "INSERT INTO Patients (Account,Password,Email,Gender) VALUES ('";
                                    query += username.getText().toString();
                                    query += "','";
                                    query += password.getText().toString();
                                    query += "','";
                                    query += email.getText().toString();
                                    query += "','male');";

                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery(query);
                                    while (rs.next()) {
                                        //do nothing
                                    }
                                }
                                else{
                                    //company register
                                    //System.err.println("here!");
                                    String query = "INSERT INTO Companies (Account,Password,Email,Authentication) VALUES ('";
                                    query += username.getText().toString();
                                    query += "','";
                                    query += password.getText().toString();
                                    query += "','";
                                    query += email.getText().toString();
                                    query += "','no');";

                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery(query);
                                    while (rs.next()) {
                                        //do nothing
                                    }
                                }

                            }
                            //String temp="";
                            //while(rs.next()){
                            //   temp+=rs.getString("Account");
                            //TextView test = (TextView) findViewById(R.id.test);
                            //test.setText(rs.getString("Account"));

                            //System.out.println(rs.getString("Account"));
                            //}
                            //EditText test = (EditText) findViewById(R.id.username);
                            //test.setHint(temp);
                        }


                    } catch (Exception ex) {
                        System.err.println("Error: Cant connect to server");
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });

        //set the link for terms of service
        TextView textView = (TextView) findViewById(R.id.agreement);
        textView.setLinkTextColor(Color.BLUE); // default link color for clickable span, we can also set it in xml by android:textColorLink=""
        ClickableSpan normalLinkClickSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Terms of Service", Toast.LENGTH_SHORT).show();
                Intent startIntent=new Intent(getApplicationContext(),TermsOfService.class);
                startActivity(startIntent);
            }

        };
        //set the link for privacy policy
        ClickableSpan normalLinkClickSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Privacy Policy", Toast.LENGTH_SHORT).show();
                Intent startIntent=new Intent(getApplicationContext(),TermsOfPrivacy.class);
                startActivity(startIntent);
            }

        };

        makeLinks(textView, new String[] {
                "Terms of Service", "Privacy Policy"
        }, new ClickableSpan[] {
                normalLinkClickSpan,normalLinkClickSpan1
        });




    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        SpannableString spannableString = new SpannableString(textView.getText());
        for (int i = 0; i < links.length; i++) {
            ClickableSpan clickableSpan = clickableSpans[i];
            String link = links[i];

            int startIndexOfLink = textView.getText().toString().indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink,
                    startIndexOfLink + link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setHighlightColor(
                Color.TRANSPARENT); // prevent TextView change background when highlight
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
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

