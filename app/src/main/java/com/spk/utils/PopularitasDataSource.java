package com.spk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.spk.model.Popularitas;

/**
 * Created by Andini17 on 05/09/2015.
 */
public class PopularitasDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {MySQLiteHelper.TABLE_POPULARITAS_SANGAT_RENDAH_1,
            MySQLiteHelper.TABLE_POPULARITAS_SANGAT_RENDAH_2,
            MySQLiteHelper.TABLE_POPULARITAS_RENDAH_1,
            MySQLiteHelper.TABLE_POPULARITAS_RENDAH_2,
            MySQLiteHelper.TABLE_POPULARITAS_SEDANG_1,
            MySQLiteHelper.TABLE_POPULARITAS_SEDANG_2,
            MySQLiteHelper.TABLE_POPULARITAS_TINGGI_1,
            MySQLiteHelper.TABLE_POPULARITAS_TINGGI_2,
            MySQLiteHelper.TABLE_POPULARITAS_SANGAT_TINGGI_1,
            MySQLiteHelper.TABLE_POPULARITAS_SANGAT_TINGGI_2};

    public PopularitasDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public Popularitas getPop() {
        open();
        String query = "SELECT * FROM " + MySQLiteHelper.TABLE_POPULARITAS;
        Cursor cursor = database.rawQuery(query, null);

        Popularitas pop = new Popularitas();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                pop.setId(Integer.parseInt(cursor.getString(0)));
                pop.setPopSangatRendah1(cursor.getString(1));
                pop.setPopSangatRendah2(cursor.getString(2));
                pop.setPopRendah1(cursor.getString(3));
                pop.setPopRendah2(cursor.getString(4));
                pop.setPopSedang1(cursor.getString(5));
                pop.setPopSedang2(cursor.getString(6));
                pop.setPopTinggi1(cursor.getString(7));
                pop.setPopTinggi2(cursor.getString(8));
                pop.setPopSangatTinggi1(cursor.getString(9));
                pop.setPopSangatTinggi2(cursor.getString(10));

            } else {
                pop = null;
            }
        } else {
            pop = null;
        }
        close();
        return pop;
    }

    public int updatePop(Popularitas pop) {
        open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SANGAT_RENDAH_1, pop.getPopSangatRendah1());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SANGAT_RENDAH_2, pop.getPopSangatRendah2());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_RENDAH_1, pop.getPopRendah1());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_RENDAH_2, pop.getPopRendah2());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SEDANG_1, pop.getPopSedang1());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SEDANG_2, pop.getPopSedang2());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_TINGGI_1, pop.getPopTinggi1());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_TINGGI_2, pop.getPopTinggi2());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SANGAT_TINGGI_1, pop.getPopSangatTinggi1());
        values.put(MySQLiteHelper.TABLE_POPULARITAS_SANGAT_TINGGI_2, pop.getPopSangatTinggi2());
        
        int i = database.update(MySQLiteHelper.TABLE_POPULARITAS, values,
                MySQLiteHelper.TABLE_POPULARITAS_ID + " = ?",
                new String[]{String.valueOf(pop.getId())});

        database.close();

        return i;
    }


}
