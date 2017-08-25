package com.spk.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.spk.model.DestinasiWisata;
import com.spk.utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdapterDestinasiAdmin extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    List<DestinasiWisata> listDestinasi;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TextView destinasi_nama;
    TextView destinasi_alamat;
    NetworkImageView destinasi_gambar;
    Button btnEdit;
    Button btnHapus;
    ProgressDialog pDialog;
    String id;

    public ListAdapterDestinasiAdmin(Activity activity,
                                     List<DestinasiWisata> listDestinasi) {
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
            convertView = inflater.inflate(R.layout.list_destinasi_admin, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        destinasi_gambar = (NetworkImageView) convertView
                .findViewById(R.id.gambarDestinasi);
        destinasi_nama = (TextView) convertView
                .findViewById(R.id.namaDestinasi);
        destinasi_alamat = (TextView) convertView
                .findViewById(R.id.alamatDestinasi);
        final DestinasiWisata model_destinasi = listDestinasi.get(position);

        id = String.valueOf(model_destinasi.getId());
        btnHapus = (Button) convertView.findViewById(R.id.btnHapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deteleData();
            }
        });

        btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,
                        EditDestinasi.class);

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
        });


        destinasi_nama.setText(model_destinasi.getnama());
        destinasi_alamat.setText(model_destinasi.getalamat());
        destinasi_gambar
                .setImageUrl(model_destinasi.geturl_foto(), imageLoader);

        convertView.setOnClickListener(new View.OnClickListener() {
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
        });
        return convertView;
    }

    public void deteleData() {
        String url = "http://spksaw.hol.es/spk-saw/delete_destinasi_wisata.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        try {
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                Toast.makeText(activity,
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_destinasi_wisata", id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(req);
    }

}
