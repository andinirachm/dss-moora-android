package com.spk.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.spk.utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahAlternatif extends ActionBarActivity {

    EditText etNamaDestinasi;
    EditText etAlamat;
    EditText etAlamatLengkap;
    EditText etLatitude;
    EditText etLongitude;
    EditText etDeskripsi;
    EditText etUrlGambar;
    EditText etUrlWeb;
    EditText etBiaya;
    EditText etJmlWisatawan;
    Button btnTambahAlt;
    ProgressDialog pDialog;

    String nama, alamat, alamat_lengkap, lat, longi, desk, urlG, urlW, biaya, jmlWisatawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_alternatif);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initComponent();
        btnTambahAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(TambahAlternatif.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
                loadData();
            }
        });
    }

    private void initComponent() {
        etNamaDestinasi = (EditText) findViewById(R.id.etNamaDestinasi);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etAlamatLengkap = (EditText) findViewById(R.id.etAlamatLengkap);
        etLatitude = (EditText) findViewById(R.id.etLatitude);
        etLongitude = (EditText) findViewById(R.id.etLongitude);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        etUrlGambar = (EditText) findViewById(R.id.etUrlGambar);
        etUrlWeb = (EditText) findViewById(R.id.etUrlWebsite);
        etBiaya = (EditText) findViewById(R.id.etBiaya);
        etJmlWisatawan = (EditText) findViewById(R.id.etJmlWisatawan);
        btnTambahAlt = (Button) findViewById(R.id.btnTambahAlt);

    }

    private void initAction() {
        nama = etNamaDestinasi.getText().toString();
        alamat = etAlamat.getText().toString();
        alamat_lengkap = etAlamatLengkap.getText().toString();
        lat = etLatitude.getText().toString();
        longi = etLongitude.getText().toString();
        desk = etDeskripsi.getText().toString();
        urlG = etUrlGambar.getText().toString();
        urlW = etUrlWeb.getText().toString();
        biaya = etBiaya.getText().toString();
        jmlWisatawan = etJmlWisatawan.getText().toString();

    }

    public void loadData() {
        initAction();
        String url = "http://spksaw.hol.es/spk-saw/insert_destinasi_wisata.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");

                            finish();
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
                Toast.makeText(TambahAlternatif.this,
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_destinasi", nama);
                params.put("alamat", alamat);
                params.put("alamat_lengkap", alamat_lengkap);
                params.put("lat", lat);
                params.put("longi", longi);
                params.put("deskripsi", desk);
                params.put("url_gambar", urlG);
                params.put("url_web", urlW);
                params.put("biaya", biaya);
                params.put("jml_wisatawan", jmlWisatawan);
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(req);
    }
}
