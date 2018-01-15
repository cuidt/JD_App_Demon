package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import jd.com.jd_app_demon.R;

public class Gvadapter extends BaseAdapter {
    private List<String> list;
    private List<String> listimg;
    private Context context;

    public Gvadapter(List<String> listimg, List<String> list, Context context) {
        this.list = list;
        this.listimg = listimg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.item3,null);
        TextView gvname= (TextView) view.findViewById(R.id.gvname);
        gvname.setText(list.get(position));

        SimpleDraweeView img= (SimpleDraweeView) view.findViewById(R.id.sdv1s);
        img.setImageURI(listimg.get(position));
        //Glide.with(context).load(listimg.get(position)).into(img);

        return view;
    }
}
