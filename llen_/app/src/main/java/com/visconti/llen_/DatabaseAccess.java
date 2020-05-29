package com.visconti.llen_;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    private int randId;
    private boolean check;
    Cursor c = null;
    ArrayList<Integer> listId = new ArrayList<Integer>();

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null)
            instance = new DatabaseAccess(context);
        return instance;
    }

    void addListId(int id){
        this.listId.add(id);
    }
    public void setWordId(int id){
        this.listId.add(id);
    }
    public void deleteListId(int id){
        this.listId.remove(id);
    }
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public void close(){ if(db!= null)this.db.close(); }
    public int getRandId(){
        return this.randId;
    }
    public int getId(int id){
        return this.listId.get(id);
    }

    public String getWord(int id){
        c = db.rawQuery("Select word from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String wordDb = c.getString(0);
            buffer.append("" + wordDb);
        }
        return buffer.toString();
    }

    public String getPronunciation (int id){
        c = db.rawQuery("Select pronunciation from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String pronunciationDb = c.getString(0);
            buffer.append("" + "[" + pronunciationDb + "]");
        }
        return buffer.toString();
    }
    public String getTranslate(int id){
        c = db.rawQuery("Select translate from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String translateDb = c.getString(0);
            buffer.append("" + translateDb);
        }
        return buffer.toString();
    }
    public String getDefinition(int id){
        c = db.rawQuery("Select definition from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String translateDb = c.getString(0);
            buffer.append("" + translateDb);
        }
        return buffer.toString();
    }
    public String getExample1(int id){
        c = db.rawQuery("Select example1 from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String translateDb = c.getString(0);
            buffer.append("" + translateDb);
        }
        return buffer.toString();
    }
    public String getExample2(int id){
        c = db.rawQuery("Select example2 from wordsTop where _id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String translateDb = c.getString(0);
            buffer.append("" + translateDb);
        }
        return buffer.toString();
    }


    public int getWordCount(String tableName) {
        int count;
        if (tableName == "listId")
            count = this.listId.size();
        else {
            count = (int) (DatabaseUtils.queryNumEntries(db, "wordsTop"));
        }
            return count;
    }

    public void setRandId(String tableName){
        int randId;
        if (tableName.equals("userWord")){
            randId = (int)(Math.random()*getWordCount("listId"));
        }
        else {
            randId = (int)(Math.random()*getWordCount("wordsTop") + 1);
        }
        this.randId = randId;
    }


    public String getWordId(String word){
        c = db.rawQuery("Select _id from wordsTop where word = '" + word + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        String ID = "";
        while (c.moveToNext()){
            ID = c.getString(0);
            buffer.append("" + ID);
        }
        if (ID.equals(""))
            return "-1";
        else
            return buffer.toString();
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public boolean getCheck(){
        return this.check;
    }
}
