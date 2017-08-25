package com.spk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.spk.model.Jarak;

/**
 * Created by Andini17 on 05/09/2015.
 */
public class JarakDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {
            MySQLiteHelper.TABLE_JARAK_ID,
            MySQLiteHelper.TABLE_JARAK_SANGAT_DEKAT_1,
            MySQLiteHelper.TABLE_JARAK_SANGAT_DEKAT_2,
            MySQLiteHelper.TABLE_JARAK_DEKAT_1,
            MySQLiteHelper.TABLE_JARAK_DEKAT_2,
            MySQLiteHelper.TABLE_JARAK_SEDANG_1,
            MySQLiteHelper.TABLE_JARAK_SEDANG_2,
            MySQLiteHelper.TABLE_JARAK_JAUH_1,
            MySQLiteHelper.TABLE_JARAK_JAUH_2,
            MySQLiteHelper.TABLE_JARAK_SANGAT_JAUH_1,
            MySQLiteHelper.TABLE_JARAK_SANGAT_JAUH_2};

    public JarakDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public Jarak getJarak() {
        open();
        String query = "SELECT * FROM " + MySQLiteHelper.TABLE_JARAK;
        Cursor cursor = database.rawQuery(query, null);

        Jarak jarak = new Jarak();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                jarak.setId(Integer.parseInt(cursor.getString(0)));
                jarak.setJarakSangatDekat1(cursor.getString(1));
                jarak.setJarakSangatDekat2(cursor.getString(2));
                jarak.setJarakDekat1(cursor.getString(3));
                jarak.setJarakDekat2(cursor.getString(4));
                jarak.setJarakSedang1(cursor.getString(5));
                jarak.setJarakSedang2(cursor.getString(6));
                jarak.setJarakJauh1(cursor.getString(7));
                jarak.setJarakJauh2(cursor.getString(8));
                jarak.setJarakSangatJauh1(cursor.getString(9));
                jarak.setJarakSangatJauh2(cursor.getString(10));

            } else {
                jarak = null;
            }
        } else {
            jarak = null;
        }
        close();
        return jarak;
    }

    public int updateJarak(Jarak jarak) {
        open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.TABLE_JARAK_SANGAT_DEKAT_1, jarak.getJarakSangatDekat1());
        values.put(MySQLiteHelper.TABLE_JARAK_SANGAT_DEKAT_2, jarak.getJarakSangatDekat2());
        values.put(MySQLiteHelper.TABLE_JARAK_DEKAT_1, jarak.getJarakDekat1());
        values.put(MySQLiteHelper.TABLE_JARAK_DEKAT_2, jarak.getJarakDekat2());
        values.put(MySQLiteHelper.TABLE_JARAK_SEDANG_1, jarak.getJarakSedang1());
        values.put(MySQLiteHelper.TABLE_JARAK_SEDANG_2, jarak.getJarakSedang2());
        values.put(MySQLiteHelper.TABLE_JARAK_JAUH_1, jarak.getJarakJauh1());
        values.put(MySQLiteHelper.TABLE_JARAK_JAUH_2, jarak.getJarakJauh2());
        values.put(MySQLiteHelper.TABLE_JARAK_SANGAT_JAUH_1, jarak.getJarakSangatJauh1());
        values.put(MySQLiteHelper.TABLE_JARAK_SANGAT_JAUH_2, jarak.getJarakSangatJauh2());


        // 2. updating row
        int i = database.update(MySQLiteHelper.TABLE_JARAK, values,
                MySQLiteHelper.TABLE_JARAK_ID + " = ?",
                new String[]{String.valueOf(jarak.getId())});

        // 4. close
        database.close();

        close();
        return i;
    }


}
