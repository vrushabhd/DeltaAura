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
public class WorkFragment extends Fragment {
    private WebView webView;
    public static String weburlHome ="https://www.deltatheinnovators.com/work";

    public WorkFragment() {
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



        View view =inflater.inflate(R.layout.fragment_work, container, false);


        webView =view.findViewById(R.id.work);
        webView.setBackgroundColor(Color.BLACK);


        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return view;
    }
}
