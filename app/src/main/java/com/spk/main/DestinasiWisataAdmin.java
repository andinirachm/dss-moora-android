package com.spk.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.clans.fab.FloatingActionButton;
import com.spk.model.DestinasiWisata;
import com.spk.utils.AppController;
import com.spk.utils.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinasiWisataAdmin extends Fragment {
    FloatingActionButton btnTambahAlt;
    ListView listViewDestinasi;

    com.spk.model.DestinasiWisata model_destinasi;
    ProgressDialog pDialog;
    ListAdapterDestinasiAdmin adapter;
    PreferencesHelper ph;

    String id;
    String nama;
    String alamat;
    String lat;
    String longi;
    String deskripsi;
    String url_gambar;
    String biaya;
    String jarak;
    String alamat_lengkap;
    String url_web;
    String jml_wisatawan;

    JSONArray arr;

    List<com.spk.model.DestinasiWisata> list_destinasi2 = new ArrayList<DestinasiWisata>();
    HashMap<String, String> hashMap;

    public static String KEY_DESTINASI_ID = "id_destinasi_wisata";
    public static String KEY_DESTINASI_NAMA = "nama";
    public static String KEY_DESTINASI_ALAMAT = "alamat";
    public static String KEY_DESTINASI_LAT = "lat";
    public static String KEY_DESTINASI_LONGI = "longi";
    public static String KEY_DESTINASI_DESKRIPSI = "deskripsi";
    public static String KEY_DESTINASI_URL_GAMBAR = "url_gambar";
    public static String KEY_DESTINASI_BIAYA = "biaya";
    public static String KEY_DESTINASI_JARAK = "jarak";
    public static String KEY_DESTINASI_ALAMAT_LENGKAP = "alamat_lengkap";
    public static String KEY_DESTINASI_WEBSITE = "url_web";
    public static String KEY_DESTINASI_JML_WISATAWAN= "jml_wisatawan";

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_menu_admin, container, false);
        btnTambahAlt = (FloatingActionButton) rootView.findViewById(R.id.btnTambahAlt);
        listViewDestinasi = (ListView) rootView.findViewById(R.id.listViewDestinasi);

        ph = new PreferencesHelper(getActivity());

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        //loadData();
        btnTambahAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), TambahAlternatif.class);
                startActivity(i);
            }
        });

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        list_destinasi2.clear();
        loadData();
    }

    public void loadData() {
        String url = "http://spksaw.hol.es/spk-saw/get_all_destinasi_wisata.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            arr = obj.getJSONArray("destinasi");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonObj = arr.getJSONObject(i);
                                id = jsonObj.getString(KEY_DESTINASI_ID);
                                nama = jsonObj.getString(KEY_DESTINASI_NAMA);
                                alamat = jsonObj
                                        .getString(KEY_DESTINASI_ALAMAT);
                                lat = jsonObj.getString(KEY_DESTINASI_LAT);
                                longi = jsonObj.getString(KEY_DESTINASI_LONGI);
                                deskripsi = jsonObj
                                        .getString(KEY_DESTINASI_DESKRIPSI);
                                url_gambar = jsonObj
                                        .getString(KEY_DESTINASI_URL_GAMBAR);
                                biaya = jsonObj.getString(KEY_DESTINASI_BIAYA);
                                jarak = jsonObj.getString(KEY_DESTINASI_JARAK);
                                alamat_lengkap = jsonObj.getString(KEY_DESTINASI_ALAMAT_LENGKAP);
                                url_web = jsonObj.getString(KEY_DESTINASI_WEBSITE);
                                jml_wisatawan = jsonObj.getString(KEY_DESTINASI_JML_WISATAWAN);

                                model_destinasi = new com.spk.model.DestinasiWisata();
                                model_destinasi.setId(Integer.parseInt(id));
                                model_destinasi.setnama(nama);
                                model_destinasi.setalamat(alamat);
                                model_destinasi.setLat(lat);
                                model_destinasi.setLongi(longi);
                                model_destinasi.setdeskripsi(deskripsi);
                                model_destinasi.seturl_foto(url_gambar);
                                model_destinasi.setBiaya(biaya);
                                model_destinasi.setJarak(jarak);
                                model_destinasi.setAlamatLengkap(alamat_lengkap);
                                model_destinasi.setUrlWeb(url_web);
                                model_destinasi.setJmlWisatawan(jml_wisatawan);
                                list_destinasi2.add(model_destinasi);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        adapter = new ListAdapterDestinasiAdmin(getActivity(), list_destinasi2);
                        listViewDestinasi.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                Toast.makeText(getActivity(),
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                return null;

            }
        };
        AppController.getInstance().addToRequestQueue(req);
    }
}
