package com.deltaaura.app;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {
    private WebView webView;
    public static String weburlHome ="https://www.deltatheinnovators.com/forum";


    public ForumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_forum, container, false);

        webView =view.findViewById(R.id.forum);
        webView.setBackgroundColor(Color.BLACK);

        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return view;
    }

}
