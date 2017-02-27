package com.beta.connected.connected.IntroFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beta.connected.connected.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment4 extends Fragment {

    public IntroFragment4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_intro_fragment4, container, false);
        
        return view;
    }

}
