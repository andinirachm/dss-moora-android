package com.spk.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.spk.drawer.MainActivity;
import com.spk.drawerAdmin.MainActivityAdmin;
import com.spk.utils.AppController;
import com.spk.utils.PreferencesHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements OnClickListener {

	EditText etUsername;
	EditText etPassword;
	Button btnLogin;
	Button btnRegister;

	String username;
	String password;
    String id_user;
    String nama_user;
    String email;

	PreferencesHelper ph;
    ProgressDialog pDialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		ph = new PreferencesHelper(this);
		initComponent();
		initAction();
	}

	private void initComponent() {
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);
	}

	private void initAction() {
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btnLogin) {
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Loading...");
            pDialog.show();
            if(etUsername.getText().toString().equals("admin")){
                pDialog.dismiss();
                Intent i = new Intent(Login.this, MainActivityAdmin.class);
                startActivity(i);
                ph.savePreferences("user","admin");
                ph.savePreferences("status_login_admin","1");
            }
            else {
                loadData();
                ph.savePreferences("user","user");
            }
		}
        else if(v.getId() == R.id.btnRegister){
            finish();
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        }
	}

	public void loadData() {
		username = etUsername.getText().toString();
		password = etPassword.getText().toString();

		RequestQueue queue = Volley.newRequestQueue(Login.this);
		String url = "http://spksaw.hol.es/spk-saw/login.php";
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
							if (success.equals("0")) {
								Toast.makeText(Login.this, "Username dan password tidak cocok",
										Toast.LENGTH_LONG).show();
							} else {
                                JSONArray arr = obj.getJSONArray("login");
                                for (int i =0; i<arr.length(); i++){
                                    JSONObject obj2 = arr.getJSONObject(0);
                                    id_user = obj2.getString("id_user");
                                    username = obj2.getString("username");
                                    password = obj2.getString("password");
                                    nama_user = obj2.getString("nama_user");
                                    email = obj2.getString("email");
                                }

                                ph.savePreferences("status_login", "1");
                                ph.savePreferences("id_user", id_user);
                                ph.savePreferences("username", username);
                                ph.savePreferences("password", password);
                                ph.savePreferences("nama_user", nama_user);
                                ph.savePreferences("email", email);

                                finish();
								Intent i = new Intent(Login.this, MainActivity.class);
								startActivity(i);
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
                        pDialog.dismiss();
						Toast.makeText(Login.this, "Volley Error",
								Toast.LENGTH_SHORT).show();
					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", username);
				params.put("password", password);
				return params;

			}
		};

	      AppController.getInstance().addToRequestQueue(req);
	}
}
