package com.deltaaura.app;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {
     private WebView webView;
    public static String weburlHome ="https://www.deltatheinnovators.com/about";
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


           View view =inflater.inflate(R.layout.fragment_home, container, false);


        webView =view.findViewById(R.id.webViewHome);
    webView.setBackgroundColor(Color.BLACK);
//         webView.setWebViewClient(new WebViewClient()
//         {
//
//
//             @SuppressWarnings("deprecation")
//             @Override
//             public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                 if (url.equals(weburlHome)) {
//                     view.loadUrl(url);
//                 }
//                 return true;
//             }
//
//             @TargetApi(Build.VERSION_CODES.N)
//             @Override
//             public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                 if (request.equals(weburlHome)) {
//                     view.loadUrl(weburlHome);
//                 }
//                 return true;
//             }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//         });
//




        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
          webSettings.setJavaScriptEnabled(true);

        return view;
    }

}
