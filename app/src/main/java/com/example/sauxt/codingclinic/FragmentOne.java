package com.example.sauxt.codingclinic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOne extends Fragment {
    View view;
    private Object myData;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        String birthday = getArguments().getString("birth_string");
        TextView birthText = (TextView)view.findViewById(R.id.birth_text);

        birthText.setText(birthday);

        return view;
    }

}
