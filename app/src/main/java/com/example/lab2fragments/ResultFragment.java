package com.example.lab2fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private String resultText;


    public static ResultFragment newInstance(String faculty, String course) {
        ResultFragment fragment = new ResultFragment();
        fragment.resultText = "Студент зарахований:\nФакультет: " + faculty + "\nКурс: " + course;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView tvResult = view.findViewById(R.id.tvResult);
        tvResult.setText(resultText);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {

            ((MainActivity) getActivity()).removeResultAndClearInput();
        });

        return view;
    }
}