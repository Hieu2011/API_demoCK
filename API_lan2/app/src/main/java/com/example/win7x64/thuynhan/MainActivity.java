package com.example.win7x64.thuynhan;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText edtus,edtpa;
    Button btnlogin;
    String url="http://5caca7e401a0b80014dccf9d.mockapi.io/hpm/h1/Dangnhap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtus=(EditText)findViewById(R.id.edt_us);
        edtpa=(EditText)findViewById(R.id.edt_pa);
        btnlogin=(Button)findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chedkus();
            }
        });
    }
    void chedkus(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=(JSONObject)response.get(i);
                        if(jsonObject.getString("user").toString().equals(edtus.getText().toString())
                                && jsonObject.getString("password").toString().equals(edtpa.getText().toString())){
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Listview_show.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User hoặc Pass không trùng khớp", Toast.LENGTH_SHORT).show();
                            edtus.setText("");
                            edtpa.setText("");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Faill Login", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue re= Volley.newRequestQueue(this);
        re.add(jsonArrayRequest);
    }
}
