package com.visconti.llen_.ui.home.fullWord;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;
import com.visconti.llen_.ui.home.HomeFragment;

public class homeFullWordInformation extends Fragment {

    public TextView resultWord;
    public TextView resultTranslate;
    public TextView resultPronunciation;
    public TextView resultDefiniion;
    public TextView resultExample1;
    public TextView resultExample2;

    public String word;
    public String translate;
    public String pronunciation;
    public String definition;
    public String example1;
    public String example2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_full_word_information, container, false);

        resultWord = view.findViewById(R.id.resultWord);
        resultTranslate = view.findViewById(R.id.resultTranslate);
        resultPronunciation = view.findViewById(R.id.resultPronunciation);
        resultDefiniion = view.findViewById(R.id.resultDefinition);
        resultExample1 = view.findViewById(R.id.resultExample1);
        resultExample2 = view.findViewById(R.id.resultExample2);

        if (savedInstanceState != null){
            resultWord.setText(savedInstanceState.getString("word"));
            resultTranslate.setText(savedInstanceState.getString("translate"));
            resultPronunciation.setText(savedInstanceState.getString("pronunciation"));
        }


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();

        word = databaseAccess.getWord(databaseAccess.getId(databaseAccess.getRandId()));
        translate = databaseAccess.getTranslate(databaseAccess.getId(databaseAccess.getRandId()));
        pronunciation = databaseAccess.getPronunciation(databaseAccess.getId(databaseAccess.getRandId()));
        definition = databaseAccess.getDefinition(databaseAccess.getId(databaseAccess.getRandId()));
        example1 = databaseAccess.getExample1(databaseAccess.getId(databaseAccess.getRandId()));
        example2 = databaseAccess.getExample2(databaseAccess.getId(databaseAccess.getRandId()));

        resultWord.setText(word);
        resultTranslate.setText(translate);
        resultPronunciation.setText(pronunciation);
        resultDefiniion.setText(definition);
        resultExample1.setText(example1);
        resultExample2.setText(example2);

        databaseAccess.close();

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("word", resultWord.getText().toString());
        savedInstanceState.putString("translate", resultTranslate.getText().toString());
        savedInstanceState.putString("pronunciation", resultPronunciation.getText().toString());
        savedInstanceState.putString("definition", resultDefiniion.getText().toString());
        savedInstanceState.putString("example1", resultExample1.getText().toString());
        savedInstanceState.putString("example2", resultExample2.getText().toString());
    }
}