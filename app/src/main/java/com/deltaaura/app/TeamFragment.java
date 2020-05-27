package com.deltaaura.app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class TeamFragment extends Fragment {
    private WebView webView;
    public static String weburlHome ="https://www.deltatheinnovators.com/team";

    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =inflater.inflate(R.layout.fragment_team, container, false);


      webView =view.findViewById(R.id.team);
        webView.setBackgroundColor(Color.BLACK);


        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
       webSettings.setJavaScriptEnabled(true);
        return view;
    }
}
