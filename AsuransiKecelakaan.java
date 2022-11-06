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

public class AsuransiKecelakaan extends AppCompatActivity {

    EditText edtNamaKCLKN, edtNoKCLKN, edtEmailKCLKN, edtTglLahirKCLKN;
    Button btnSubmitKCLKN;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asuransi_kecelakaan);

        edtNamaKCLKN = (EditText) findViewById(R.id.edtNamaKCLKN);
        edtNoKCLKN = (EditText) findViewById(R.id.edtNoKCLKN);
        edtEmailKCLKN = (EditText) findViewById(R.id.edtEmailKCLKN);
        edtTglLahirKCLKN = (EditText) findViewById(R.id.edtTglLahirKCLKN);
        btnSubmitKCLKN = (Button) findViewById(R.id.btnSubmitKCLKN);
        progressDialog = new ProgressDialog(AsuransiKecelakaan.this);

        btnSubmitKCLKN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nNama = edtNamaKCLKN.getText().toString();
                String nNo = edtNoKCLKN.getText().toString();
                String nEmail = edtEmailKCLKN.getText().toString();
                String nTglLahir = edtTglLahirKCLKN.getText().toString();

                if(nNama !="" && nNo!="" && nEmail !="" && nTglLahir !=""){
                    CreateDataToServer(nNama, nNo, nEmail, nTglLahir);

                    edtNamaKCLKN.getText().clear();
                    edtNoKCLKN.getText().clear();
                    edtEmailKCLKN.getText().clear();
                    edtTglLahirKCLKN.getText().clear();
                }
            }
        });
    }

    public void CreateDataToServer(final String nNama, final String nNo, final String nEmail, final String nTglLahir) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_INPUT_KCLKN_URL,
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
                    params.put("NamaLengkapKCLKN", nNama);
                    params.put("NoHPKCLKN", nNo);
                    params.put("EmailKCLKN", nEmail);
                    params.put("TanggalLahirKCLKN", nTglLahir);
                    return params;
                }
            };

            VolleyConnection.getInstance(AsuransiKecelakaan.this).addToRequestQue(stringRequest);

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