package net.crow31415.cubic_ox_game.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.crow31415.cubic_ox_game.R;

public class RuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_web_view);

        WebView webView = findViewById( R.id.rule_web_view);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        webView.loadUrl("https://crow31415.net/nitnc/3i/al/logical_cubic/rule/");
    }
}
