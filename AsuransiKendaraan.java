package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AsuransiKendaraan extends AppCompatActivity {

    EditText namaKND, noKND, emailKND, tipeMobilKND, tahunKendaraanKND;
    Button btnSubmitKND;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asuransi_kendaraan);

        namaKND = (EditText) findViewById(R.id.edtNamaKND);
        noKND = (EditText) findViewById(R.id.edtNoKND);
        emailKND = (EditText) findViewById(R.id.edtEmailKND);
        tipeMobilKND = (EditText) findViewById(R.id.edtTipeMobilKND);
        tahunKendaraanKND = (EditText) findViewById(R.id.edtTahunKND);
        btnSubmitKND = (Button) findViewById(R.id.btnSubmitKND);
        progressDialog = new ProgressDialog(AsuransiKendaraan.this);

        btnSubmitKND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kNama = namaKND.getText().toString();
                String kNomor = noKND.getText().toString();
                String kEmailKND = emailKND.getText().toString();
                String kTipeMobil = tipeMobilKND.getText().toString();
                String kTahunMobil = tahunKendaraanKND.getText().toString();

                if (kNama != "" && kNomor != "" && kEmailKND != "" && kTipeMobil != "" && kTahunMobil != "") {
                    CreateDataToServer(kNama, kNomor, kEmailKND, kTipeMobil, kTahunMobil);

                    namaKND.getText().clear();
                    noKND.getText().clear();
                    emailKND.getText().clear();
                    tipeMobilKND.getText().clear();
                    tahunKendaraanKND.getText().clear();
                }
            }
        });
    }

    public void CreateDataToServer(final String kNama, final String kNomor, final String kEmailKND, String kTipeMobil, String kTahunMobil) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_INPUT_KND_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Pengajuan Polis Berhasil! Agen kami akan segera menghubungi anda!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("NamaLengkapKND", kNama);
                    params.put("NoHPKND", kNomor);
                    params.put("EmailKND", kEmailKND);
                    params.put("TipeMobilKND", kTipeMobil);
                    params.put("TahunKendaraanKND", kTahunMobil);
                    return params;
                }
            };

            VolleyConnection.getInstance(AsuransiKendaraan.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}