package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.Personal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DbPersonal extends DbHelper{

    Context context;

    public DbPersonal (@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPersonal(String nombre, int dni, String fecNac, int codCar, int codPai, String estReg){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("PerNom", nombre);
            values.put("PerDni", dni);
            values.put("PerFecNac", fecNac);
            values.put("PerCodCar", codCar);
            values.put("PerCodPai", codPai);
            values.put("PerEstReg", estReg);
            id = db.insert(TABLE_PERSONAL, null, values);
        } catch (Exception e){
            System.out.print("Error: " + e);
        }

        return id;
    }

    public ArrayList<Personal> mostrarPersonal() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Personal> listaPersonal = new ArrayList<>();
        Personal personal;
        Cursor cursorPersonal;
        String codigo = "*";

        cursorPersonal = db.rawQuery("SELECT * FROM " + TABLE_PERSONAL + " WHERE PerEstReg NOT LIKE '" + codigo + "'", null);

        if(cursorPersonal.moveToFirst()){
            do{
                personal = new Personal();
                personal.setPerCod(cursorPersonal.getInt(0));
                personal.setPerNom(cursorPersonal.getString(1));
                personal.setPerDni(cursorPersonal.getInt(2));
                personal.setPerFecNac((cursorPersonal.getString(3)));
                personal.setPerCodCar(cursorPersonal.getInt(4));
                personal.setPerCodPai(cursorPersonal.getInt(5));
                personal.setPerEstReg(cursorPersonal.getString(6));
                listaPersonal.add(personal);
            } while (cursorPersonal.moveToNext());
        }
        cursorPersonal.close();
        return listaPersonal;
    }

    public Personal verPersonal(int codigo) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Personal personal = null;
        Cursor cursorPersonal;

        cursorPersonal = db.rawQuery("SELECT * FROM "+ TABLE_PERSONAL + " WHERE PerCod = '" + codigo + "' LIMIT 1", null);

        if(cursorPersonal.moveToFirst()){
            personal = new Personal();
            personal.setPerCod(cursorPersonal.getInt(0));
            personal.setPerNom(cursorPersonal.getString(1));
            personal.setPerDni(cursorPersonal.getInt(2));
            personal.setPerFecNac(cursorPersonal.getString(3));
            personal.setPerCodCar(cursorPersonal.getInt(4));
            personal.setPerCodPai(cursorPersonal.getInt(5));
            personal.setPerEstReg(cursorPersonal.getString(6));
        }

        cursorPersonal.close();
        return personal;
    }

    public boolean editarPersonal(int codigo, String nombre, int dni, String fecNac, int codCar, int codPai, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_PERSONAL +" SET " +
                    "PerNom = '" + nombre +
                    "' , PerDni = '" + dni +
                    "' , PerFecNac = '" + fecNac +
                    "' , PerCodCar = '" + codCar +
                    "' , PerCodPai = '" + codPai +
                    "' , PerEstReg = '" + estReg +
                    "' WHERE PerCod = '" + codigo + "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }

        dbHelper.close();
        return correcto;
    }

    public boolean editarPersonal(int codigo, String nombre, Integer dni, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_PERSONAL +" SET " +
                    "PerNom = '" + nombre +
                    "PerDni = '" + dni +
                    "' , PerEstReg = '" + estReg +
                    "' WHERE PerCod = '" + codigo + "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }

        dbHelper.close();
        return correcto;
    }

    public ArrayList<Personal> filtrarPersonal(String str) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Personal> listaPersonal = new ArrayList<>();
        Personal personal;
        Cursor cursorPersonal;

        cursorPersonal = db.rawQuery("SELECT * FROM " + TABLE_PERSONAL + " WHERE PerNom LIKE  '%" + str + "%' AND PerEstReg NOT LIKE '%*%'", null);

        if(cursorPersonal.moveToFirst()){
            do{
                personal = new Personal();
                personal.setPerCod(cursorPersonal.getInt(0));
                personal.setPerNom(cursorPersonal.getString(1));
                personal.setPerDni(cursorPersonal.getInt(2));
                personal.setPerFecNac((cursorPersonal.getString(3)));
                personal.setPerCodCar(cursorPersonal.getInt(4));
                personal.setPerCodPai(cursorPersonal.getInt(5));
                personal.setPerEstReg(cursorPersonal.getString(6));
                listaPersonal.add(personal);
            } while (cursorPersonal.moveToNext());
        }
        cursorPersonal.close();
        return listaPersonal;
    }

    public boolean operationPersonal(int codigo, String estReg){

        boolean correcto;

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.execSQL("UPDATE "+ TABLE_PERSONAL +" SET PerEstReg = '" + estReg +
                    "' WHERE PerCod = '" + codigo + "'");
            correcto = true;
        } catch (Exception e) {
            System.out.print("Error: " + e);
            correcto = false;
        }
        return correcto;
    }
}
