package com.visconti.llen_.ui.wordlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;

import java.util.ArrayList;

public class wordList extends Fragment {
    ArrayList<String> wordList;
    DatabaseAccess databaseAccess;
    ListView listWord;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        wordList = new ArrayList<>();

        listWord = view.findViewById(R.id.listWord);
        textView = view.findViewById(R.id.textListWord);

        setWordList();
        listWord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                databaseAccess.deleteListId(position);
                setWordList();
                return true;
            }
        });
        databaseAccess.close();
        return view;
    }
    private void setWordList(){
        databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();
        wordList.clear();
        for (int i = 0; i < databaseAccess.getWordCount("listId"); i++)
            wordList.add(databaseAccess.getWord(databaseAccess.getId(i)));
        if (databaseAccess.getWordCount("listId") == 0)
            textView.setText("Добавьте слова");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, wordList);
        this.listWord.setAdapter(adapter);
    }
}
