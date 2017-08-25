package com.spk.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.spk.model.HasilAkhir;
import com.spk.utils.AppController;

import java.util.List;

public class ListAdapterDestinasiHasil extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    List<HasilAkhir> listDestinasi;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TextView destinasi_nama;
    TextView destinasi_alamat;
    NetworkImageView destinasi_gambar;
    Button btnRecommended;

    public ListAdapterDestinasiHasil(Activity activity,
                                     List<HasilAkhir> listDestinasi) {
        this.activity = activity;
        this.listDestinasi = listDestinasi;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listDestinasi.size();
    }

    @Override
    public Object getItem(int location) {
        // TODO Auto-generated method stub
        return listDestinasi.get(location);
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
            convertView = inflater.inflate(R.layout.list_destinasi_hasil, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        destinasi_gambar = (NetworkImageView) convertView
                .findViewById(R.id.gambarDestinasi);
        destinasi_nama = (TextView) convertView
                .findViewById(R.id.namaDestinasi);

        btnRecommended = (Button) convertView.findViewById(R.id.btnRecommended);

        final HasilAkhir model_destinasi = listDestinasi.get(position);
        destinasi_nama.setText(model_destinasi.getNamaDestinasi());
        if (position == 0) {
            //destinasi_alamat.setText("Terkomendasi");
            btnRecommended.setBackgroundResource(R.color.green);
            btnRecommended.setText("Terekomendasi");

        } else {
            //destinasi_alamat.setText("Tidak Terekomendasi");
            btnRecommended.setBackgroundResource(android.R.color.holo_red_dark);
            btnRecommended.setText("Tidak Terekomendasi");
        }
        destinasi_gambar
                .setImageUrl(model_destinasi.getUrl(), imageLoader);

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,
                        DetailDestinasiWisata.class);

                intent.putExtra("id", String.valueOf(model_destinasi.getId()));
                intent.putExtra("nama", model_destinasi.getnama());
                intent.putExtra("alamat", model_destinasi.getalamat());
                intent.putExtra("gambar", model_destinasi.geturl_foto());
                intent.putExtra("deskripsi", model_destinasi.getdeskripsi());
                intent.putExtra("biaya", model_destinasi.getBiaya());
                intent.putExtra("jarak", model_destinasi.getJarak());
                intent.putExtra("lat", model_destinasi.getLat());
                intent.putExtra("longi", model_destinasi.getLongi());
                intent.putExtra("url_web", model_destinasi.getUrlWeb());
                intent.putExtra("alamat_lengkap", model_destinasi.getAlamatLengkap());
                intent.putExtra("jml_wisatawan", model_destinasi.getJmlWisatawan());

                activity.startActivity(intent);

            }
        });*/
        return convertView;
    }

}
