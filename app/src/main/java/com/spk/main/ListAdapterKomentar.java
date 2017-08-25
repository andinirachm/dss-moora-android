package com.spk.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spk.model.Komentar;

import java.util.List;

public class ListAdapterKomentar extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    List<Komentar> listKomentar;

    TextView tvNamaUser;
    TextView tvTanggal;
    TextView tvIsi;


    public ListAdapterKomentar(Activity activity,
                               List<Komentar> listKomentar) {
        this.activity = activity;
        this.listKomentar = listKomentar;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listKomentar.size();
    }

    @Override
    public Object getItem(int location) {
        // TODO Auto-generated method stub
        return listKomentar.get(location);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_komentar, null);

        tvNamaUser = (TextView) convertView
                .findViewById(R.id.namaUser);
        tvTanggal = (TextView) convertView
                .findViewById(R.id.tglKomentar);
        tvIsi = (TextView) convertView
                .findViewById(R.id.isiKomentar);


        final Komentar komentar = listKomentar.get(position);
        tvNamaUser.setText(komentar.getNamaUser());
        tvTanggal.setText(komentar.getTanggal());
        tvIsi.setText(komentar.getIsi());


        return convertView;
    }

}
