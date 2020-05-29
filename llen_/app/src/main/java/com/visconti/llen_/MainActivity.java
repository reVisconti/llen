package com.visconti.llen_;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.TypedValue;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        if(databaseAccess.getWordCount("listId") == 0){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("userWord.txt")));
                String line ="";
                while ((line = bufferedReader.readLine()) != null)
                    databaseAccess.addListId(Integer.parseInt(line));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        final Intent intent = new Intent(this, addWordPanel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton addWord = findViewById(R.id.addWordButton);
        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                if (databaseAccess.getCheck()) {
                    setTheme(R.style.LightTheme);
                } else {
                    setTheme(R.style.AppTheme);
                }
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = getTheme();

                theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true);
                getWindow().getDecorView().setBackgroundColor(typedValue.data);

                theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(typedValue.data));

                theme.resolveAttribute(android.R.attr.colorPrimaryDark, typedValue, true);
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(typedValue.data);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_word_list,
                R.id.nav_tools, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onStop(){
        super.onStop();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput("userWord.txt", MODE_PRIVATE)));
            bufferedWriter.write("");
            for (int i = 0; i < databaseAccess.getWordCount("listId"); i++){
                String line = Integer.toString(databaseAccess.getId(i));
                bufferedWriter.write(line + "\n");
                //Log.d("myLogs", line);
            }
            bufferedWriter.close();

        }catch (FileNotFoundException ex){
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
