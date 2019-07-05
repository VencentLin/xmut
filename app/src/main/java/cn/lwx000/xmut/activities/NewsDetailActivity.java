package cn.lwx000.xmut.activities;

import android.os.Bundle;

import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import cn.lwx000.xmut.R;

public class NewsDetailActivity extends HomeAsUpBaseActivity{

    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        final TextView titleView = findViewById(R.id.title);
        String url = getIntent().getStringExtra("url");
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout,
                        new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        titleView.setText(title);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
