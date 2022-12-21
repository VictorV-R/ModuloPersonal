package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.EstReg;

import java.util.ArrayList;

public class DbEstReg extends DbHelper{

    Context context;

    public DbEstReg(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEstReg(String codigo, String nombre, String estReg){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("EstRegCod", codigo);
            values.put("EstRegNom", nombre);
            values.put("EstRegEstReg", estReg);

            id = db.insert(TABLE_ESTREG, null, values);
        } catch (Exception e){
            System.out.print("Error: " + e);
        }
        return id;
    }

    public ArrayList<EstReg> mostrarEstRegs(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<EstReg> listaEstRegs = new ArrayList<>();
        EstReg estRegs;
        Cursor cursorEstRegs;
        String codigo = "*";

        cursorEstRegs = db.rawQuery("SELECT * FROM " + TABLE_ESTREG + " WHERE EstRegEstReg NOT LIKE '" + codigo + "'", null);

        if(cursorEstRegs.moveToFirst()){
            do{
                estRegs = new EstReg();
                estRegs.setCodigo(cursorEstRegs.getString(0));
                estRegs.setNombre(cursorEstRegs.getString(1));
                estRegs.setEstado_registro(cursorEstRegs.getString(2));
                listaEstRegs.add(estRegs);
            } while (cursorEstRegs.moveToNext());
        }

        cursorEstRegs.close();
        return listaEstRegs;
    }

    public ArrayList<EstReg> mostrarEstRegActivos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<EstReg> listaEstRegs = new ArrayList<>();
        EstReg estRegs;
        Cursor cursorEstRegs;
        String codigo = "A";

        cursorEstRegs = db.rawQuery("SELECT * FROM " + TABLE_ESTREG + " WHERE EstRegCod = '" + codigo + "'", null);

        if(cursorEstRegs.moveToFirst()){
            do{
                estRegs = new EstReg();
                estRegs.setCodigo(cursorEstRegs.getString(0));
                estRegs.setNombre(cursorEstRegs.getString(1));
                estRegs.setEstado_registro(cursorEstRegs.getString(2));
                listaEstRegs.add(estRegs);
            } while (cursorEstRegs.moveToNext());
        }

        cursorEstRegs.close();
        return listaEstRegs;
    }

    public EstReg verEstReg(String codigo){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        EstReg estReg = null;
        Cursor cursorEstRegs;

        cursorEstRegs = db.rawQuery("SELECT * FROM "+ TABLE_ESTREG + " WHERE EstRegCod = '" + codigo + "' LIMIT 1", null);

        if(cursorEstRegs.moveToFirst()){
                estReg = new EstReg();
                estReg.setCodigo(cursorEstRegs.getString(0));
                estReg.setNombre(cursorEstRegs.getString(1));
                estReg.setEstado_registro(cursorEstRegs.getString(2));
        }

        cursorEstRegs.close();
        return estReg;
    }

    public boolean editarEstReg(String codigo, String nombre, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_ESTREG +" SET EstRegNom = '" + nombre + "' , EstRegEstReg = '" + estReg + "' WHERE EstRegCod = '" + codigo+ "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }
        return correcto;
    }

    public ArrayList<EstReg> filtrarEstReg(String str){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<EstReg> listaEstRegs = new ArrayList<>();
        EstReg estRegs;
        Cursor cursorEstRegs;

        cursorEstRegs = db.rawQuery("SELECT * FROM " + TABLE_ESTREG + " WHERE EstRegNom LIKE  '%" + str + "%'", null);

        if(cursorEstRegs.moveToFirst()){
            do{
                estRegs = new EstReg();
                estRegs.setCodigo(cursorEstRegs.getString(0));
                estRegs.setNombre(cursorEstRegs.getString(1));
                estRegs.setEstado_registro(cursorEstRegs.getString(2));
                listaEstRegs.add(estRegs);
            } while (cursorEstRegs.moveToNext());
        }

        cursorEstRegs.close();
        return listaEstRegs;
    }

    public boolean operationEstReg(String codigo, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_ESTREG +" SET EstRegEstReg = '" + estReg + "' WHERE EstRegCod = '" + codigo+ "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }
        return correcto;
    }

}
