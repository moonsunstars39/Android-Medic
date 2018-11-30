package com.example.haotianyuan.meditrial;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class TermsOfPrivacy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_privacy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView view = (WebView) findViewById(R.id.PrivacyContent );
        String text;
        text = "<html><body> <h3>Using our Services</h3> <p align=\"justify\">";
        text+="<h1>Privacy Policy</h1><br>";
        text+="<br>";
        text+="<br>";
        text+="<p>Effective date: November 13, 2018</p><br>";
        text+= "<br>";
        text+= "<br>";
        text+= "<p>Meditrial (\"us\", \"we\", or \"our\") operates the Meditrial mobile application (the \"Service\").</p><br>";
        text+= "<br>";
        text+= "<p>This page informs you of our policies regarding the collection, use, and disclosure of personal data when you use our Service and the choices you have associated with that data. Our Privacy Policy  for Meditrial is managed through <a href=\"https://www.freeprivacypolicy.com/free-privacy-policy-generator.php\">Free Privacy Policy Website</a>.</p><br>";
        text+= "<br>";
        text+= "<p>We use your data to provide and improve the Service. By using the Service, you agree to the collection and use of information in accordance with this policy. Unless otherwise defined in this Privacy Policy, terms used in this Privacy Policy have the same meanings as in our Terms and Conditions.</p><br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<h2>Information Collection And Use</h2><br>";
        text+=  "<br>";
        text+=  "<p>We collect several different types of information for various purposes to provide and improve our Service to you.</p><br>";
        text+=  "<br>";
        text+=  "<h3>Types of Data Collected</h3><br>";
        text+=  "<br>";
        text+=  "<h4>Personal Data</h4><br>";
        text+=  "<br>";
        text+=  "<p>While using our Service, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you (\"Personal Data\"). Personally identifiable information may include, but is not limited to:</p><br>";
        text+=  "<br>";
        text+=  "<ul><br>";
        text+=  "<li>Email address</li><li>First name and last name</li><li>Phone number</li><li>Address, State, Province, ZIP/Postal code, City</li><li>Cookies and Usage Data</li><br>";
        text+=  "</ul><br>";
        text+=  "<br>";
        text+=  "<h4>Usage Data</h4><br>";
        text+=  "<br>";
        text+=  "<p>When you access the Service by or through a mobile device, we may collect certain information automatically, including, but not limited to, the type of mobile device you use, your mobile device unique ID, the IP address of your mobile device, your mobile operating system, the type of mobile Internet browser you use, unique device identifiers and other diagnostic data (\"Usage Data\").</p><br>";
        text+=  "<br>";
        text+=  "<h4>Tracking & Cookies Data</h4><br>";
        text+=  "<p>We use cookies and similar tracking technologies to track the activity on our Service and hold certain information.</p><br>";
        text+=  "<p>Cookies are files with small amount of data which may include an anonymous unique identifier. Cookies are sent to your browser from a website and stored on your device. Tracking technologies also used are beacons, tags, and scripts to collect and track information and to improve and analyze our Service.</p><br>";
        text+=  "<p>You can instruct your browser to refuse all cookies or to indicate when a cookie is being sent. However, if you do not accept cookies, you may not be able to use some portions of our Service.</p>\n";
        text+=  "<p>Examples of Cookies we use:</p><br>";
        text+=  "<ul><br>";
        text+=  "    <li><strong>Session Cookies.</strong> We use Session Cookies to operate our Service.</li><br>";
        text+=  "    <li><strong>Preference Cookies.</strong> We use Preference Cookies to remember your preferences and various settings.</li><br>";
        text+=  "    <li><strong>Security Cookies.</strong> We use Security Cookies for security purposes.</li><br>";
        text+=  "</ul><br>";
        text+=  "<br>";
        text+=  "<h2>Use of Data</h2><br>";
        text+=  "    <br>";
        text+=  "<p>Meditrial uses the collected data for various purposes:</p>    <br>";
        text+=  "<ul><br>";
        text+=  "    <li>To provide and maintain the Service</li><br>";
        text+=  "    <li>To notify you about changes to our Service</li><br>";
        text+=  "    <li>To allow you to participate in interactive features of our Service when you choose to do so</li><br>";
        text+=  "    <li>To provide customer care and support</li><br>";
        text+=  "    <li>To provide analysis or valuable information so that we can improve the Service</li><br>";
        text+=  "    <li>To monitor the usage of the Service</li><br>";
        text+=  "    <li>To detect, prevent and address technical issues</li><br>";
        text+=  "</ul><br>";
        text+=  "<br>";
        text+=  "<h2>Transfer Of Data</h2><br>";
        text+=  "<p>Your information, including Personal Data, may be transferred to — and maintained on — computers located outside of your state, province, country or other governmental jurisdiction where the data protection laws may differ than those from your jurisdiction.</p><br>";
        text+=  "<p>If you are located outside United States and choose to provide information to us, please note that we transfer the data, including Personal Data, to United States and process it there.</p><br>";
        text+=  "<p>Your consent to this Privacy Policy followed by your submission of such information represents your agreement to that transfer.</p>\n";
        text+=  "<p>Meditrial will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this Privacy Policy and no transfer of your Personal Data will take place to an organization or a country unless there are adequate controls in place including the security of your data and other personal information.</p><br>";
        text+=  "<br>";
        text+=  "<h2>Disclosure Of Data</h2><br>";
        text+=  "<br>";
        text+=  "<h3>Legal Requirements</h3><br>";
        text+=  "<p>Meditrial may disclose your Personal Data in the good faith belief that such action is necessary to:</p><br>";
        text+=  "<ul><br>";
        text+=  "    <li>To comply with a legal obligation</li><br>";
        text+=  "    <li>To protect and defend the rights or property of Meditrial</li><br>";
        text+=  "    <li>To prevent or investigate possible wrongdoing in connection with the Service</li><br>";
        text+=  "    <li>To protect the personal safety of users of the Service or the public</li><br>";
        text+=  "    <li>To protect against legal liability</li><br>";
        text+=  "</ul><br>";
        text+=  "<br>";
        text+=  "<h2>Security Of Data</h2><br>";
        text+=  "<p>The security of your data is important to us, but remember that no method of transmission over the Internet, or method of electronic storage is 100% secure. While we strive to use commercially acceptable means to protect your Personal Data, we cannot guarantee its absolute security.</p><br>";
        text+=  "<br>";
        text+=  "<h2>Service Providers</h2><br>";
        text+=  "<p>We may employ third party companies and individuals to facilitate our Service (\"Service Providers\"), to provide the Service on our behalf, to perform Service-related services or to assist us in analyzing how our Service is used.</p><br>";
        text+=  "<p>These third parties have access to your Personal Data only to perform these tasks on our behalf and are obligated not to disclose or use it for any other purpose.</p><br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<h2>Links To Other Sites</h2><br>";
        text+=  "<p>Our Service may contain links to other sites that are not operated by us. If you click on a third party link, you will be directed to that third party's site. We strongly advise you to review the Privacy Policy of every site you visit.</p><br>";
        text+=  "<p>We have no control over and assume no responsibility for the content, privacy policies or practices of any third party sites or services.</p><br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<h2>Children's Privacy</h2><br>";
        text+=  "<p>Our Service does not address anyone under the age of 18 (\"Children\").</p><br>";
        text+=  "<p>We do not knowingly collect personally identifiable information from anyone under the age of 18. If you are a parent or guardian and you are aware that your Children has provided us with Personal Data, please contact us. If we become aware that we have collected Personal Data from children without verification of parental consent, we take steps to remove that information from our servers.</p><br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<h2>Changes To This Privacy Policy</h2><br>";
        text+=  "<p>We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.</p><br>";
        text+=  "<p>We will let you know via email and/or a prominent notice on our Service, prior to the change becoming effective and update the \"effective date\" at the top of this Privacy Policy.</p><br>";
        text+=  "<p>You are advised to review this Privacy Policy periodically for any changes. Changes to this Privacy Policy are effective when they are posted on this page.</p><br>";
        text+=  "<br>";
        text+=  "<br>";
        text+=  "<h2>Contact Us</h2><br>";
        text+=  "<p>If you have any questions about this Privacy Policy, please contact us:</p><br>";
        text+=  "<ul><br>";
        text+=  "        <li>By email: yuanhaotian2015@gmail.com</li><br>";
        text+=  "          <br>";
        text+=  "        </ul>";

        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
