package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NOMBRE = "modulo_personal.db";
    protected static final String TABLE_PERSONAL = "PERSONAL";
    protected static final String TABLE_PAISES = "PAISES";
    protected static final String TABLE_CARGO = "CARGO";
    protected static final String TABLE_ESTREG = "ESTADO_REGISTRO";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure (SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"ESTADO_REGISTRO\"\n" +
                "(\n" +
                "  \"EstRegCod\" \"char\" NOT NULL,\n" +
                "  \"EstRegNom\" TEXT,\n" +
                "  \"EstRegEstReg\" TEXT,\n" +
                "  CONSTRAINT \"PK_ESTADO_REGISTRO\" PRIMARY KEY (\"EstRegCod\")\n" +
                ")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE TABLE \"PAISES\"\n" +
                "(\n" +
                "  \"PaiCod\" INTEGER NOT NULL\n" +
                "        CONSTRAINT \"PK_PAISES\" PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"PaiNom\" TEXT,\n" +
                "  \"PaiEstReg\" \"char\",\n" +
                "  CONSTRAINT \"Relationship12\"\n" +
                "    FOREIGN KEY (\"PaiEstReg\")\n" +
                "    REFERENCES \"ESTADO_REGISTRO\" (\"EstRegCod\")\n" +
                ")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE INDEX \"IX_Relationship12\"\n" +
                "  ON \"PAISES\" (\"PaiEstReg\")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE TABLE \"CARGO\"\n" +
                "(\n" +
                "  \"CarCod\" INTEGER NOT NULL\n" +
                "        CONSTRAINT \"PK_CARGO\" PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"CarNom\" TEXT,\n" +
                "  \"CarEstReg\" \"char\",\n" +
                "  CONSTRAINT \"Relationship11\"\n" +
                "    FOREIGN KEY (\"CarEstReg\")\n" +
                "    REFERENCES \"ESTADO_REGISTRO\" (\"EstRegCod\")\n" +
                ")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE INDEX \"IX_Relationship11\"\n" +
                "  ON \"CARGO\" (\"CarEstReg\")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE TABLE \"PERSONAL\"\n" +
                "(\n" +
                "  \"PerCod\" INTEGER NOT NULL\n" +
                "        CONSTRAINT \"PK_PERSONAL\" PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"PerNom\" TEXT,\n" +
                "  \"PerDni\" INTEGER,\n" +
                "  \"PerFecNac\" TEXT,\n" +
                "  \"PerCodCar\" INTEGER,\n" +
                "  \"PerCodPai\" INTEGER,\n" +
                "  \"PerEstReg\" \"char\",\n" +
                "  CONSTRAINT \"Relationship8\"\n" +
                "    FOREIGN KEY (\"PerCodCar\")\n" +
                "    REFERENCES \"CARGO\" (\"CarCod\"),\n" +
                "  CONSTRAINT \"Relationship9\"\n" +
                "    FOREIGN KEY (\"PerCodPai\")\n" +
                "    REFERENCES \"PAISES\" (\"PaiCod\"),\n" +
                "  CONSTRAINT \"Relationship10\"\n" +
                "    FOREIGN KEY (\"PerEstReg\")\n" +
                "    REFERENCES \"ESTADO_REGISTRO\" (\"EstRegCod\")\n" +
                ")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE INDEX \"IX_Relationship8\"\n" +
                "  ON \"PERSONAL\" (\"PerCodCar\")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE INDEX \"IX_Relationship9\"\n" +
                "  ON \"PERSONAL\" (\"PerCodPai\")\n" +
                ";");
        sqLiteDatabase.execSQL("CREATE INDEX \"IX_Relationship10\"\n" +
                "  ON \"PERSONAL\" (\"PerEstReg\")\n" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PERSONAL);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CARGO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PAISES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ESTREG);
        onCreate(sqLiteDatabase);
    }
}
