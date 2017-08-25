package com.spk.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.spk.utils.AppController;
import com.spk.utils.PreferencesHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andini17 on 11/08/2015.
 */
public class PostKomentar extends ActionBarActivity {
    String id_destinasi_wisata;
    String nama;
    String id_user;
    String tgl_komentar;
    String isi_komentar;
    int tgl;
    String wkt;

    PreferencesHelper ph;
    ProgressDialog pDialog;
    Button btnPostKomentar;
    EditText etKomentar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_post_komentar);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ph = new PreferencesHelper(this);
        id_user = ph.getPreferences("id_user");

        Intent i = getIntent();
        id_destinasi_wisata = i.getStringExtra("id_destinasi_wisata");
        nama = i.getStringExtra("nama");

        getTanggal();
        tgl_komentar = "" + tgl + " " + wkt;

        initComponent();

        btnPostKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isi_komentar = etKomentar.getText().toString();
                pDialog = new ProgressDialog(PostKomentar.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
                loadData();
            }
        });
    }

    private void initComponent() {
        etKomentar = (EditText) findViewById(R.id.etIsiKomentar);
        btnPostKomentar = (Button) findViewById(R.id.btnPostKomentar);
    }

    public void loadData() {
        String url = "http://spksaw.hol.es/spk-saw/post_comment.php";
        StringRequest req = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(PostKomentar.this,
                                    "" + obj, Toast.LENGTH_SHORT)
                                    .show();
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
                Toast.makeText(PostKomentar.this,
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_destinasi_wisata", id_destinasi_wisata);
                params.put("id_user", id_user);
                params.put("tgl_komentar", tgl_komentar);
                params.put("isi_komentar", isi_komentar);
                return params;

            }
        };

        AppController.getInstance().addToRequestQueue(req);
    }


    private void getTanggal() {
        Calendar cal = Calendar.getInstance();
        tgl = cal.get(Calendar.DATE);
        Date now = cal.getTime();
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        wkt = outFormat.format(now);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
