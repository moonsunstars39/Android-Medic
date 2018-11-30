package com.example.haotianyuan.meditrial;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class TermsOfService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView view = (WebView) findViewById(R.id.ServiceContent );
        String text;
        text = "<html><body> <h3>Using our Services</h3> <p align=\"justify\">";
        text+= "You must follow any policies made available to you within the Services.<br>For example, don’t interfere with our Services or try to access them using a method other than the interface and the instructions that we provide. You may use our Services only as permitted by law, including applicable export and re-export control laws and regulations. We may suspend or stop providing our Services to you if you do not comply with our terms or policies or if we are investigating suspected misconduct.";
        text+= "<br>Using our Services does not give you ownership of any intellectual property rights in our Services or the content you access. You may not use content from our Services unless you obtain permission from its owner or are otherwise permitted by law. These terms do not grant you the right to use any branding or logos used in our Services. Don’t remove, obscure, or alter any legal notices displayed in or along with our Services.";
        text+= "<br>Our Services display some content that is not Meditrial’s. This content is the sole responsibility of the entity that makes it available. We may review content to determine whether it is illegal or violates our policies, and we may remove or refuse to display content that we reasonably believe violates our policies or the law. But that does not necessarily mean that we review content, so please don’t assume that we do.";
        text+= "<br> In connection with your use of the Services, we may send you service announcements, administrative messages, and other information. You may opt out of some of those communications.";
        text+= "<br> Some of our Services are available on mobile devices. Do not use such Services in a way that distracts you and prevents you from obeying traffic or safety laws.</p>";
        text+= "<br><h3>Your Meditrial Account</h3>";
        text+= "<p align=\"justify\">You may need a Meditrial Account in order to use some of our Services. You may create your own Meditrial Account, or your Meditrial Account may be assigned to you by an administrator, such as your employer or educational institution. If you are using a Meditrial Account assigned to you by an administrator, different or additional terms may apply and your administrator may be able to access or disable your account.";
        text+= "<br>To protect your Meditrial Account, keep your password confidential. You are responsible for the activity that happens on or through your Meditrial Account. Try not to reuse your Meditrial Account password on third-party applications.</p>";
        text+= "<br><h3>Your Content in our Services</h3>";
        text+= "<p align=\"justify\">Some of our Services allow you to upload, submit, store, send or receive content. You retain ownership of any intellectual property rights that you hold in that content. In short, what belongs to you stays yours.";
        text+= "<br>When you upload, submit, store, send or receive content to or through our Services, you give Meditrial (and those we work with) a worldwide license to use, host, store, reproduce, modify, create derivative works (such as those resulting from translations, adaptations or other changes we make so that your content works better with our Services), communicate, publish, publicly perform, publicly display and distribute such content. The rights you grant in this license are for the limited purpose of operating, promoting, and improving our Services, and to develop new ones. This license continues even if you stop using our Services (for example, for a business listing you have added to Google Maps). Some Services may offer you ways to access and remove content that has been provided to that Service. Also, in some of our Services, there are terms or settings that narrow the scope of our use of the content submitted in those Services. Make sure you have the necessary rights to grant us this license for any content that you submit to our Services.";
        text+= "<br>Our automated systems analyze your content (including emails) to provide you personally relevant product features, such as customized search results, tailored advertising, and spam and malware detection. This analysis occurs as the content is sent, received, and when it is stored.";
        text+= "<br>If you have a Meditrial Account, we may display your Profile name, Profile photo, and actions you take on Meditrial or on third-party applications connected to your Meditrial Account (such as +1’s, reviews you write and comments you post) in our Services, including displaying in ads and other commercial contexts. We will respect the choices you make to limit sharing or visibility settings in your Meditrial Account. For example, you can choose your settings so your name and photo do not appear in an ad.";
        text+= "<br>You can find more information about how Meditrial uses and stores content in the privacy policy or additional terms for particular Services. If you submit feedback or suggestions about our Services, we may use your feedback or suggestions without obligation to you.</p>";
        text+= "<br><h3>About these Terms</h3>";
        text+= "<p align=\"justify\">We may modify these terms or any additional terms that apply to a Service to, for example, reflect changes to the law or changes to our Services. You should look at the terms regularly. We’ll post notice of modifications to these terms on this page. We’ll post notice of modified additional terms in the applicable Service. Changes will not apply retroactively and will become effective no sooner than fourteen days after they are posted. However, changes addressing new functions for a Service or changes made for legal reasons will be effective immediately. If you do not agree to the modified terms for a Service, you should discontinue your use of that Service.";
        text+= "<br>If there is a conflict between these terms and the additional terms, the additional terms will control for that conflict.";
        text+= "<br>These terms control the relationship between Meditrial and you. They do not create any third party beneficiary rights.";
        text+= "<br>If you do not comply with these terms, and we don’t take action right away, this doesn’t mean that we are giving up any rights that we may have (such as taking action in the future).";
        text+= "<br>If it turns out that a particular term is not enforceable, this will not affect any other terms.";
        text+= "<br>The courts in some countries will not apply California law to some types of disputes. If you reside in one of those countries, then where California law is excluded from applying, your country’s laws will apply to such disputes related to these terms. Otherwise, you agree that the laws of California, U.S.A., excluding California’s choice of law rules, will apply to any disputes arising out of or relating to these terms or the Services. Similarly, if the courts in your country will not permit you to consent to the jurisdiction and venue of the courts in Santa Clara County, California, U.S.A., then your local jurisdiction and venue will apply to such disputes related to these terms. Otherwise, all claims arising out of or relating to these terms or the services will be litigated exclusively in the federal or state courts of Santa Clara County, California, USA, and you and Google consent to personal jurisdiction in those courts.";
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
