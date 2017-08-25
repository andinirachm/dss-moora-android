package com.spk.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.spk.utils.AppController;
import com.spk.utils.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Andini17 on 10/08/2015.
 */

public class Konfigurasi extends Fragment {

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

    EditText etJarakSangatDekat2;
    EditText etJarakDekat1;
    EditText etJarakDekat2;
    EditText etJarakSedang1;
    EditText etJarakSedang2;
    EditText etJarakJauh1;
    EditText etJarakJauh2;
    EditText etJarakSangatJauh2;

    EditText etBiayaSangatMurah2;
    EditText etBiayaMurah1;
    EditText etBiayaMurah2;
    EditText etBiayaSedang1;
    EditText etBiayaSedang2;
    EditText etBiayaMahal1;
    EditText etBiayaMahal2;
    EditText etBiayaSangatMahal2;

    EditText etPopSangatRendah2;
    EditText etPopRendah1;
    EditText etPopRendah2;
    EditText etPopSedang1;
    EditText etPopSedang2;
    EditText etPopTinggi1;
    EditText etPopTinggi2;
    EditText etPopSangatTinggi2;

    EditText etProSangatSedikit2;
    EditText etProSedikit1;
    EditText etProSedikit2;
    EditText etProSedang1;
    EditText etProSedang2;
    EditText etProBanyak1;
    EditText etProBanyak2;
    EditText etProSangatBanyak2;

    Button btnSimpan;
    PreferencesHelper ph;
    ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_konfigurasi, container, false);

        etJarakSangatDekat2 = (EditText) rootView.findViewById(R.id.etJarakSangatDekat2);
        etJarakDekat1 = (EditText) rootView.findViewById(R.id.etJarakDekat1);
        etJarakDekat2 = (EditText) rootView.findViewById(R.id.etJarakDekat2);
        etJarakSedang1 = (EditText) rootView.findViewById(R.id.etJarakSedang1);
        etJarakSedang2 = (EditText) rootView.findViewById(R.id.etJarakSedang2);
        etJarakJauh1 = (EditText) rootView.findViewById(R.id.etJarakJauh1);
        etJarakJauh2 = (EditText) rootView.findViewById(R.id.etJarakJauh2);
        etJarakSangatJauh2 = (EditText) rootView.findViewById(R.id.etJarakSangatJauh2);

        etBiayaSangatMurah2 = (EditText) rootView.findViewById(R.id.etBiayaSangatMurah2);
        etBiayaMurah1 = (EditText) rootView.findViewById(R.id.etBiayaMurah1);
        etBiayaMurah2 = (EditText) rootView.findViewById(R.id.etBiayaMurah2);
        etBiayaSedang1 = (EditText) rootView.findViewById(R.id.etBiayaSedang1);
        etBiayaSedang2 = (EditText) rootView.findViewById(R.id.etBiayaSedang2);
        etBiayaMahal1 = (EditText) rootView.findViewById(R.id.etBiayaMahal1);
        etBiayaMahal2 = (EditText) rootView.findViewById(R.id.etBiayaMahal2);
        etBiayaSangatMahal2 = (EditText) rootView.findViewById(R.id.etBiayaSangatMahal2);

        etPopSangatRendah2 = (EditText) rootView.findViewById(R.id.etPopSangatRendah2);
        etPopRendah1 = (EditText) rootView.findViewById(R.id.etPopRendah1);
        etPopRendah2 = (EditText) rootView.findViewById(R.id.etPopRendah2);
        etPopSedang1 = (EditText) rootView.findViewById(R.id.etPopSedang1);
        etPopSedang2 = (EditText) rootView.findViewById(R.id.etPopSedang2);
        etPopTinggi1 = (EditText) rootView.findViewById(R.id.etPopTinggi1);
        etPopTinggi2 = (EditText) rootView.findViewById(R.id.etPopTinggi2);
        etPopSangatTinggi2 = (EditText) rootView.findViewById(R.id.etPopSangatTinggi2);


        etProSangatSedikit2 = (EditText) rootView.findViewById(R.id.etProSangatSedikit2);
        etProSedikit1 = (EditText) rootView.findViewById(R.id.etProSedikit1);
        etProSedikit2 = (EditText) rootView.findViewById(R.id.etProSedikit2);
        etProSedang1 = (EditText) rootView.findViewById(R.id.etProSedang1);
        etProSedang2 = (EditText) rootView.findViewById(R.id.etProSedang2);
        etProBanyak1 = (EditText) rootView.findViewById(R.id.etProBanyak1);
        etProBanyak2 = (EditText) rootView.findViewById(R.id.etProBanyak2);
        etProSangatBanyak2 = (EditText) rootView.findViewById(R.id.etProSangatBanyak2);


        btnSimpan = (Button) rootView.findViewById(R.id.btnSimpan);

        pDialog = new ProgressDialog(getActivity());

        loadData();

        ph = new PreferencesHelper(getActivity());
        if (ph.getPreferences("user").equals("user")) {
            btnSimpan.setVisibility(View.GONE);
        } else {
            btnSimpan.setVisibility(View.VISIBLE);
        }
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return rootView;
    }

    public void loadData() {
        String url = "http://spksaw.hol.es/spk-saw/get_all_configuration.php";
        StringRequest req = new StringRequest(
                com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray arr = obj.getJSONArray("konfigurasi");
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

                        etJarakSangatDekat2.setText(jarakSangatDekat2);
                        etJarakDekat1.setText(jarakDekat1);
                        etJarakDekat2.setText(jarakDekat2);
                        etJarakSedang1.setText(jarakSedang1);
                        etJarakSedang2.setText(jarakSedang2);
                        etJarakJauh1.setText(jarakJauh1);
                        etJarakJauh2.setText(jarakJauh2);
                        etJarakSangatJauh2.setText(jarakSangatJauh2);

                        etBiayaSangatMurah2.setText(biayaSangatMurah2);
                        etBiayaMurah1.setText(biayaMurah1);
                        etBiayaMurah2.setText(biayaMurah2);
                        etBiayaSedang1.setText(biayaSedang1);
                        etBiayaSedang2.setText(biayaSedang2);
                        etBiayaMahal1.setText(biayaMahal1);
                        etBiayaMahal2.setText(biayaMahal2);
                        etBiayaSangatMahal2.setText(biayaSangatMahal2);

                        etPopSangatRendah2.setText(popularitasSangatRendah2);
                        etPopRendah1.setText(popularitasRendah1);
                        etPopRendah2.setText(popularitasRendah2);
                        etPopSedang1.setText(popularitasSedang1);
                        etPopSedang2.setText(popularitasSedang2);
                        etPopTinggi1.setText(popularitasTinggi1);
                        etPopTinggi2.setText(popularitasTinggi2);
                        etPopSangatTinggi2.setText(popularitasSangatTinggi2);

                        etProSangatSedikit2.setText(produkSangatSedikit2);
                        etProSedikit1.setText(produkSedikit1);
                        etProSedikit2.setText(produkSedikit2);
                        etProSedang1.setText(produkSedang1);
                        etProSedang2.setText(produkSedang2);
                        etProBanyak1.setText(produkBanyak1);
                        etProBanyak2.setText(produkBanyak2);
                        etProSangatBanyak2.setText(produkSangatBanyak2);

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

