package com.visconti.llen_.ui.home.onlyWord;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;

public class homeOnlyWord extends Fragment {
    public TextView resultWord;
    public String word;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_only_word, container, false);

        resultWord = view.findViewById(R.id.resultWord);

        if (savedInstanceState != null) {
            resultWord.setText(savedInstanceState.getString("word"));

        }
        else {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
            databaseAccess.open();
            databaseAccess.setRandId("userWord");

            word = databaseAccess.getWord(databaseAccess.getId(databaseAccess.getRandId()));
            resultWord.setText(word);
            databaseAccess.close();

        }
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("word", resultWord.getText().toString());
    }
}