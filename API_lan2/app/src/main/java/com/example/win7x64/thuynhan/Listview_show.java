package com.example.win7x64.thuynhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listview_show extends AppCompatActivity {
    ListView lvwds;
    ArrayList<Date>arr;
    Add_lvw add;
    String url="http://5caca7e401a0b80014dccf9d.mockapi.io/hpm/h1/API-CRUD";
    public static final int Goimasua=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_show);
        anhxa();
        getdata(url);
    }
    void anhxa(){
        lvwds=(ListView)findViewById(R.id.lvw_ds);
        arr=new ArrayList<Date>();
        add=new Add_lvw(this,R.layout.customer_lvw,arr);
        lvwds.setAdapter(add);
    }
    private void getdata(String url){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jo=response.getJSONObject(i);
                        arr.add(new Date(
                                jo.getInt("id"),
                                jo.getString("Project"),
                                jo.getString("Date"),
                                jo.getString("FinishDate")
                        ));
                        add.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_add:{
                startActivity(new Intent(Listview_show.this,Them_Activity.class));
                break;
            }
            case R.id.mnu_logout:{
                startActivity(new Intent(Listview_show.this,MainActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
