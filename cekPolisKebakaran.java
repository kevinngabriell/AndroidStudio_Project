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

public class cekPolisKebakaran extends AppCompatActivity {

    ListView lv;
    Button btnFetch;
    EditText txtvalue;
    ArrayList<HashMap<String, String>> list_dataK;
    ProgressDialog progressDialog;
    String url_get_data = "https://kelompok4mobapp.000webhostapp.com//cekAsuransiKebakaran.php";

    private static final String TAG_DATAK = "data";
    private static final String TAG_ID = "IDAsuransiKebakaran";
    private static final String TAG_NAMA = "NamaLengkapAK";
    private static final String TAG_EMAIL = "EmailAK";
    private static final String TAG_NOHP = "NoHPAK";
    private static final String TAG_LOKASI = "LokasiPropertiAK";
    private static final String TAG_HARGA = "HargaPropertiAK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_polis_kebakaran);

        progressDialog = new ProgressDialog(cekPolisKebakaran.this);
        btnFetch = (Button) findViewById(R.id.buttonfetch);
        txtvalue = (EditText) findViewById(R.id.editText);

        list_dataK = new ArrayList<>();
        lv = findViewById(R.id.listView);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDAKB = txtvalue.getText().toString();
                if(!IDAKB.equals("")){
                    getDataByIDAKB(IDAKB);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Silahkan Input ID Asuransi Kebakaran", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getDataByIDAKB(final String IDAKB) {
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
                                    String lokasi = a.getString(TAG_LOKASI);
                                    String harga = a.getString(TAG_HARGA);

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("IDAsuransiKebakaran", "ID Asuransi Kebakaran = " + id);
                                    map.put("NamaLengkapAK","Nama Lengkap = " + nama);
                                    map.put("NoHPAK", "NomorHP = " + nohp);
                                    map.put("EmailAK", "Email = " + email);
                                    map.put("LokasiPropertiAK", "Lokasi Properti = " + lokasi);
                                    map.put("HargaPropertiAK", "Harga Properti = " + harga);

                                    list_dataK.add(map);
                                    String[] from = {"IDAsuransiKebakaran", "NamaLengkapAK", "NoHPAK", "EmailAK", "LokasiPropertiAK", "HargaPropertiAK"};
                                    int[] to = {R.id.edtID, R.id.edtNama, R.id.edtNOHP, R.id.edtEmail, R.id.edtLokasi, R.id.edtHarga};

                                    ListAdapter adapter = new SimpleAdapter(
                                            cekPolisKebakaran.this, list_dataK, R.layout.list_data,
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
                    params.put("IDAsuransiKebakaran", IDAKB);
                    return params;
                }
            };

            VolleyConnection.getInstance(cekPolisKebakaran.this).addToRequestQue(stringRequest);

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





