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
import com.spk.utils.AppController;
import com.spk.utils.PreferencesHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements OnClickListener {

    EditText etNama;
    EditText etEmail;
	EditText etUsername;
	EditText etPassword;
	Button btnRegister;

	String username;
	String password;
    String nama_user;
    String email;

	PreferencesHelper ph;
    ProgressDialog pDialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		ph = new PreferencesHelper(this);
		initComponent();
		initAction();
	}

	private void initComponent() {
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
	}

	private void initAction() {
		btnRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btnRegister) {
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Loading...");
            pDialog.show();
			loadData();
		}
	}

	public void loadData() {
        nama_user = etNama.getText().toString();
        email = etEmail.getText().toString();
		username = etUsername.getText().toString();
		password = etPassword.getText().toString();

		RequestQueue queue = Volley.newRequestQueue(Register.this);
		String url = "http://spksaw.hol.es/spk-saw/register.php";
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
								Toast.makeText(Register.this, "Gagal register",
										Toast.LENGTH_LONG).show();
							} else {
                                finish();
								Intent i = new Intent(Register.this, Login.class);
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
						Toast.makeText(Register.this, "Volley Error",
								Toast.LENGTH_SHORT).show();
					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", username);
                params.put("email", email);
                params.put("nama", nama_user);
				params.put("password", password);
				return params;

			}
		};

	      AppController.getInstance().addToRequestQueue(req);
	}
}
