package com.example.lab2fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputFragment extends Fragment {

    private RadioGroup rgFaculty, rgCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        rgFaculty = view.findViewById(R.id.rgFaculty);
        rgCourse = view.findViewById(R.id.rgCourse);
        Button btnOk = view.findViewById(R.id.btnOk);
        Button btnOpen = view.findViewById(R.id.btnOpen);

        btnOk.setOnClickListener(v -> {
            int selectedFacultyId = rgFaculty.getCheckedRadioButtonId();
            int selectedCourseId = rgCourse.getCheckedRadioButtonId();

            if (selectedFacultyId == -1 || selectedCourseId == -1) {
                Toast.makeText(getContext(), "Увага! Завершіть введення всіх даних.", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rbFaculty = view.findViewById(selectedFacultyId);
                RadioButton rbCourse = view.findViewById(selectedCourseId);

                String faculty = rbFaculty.getText().toString();
                String course = rbCourse.getText().toString();


                ((MainActivity) getActivity()).showResultFragment(faculty, course);

                String dataToSave = "Факультет: " + faculty + ", Курс: " + course + "\n";
                saveDataToFile(dataToSave);
            }
        });

        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HistoryActivity.class);
            startActivity(intent);
        });
        return view;
    }

    private void saveDataToFile(String data) {
        try {

            FileOutputStream fos = requireActivity().openFileOutput("students_data.txt", Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getContext(), "Успіх! Дані збережено у файл.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Помилка збереження даних!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearForm() {
        if (rgFaculty != null) rgFaculty.clearCheck();
        if (rgCourse != null) rgCourse.clearCheck();
    }
}