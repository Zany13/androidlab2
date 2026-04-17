package com.example.lab2fragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_input, new InputFragment(), "INPUT_FRAG")
                    .commit();
        }
    }


    public void showResultFragment(String faculty, String course) {
        ResultFragment resultFragment = ResultFragment.newInstance(faculty, course);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_result, resultFragment, "RESULT_FRAG")
                .commit();
    }


    public void removeResultAndClearInput() {

        Fragment resultFragment = getSupportFragmentManager().findFragmentByTag("RESULT_FRAG");
        if (resultFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(resultFragment)
                    .commit();
        }


        InputFragment inputFragment = (InputFragment) getSupportFragmentManager().findFragmentByTag("INPUT_FRAG");
        if (inputFragment != null) {
            inputFragment.clearForm();
        }
    }
}