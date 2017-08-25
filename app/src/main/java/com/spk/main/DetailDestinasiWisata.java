package com.spk.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.manuelpeinado.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;
import com.spk.model.Komentar;
import com.spk.utils.AppController;
import com.spk.utils.Helper;
import com.spk.utils.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andini17 on 11/08/2015.
 */
public class DetailDestinasiWisata extends ActionBarActivity implements OnMapReadyCallback {
    String id;
    String nama;
    String alamat;
    String url_foto;
    String deskripsi;
    String biaya;
    String jarak;
    double lat;
    double longi;
    String url_web;
    String alamat_lengkap;
    String produk;

    String nama_destinasi;
    String nama_user;
    String tgl;
    String isi;

    String id_user;

    String date_komentar;
    int tgl_komentar;
    int bln_komentar;
    int thn_komentar;
    int jam_komentar;
    int mnt_komentar;
    String isi_komentar;

    ListAdapterKomentar adapterKomentar;
    ListView listView;
    FloatingActionButton btnShare;
    ProgressDialog pDialog;
    ProgressDialog pDialogKomentar;
    Dialog dialog;
    List<Komentar> list_komentar = new ArrayList<Komentar>();
    List<String> list_produk = new ArrayList<>();

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private TextView title;
    TextView tvAlamat, tvDeskripsi, tvBiaya, tvWebsite;
    NetworkImageView imgHeader;
    Button btnKomentar;
    private GoogleMap map;
    LinearLayout linearProduk;
    LinearLayout linearProduk2;
    TextView tvProduk;

    public static String KEY_KOMENTAR_NAMA_DESTINASI = "nama";
    public static String KEY_KOMENTAR_USER = "nama_user";
    public static String KEY_KOMENTAR_ISI = "isi_komentar";
    public static String KEY_KOMENTAR_TANGGGAL = "tgl_komentar";
    JSONArray arr;

    Komentar komentar;

    PreferencesHelper ph;

    int success;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        nama = i.getStringExtra("nama");
        alamat = i.getStringExtra("alamat");
        url_foto = i.getStringExtra("gambar");
        deskripsi = i.getStringExtra("deskripsi");
        biaya = i.getStringExtra("biaya");
        jarak = i.getStringExtra("jarak");
        lat = Double.parseDouble(i.getStringExtra("lat"));
        longi = Double.parseDouble(i.getStringExtra("longi"));
        alamat_lengkap = i.getStringExtra("alamat_lengkap");
        url_web = i.getStringExtra("url_web");

