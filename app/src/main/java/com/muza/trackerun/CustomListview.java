package com.muza.trackerun;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.resource;

/**
 * Created by user on 12/20/2017.
 */

public class CustomListview extends ArrayAdapter<String> {

    private String[] eventname;
    private String[] desc;
    private Integer[] imgid;
    private Activity context;

    public CustomListview(Activity context, String[] eventname, String[] desc, Integer[] imgid) {
        super(context, R.layout.listview_layout, eventname);

        this.context = context;
        this.eventname = eventname;
        this.desc = desc;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder= new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) r.getTag();
        }
        viewHolder.ivw.setImageResource(imgid[position]);

        viewHolder.tvw1.setText(eventname[position]);
        viewHolder.tvw2.setText(desc[position]);


        return r;
    }
    class ViewHolder
    {
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1= (TextView) v.findViewById(R.id.tveventname);
            tvw2= (TextView) v.findViewById(R.id.tvdescription);
            ivw= (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
