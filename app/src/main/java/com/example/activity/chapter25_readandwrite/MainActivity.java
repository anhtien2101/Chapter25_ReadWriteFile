package com.example.activity.chapter25_readandwrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public static final String NOTE = "note.txt";
    EditText edtEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEditor = (EditText) findViewById(R.id.edtEditor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            // open file to read
            InputStream is = openFileInput(NOTE);
            if (is != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String str;
                // read each line while it not null
                while ((str = reader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                // close inputstream
                is.close();
                // update edittext after read file
                edtEditor.setText(stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            // open file to write to it
            OutputStream out = openFileOutput(NOTE, 0);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
            // write to file
            outputStreamWriter.write(edtEditor.getText().toString());
            // close if complete
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
