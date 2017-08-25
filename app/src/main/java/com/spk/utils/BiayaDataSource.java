package com.spk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.spk.model.Biaya;

/**
 * Created by Andini17 on 05/09/2015.
 */
public class BiayaDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {MySQLiteHelper.TABLE_BIAYA_SANGAT_MURAH_1,
            MySQLiteHelper.TABLE_BIAYA_SANGAT_MURAH_2,
            MySQLiteHelper.TABLE_BIAYA_MURAH_1,
            MySQLiteHelper.TABLE_BIAYA_MURAH_2,
            MySQLiteHelper.TABLE_BIAYA_SEDANG_1,
            MySQLiteHelper.TABLE_BIAYA_SEDANG_2,
            MySQLiteHelper.TABLE_BIAYA_MAHAL_1,
            MySQLiteHelper.TABLE_BIAYA_MAHAL_2,
            MySQLiteHelper.TABLE_BIAYA_SANGAT_MAHAL_1,
            MySQLiteHelper.TABLE_BIAYA_SANGAT_MAHAL_2};

    public BiayaDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public Biaya getBiaya() {
        open();
        String query = "SELECT * FROM " + MySQLiteHelper.TABLE_BIAYA;
        Cursor cursor = database.rawQuery(query, null);

        Biaya biaya = new Biaya();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                biaya.setId(Integer.parseInt(cursor.getString(0)));
                biaya.setBiayaSangatMurah1(cursor.getString(1));
                biaya.setBiayaSangatMurah2(cursor.getString(2));
                biaya.setBiayaMurah1(cursor.getString(3));
                biaya.setBiayaMurah2(cursor.getString(4));
                biaya.setBiayaSedang1(cursor.getString(5));
                biaya.setBiayaSedang2(cursor.getString(6));
                biaya.setBiayaMahal1(cursor.getString(7));
                biaya.setBiayaMahal2(cursor.getString(8));
                biaya.setBiayaSangatMahal1(cursor.getString(9));
                biaya.setBiayaSangatMahal2(cursor.getString(10));

            } else {
                biaya = null;
            }
        } else {
            biaya = null;
        }
        close();
        return biaya;
    }

    public int updateBiaya(Biaya biaya) {
        open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.TABLE_BIAYA_SANGAT_MURAH_1, biaya.getBiayaSangatMurah1());
        values.put(MySQLiteHelper.TABLE_BIAYA_SANGAT_MURAH_2, biaya.getBiayaSangatMurah2());
        values.put(MySQLiteHelper.TABLE_BIAYA_MURAH_1, biaya.getBiayaMurah1());
        values.put(MySQLiteHelper.TABLE_BIAYA_MURAH_2, biaya.getBiayaMurah2());
        values.put(MySQLiteHelper.TABLE_BIAYA_SEDANG_1, biaya.getBiayaSedang1());
        values.put(MySQLiteHelper.TABLE_BIAYA_SEDANG_2, biaya.getBiayaSedang2());
        values.put(MySQLiteHelper.TABLE_BIAYA_MAHAL_1, biaya.getBiayaMahal1());
        values.put(MySQLiteHelper.TABLE_BIAYA_MAHAL_2, biaya.getBiayaMahal2());
        values.put(MySQLiteHelper.TABLE_BIAYA_SANGAT_MAHAL_1, biaya.getBiayaSangatMahal1());
        values.put(MySQLiteHelper.TABLE_BIAYA_SANGAT_MAHAL_2, biaya.getBiayaSangatMahal2());

        int i = database.update(MySQLiteHelper.TABLE_BIAYA, values,
                MySQLiteHelper.TABLE_BIAYA_ID + " = ?",
                new String[]{String.valueOf(biaya.getId())});

        database.close();

        return i;
    }


}
