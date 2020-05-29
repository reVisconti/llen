package com.visconti.llen_.ui.tools;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.fragment.app.Fragment;

import com.visconti.llen_.DatabaseAccess;
import com.visconti.llen_.R;

public class ToolsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedzInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tools, container, false);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());

        Switch themeSwitch = view.findViewById(R.id.ThemeSwitch);
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                databaseAccess.setCheck(isChecked);
            }
        });

        return view;
    }
}