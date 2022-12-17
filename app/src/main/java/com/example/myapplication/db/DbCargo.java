package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.Cargo;

import java.util.ArrayList;

public class DbCargo extends DbHelper{

    Context context;

    public DbCargo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCargo(String nombre, String estReg){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("CarNom", nombre);
            values.put("CarEstReg", estReg);

            id = db.insert(TABLE_CARGO, null, values);
        } catch (Exception e){
            Log.e("Error", String.valueOf(e));
        }
        return id;
    }

    public ArrayList<Cargo> mostrarCargos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cargo> listCargos = new ArrayList<>();
        Cargo cargo;
        Cursor cursorCargo;

        cursorCargo = db.rawQuery("SELECT * FROM " + TABLE_CARGO, null);

        if(cursorCargo.moveToFirst()){
            do{
                cargo = new Cargo();
                cargo.setCodigo(cursorCargo.getInt(0));
                cargo.setNombre(cursorCargo.getString(1));
                cargo.setEstReg(cursorCargo.getString(2));
                listCargos.add(cargo);
            } while (cursorCargo.moveToNext());
        }

        cursorCargo.close();
        return listCargos;
    }

    public Cargo verCargo(int codigo){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cargo cargo = null;
        Cursor cursorCargo;

        cursorCargo = db.rawQuery("SELECT * FROM "+ TABLE_CARGO + " WHERE CarCod = '" + codigo + "' LIMIT 1", null);

        if(cursorCargo.moveToFirst()){
            cargo = new Cargo();
            cargo.setCodigo(cursorCargo.getInt(0));
            cargo.setNombre(cursorCargo.getString(1));
            cargo.setEstReg(cursorCargo.getString(2));
        }
        cursorCargo.close();
        return cargo;
    }

    public boolean editarCargo(int codigo, String nombre, String estReg){
        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_CARGO +" SET CarNom = '" + nombre + "' , CarEstReg = '" + estReg + "' WHERE CarCod = '" + codigo+ "'");
            correcto = true;
        } catch (Exception e) {
            Log.e("Error:", String.valueOf(e));
            correcto = false;
        }
        return correcto;
    }
}
