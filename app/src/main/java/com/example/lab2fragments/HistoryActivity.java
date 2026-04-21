package com.example.lab2fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class HistoryActivity extends AppCompatActivity {

    private TextView tvHistoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvHistoryData = findViewById(R.id.tvHistoryData);
        Button btnClearData = findViewById(R.id.btnClearData);

        loadDataFromFile();

        btnClearData.setOnClickListener(v -> {
            clearStorage();
        });

        Button btnBack = findViewById(R.id.btnBack);


        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void loadDataFromFile() {
        try {
            FileInputStream fis = openFileInput("students_data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            if (sb.toString().trim().isEmpty()) {
                tvHistoryData.setText("Сховище пусте");
            } else {
                tvHistoryData.setText(sb.toString());
            }

        } catch (Exception e) {

            tvHistoryData.setText("Сховище пусте");
        }
    }

    private void clearStorage() {
        try {

            FileOutputStream fos = openFileOutput("students_data.txt", MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();

            tvHistoryData.setText("Сховище пусте");
            Toast.makeText(this, "Дані видалено!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Помилка очищення", Toast.LENGTH_SHORT).show();
        }
    }
}