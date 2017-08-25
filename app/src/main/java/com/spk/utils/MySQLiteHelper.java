package com.spk.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Andini17 on 05/09/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "spk.db";
    private static final int DATABASE_VERSION = 1;

    public static String DB_FILEPATH = "/data/data/" + "com.spk.main"
            + "/databases/spk.db";

    public static final String TABLE_JARAK = "jarak";
    public static final String TABLE_JARAK_ID = "id_jarak";
    public static final String TABLE_JARAK_SANGAT_DEKAT_1 = "jarak_sangat_dekat_1";
    public static final String TABLE_JARAK_SANGAT_DEKAT_2 = "jarak_sangat_dekat_2";
    public static final String TABLE_JARAK_DEKAT_1 = "jarak_dekat_1";
    public static final String TABLE_JARAK_DEKAT_2 = "jarak_dekat_2";
    public static final String TABLE_JARAK_SEDANG_1 = "jarak_sedang_1";
    public static final String TABLE_JARAK_SEDANG_2 = "jarak_sedang_2";
    public static final String TABLE_JARAK_JAUH_1 = "jarak_jauh_1";
    public static final String TABLE_JARAK_JAUH_2 = "jarak_jauh_2";
    public static final String TABLE_JARAK_SANGAT_JAUH_1 = "jarak_sangat_jauh_1";
    public static final String TABLE_JARAK_SANGAT_JAUH_2 = "jarak_sangat_jauh_2";

    public static final String TABLE_BIAYA = "biaya";
    public static final String TABLE_BIAYA_ID = "id_biaya";
    public static final String TABLE_BIAYA_SANGAT_MURAH_1 = "biaya_sangat_murah_1";
    public static final String TABLE_BIAYA_SANGAT_MURAH_2 = "biaya_sangat_murah_2";
    public static final String TABLE_BIAYA_MURAH_1 = "biaya_murah_1";
    public static final String TABLE_BIAYA_MURAH_2 = "biaya_murah_2";
    public static final String TABLE_BIAYA_SEDANG_1 = "biaya_sedang_1";
    public static final String TABLE_BIAYA_SEDANG_2 = "biaya_sedang_2";
    public static final String TABLE_BIAYA_MAHAL_1 = "biaya_mahal_1";
    public static final String TABLE_BIAYA_MAHAL_2 = "biaya_mahal_2";
    public static final String TABLE_BIAYA_SANGAT_MAHAL_1 = "biaya_sangat_mahal_1";
    public static final String TABLE_BIAYA_SANGAT_MAHAL_2 = "biaya_sangat_mahal_2";

    public static final String TABLE_POPULARITAS = "popularitas";
    public static final String TABLE_POPULARITAS_ID = "id_popularitas";
    public static final String TABLE_POPULARITAS_SANGAT_RENDAH_1 = "popularitas_sangat_rendah_1";
    public static final String TABLE_POPULARITAS_SANGAT_RENDAH_2 = "popularitas_sangat_rendah_2";
    public static final String TABLE_POPULARITAS_RENDAH_1 = "popularitas_rendah_1";
    public static final String TABLE_POPULARITAS_RENDAH_2 = "popularitas_rendah_2";
    public static final String TABLE_POPULARITAS_SEDANG_1 = "popularitas_sedang_1";
    public static final String TABLE_POPULARITAS_SEDANG_2 = "popularitas_sedang_2";
    public static final String TABLE_POPULARITAS_TINGGI_1 = "popularitas_tinggi_1";
    public static final String TABLE_POPULARITAS_TINGGI_2 = "popularitas_tinggi_2";
    public static final String TABLE_POPULARITAS_SANGAT_TINGGI_1 = "popularitas_sangat_tinggi_1";
    public static final String TABLE_POPULARITAS_SANGAT_TINGGI_2 = "popularitas_sangat_tinggi_2";

    private static final String CREATE_TABLE_JARAK = "create table if not EXISTS "
            + TABLE_JARAK
            + "("
            + TABLE_JARAK_ID
            + " integer primary key autoincrement,"
            + TABLE_JARAK_SANGAT_DEKAT_1
            + " integer,"
            + TABLE_JARAK_SANGAT_DEKAT_2
            + " integer,"
            + TABLE_JARAK_DEKAT_1
            + " integer,"
            + TABLE_JARAK_DEKAT_2
            + " integer,"
            + TABLE_JARAK_SEDANG_1
            + " integer,"
            + TABLE_JARAK_SEDANG_2
            + " integer,"
            + TABLE_JARAK_JAUH_1
            + " integer,"
            + TABLE_JARAK_JAUH_2
            + " integer,"
            + TABLE_JARAK_SANGAT_JAUH_1
            + " integer,"
            + TABLE_JARAK_SANGAT_JAUH_2
            + " integer);";

    private static final String CREATE_TABLE_BIAYA = "create table if not EXISTS "
            + TABLE_BIAYA
            + "("
            + TABLE_BIAYA_ID
            + " integer primary key autoincrement,"
            + TABLE_BIAYA_SANGAT_MURAH_1
            + " integer,"
            + TABLE_BIAYA_SANGAT_MURAH_2
            + " integer,"
            + TABLE_BIAYA_MURAH_1
            + " integer,"
            + TABLE_BIAYA_MURAH_2
            + " integer,"
            + TABLE_BIAYA_SEDANG_1
            + " integer,"
            + TABLE_BIAYA_SEDANG_2
            + " integer,"
            + TABLE_BIAYA_MAHAL_1
            + " integer,"
            + TABLE_BIAYA_MAHAL_2
            + " integer,"
            + TABLE_BIAYA_SANGAT_MAHAL_1
            + " integer,"
            + TABLE_BIAYA_SANGAT_MAHAL_2
            + " integer);";


    private static final String CREATE_TABLE_POPULARITAS = "create table if not EXISTS "
            + TABLE_POPULARITAS
            + "("
            + TABLE_POPULARITAS_ID
            + " integer primary key autoincrement,"
            + TABLE_POPULARITAS_SANGAT_RENDAH_1
            + " integer,"
            + TABLE_POPULARITAS_SANGAT_RENDAH_2
            + " integer,"
            + TABLE_POPULARITAS_RENDAH_1
            + " integer,"
            + TABLE_POPULARITAS_RENDAH_2
            + " integer,"
            + TABLE_POPULARITAS_SEDANG_1
            + " integer,"
            + TABLE_POPULARITAS_SEDANG_2
            + " integer,"
            + TABLE_POPULARITAS_TINGGI_1
            + " integer,"
            + TABLE_POPULARITAS_TINGGI_2
            + " integer,"
            + TABLE_POPULARITAS_SANGAT_TINGGI_1
            + " integer,"
            + TABLE_POPULARITAS_SANGAT_TINGGI_2
            + " integer);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JARAK);
        db.execSQL(CREATE_TABLE_BIAYA);
        db.execSQL(CREATE_TABLE_POPULARITAS);
        db.execSQL("INSERT INTO jarak (jarak_sangat_dekat_1, jarak_sangat_dekat_2, jarak_dekat_1, jarak_dekat_2, jarak_sedang_1,jarak_sedang_2, jarak_jauh_1, jarak_jauh_2, jarak_sangat_jauh_1, jarak_sangat_jauh_2) VALUES ('0','7','7','15','15','20','20','25','1','25');");
        db.execSQL("INSERT INTO biaya (biaya_sangat_murah_1, biaya_sangat_murah_2, biaya_murah_1, biaya_murah_2, biaya_sedang_1,biaya_sedang_2, biaya_mahal_1, biaya_mahal_2, biaya_sangat_mahal_1, biaya_sangat_mahal_2) VALUES ('0','50000','50000','100000','100000','150000','150000','250000','250000','300000');");
        db.execSQL("INSERT INTO popularitas (popularitas_sangat_rendah_1, popularitas_sangat_rendah_2, popularitas_rendah_1, popularitas_rendah_2, popularitas_sedang_1,popularitas_sedang_2, popularitas_tinggi_1, popularitas_tinggi_2, popularitas_sangat_tinggi_1, popularitas_sangat_tinggi_2) VALUES ('0','100000','100000','500000','500000','1000000','1000000','5000000','5000000','5000000');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JARAK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIAYA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POPULARITAS);
    }
}
