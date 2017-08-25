package com.spk.main;

import android.app.ProgressDialog;
import android.location.Location;
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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.spk.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andini17 on 10/08/2015.
 */
public class DestinasiWisata extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    com.spk.model.DestinasiWisata model_destinasi;
    View rootView;
    ListView listViewDestinasi;
    ProgressDialog pDialog;
    private SliderLayout mDemoSlider;

    ListAdapterDestinasi adapter;

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
    String produk;

    JSONArray arr;

    List<com.spk.model.DestinasiWisata> list_destinasi2 = new ArrayList<com.spk.model.DestinasiWisata>();
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
    public static String KEY_DESTINASI_PRODUK= "produk";

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    double latitude;
    double longitude;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sample, container, false);
        listViewDestinasi = (ListView) rootView.findViewById(R.id.listViewDestinasi);
        listViewDestinasi.setClickable(false);
        mDemoSlider = (SliderLayout) rootView.findViewById(R.id.slider);
        mDemoSlider.setCustomIndicator((PagerIndicator) rootView.findViewById(R.id.custom_indicator));

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        displayLocation();


        loadData();
        HashMap<String, String> file_maps = new HashMap<String, String>();
        file_maps.put("Visit Jakarta", "http://www.jakarta-tourism.go.id/sites/default/files/styles/slideshow/public/headers/header-JTCO-1600x700-2_1.jpg?itok=oghkvmT5");
        file_maps.put("Kota Jakarta", "http://www.worldpropertychannel.com/news-assets/Jakarta-Indonesia-Skyline.jpg");
        file_maps.put("Landmark Jakarta", "http://cekiwir.com/wp-content/uploads/2015/05/monas-3.jpg");
        file_maps.put("Museum Fatahillah", "http://kui.atmajaya.ac.id/wp-content/uploads/2014/06/museum-fatahillah.jpg");
        file_maps.put("Dunia Fantasi", "http://www.ciputranews.com/media/images/boni/dufan2.jpg");
        file_maps.put("Taman Mini", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRz5yiugNbA7_AKPHJmrgciqsrDrpvONqgeG8dFKxjJfciI5rxU");
        file_maps.put("Snow Bay", "http://travelspromo.com/wp-content/uploads/2014/08/SnowBay-Water-Park-TMII7001-e1407608200986.jpg");

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);


            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

        return rootView;
    }

    public void loadData() {
        String url = "http://spksaw.hol.es/spk-saw/get_all_destinasi_wisata.php?ll=" + latitude + "," + longitude;
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

                        adapter = new ListAdapterDestinasi(getActivity(), list_destinasi2);
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

