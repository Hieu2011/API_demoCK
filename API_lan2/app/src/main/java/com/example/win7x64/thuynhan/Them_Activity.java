package com.example.win7x64.thuynhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Them_Activity extends AppCompatActivity {
    EditText edtpro,edtds,edtdf;
    Button btnadd,btncan;
    TextView tvtt;
    String url="http://5caca7e401a0b80014dccf9d.mockapi.io/hpm/h1/API-CRUD";
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_);
        anhxa();
        Intent intent=getIntent();
        if(intent.getSerializableExtra("sua")!=null){
            Date da=(Date)intent.getSerializableExtra("sua");
            id=da.getId();
            edtpro.setText(da.getProjectname());
            edtds.setText(da.getDatestart());
            edtdf.setText(da.getDatefinish());
            btnadd.setText("Update");
            tvtt.setText("Update Date");


        }
        else {
            btnadd.setText("Add");
        }
    }
    void anhxa(){
        edtpro=(EditText)findViewById(R.id.edt_pro);
        edtds=(EditText)findViewById(R.id.edt_ds);
        edtdf=(EditText)findViewById(R.id.edt_df);
        btnadd=(Button)findViewById(R.id.btn_add);
        btncan=(Button)findViewById(R.id.btn_can);
        tvtt=(TextView)findViewById(R.id.tv_tt);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnadd.getText().equals("Add")){
                    addDate(url);
                }
                else {
                    upDate(url);
                }
            }
        });
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void addDate(String url){
        RequestQueue re= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Them_Activity.this, "Add sucessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Them_Activity.this,Listview_show.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Them_Activity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> par=new HashMap<>();
                par.put("Project",edtpro.getText().toString().trim());
                par.put("Date",edtds.getText().toString().trim());
                par.put("FinishDate",edtdf.getText().toString().trim());

                return par;
            }
        };
        re.add(stringRequest);
    }
    private void upDate(String url){
        RequestQueue re= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.PUT, url+"/"+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Them_Activity.this, "Update sucessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Them_Activity.this,Listview_show.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Them_Activity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> par=new HashMap<>();
                par.put("Project",edtpro.getText().toString().trim());
                par.put("Date",edtds.getText().toString().trim());
                par.put("FinishDate",edtdf.getText().toString().trim());

                return par;
            }
        };
        re.add(stringRequest);
    }
}
