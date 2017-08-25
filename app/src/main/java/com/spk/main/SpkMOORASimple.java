package com.spk.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.spk.model.DestinasiWisata;
import com.spk.model.HasilAkhir;
import com.spk.utils.AppController;
import com.spk.utils.Helper2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Andini17 on 10/08/2015.
 */
public class SpkMOORASimple extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    double latitude;
    double longitude;
    Spinner sp;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    NetworkImageView destinasi_gambar;
    AlertDialog dialog;
    Button btnProses;
    Button btnSelesai;
    TextView lbl1;
    TextView lbl2;
    TextView lbl3;
    TextView lbl4;
    TextView lbl5;
    TextView lbl6;
    TextView lbl7;
    TextView lbl8;
    TextView lbl9;
    TextView lbl10;
    TextView lbl11;
    TextView lbl12;
    TextView lbl13;
    TextView lbl14;
    TextView lbl15;
    TextView lbl16;
    TextView lbl17;
    TextView lbl18;
    TextView lbl19;
    TextView lbl20;
    TextView lbl21;
    TextView lbl22;
    TextView lbl23;
    TextView lbl24;
    TextView lbl25;
    TextView lbl26;
    TextView lbl27;
    TextView lbl28;
    TextView lbl29;
    TextView lbl30;

    LinearLayout linearAlternatif;
    TextView tvAlt;

    DestinasiWisata model_destinasi;

    View rootView;
    ProgressDialog pDialog;

    String id;
    String nama;
    String alamat;
    String lat;
    String longi;
    String deskripsi;
    String url_gambar;
    String url_web;
    String biaya;
    String jarak;
    String jml_wisatawan;
    String produk;
    String alamat_lengkap;

    JSONArray arr, arr2;

    List<DestinasiWisata> list_destinasi = new ArrayList<DestinasiWisata>();
    List<HasilAkhir> list_hasil = new ArrayList<HasilAkhir>();
    List<String> list = new ArrayList<>();
    List<String> list_id = new ArrayList<>();
    List<String> list_url_gambar = new ArrayList<>();

    List<String> list_alternatif = new ArrayList<>();
    List<Float> arrayListJarak = new ArrayList<>();
    List<Float> arrayListBiaya = new ArrayList<>();
    List<Float> arrayListJumlah = new ArrayList<>();
    List<Float> arrayListProduk = new ArrayList<>();
    List<Float> hasilAkhir = new ArrayList<>();

    List<Float> arrayListBobotJarak = new ArrayList<>();
    List<Float> arrayListBobotBiaya = new ArrayList<>();
    List<Float> arrayListBobotJumlah = new ArrayList<>();
    List<Float> arrayListBobotProduk = new ArrayList<>();

    List<Float> listMatriksEvaluasi = new ArrayList<>();
    List<Float> listMatriksEvaluasi2 = new ArrayList<>();
    List<Float> listMatriksEvaluasi3 = new ArrayList<>();
    List<Float> listMatriksEvaluasi4 = new ArrayList<>();

    List<Float> listProbabilitas = new ArrayList<>();
    List<Float> listProbabilitas2 = new ArrayList<>();
    List<Float> listProbabilitas3 = new ArrayList<>();
    List<Float> listProbabilitas4 = new ArrayList<>();

    List<Float> listEntropy = new ArrayList<>();
    List<Float> listEntropy2 = new ArrayList<>();
    List<Float> listEntropy3 = new ArrayList<>();
    List<Float> listEntropy4 = new ArrayList<>();

    float mBobot[][] = new float[100][4];
    float mBobot2[][] = new float[100][4];
    double mBobot3[][] = new double[100][4];
    double mBobot4[][] = new double[100][4];

    float sumOfVarBobotBiaya = 0;
    float sumOfVarBobotJarak = 0;
    float sumOfVarBobotJumlah = 0;
    float sumOfVarBobotProduk = 0;

    float sumOfVarBobotEntropyBiaya = 0;
    float sumOfVarBobotEntropyJumlah = 0;
    float sumOfVarBobotEntropyJarak = 0;
    float sumOfVarBobotEntropyProduk = 0;

    int m_jarak = 0;
    int m_biaya = 0;
    int m_jml_wisatawan = 0;
    int m_produk = 0;

    float EkJarak = 0;
    float EkBiaya = 0;
    float EkJumlah = 0;
    float EkProduk = 0;
    float SumOfEk = 0;

    float LkJarak = 0;
    float LkBiaya = 0;
    float LkJumlah = 0;
    float LkProduk = 0;

    float WEkJarak = 0;
    float WEkBiaya = 0;
    float WEkJumlah = 0;
    float WEkProduk = 0;
    float SumOfWEk = 0;

    float bobotAkhirJarak = 0;
    float bobotAkhirBiaya = 0;
    float bobotAkhirJumlah = 0;
    float bobotAkhirProduk = 0;

    float hasil[][] = new float[1][100];
    float m2[][] = new float[100][4];
    float m3[][] = new float[100][4];

    List<Float> array1m3 = new ArrayList<>();
    List<Float> array2m3 = new ArrayList<>();
    List<Float> array3m3 = new ArrayList<>();
    List<Float> array4m3 = new ArrayList<>();

    List<Float> sqrt1m3 = new ArrayList<>();
    List<Float> sqrt2m3 = new ArrayList<>();
    List<Float> sqrt3m3 = new ArrayList<>();
    List<Float> sqrt4m3 = new ArrayList<>();

    double sumOfVarBiaya = 0;
    double sumOfVarJarak = 0;
    double sumOfVarJumlah = 0;
    double sumOfVarProduk = 0;

    float w1, w2, w3, w4;

    float j, b, jum, pro;

    public static String KEY_DESTINASI_ID = "id_destinasi_wisata";
    public static String KEY_DESTINASI_NAMA = "nama";
    public static String KEY_DESTINASI_ALAMAT = "alamat";
    public static String KEY_DESTINASI_ALAMAT_LENGKAP = "alamat_lengkap";
    public static String KEY_DESTINASI_LAT = "lat";
    public static String KEY_DESTINASI_LONGI = "longi";
    public static String KEY_DESTINASI_DESKRIPSI = "deskripsi";
    public static String KEY_DESTINASI_URL_GAMBAR = "url_gambar";
    public static String KEY_DESTINASI_BIAYA = "biaya";
    public static String KEY_DESTINASI_JARAK = "jarak";
    public static String KEY_DESTINASI_JML_WISATAWAN = "jml_wisatawan";
    public static String KEY_DESTINASI_PRODUK = "produk";
    public static String KEY_DESTINASI_URL_WEB = "url_web";

    String jarakSangatDekat2;
    String jarakDekat1;
    String jarakDekat2;
    String jarakSedang1;
    String jarakSedang2;
    String jarakJauh1;
    String jarakJauh2;
    String jarakSangatJauh2;

    String biayaSangatMurah2;
    String biayaMurah1;
    String biayaMurah2;
    String biayaSedang1;
    String biayaSedang2;
    String biayaMahal1;
    String biayaMahal2;
    String biayaSangatMahal2;

    String popularitasSangatRendah2;
    String popularitasRendah1;
    String popularitasRendah2;
    String popularitasSedang1;
    String popularitasSedang2;
    String popularitasTinggi1;
    String popularitasTinggi2;
    String popularitasSangatTinggi2;

    String produkSangatSedikit2;
    String produkSedikit1;
    String produkSedikit2;
    String produkSedang1;
    String produkSedang2;
    String produkBanyak1;
    String produkBanyak2;
    String produkSangatBanyak2;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    List<String> listSpinner = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_spk_moora_simple, container, false);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        listSpinner.add("Popularitas");
        listSpinner.add("Produk");
        listSpinner.add("Biaya");
        listSpinner.add("Jarak");

        btnSelesai = (Button) rootView.findViewById(R.id.btnSelesai);
        btnSelesai.setVisibility(View.GONE);
        btnProses = (Button) rootView.findViewById(R.id.btnProses);
        linearAlternatif = (LinearLayout) rootView.findViewById(R.id.linearAlternatif);

        lbl1 = (TextView) rootView.findViewById(R.id.lbl1);
        lbl2 = (TextView) rootView.findViewById(R.id.lbl2);
        lbl3 = (TextView) rootView.findViewById(R.id.lbl3);
        lbl4 = (TextView) rootView.findViewById(R.id.lbl4);
        lbl5 = (TextView) rootView.findViewById(R.id.lbl5);
        lbl6 = (TextView) rootView.findViewById(R.id.lbl6);
        lbl7 = (TextView) rootView.findViewById(R.id.lbl7);
        lbl8 = (TextView) rootView.findViewById(R.id.lbl8);
        lbl9 = (TextView) rootView.findViewById(R.id.lbl9);
        lbl10 = (TextView) rootView.findViewById(R.id.lbl10);

        lbl11 = (TextView) rootView.findViewById(R.id.lbl11);
        lbl12 = (TextView) rootView.findViewById(R.id.lbl12);
        lbl13 = (TextView) rootView.findViewById(R.id.lbl13);
        lbl14 = (TextView) rootView.findViewById(R.id.lbl14);
        lbl15 = (TextView) rootView.findViewById(R.id.lbl15);
        lbl16 = (TextView) rootView.findViewById(R.id.lbl16);
        lbl17 = (TextView) rootView.findViewById(R.id.lbl17);
        lbl18 = (TextView) rootView.findViewById(R.id.lbl18);
        lbl19 = (TextView) rootView.findViewById(R.id.lbl19);
        lbl20 = (TextView) rootView.findViewById(R.id.lbl20);

        lbl21 = (TextView) rootView.findViewById(R.id.lbl21);
        lbl22 = (TextView) rootView.findViewById(R.id.lbl22);
        lbl23 = (TextView) rootView.findViewById(R.id.lbl23);
        lbl24 = (TextView) rootView.findViewById(R.id.lbl24);
        lbl25 = (TextView) rootView.findViewById(R.id.lbl25);
        lbl26 = (TextView) rootView.findViewById(R.id.lbl26);
        lbl27 = (TextView) rootView.findViewById(R.id.lbl27);
        lbl28 = (TextView) rootView.findViewById(R.id.lbl28);
        lbl29 = (TextView) rootView.findViewById(R.id.lbl29);
        lbl30 = (TextView) rootView.findViewById(R.id.lbl30);

        sp = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);

        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        displayLocation();

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        getKonfigurasi();

        //loadData();

        lbl1.setVisibility(View.GONE);
        lbl2.setVisibility(View.GONE);
        lbl3.setVisibility(View.GONE);
        lbl4.setVisibility(View.GONE);
        lbl5.setVisibility(View.GONE);
        lbl6.setVisibility(View.GONE);
        lbl7.setVisibility(View.GONE);
        lbl8.setVisibility(View.GONE);
        lbl9.setVisibility(View.GONE);
        lbl10.setVisibility(View.GONE);

        lbl11.setVisibility(View.GONE);
        lbl12.setVisibility(View.GONE);
        lbl13.setVisibility(View.GONE);
        lbl14.setVisibility(View.GONE);
        lbl15.setVisibility(View.GONE);
        lbl16.setVisibility(View.GONE);
        lbl17.setVisibility(View.GONE);
        lbl18.setVisibility(View.GONE);
        lbl19.setVisibility(View.GONE);
        lbl20.setVisibility(View.GONE);

        lbl21.setVisibility(View.GONE);
        lbl22.setVisibility(View.GONE);
        lbl23.setVisibility(View.GONE);
        lbl24.setVisibility(View.GONE);
        lbl25.setVisibility(View.GONE);
        lbl26.setVisibility(View.GONE);
        lbl27.setVisibility(View.GONE);
        lbl28.setVisibility(View.GONE);
        lbl29.setVisibility(View.GONE);
        lbl30.setVisibility(View.GONE);


        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),
                        " "+ String.valueOf(sp.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                if(String.valueOf(sp.getSelectedItem()).equals("Popularitas")){
                    w1 = (float) 0.9;
                    w2 = (float) 0.033;
                    w3 = (float) 0.033;
                    w4 = (float) 0.033;
                }
                else if(String.valueOf(sp.getSelectedItem()).equals("Produk")){
                    w1 = (float) 0.033;
                    w2 = (float) 0.9;
                    w3 = (float) 0.033;
                    w4 = (float) 0.033;
                }
                else if(String.valueOf(sp.getSelectedItem()).equals("Biaya")){
                    w1 = (float) 0.033;
                    w2 = (float) 0.033;
                    w3 = (float) 0.9;
                    w4 = (float) 0.033;
                }
                else if(String.valueOf(sp.getSelectedItem()).equals("Jarak")){
                    w1 = (float) 0.033;
                    w2 = (float) 0.033;
                    w3 = (float) 0.033;
                    w4 = (float) 0.9;
                }
                loadData();
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Loading...");
                pDialog.show();

                lbl1.setVisibility(View.VISIBLE);
                lbl2.setVisibility(View.GONE);
                lbl3.setVisibility(View.VISIBLE);
                lbl4.setVisibility(View.GONE);
                lbl5.setVisibility(View.VISIBLE);
                lbl6.setVisibility(View.GONE);
                lbl7.setVisibility(View.VISIBLE);
                lbl8.setVisibility(View.GONE);
                lbl9.setVisibility(View.VISIBLE);
                lbl10.setVisibility(View.GONE);

                lbl11.setVisibility(View.VISIBLE);
                lbl12.setVisibility(View.GONE);
                lbl13.setVisibility(View.VISIBLE);
                lbl14.setVisibility(View.GONE);
                lbl15.setVisibility(View.VISIBLE);
                lbl16.setVisibility(View.GONE);
                lbl17.setVisibility(View.VISIBLE);
                lbl18.setVisibility(View.GONE);
                lbl19.setVisibility(View.VISIBLE);
                lbl20.setVisibility(View.GONE);

                lbl21.setVisibility(View.GONE);
                lbl22.setVisibility(View.GONE);
                lbl23.setVisibility(View.VISIBLE);
                lbl24.setVisibility(View.GONE);
                lbl25.setVisibility(View.VISIBLE);
                lbl26.setVisibility(View.GONE);
                lbl27.setVisibility(View.VISIBLE);
                lbl28.setVisibility(View.GONE);
                lbl29.setVisibility(View.VISIBLE);
                lbl30.setVisibility(View.GONE);

                 /* BUAT TABEL */
                createTableAlternatif();
                createTableKriteria();
                createTableCostBenefit();
                createTableOptimasi();
                createTableMatriksEvalua();
                createTableMatriksEvaluasi();
                createTableMatriksProbabilitas();
                createTableMatriksEntropy();
                createTableEk();
                createTable1();
                createTableHasil();
                createTable3();
                createTable2();
                createTableSquareRoot();
                createTableBobot();
                pDialog.dismiss();


            }
        });
        return rootView;
    }

    public void getKonfigurasi() {
        String url = "http://spksaw.hol.es/spk-saw/get_all_configuration.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        try {
                            pDialog.dismiss();
                            JSONObject obj = new JSONObject(response);
                            JSONArray arr = obj.getJSONArray("konfigurasi");
                            System.out.println("KONFIGURASI : " + arr);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonObj = arr.getJSONObject(0);

                                jarakSangatDekat2 = jsonObj.getString("jarak_sangat_dekat_2");
                                jarakDekat1 = jsonObj.getString("jarak_dekat_1");
                                jarakDekat2 = jsonObj.getString("jarak_dekat_2");
                                jarakSedang1 = jsonObj.getString("jarak_sedang_1");
                                jarakSedang2 = jsonObj.getString("jarak_sedang_2");
                                jarakJauh1 = jsonObj.getString("jarak_jauh_1");
                                jarakJauh2 = jsonObj.getString("jarak_jauh_2");
                                jarakSangatJauh2 = jsonObj.getString("jarak_sangat_jauh_2");

                                biayaSangatMurah2 = jsonObj.getString("biaya_sangat_murah_2");
                                biayaMurah1 = jsonObj.getString("biaya_murah_1");
                                biayaMurah2 = jsonObj.getString("biaya_murah_2");
                                biayaSedang1 = jsonObj.getString("biaya_sedang_1");
                                biayaSedang2 = jsonObj.getString("biaya_sedang_2");
                                biayaMahal1 = jsonObj.getString("biaya_mahal_1");
                                biayaMahal2 = jsonObj.getString("biaya_mahal_2");
                                biayaSangatMahal2 = jsonObj.getString("biaya_sangat_mahal_2");

                                popularitasSangatRendah2 = jsonObj.getString("popularitas_sangat_rendah_2");
                                popularitasRendah1 = jsonObj.getString("popularitas_rendah_1");
                                popularitasRendah2 = jsonObj.getString("popularitas_rendah_2");
                                popularitasSedang1 = jsonObj.getString("popularitas_sedang_1");
                                popularitasSedang2 = jsonObj.getString("popularitas_sedang_2");
                                popularitasTinggi1 = jsonObj.getString("popularitas_tinggi_1");
                                popularitasTinggi2 = jsonObj.getString("popularitas_tinggi_2");
                                popularitasSangatTinggi2 = jsonObj.getString("popularitas_sangat_tinggi_2");

                                produkSangatSedikit2 = jsonObj.getString("produk_sangat_sedikit_2");
                                produkSedikit1 = jsonObj.getString("produk_sedikit_1");
                                produkSedikit2 = jsonObj.getString("produk_sedikit_2");
                                produkSedang1 = jsonObj.getString("produk_sedang_1");
                                produkSedang2 = jsonObj.getString("produk_sedang_2");
                                produkBanyak1 = jsonObj.getString("produk_banyak_1");
                                produkBanyak2 = jsonObj.getString("produk_banyak_2");
                                produkSangatBanyak2 = jsonObj.getString("produk_sangat_banyak_2");

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
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

    public void loadData() {
        hasilAkhir.clear();
        list.clear();
        list_id.clear();
        list_hasil.clear();
        list_url_gambar.clear();
        list_destinasi.clear();
        list_alternatif.clear();
        arrayListBiaya.clear();
        arrayListJarak.clear();
        arrayListJumlah.clear();
        arrayListBobotProduk.clear();
        array1m3.clear();
        array2m3.clear();
        array3m3.clear();
        array4m3.clear();
        sqrt1m3.clear();
        sqrt2m3.clear();
        sqrt3m3.clear();
        sqrt4m3.clear();
        list_hasil.clear();
        arrayListBobotJarak.clear();
        arrayListBobotJumlah.clear();
        arrayListBobotBiaya.clear();
        arrayListBobotProduk.clear();
        listMatriksEvaluasi.clear();
        listMatriksEvaluasi2.clear();
        listMatriksEvaluasi3.clear();
        listMatriksEvaluasi4.clear();
        listProbabilitas.clear();
        listProbabilitas2.clear();
        listProbabilitas3.clear();
        listProbabilitas4.clear();
        listEntropy.clear();
        listEntropy2.clear();
        listEntropy3.clear();
        listEntropy4.clear();
        String url = "http://spksaw.hol.es/spk-saw/get_destinasi.php?ll=" + latitude + "," + longitude;
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub

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
                                jml_wisatawan = jsonObj.getString(KEY_DESTINASI_JML_WISATAWAN);
                                arr2 = jsonObj.getJSONArray(KEY_DESTINASI_PRODUK);
                                produk = String.valueOf(arr2.length());
                                url_web = jsonObj.getString(KEY_DESTINASI_URL_WEB);
                                alamat_lengkap = jsonObj.getString(KEY_DESTINASI_ALAMAT_LENGKAP);

                                bentukMatriksEvaluasi();

                                model_destinasi = new DestinasiWisata();
                                model_destinasi.setId(Integer.parseInt(id));
                                model_destinasi.setnama(nama);
                                model_destinasi.setalamat(alamat);
                                model_destinasi.setLat(lat);
                                model_destinasi.setLongi(longi);
                                model_destinasi.setdeskripsi(deskripsi);
                                model_destinasi.seturl_foto(url_gambar);
                                model_destinasi.setUrlWeb(url_gambar);
                                model_destinasi.setBiaya(biaya);
                                model_destinasi.setJarak(jarak);
                                model_destinasi.setJmlWisatawan(jml_wisatawan);
                                model_destinasi.setProduk(produk);

                                list_destinasi.add(model_destinasi);

                                arrayListBiaya.add(Float.parseFloat(biaya));
                                arrayListJarak.add(Float.parseFloat(jarak));
                                arrayListJumlah.add(Float.parseFloat(jml_wisatawan));
                                arrayListProduk.add(Float.parseFloat(produk));
                                List<String> list_url_web = new ArrayList<>();
                                List<String> list_produk = new ArrayList<>();
                                List<String> list_lat = new ArrayList<>();
                                List<String> list_longi = new ArrayList<>();
                                List<String> list_alamat = new ArrayList<>();
                                List<String> list_alamat_lengkap = new ArrayList<>();
                                List<String> list_jarak = new ArrayList<>();
                                List<String> list_biaya = new ArrayList<>();
                                List<String> list_deskripsi = new ArrayList<>();
                                list.add(nama);
                                list_id.add(id);
                                list_url_web.add(url_web);
                                list_produk.add(produk);
                                list_lat.add(lat);
                                list_longi.add(longi);
                                list_alamat.add(alamat);
                                list_alamat_lengkap.add(alamat);
                                list_jarak.add(jarak);
                                list_biaya.add(biaya);
                                list_deskripsi.add(deskripsi);
                                list_url_gambar.add(url_gambar);

                            }

                            sumOfVarBobotJarak = 0;
                            sumOfVarBobotEntropyJarak = 0;

                            sumOfVarBobotBiaya = 0;
                            sumOfVarBobotEntropyBiaya = 0;

                            sumOfVarBobotJumlah = 0;
                            sumOfVarBobotEntropyJumlah = 0;

                            sumOfVarBobotProduk = 0;
                            sumOfVarBobotEntropyProduk = 0;

                            normalisasiMatriksEvaluasi();

                            matriksProbabilitasDanEntropy();

                            hitungBobotEntropy();

                            hitungRasio();

                            /* NORMALISASI */
                            for (int i = 0; i < array2m3.size(); i++) {
                                hasil[0][i] = (float) (((bobotAkhirProduk * m3[i][3]) + (bobotAkhirJumlah * m3[i][2])) - (bobotAkhirBiaya * (m3[i][0])-(bobotAkhirJarak * (m3[i][1]))));
                                hasilAkhir.add(hasil[0][i]);

                                HasilAkhir hsl = new HasilAkhir();
                                hsl.setId(Integer.parseInt(list_id.get(i)));
                                hsl.setNamaDestinasi(list.get(i));
                                hsl.setUrl(list_url_gambar.get(i));
                                hsl.setHasil(hasil[0][i]);

                                list_hasil.add(hsl);
                            }

                            Collections.sort(list_hasil, Collections.reverseOrder(HasilAkhir.StuRollno));
                            for (HasilAkhir str : list_hasil) {
                                System.out.println(str.getId() + " - " + str.getNamaDestinasi() + " - " + str.getHasil());
                            }

                            ListView listViewDestinasi;
                            ListAdapterDestinasiHasil adapter;
                            listViewDestinasi = (ListView) rootView.findViewById(R.id.listViewDestinasi);
                            adapter = new ListAdapterDestinasiHasil(getActivity(), list_hasil);
                            listViewDestinasi.setAdapter(adapter);
                            Helper2.getListViewSize(listViewDestinasi);
                            btnSelesai.setVisibility(View.VISIBLE);


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

    private void bentukMatriksEvaluasi() {
        m_jarak = 0;

        if (Float.parseFloat(jarak) <= Float.parseFloat(jarakSangatDekat2)) {
            m_jarak = 5;
        } else if (Float.parseFloat(jarakDekat1) < Float.parseFloat(jarak) && Float.parseFloat(jarak) <= Float.parseFloat(jarakDekat2)) {
            m_jarak = 4;
        } else if (Float.parseFloat(jarakSedang1) < Float.parseFloat(jarak) && Float.parseFloat(jarak) <= Float.parseFloat(jarakSedang2)) {
            m_jarak = 3;
        } else if (Float.parseFloat(jarakJauh1) < Float.parseFloat(jarak) && Float.parseFloat(jarak) <= Float.parseFloat(jarakJauh2)) {
            m_jarak = 2;
        } else if (Float.parseFloat(jarak) > Float.parseFloat(jarakSangatJauh2)) {
            m_jarak = 1;
        }

        arrayListBobotJarak.add(Float.parseFloat(String.valueOf(m_jarak)));

        m_biaya = 0;

        if (Float.parseFloat(biaya) <= Float.parseFloat(biayaSangatMurah2)) {
            m_biaya = 5;
        } else if (Float.parseFloat(biayaMurah1) < (Float.parseFloat(biaya)) && (Float.parseFloat(biaya)) <= Float.parseFloat(biayaMurah2)) {
            m_biaya = 4;
        } else if (Float.parseFloat(biayaSedang1) < (Float.parseFloat(biaya)) && (Float.parseFloat(biaya) <= Float.parseFloat(biayaSedang2))) {
            m_biaya = 3;
        } else if (Float.parseFloat(biayaMahal1) < (Float.parseFloat(biaya)) && (Float.parseFloat(biaya) <= Float.parseFloat(biayaMahal2))) {
            m_biaya = 2;
        } else if (Float.parseFloat(biaya) > Float.parseFloat(biayaSangatMahal2)) {
            m_biaya = 1;
        }

        arrayListBobotBiaya.add(Float.parseFloat(String.valueOf(m_biaya)));

        m_jml_wisatawan = 0;

        if (Float.parseFloat(jml_wisatawan) <= Float.parseFloat(popularitasSangatRendah2)) {
            m_jml_wisatawan = 1;
        } else if (Float.parseFloat(popularitasRendah1) < (Float.parseFloat(jml_wisatawan)) && (Float.parseFloat(jml_wisatawan)) <= Float.parseFloat(popularitasRendah2)) {
            m_jml_wisatawan = 2;
        } else if (Float.parseFloat(popularitasSedang1) < (Float.parseFloat(jml_wisatawan)) && Float.parseFloat(jml_wisatawan) <= Float.parseFloat(popularitasSedang2)) {
            m_jml_wisatawan = 3;
        } else if (Float.parseFloat(popularitasTinggi1) < (Float.parseFloat(jml_wisatawan)) && (Float.parseFloat(jml_wisatawan) <= Float.parseFloat(popularitasTinggi2))) {
            m_jml_wisatawan = 4;
        } else if (Float.parseFloat(jml_wisatawan) > Float.parseFloat(popularitasSangatTinggi2)) {
            m_jml_wisatawan = 5;
        }

        arrayListBobotJumlah.add(Float.parseFloat(String.valueOf(m_jml_wisatawan)));

        m_produk = 0;

        if (Float.parseFloat(produk) <= Float.parseFloat(produkSangatSedikit2)) {
            m_produk = 1;
        } else if (Float.parseFloat(produkSedikit1) < (Float.parseFloat(produk)) && Float.parseFloat(produk) <= Float.parseFloat(produkSedikit2)) {
            m_produk = 2;
        } else if (Float.parseFloat(produkSedang1) < (Float.parseFloat(produk)) && Float.parseFloat(produk) <= Float.parseFloat(produkSedang2)) {
            m_produk = 3;
        } else if (Float.parseFloat(produkBanyak1) < (Float.parseFloat(produk)) && Float.parseFloat(produk) <= Float.parseFloat(produkBanyak2)) {
            m_produk = 4;
        } else if (Float.parseFloat(produk) > Float.parseFloat(produkSangatBanyak2)) {
            m_produk = 5;
        }

        arrayListBobotProduk.add(Float.parseFloat(String.valueOf(m_produk)));

    }

    private void normalisasiMatriksEvaluasi() {

        float minBobotJarak = Collections.min(arrayListBobotJarak);
        float minBobotBiaya = Collections.min(arrayListBobotBiaya);
        float maxBobotPopularitas = Collections.max(arrayListBobotJumlah);
        float maxBobotProduk = Collections.max(arrayListBobotProduk);

        for (int a = 0; a < arrayListBobotJarak.size(); a++) {
            mBobot[a][0] = minBobotJarak / arrayListBobotJarak.get(a);
            listMatriksEvaluasi.add(mBobot[a][0]);
            sumOfVarBobotJarak = sumOfVarBobotJarak + mBobot[a][0];
        }
        for (int a = 0; a < arrayListBobotBiaya.size(); a++) {
            mBobot[a][1] = minBobotBiaya / arrayListBobotBiaya.get(a);
            listMatriksEvaluasi2.add(mBobot[a][1]);
            sumOfVarBobotBiaya = sumOfVarBobotBiaya + mBobot[a][1];
        }

        for (int a = 0; a < arrayListBobotJumlah.size(); a++) {
            mBobot[a][2] = arrayListBobotJumlah.get(a) / maxBobotPopularitas;
            listMatriksEvaluasi3.add(mBobot[a][2]);
            sumOfVarBobotJumlah = sumOfVarBobotJumlah + mBobot[a][2];
        }

        for (int a = 0; a < arrayListBobotProduk.size(); a++) {
            mBobot[a][3] = arrayListBobotProduk.get(a) / maxBobotProduk;
            listMatriksEvaluasi4.add(mBobot[a][3]);
            sumOfVarBobotProduk = sumOfVarBobotProduk + mBobot[a][3];
        }

    }

    private void matriksProbabilitasDanEntropy() {

        for (int a = 0; a < arrayListBobotJarak.size(); a++) {
            mBobot2[a][0] = mBobot[a][0] / sumOfVarBobotJarak;
            listProbabilitas.add(mBobot2[a][0]);
            mBobot3[a][0] = mBobot2[a][0] * Math.log(mBobot2[a][0]);
            listEntropy.add((float) mBobot3[a][0]);
            sumOfVarBobotEntropyJarak = (float) (sumOfVarBobotEntropyJarak + mBobot3[a][0]);
            EkJarak = (float) ((-1 / (Math.log(arrayListBobotJarak.size()))) * sumOfVarBobotEntropyJarak);
        }

        for (int a = 0; a < arrayListBobotBiaya.size(); a++) {
            mBobot2[a][1] = mBobot[a][1] / sumOfVarBobotBiaya;
            listProbabilitas2.add(mBobot2[a][1]);
            mBobot3[a][1] = mBobot2[a][1] * Math.log(mBobot2[a][1]);
            listEntropy2.add((float) mBobot3[a][1]);
            sumOfVarBobotEntropyBiaya = (float) (sumOfVarBobotEntropyBiaya + mBobot3[a][1]);
            EkBiaya = (float) ((-1 / (Math.log(arrayListBobotBiaya.size()))) * sumOfVarBobotEntropyBiaya);
        }

        for (int a = 0; a < arrayListBobotJumlah.size(); a++) {
            mBobot2[a][2] = mBobot[a][2] / sumOfVarBobotJumlah;
            listProbabilitas3.add(mBobot2[a][2]);
            mBobot3[a][2] = mBobot2[a][2] * Math.log(mBobot2[a][2]);
            listEntropy3.add((float) mBobot3[a][2]);
            sumOfVarBobotEntropyJumlah = (float) (sumOfVarBobotEntropyJumlah + mBobot3[a][2]);
            EkJumlah = (float) ((-1 / (Math.log(arrayListBobotJumlah.size()))) * sumOfVarBobotEntropyJumlah);
        }

        for (int a = 0; a < arrayListBobotProduk.size(); a++) {
            mBobot2[a][3] = mBobot[a][3] / sumOfVarBobotProduk;
            listProbabilitas4.add(mBobot2[a][3]);
            mBobot3[a][3] = mBobot2[a][3] * Math.log(mBobot2[a][3]);
            listEntropy4.add((float) mBobot3[a][3]);
            sumOfVarBobotEntropyProduk = (float) (sumOfVarBobotEntropyProduk + mBobot3[a][3]);
            EkProduk = (float) ((-1 / (Math.log(arrayListBobotProduk.size()))) * sumOfVarBobotEntropyProduk);
        }

    }

    private void hitungBobotEntropy() {
        SumOfEk = EkBiaya + EkJumlah + EkProduk;

        LkJarak = (1 / (4 - (SumOfEk))) * (1 - EkJarak);
        LkBiaya = (1 / (4 - (SumOfEk))) * (1 - EkBiaya);
        LkJumlah = (1 / (4 - (SumOfEk))) * (1 - EkJumlah);
        LkProduk = (1 / (4 - (SumOfEk))) * (1 - EkProduk);

        WEkJarak = LkJarak * (float)w4;
        WEkBiaya = LkBiaya * (float)w3;
        WEkJumlah = LkJumlah * (float)w1;
        WEkProduk = LkProduk * (float)w2;

        SumOfWEk = WEkBiaya + WEkJumlah + WEkProduk + WEkJarak;

        bobotAkhirJarak = WEkJarak / SumOfWEk;
        bobotAkhirBiaya = WEkBiaya / SumOfWEk;
        bobotAkhirJumlah = WEkJumlah / SumOfWEk;
        bobotAkhirProduk = WEkProduk / SumOfWEk;
    }

    private void hitungRasio() {
        sumOfVarBiaya = 0;
        sumOfVarJarak = 0;
        sumOfVarJumlah = 0;
        sumOfVarProduk = 0;


        for (int a = 0; a < arrayListBiaya.size(); a++) {
            m2[a][0] = arrayListBiaya.get(a);
        }

        for (int a = 0; a < arrayListBiaya.size(); a++) {
            b = (m2[a][0] * m2[a][0]);
            sumOfVarBiaya = sumOfVarBiaya + b;
        }

        for (int b = 0; b < arrayListJarak.size(); b++) {
            m2[b][1] = arrayListJarak.get(b);
        }

        for (int a = 0; a < arrayListJarak.size(); a++) {
            j = (m2[a][1] * m2[a][1]);
            sumOfVarJarak = sumOfVarJarak + j;
        }

        for (int b = 0; b < arrayListJumlah.size(); b++) {
            m2[b][2] = arrayListJumlah.get(b);
        }

        for (int a = 0; a < arrayListJumlah.size(); a++) {
            jum = (m2[a][2] * m2[a][2]);
            sumOfVarJumlah = sumOfVarJumlah + jum;
        }

        for (int b = 0; b < arrayListProduk.size(); b++) {
            m2[b][3] = arrayListProduk.get(b);
        }

        for (int a = 0; a < arrayListProduk.size(); a++) {
            pro = (m2[a][3] * m2[a][3]);
            sumOfVarProduk = sumOfVarProduk + pro;
        }

                            /**/
        for (int a = 0; a < arrayListBiaya.size(); a++) {
            m3[a][0] = (float) (m2[a][0] / Math.sqrt(sumOfVarBiaya));
            array1m3.add(m3[a][0]);
            sqrt1m3.add((float) Math.sqrt(sumOfVarBiaya));
        }

        for (int b = 0; b < arrayListJarak.size(); b++) {
            m3[b][1] = (float) (m2[b][1] / Math.sqrt(sumOfVarJarak));
            array2m3.add(m3[b][1]);
            sqrt2m3.add((float) Math.sqrt(sumOfVarJarak));
        }

        for (int c = 0; c < arrayListJumlah.size(); c++) {
            m3[c][2] = (float) (m2[c][2] / Math.sqrt(sumOfVarJumlah));
            array3m3.add(m3[c][2]);
            sqrt3m3.add((float) Math.sqrt(sumOfVarJumlah));
        }

        for (int c = 0; c < arrayListProduk.size(); c++) {
            m3[c][3] = (float) (m2[c][3] / Math.sqrt(sumOfVarProduk));
            array4m3.add(m3[c][3]);
            sqrt4m3.add((float) Math.sqrt(sumOfVarProduk));
        }
    }

    private void createTableAlternatif() {
        TableLayout tableAlt = (TableLayout) rootView.findViewById(R.id.tableLayoutAlternatif);
        tableAlt.removeAllViews();
        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        list_alternatif.clear();
        linearAlternatif.removeAllViews();
        for (int a = 0; a < list.size(); a++) {
            list_alternatif.add("A" + (a + 1));
            tvAlt = new TextView(getActivity());
            tvAlt.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tvAlt.setText(list_alternatif.get(a) + " = " + list.get(a));
            tvAlt.setTextSize(13);
            tvAlt.setTextColor(Color.BLACK);
            linearAlternatif.addView(tvAlt);

        }

        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("" + list_alternatif.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(5, 5, 5, 5);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);

        }
        tableAlt.addView(row, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    private void createTableKriteria() {
        TableLayout tableKriteria = (TableLayout) rootView.findViewById(R.id.tableLayoutKriteria);

        tableKriteria.removeAllViews();

        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 1; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("Jarak");
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);

            TextView tv2 = new TextView(getActivity());
            tv2.setText("Biaya");
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);


            TextView tv3 = new TextView(getActivity());
            tv3.setText("Popularitas");
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("Produk");
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
        }
        tableKriteria.addView(row, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

    }

    private void createTableOptimasi() {
        TableLayout tableOptimasi = (TableLayout) rootView.findViewById(R.id.tableLayoutOptimasi);

        tableOptimasi.removeAllViews();

        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 1; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("Min");
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);

            TextView tv2 = new TextView(getActivity());
            tv2.setText("Min");
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("Max");
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("Max");
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);

        }
        tableOptimasi.addView(row, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

    }

    private void createTableCostBenefit() {
        TableLayout tableOptimasi = (TableLayout) rootView.findViewById(R.id.tableLayoutCostBenefit);

        tableOptimasi.removeAllViews();

        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 1; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("Cost");
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);

            TextView tv2 = new TextView(getActivity());
            tv2.setText("Cost");
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("Benefit");
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("Benefit");
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);

        }
        tableOptimasi.addView(row, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

    }

    private void createTableBobot() {
        TableLayout tableOptimasi = (TableLayout) rootView.findViewById(R.id.tableLayoutBobot);

        tableOptimasi.removeAllViews();

        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 1; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("" + w1);
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);

            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + w2);
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + w3);
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + w4);
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
        }
        tableOptimasi.addView(row, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

    }

    private void createTableHasil() {
        TableLayout tableHasil = (TableLayout) rootView.findViewById(R.id.tableLayoutHasil);

        tableHasil.removeAllViews();
        for (int i = 0; i < arrayListBiaya.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + list_alternatif.get(i));
            tv.setTextSize(13);
            tv.setBackgroundColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + hasilAkhir.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);
            tableHasil.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableSquareRoot() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutBobotAkhir);

        ll.removeAllViews();
        for (int i = 0; i < 1; i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + bobotAkhirJarak);
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + bobotAkhirBiaya);
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + bobotAkhirJumlah);
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + bobotAkhirProduk);
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);

            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableMatriksEvalua() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutEvalua);

        ll.removeAllViews();
        for (int i = 0; i < arrayListBobotJarak.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + arrayListBobotJarak.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + arrayListBobotBiaya.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + arrayListBobotJumlah.get(i));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + arrayListBobotProduk.get(i));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);

            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableMatriksEvaluasi() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutEvaluasi);

        ll.removeAllViews();
        for (int i = 0; i < arrayListBobotJarak.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + listMatriksEvaluasi.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + listMatriksEvaluasi2.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + listMatriksEvaluasi3.get(i));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + listMatriksEvaluasi4.get(i));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableMatriksProbabilitas() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutProbabilitas);

        ll.removeAllViews();
        for (int i = 0; i < arrayListBobotJarak.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + listProbabilitas.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + listProbabilitas2.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + listProbabilitas3.get(i));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + listProbabilitas4.get(i));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableMatriksEntropy() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutEntropy);

        ll.removeAllViews();
        for (int i = 0; i < arrayListBobotJarak.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + listEntropy.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + listEntropy2.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + listEntropy3.get(i));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + listEntropy4.get(i));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTableEk() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayoutEk);

        ll.removeAllViews();
        for (int i = 0; i < 1; i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + EkJarak);
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + EkBiaya);
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + EkJumlah);
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + EkProduk);
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);
            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTable1() {
        TableLayout ll = (TableLayout) rootView.findViewById(R.id.tableLayout);

        ll.removeAllViews();
        for (int i = 0; i < arrayListBiaya.size(); i++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv = new TextView(getActivity());
            tv.setText("" + arrayListJarak.get(i));
            tv.setTextSize(13);
            tv.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setPadding(10, 10, 10, 10);
            tv.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv);


            TextView tv2 = new TextView(getActivity());
            tv2.setText("" + arrayListBiaya.get(i));
            tv2.setTextSize(13);
            tv2.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv2);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + arrayListJumlah.get(i));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + arrayListProduk.get(i));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row.addView(tv4);

            ll.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTable2() {
        TableLayout ll2 = (TableLayout) rootView.findViewById(R.id.tableLayout2);
        ll2.removeAllViews();

        for (int j = 0; j < array1m3.size(); j++) {

            TableRow row2 = new TableRow(getActivity());
            row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv1 = new TextView(getActivity());
            tv1.setText("" + array2m3.get(j));
            tv1.setTextSize(13);
            tv1.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv1.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv1.setPadding(10, 10, 10, 10);
            tv1.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv1);


            TextView tv22 = new TextView(getActivity());
            tv22.setText("" + array1m3.get(j));
            tv22.setTextSize(13);
            tv22.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv22.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv22.setPadding(10, 10, 10, 10);
            tv22.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv22);

            TextView tv3 = new TextView(getActivity());
            tv3.setText("" + array3m3.get(j));
            tv3.setTextSize(13);
            tv3.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(10, 10, 10, 10);
            tv3.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv3);

            TextView tv4 = new TextView(getActivity());
            tv4.setText("" + array4m3.get(j));
            tv4.setTextSize(13);
            tv4.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(10, 10, 10, 10);
            tv4.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv4);

            ll2.addView(row2, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void createTable3() {

        TableLayout ll3 = (TableLayout) rootView.findViewById(R.id.tableLayout3);
        ll3.removeAllViews();

        TableRow rowTitle = new TableRow(getActivity());
        rowTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvTitle1 = new TextView(getActivity());
        tvTitle1.setText("Destinasi Wisata");
        tvTitle1.setTextSize(14);
        tvTitle1.setTypeface(null, Typeface.BOLD);
        tvTitle1.setGravity(Gravity.CENTER);
        tvTitle1.setTextColor(Color.parseColor("#ffffff"));
        tvTitle1.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tvTitle1.setPadding(10, 10, 10, 10);
        tvTitle1.setBackgroundResource(R.drawable.cell_shape_title);
        rowTitle.addView(tvTitle1);

        ll3.addView(rowTitle, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TableRow rowTitle2 = new TableRow(getActivity());
        rowTitle2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvTitle2 = new TextView(getActivity());
        tvTitle2.setText("Hasil Ranking");
        tvTitle2.setTextSize(14);
        tvTitle2.setTypeface(null, Typeface.BOLD);
        tvTitle2.setGravity(Gravity.CENTER);
        tvTitle2.setTextColor(Color.parseColor("#ffffff"));
        tvTitle2.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tvTitle2.setPadding(10, 10, 10, 10);
        tvTitle2.setBackgroundResource(R.drawable.cell_shape_title);
        rowTitle.addView(tvTitle2);

        ll3.addView(rowTitle2);

        Collections.sort(list_hasil, Collections.reverseOrder(HasilAkhir.StuRollno));

        for (int i = 0; i < list_hasil.size(); i++) {

            HasilAkhir hsl = list_hasil.get(i);

            TableRow row2 = new TableRow(getActivity());
            row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView tv1 = new TextView(getActivity());
            tv1.setText("" + hsl.getNamaDestinasi());
            tv1.setTextSize(14);
            tv1.setGravity(Gravity.CENTER);
            tv1.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv1.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv1.setPadding(10, 10, 10, 10);
            tv1.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv1);

            TextView tv22 = new TextView(getActivity());
            tv22.setText("" + hsl.getHasil());
            tv22.setTextSize(14);
            tv22.setGravity(Gravity.CENTER);
            tv22.setTextColor(getResources().getColor(R.color.myPrimaryColor));
            tv22.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv22.setPadding(10, 10, 10, 10);
            tv22.setBackgroundResource(R.drawable.cell_shape);
            row2.addView(tv22);
            ll3.addView(row2);
        }

        /*final HasilAkhir hsl = list_hasil.get(0);
        LayoutInflater li = LayoutInflater.from(getActivity());
        View dialogView = li.inflate(R.layout.dialog_hasil_rekomendasi, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(dialogView);
        dialog = alertDialog.create();

        TextView tvTitle = (TextView) dialogView.findViewById(R.id.namaDestinasi);
        Button btnDetail = (Button) dialogView.findViewById(R.id.btnDetail);
        destinasi_gambar = (NetworkImageView) dialogView.findViewById(R.id.gambarDestinasi);

        tvTitle.setText(hsl.getNamaDestinasi());
        destinasi_gambar
                .setImageUrl(hsl.getUrl(), imageLoader);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        DetailDestinasiWisata.class);

                intent.putExtra("id", String.valueOf(hsl.getId()));
                intent.putExtra("nama", hsl.getNamaDestinasi());
                intent.putExtra("alamat", model_destinasi.getalamat());
                intent.putExtra("gambar", hsl.getUrl());
                intent.putExtra("deskripsi", model_destinasi.getdeskripsi());
                intent.putExtra("biaya", model_destinasi.getBiaya());
                intent.putExtra("jarak", model_destinasi.getJarak());
                intent.putExtra("lat", model_destinasi.getLat());
                intent.putExtra("longi", model_destinasi.getLongi());
                intent.putExtra("url_web", model_destinasi.getUrlWeb());
                intent.putExtra("alamat_lengkap", model_destinasi.getAlamatLengkap());
                intent.putExtra("jml_wisatawan", model_destinasi.getJmlWisatawan());

                getActivity().startActivity(intent);
            }
        });

        dialog.show();
*/
    }

    private void displayLocation() {

        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();


        } else {


        }
    }

    /**
     * Creating google api client object
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getActivity(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                getActivity().finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {

    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }
}

