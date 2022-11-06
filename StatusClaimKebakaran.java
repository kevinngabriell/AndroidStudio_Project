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

public class StatusClaimKebakaran extends AppCompatActivity {

    ListView lv;
    Button btnFetch;
    EditText txtvalue;
    ArrayList<HashMap<String, String>> list_dataK;
    ProgressDialog progressDialog;
    String url_get_data = "https://kelompok4mobapp.000webhostapp.com//StatusClaimKebakaran.php";

    private static final String TAG_DATAK = "data";
    private static final String TAG_ID = "IDAsuransiKebakaran";
    private static final String TAG_NAMA = "NamaLengkapAK";
    private static final String TAG_STATUS = "StatusClaim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_claim_kebakaran);

        progressDialog = new ProgressDialog(StatusClaimKebakaran.this);
        btnFetch = (Button) findViewById(R.id.buttonfetch7);
        txtvalue = (EditText) findViewById(R.id.editText);

        list_dataK = new ArrayList<>();
        lv = findViewById(R.id.listView);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SCAKB = txtvalue.getText().toString();
                if(!SCAKB.equals("")){
                    getDataBySCAKB(SCAKB);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Silahkan Input ID Asuransi Kebakaran", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getDataBySCAKB(final String SCAKB) {
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
                                    String status = a.getString(TAG_STATUS);

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("IDAsuransiKebakaran", "ID Asuransi Kebakaran = " + id);
                                    map.put("NamaLengkapAK","Nama Lengkap = " + nama);
                                    map.put("StatusClaim", "Status Claim = " + status);

                                    list_dataK.add(map);
                                    String[] from = {"IDAsuransiKebakaran", "NamaLengkapAK", "StatusClaim"};
                                    int[] to = {R.id.test, R.id.test2, R.id.test3};

                                    ListAdapter adapter = new SimpleAdapter(
                                            StatusClaimKebakaran.this, list_dataK, R.layout.test,
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
                    params.put("IDAsuransiKebakaran", SCAKB);
                    return params;
                }
            };

            VolleyConnection.getInstance(StatusClaimKebakaran.this).addToRequestQue(stringRequest);

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