        ph = new PreferencesHelper(DetailDestinasiWisata.this);
        id_user = ph.getPreferences("id_user");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        imageLoader = AppController.getInstance().getImageLoader();
        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(R.color.myPrimaryColor)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_detail_destinasi)
                .headerOverlayLayout(R.layout.header_overlay);


        setContentView(helper.createView(this));
        initComponent();

        pDialog = new ProgressDialog(DetailDestinasiWisata.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        loadData();

        imgHeader.setImageUrl(url_foto, imageLoader);
        title.setText(nama);
        tvAlamat.setText(alamat_lengkap + ", " + alamat);
        tvDeskripsi.setText(deskripsi);
        tvBiaya.setText(biaya);
        tvWebsite.setText(url_web);
        helper.initActionBar(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal();
                getKomentarDialog();
            }
        });
    }

    private void initComponent() {
        linearProduk2 = (LinearLayout)findViewById(R.id.linearProduk);
        imgHeader = (NetworkImageView) findViewById(R.id.image_header);
        title = (TextView) findViewById(R.id.title);
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        tvBiaya = (TextView) findViewById(R.id.tvBiaya);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);
        listView = (ListView) findViewById(R.id.listView);
        btnShare = (FloatingActionButton) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,
                        "Bingung pilih destinasi wisata di DKI Jakarta? Gunakan SPK Destinasi Wisata dong... Ayo! Kunjungi " + nama
                                + "- Andini Rachmah -");
                startActivity(Intent.createChooser(i, "SPK Destinasi Wisata"));

            }
        });
        btnKomentar = (Button) findViewById(R.id.btnPostKomentar);
        if (ph.getPreferences("user").equals("admin")) {
            btnKomentar.setVisibility(View.GONE);
            btnShare.setLabelVisibility(View.GONE);
        } else {
            btnKomentar.setVisibility(View.VISIBLE);
            btnShare.setLabelVisibility(View.VISIBLE);
        }
    }

    public void loadData() {
        String url = "http://spksaw.hol.es/spk-saw/get_comment_by_id_destinasi.php?id_destinasi=" + id;
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            arr = obj.getJSONArray("komentar");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonObj = arr.getJSONObject(i);
                                nama_user = jsonObj.getString(KEY_KOMENTAR_USER);
                                tgl = jsonObj.getString(KEY_KOMENTAR_TANGGGAL);
                                isi = jsonObj.getString(KEY_KOMENTAR_ISI);

                                komentar = new Komentar();
                                komentar.setNamaUser(nama_user);
                                komentar.setTanggal(tgl);
                                komentar.setIsi(isi);

                                list_komentar.add(komentar);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        adapterKomentar = new ListAdapterKomentar(DetailDestinasiWisata.this, list_komentar);
                        listView.setAdapter(adapterKomentar);
                        Helper.getListViewSize(listView);

                        getProduct();
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                Toast.makeText(DetailDestinasiWisata.this,
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

    private void getKomentarDialog() {
        LayoutInflater li = LayoutInflater.from(DetailDestinasiWisata.this);
        View dialogView = li.inflate(R.layout.dialog_post_komentar, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailDestinasiWisata.this);
        alertDialog.setView(dialogView);
        dialog = alertDialog.create();

        final Button btnPostKomentar = (Button) dialogView.findViewById(R.id.btnPostKomentar);
        final EditText etKomentar = (EditText) dialogView.findViewById(R.id.etIsiKomentar);

        btnPostKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialogKomentar = new ProgressDialog(DetailDestinasiWisata.this);
                pDialogKomentar.setMessage("Loading...");
                pDialogKomentar.show();
                isi_komentar = etKomentar.getText().toString();
                postKomentar();
            }
        });
        dialog.show();
    }


    public void postKomentar() {

        date_komentar = "" + tgl_komentar + "-" + (bln_komentar + 1) + "-" + thn_komentar + " " + jam_komentar + ":" + mnt_komentar;

        String url = "http://spksaw.hol.es/spk-saw/post_comment.php";
        StringRequest req = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialogKomentar.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(DetailDestinasiWisata.this,
                                        "Komentar telah ditambahkan", Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                Toast.makeText(DetailDestinasiWisata.this,
                                        "Komentar gagal ditambahkan", Toast.LENGTH_SHORT)
                                        .show();
                            }
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
                Toast.makeText(DetailDestinasiWisata.this,
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_destinasi_wisata", id);
                params.put("id_user", id_user);
                params.put("tgl_komentar", date_komentar);
                params.put("isi_komentar", isi_komentar);
                return params;

            }
        };

        AppController.getInstance().addToRequestQueue(req);
    }

    private void getTanggal() {
        Calendar calendar = Calendar.getInstance();
        thn_komentar = calendar.get(Calendar.YEAR);
        bln_komentar = calendar.get(Calendar.MONTH);
        tgl_komentar = calendar.get(Calendar.DAY_OF_MONTH);

        jam_komentar = calendar.get(Calendar.HOUR);
        mnt_komentar = calendar.get(Calendar.MINUTE);

    }

    private void getProduct() {
        list_produk.clear();
        String url = "http://spksaw.hol.es/spk-saw/get_all_product_by_id.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        try {
                            JSONObject obj = new JSONObject(response);
                            arr = obj.getJSONArray("produk");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonObj = arr.getJSONObject(i);
                                produk = jsonObj.getString("produk");
                                list_produk.add(produk);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        for (int i = 0; i < list_produk.size(); i++) {
                            linearProduk = new LinearLayout(DetailDestinasiWisata.this);
                            linearProduk.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            linearProduk.setOrientation(LinearLayout.HORIZONTAL);
                            linearProduk.setPadding(0, 0, 0, 0);


                            tvProduk = new TextView(DetailDestinasiWisata.this);
                            tvProduk.setLayoutParams(new LinearLayout.LayoutParams(900,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            tvProduk.setText("  "+ list_produk.get(i));
                            tvProduk.setTextSize(15);
                            tvProduk.setTextColor(Color.BLACK);
                            tvProduk.setEnabled(false);

                            linearProduk.addView(tvProduk);

                            linearProduk2.addView(linearProduk);

                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                pDialog.dismiss();
                Toast.makeText(DetailDestinasiWisata.this,
                        "Volley error", Toast.LENGTH_SHORT)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_destinasi", id);
                return params;

            }
        };

        AppController.getInstance().addToRequestQueue(req);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Only show items in the action bar relevant to this screen
        // if the drawer is not showing. Otherwise, let the drawer
        // decide what to show in the action bar.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_action_share));
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        // Hide the zoom controls as the button panel will cover it.
        map.getUiSettings().setZoomControlsEnabled(false);

        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(lat, longi)).title(nama);
        map.addMarker(marker);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                new LatLng(lat, longi), 15);
        map.animateCamera(cameraUpdate);
    }

}
