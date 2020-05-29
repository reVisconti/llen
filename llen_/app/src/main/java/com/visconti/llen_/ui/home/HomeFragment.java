package com.visconti.llen_.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;
import com.visconti.llen_.ui.home.fullWord.homeFullWordInformation;
import com.visconti.llen_.ui.home.onlyWord.homeOnlyWord;

public class HomeFragment extends Fragment {

    Button queryButtonReplace;
    Button FalseAnswerButton;
    Button TrueAnswerButton;

    TextView textView;
    private boolean check = true;

    Fragment fragment;
    FragmentTransaction fTrans;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        queryButtonReplace = view.findViewById(R.id.queryButtonReplace);
        TrueAnswerButton = view.findViewById(R.id.TrueAnswerButton);
        FalseAnswerButton = view.findViewById(R.id.FalseAnswerButton);

        textView = view.findViewById(R.id.textViewHome);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        if (databaseAccess.getWordCount("listId") == 0) {
            textView.setText("Добавьте слово");
        }else {
            textView.setText("Нажмите на экран для повторения слов");
        }

        TrueAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        FalseAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        queryButtonReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseAccess.getWordCount("listId") != 0){
                    textView.setVisibility(View.INVISIBLE);
                    if (!check)
                    {
                        check = true;
                        fragment = new homeFullWordInformation();
                    }
                    else
                    {
                        check = false;
                        fragment = new homeOnlyWord();
                    }
                    fTrans = getActivity().getSupportFragmentManager().beginTransaction();
                    fTrans.replace(R.id.fragment_container, fragment);
                    fTrans.commit();
                }
                else{
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }
}