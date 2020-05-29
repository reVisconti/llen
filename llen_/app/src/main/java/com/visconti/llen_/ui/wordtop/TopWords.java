package com.visconti.llen_.ui.wordtop;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;

import java.util.ArrayList;

public class TopWords extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_words, container, false);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();

        ArrayList<String> wordListTop = new ArrayList<>();
        for (int i = 1; i < databaseAccess.getWordCount("wordsTop"); i++){
            wordListTop.add(databaseAccess.getWord(i));
        }

        ListView listWord = (ListView)view.findViewById(R.id.listWordTop);

        listWord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                databaseAccess.setWordId(Integer.parseInt(databaseAccess.getWordId(databaseAccess.getWord(position))));
                return true;
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, wordListTop);

        listWord.setAdapter(adapter);

        databaseAccess.close();
        return view;
    }
}
