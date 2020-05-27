package com.deltaaura.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DzoneFragment extends Fragment {

    public DzoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dzone, container, false);

        // Inflate the layout for this fragment
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.swipe_right);
        view.startAnimation(animation);


        return  view;
    }
}
