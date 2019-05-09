package com.example.win7x64.thuynhan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_lvw extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Date> arr;

    public Add_lvw(Context context, int layout, ArrayList<Date> arr) {
        this.context = context;
        this.layout = layout;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.tvpro=(TextView)convertView.findViewById(R.id.tv_pro);
            holder.tvds=(TextView)convertView.findViewById(R.id.tv_ds);
            holder.tvdf=(TextView)convertView.findViewById(R.id.tv_df);
            holder.btnup=(Button)convertView.findViewById(R.id.btn_up);
            holder.btnde=(Button)convertView.findViewById(R.id.btn_de);
            convertView.setTag(holder);

        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tvpro.setText(arr.get(position).getProjectname());
        holder.tvds.setText(arr.get(position).getDatestart());
        holder.tvdf.setText(arr.get(position).getDatefinish());
        holder.btnup.setTag(position);
        holder.btnde.setTag(position);
        holder.btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Them_Activity.class);
                intent.putExtra("sua",arr.get(position));
                ((Activity)context).startActivityForResult(intent, Listview_show.Goimasua);
            }
        });
        holder.btnde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confimdelete(arr.get(position).getProjectname(),arr.get(position).getId());
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView tvpro,tvds,tvdf;
        Button btnup,btnde;
    }
    private void confimdelete(String name, final int id){
        final String url="http://5caca7e401a0b80014dccf9d.mockapi.io/hpm/h1/API-CRUD";
        AlertDialog.Builder di=new AlertDialog.Builder(context);
        di.setTitle("Thông báo nha :v");
        di.setMessage("Cưng muốn xóa "+name+" không zợ ?");
        di.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDate(url,id);
            }
        });
        di.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Không xóa là đúng đó cưng à :v", Toast.LENGTH_SHORT).show();
            }
        });
        di.show();
    }
    private void deleteDate(String url,int id){
        RequestQueue re= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.DELETE, url+"/"+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Delete sucessfully", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context,Listview_show.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        re.add(stringRequest);
    }

}
