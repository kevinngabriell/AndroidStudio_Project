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

public class AsuransiKebakaran extends AppCompatActivity {

    EditText edtNamaBKR, edtLocBKR, edtEmailBKR,edtNoBKR, edtHargaPBKR;
    Button btnSubmitBKR;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asuransi_kebakaran);

        edtNamaBKR = (EditText) findViewById (R.id.edtNamaBKR);
        edtLocBKR = (EditText) findViewById (R.id.edtLocBKR);
        edtEmailBKR = (EditText) findViewById (R.id.edtEmailBKR);
        edtNoBKR = (EditText) findViewById (R.id.edtNoPBKR);
        edtHargaPBKR = (EditText) findViewById(R.id.edtHargaPBKR);
        btnSubmitBKR = (Button) findViewById (R.id.btnSubmitBKR);
        progressDialog = new ProgressDialog(AsuransiKebakaran.this);

        btnSubmitBKR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNama = edtNamaBKR.getText().toString();
                String sLoc = edtLocBKR.getText().toString();
                String sEmailBKR = edtEmailBKR.getText().toString();
                String sHarga = edtHargaPBKR.getText().toString();
                String sNo = edtNoBKR.getText().toString();

                if(sNama !="" && sLoc !="" && sEmailBKR !="" && sHarga !="" && sNo !=""){
                    CreateDataToServer(sNama, sNo, sEmailBKR, sLoc, sHarga);

                    edtNamaBKR.getText().clear();
                    edtLocBKR.getText().clear();
                    edtEmailBKR.getText().clear();
                    edtHargaPBKR.getText().clear();
                    edtNoBKR.getText().clear();
                }
            }
        });
    }

    public void CreateDataToServer(final String sNama, final String sNo, final String sEmailBKR, final String sLoc, final String sHarga) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_INPUT_AKB_URL,
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
                    params.put("NamaLengkapAK", sNama);
                    params.put("NoHPAK", sNo);
                    params.put("EmailAK", sEmailBKR);
                    params.put("LokasiPropertiAK", sLoc);
                    params.put("HargaPropertiAK", sHarga);
                    return params;
                }
            };

            VolleyConnection.getInstance(AsuransiKebakaran.this).addToRequestQue(stringRequest);

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