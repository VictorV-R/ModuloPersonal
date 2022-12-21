package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.EstReg;
import com.example.myapplication.entidades.Pais;

import java.util.ArrayList;

public class DbPais extends DbHelper {
    Context context;

    public DbPais(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPais(String nombre, String estReg){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("PaiNom", nombre);
            values.put("PaiEstReg", estReg);
            id = db.insert(TABLE_PAISES, null, values);
        } catch (Exception e){
            System.out.print("Error: " + e);
        }

        return id;
    }

    public ArrayList<Pais> mostrarPaises(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Pais> listaPaises = new ArrayList<>();
        Pais pais;
        Cursor cursorPais;
        String codigo = "*";

        cursorPais = db.rawQuery("SELECT * FROM " + TABLE_PAISES + " WHERE PaiEstReg NOT LIKE '" + codigo + "'", null);

        if(cursorPais.moveToFirst()){
            do{
                pais = new Pais();
                pais.setCodigo(cursorPais.getInt(0));
                pais.setNombre(cursorPais.getString(1));
                pais.setEstReg(cursorPais.getString(2));
                listaPaises.add(pais);
            } while (cursorPais.moveToNext());
        }
        cursorPais.close();
        return listaPaises;
    }

    public Pais verPais(int codigo){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Pais pais = null;
        Cursor cursorPais;

        cursorPais = db.rawQuery("SELECT * FROM "+ TABLE_PAISES + " WHERE PaiCod = '" + codigo + "' LIMIT 1", null);

        if(cursorPais.moveToFirst()){
            pais = new Pais();
            pais.setCodigo(cursorPais.getInt(0));
            pais.setNombre(cursorPais.getString(1));
            pais.setEstReg(cursorPais.getString(2));
        }

        cursorPais.close();
        return pais;
    }

    public boolean editarPais(int codigo, String nombre, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_PAISES +" SET PaiNom = '" + nombre + "' , PaiEstReg = '" + estReg + "' WHERE PaiCod = '" + codigo+ "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }
        return correcto;
    }

    public ArrayList<Pais> filtrarPais(String str){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Pais> listaPaises = new ArrayList<>();
        Pais pais;
        Cursor cursorPais;

        cursorPais = db.rawQuery("SELECT * FROM " + TABLE_PAISES + " WHERE PaiNom LIKE  '%" + str + "%'", null);

        if(cursorPais.moveToFirst()){
            do{
                pais = new Pais();
                pais.setCodigo(cursorPais.getInt(0));
                pais.setNombre(cursorPais.getString(1));
                pais.setEstReg(cursorPais.getString(2));
                listaPaises.add(pais);
            } while (cursorPais.moveToNext());
        }
        cursorPais.close();
        return listaPaises;
    }

    public boolean operationPais(int codigo, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_PAISES +" SET PaiEstReg = '" + estReg + "' WHERE PaiCod = '" + codigo+ "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }
        return correcto;
    }

}
