package com.example.lab2fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private RadioGroup rgFaculty, rgCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        rgFaculty = view.findViewById(R.id.rgFaculty);
        rgCourse = view.findViewById(R.id.rgCourse);
        Button btnOk = view.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(v -> {
            int selectedFacultyId = rgFaculty.getCheckedRadioButtonId();
            int selectedCourseId = rgCourse.getCheckedRadioButtonId();

            if (selectedFacultyId == -1 || selectedCourseId == -1) {
                Toast.makeText(getContext(), "Увага! Завершіть введення всіх даних.", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rbFaculty = view.findViewById(selectedFacultyId);
                RadioButton rbCourse = view.findViewById(selectedCourseId);


                ((MainActivity) getActivity()).showResultFragment(rbFaculty.getText().toString(), rbCourse.getText().toString());
            }
        });

        return view;
    }


    public void clearForm() {
        if (rgFaculty != null) rgFaculty.clearCheck();
        if (rgCourse != null) rgCourse.clearCheck();
    }
}