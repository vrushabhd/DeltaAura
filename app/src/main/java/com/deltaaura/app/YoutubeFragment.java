package com.deltaaura.app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class YoutubeFragment extends Fragment {
    private WebView webView;
    public static String weburlHome ="https://www.youtube.com/channel/UC8A4lxohSvvZzR1_9Vcd66w";
    public YoutubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_youtube, container, false);

        webView =view.findViewById(R.id.youtube);
        webView.setBackgroundColor(Color.BLACK);
    //    webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return view;




    }
}
