package com.visconti.llen_;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addWordPanel extends AppCompatActivity {

    Toast toast;
    public Button addWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word_panel);

        addWord = findViewById(R.id.addNewWord);
        addWord.setOnClickListener(new View.OnClickListener() {
            EditText editText = findViewById(R.id.addWordET);
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                String word = editText.getText().toString();
                int id = Integer.parseInt(databaseAccess.getWordId(word));
                if (id != -1){
                    databaseAccess.setWordId(id);
                    toast = Toast.makeText(getApplicationContext(), "Слово успешно добавленно", Toast.LENGTH_SHORT);
                }else  {
                    toast = Toast.makeText(getApplicationContext(), "Слово отсутствует, либо написано неверно", Toast.LENGTH_SHORT);
                }
                databaseAccess.close();
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                editText.getText().clear();
            }
        });
    }
}
