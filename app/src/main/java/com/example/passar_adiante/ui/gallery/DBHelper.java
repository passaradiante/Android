package com.example.passar_adiante.ui.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static int versao=1;
    private static String nome = "Login_Registro_BaseDados.db";


    public DBHelper( Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        onCreate(db);
    }

    public long CriarUtilizador(String username, String password){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", username);
        cv.put("Password",password);
        long result = db.insert("Utilizador", null,cv);
        return result;

    }

    public String ValidarLogin(String username, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username=? AND password=?", new String[]{username,password});
        if (c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }
}
