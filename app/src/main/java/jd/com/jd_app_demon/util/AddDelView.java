package jd.com.jd_app_demon.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import jd.com.jd_app_demon.R;

public class AddDelView extends LinearLayout {

    private TextView add;
    private TextView del;
    private TextView num;
    private OnItemClick onItemClick;

    public interface OnItemClick {
        public void onItemAddClick(int count);

        public void onItemDelClick(int count);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public AddDelView(Context context) {
        this(context,null);
    }

    public AddDelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.add_del_view, this);
        add = view.findViewById(R.id.add);
        del = view.findViewById(R.id.del);
        num = view.findViewById(R.id.num);
}


    public void setOnAddClickListener(OnClickListener onClickListener) {
        add.setOnClickListener(onClickListener);
    }

    public void setOnDelClickListener(OnClickListener onClickListener) {
        del.setOnClickListener(onClickListener);
    }

    public void setCount(String count) {
        num.setText(count);
    }

    public String getCount(){
        return num.getText().toString().trim();
    }
}