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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cekPolisKecelakaan extends AppCompatActivity {

    ListView lv;
    Button btnFetch;
    EditText txtvalue;
    ArrayList<HashMap<String, String>> list_dataK;
    ProgressDialog progressDialog;
    String url_get_data = "https://kelompok4mobapp.000webhostapp.com//cekAsuransiKecelakaan.php";

    private static final String TAG_DATAK = "data";
    private static final String TAG_ID = "IDAsuransiKecelakaan";
    private static final String TAG_NAMA = "NamaLengkapKCLKN";
    private static final String TAG_EMAIL = "EmailKCLKN";
    private static final String TAG_NOHP = "NoHPKCLKN";
    private static final String TAG_TGL = "TanggalLahirKCLKN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_polis_kecelakaan);

        progressDialog = new ProgressDialog(cekPolisKecelakaan.this);
        btnFetch = (Button) findViewById(R.id.buttonfetch2);
        txtvalue = (EditText) findViewById(R.id.editText);

        list_dataK = new ArrayList<>();
        lv = findViewById(R.id.listView);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDKCLKN = txtvalue.getText().toString();
                if(!IDKCLKN.equals("")){
                    getDataByIDKCLKN(IDKCLKN);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Silahkan Input ID Asuransi Kebakaran", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getDataByIDKCLKN(final String IDKCLKN) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_data,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray(TAG_DATAK);
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject a = result.getJSONObject(i);
                                    int id = a.getInt(TAG_ID);
                                    String nama = a.getString(TAG_NAMA);
                                    String nohp = a.getString(TAG_NOHP);
                                    String email = a.getString(TAG_EMAIL);
                                    String tgllahir = a.getString(TAG_TGL);

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("IDAsuransiKecelakaan", "ID Asuransi Kecelakaan = " + id);
                                    map.put("NamaLengkapKCLKN","Nama Lengkap = " + nama);
                                    map.put("NoHPKCLKN", "NomorHP = " + nohp);
                                    map.put("EmailKCLKN", "Email = " + email);
                                    map.put("TanggalLahirKCLKN", "Tanggal Lahir = " + tgllahir);

                                    list_dataK.add(map);
                                    String[] from = {"IDAsuransiKecelakaan", "NamaLengkapKCLKN", "NoHPKCLKN", "EmailKCLKN", "TanggalLahirKCLKN"};
                                    int[] to = {R.id.edtID, R.id.edtNama, R.id.edtNOHP, R.id.edtEmail, R.id.edtTglLahirKCLKN};

                                    ListAdapter adapter = new SimpleAdapter(
                                            cekPolisKecelakaan.this, list_dataK, R.layout.list_data_kclkn,
                                            from, to);
                                    lv.setAdapter(adapter);
                                }
                            }
                            catch (JSONException e) {
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
                    params.put("IDAsuransiKecelakaan", IDKCLKN);
                    return params;
                }
            };

            VolleyConnection.getInstance(cekPolisKecelakaan.this).addToRequestQue(stringRequest);

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





