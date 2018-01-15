package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.MessageCounEvent;
import jd.com.jd_app_demon.bean.MessageEvent;
import jd.com.jd_app_demon.bean.SelectBean;
import jd.com.jd_app_demon.util.AddDelView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/10.
 */

public class ShopCarAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<SelectBean.DataBean> glist;
    private List<List<SelectBean.DataBean.ListBean>> clist;
    private int count;
    private OnItemClistener onItemClistener;
    private OnNumChangeLisenter onNumChangeLisenter;

    public interface OnItemClistener {
        void onitemclick(int pid);
    }

    ;

    public void setItemcllick(OnItemClistener onItemClistener) {
        this.onItemClistener = onItemClistener;
    }

    public ShopCarAdapter(Context context, List<SelectBean.DataBean> glist, List<List<SelectBean.DataBean.ListBean>> clist) {
        this.context = context;
        this.glist = glist;
        this.clist = clist;
    }

    @Override
    public int getGroupCount() {
        return glist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return clist.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return glist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return clist.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = View.inflate(context, R.layout.group_item, null);
            holder.cb = view.findViewById(R.id.cb);
            holder.tv = view.findViewById(R.id.tvName);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        SelectBean.DataBean dataBean = glist.get(groupPosition);
        holder.cb.setChecked(dataBean.isChecked());
        holder.tv.setText(dataBean.getSellerName());
        holder.cb.setOnClickListener(new GroupCbOnClickListener(groupPosition));
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = View.inflate(context, R.layout.elv_child_item, null);
            holder.cb = view.findViewById(R.id.cb);
            holder.sdv = view.findViewById(R.id.sdv);
            holder.tv = view.findViewById(R.id.tvName);
            holder.tv_price = view.findViewById(R.id.tvPrice);
            holder.adv = view.findViewById(R.id.adv);
            // holder.bt_del = view.findViewById(R.id.bt_del);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final SelectBean.DataBean.ListBean listBean = clist.get(groupPosition).get(childPosition);
        holder.cb.setChecked(listBean.isChecked());
        holder.tv.setText(listBean.getTitle());
        double price = listBean.getPrice();
        final int num = listBean.getNum();
        final int pid = listBean.getPid();
        holder.tv_price.setText("¥:" + price + "");
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        String img = split[0];

        holder.adv.setCount(num + "");
        holder.sdv.setImageURI(img);
        holder.cb.setOnClickListener(new ChildCbOnClickListener(groupPosition, childPosition));
        holder.adv.setOnAddClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = listBean.getPid();
                String countStr = holder.adv.getCount();
                int count = Integer.parseInt(countStr);
                holder.adv.setCount(++count + "");
                listBean.setNum(count);
                listBean.setChecked(true);
                if (ischildChecked(clist.get(groupPosition))) {
                    glist.get(groupPosition).setChecked(true);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                } else {
                    glist.get(groupPosition).setChecked(false);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(false);
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                }
                MessageCounEvent msgCount = new MessageCounEvent();
                msgCount.setCount(totalCount());
                msgCount.setMoney(totalMoney());
                EventBus.getDefault().post(msgCount);
                onNumChangeLisenter.setShop(glist.get(groupPosition).getSellerid(), listBean.getPid(), num, listBean.isChecked());
                notifyDataSetChanged();
            }
        });
        holder.adv.setOnDelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countStr = holder.adv.getCount();
                int count = Integer.parseInt(countStr);
                count = --count < 1 ? 1 : count;
                holder.adv.setCount(count + "");
                listBean.setNum(count);
                if (count > 1) {
                    listBean.setChecked(true);
                }
                if (ischildChecked(clist.get(groupPosition))) {
                    glist.get(groupPosition).setChecked(true);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                } else {
                    glist.get(groupPosition).setChecked(false);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(false);
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                }
                MessageCounEvent msgCount = new MessageCounEvent();
                msgCount.setCount(totalCount());
                msgCount.setMoney(totalMoney());
                EventBus.getDefault().post(msgCount);
                onNumChangeLisenter.setShop(glist.get(groupPosition).getSellerid(), listBean.getPid(), num, listBean.isChecked());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupViewHolder {
        CheckBox cb;
        TextView tv;
    }

    class ChildViewHolder {
        CheckBox cb;
        SimpleDraweeView sdv;
        TextView tv;
        TextView tv_price;
        AddDelView adv;

    }

    class GroupCbOnClickListener implements View.OnClickListener {
        private int groupposition;

        public GroupCbOnClickListener(int groupposition) {
            this.groupposition = groupposition;
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                glist.get(groupposition).setChecked(cb.isChecked());
                List<SelectBean.DataBean.ListBean> listBeans = clist.get(groupposition);
                for (SelectBean.DataBean.ListBean listBean : listBeans) {
                    listBean.setChecked(cb.isChecked());
                }
                MessageCounEvent msgCount = new MessageCounEvent();
                msgCount.setCount(totalCount());
                msgCount.setMoney(totalMoney());
                EventBus.getDefault().post(msgCount);

                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setFlag(isGroupChecked());
                EventBus.getDefault().post(messageEvent);
                notifyDataSetChanged();

            }
        }
    }

    class ChildCbOnClickListener implements View.OnClickListener {
        private int groupPosition;
        private int childPosition;

        public ChildCbOnClickListener(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                List<SelectBean.DataBean.ListBean> listBeans = clist.get(groupPosition);
                SelectBean.DataBean.ListBean listBean = listBeans.get(childPosition);
                listBean.setChecked(cb.isChecked());

                MessageCounEvent msgCount = new MessageCounEvent();
                msgCount.setCount(totalCount());
                msgCount.setMoney(totalMoney());
                EventBus.getDefault().post(msgCount);
                if (ischildChecked(listBeans)) {
                    glist.get(groupPosition).setChecked(true);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                    notifyDataSetChanged();
                } else {
                    glist.get(groupPosition).setChecked(false);
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setFlag(false);
                    messageEvent.setFlag(isGroupChecked());
                    EventBus.getDefault().post(messageEvent);
                    notifyDataSetChanged();
                }
            }
        }
    }

    //判断所有商家是否选中
    private boolean isGroupChecked() {
        for (SelectBean.DataBean group : glist) {
            if (!group.isChecked()) {
                return false;
            }
        }
        return true;
    }

    //判断商家的所有商品是否选中
    private boolean ischildChecked(List<SelectBean.DataBean.ListBean> childlist) {
        for (int i = 0; i < childlist.size(); i++) {
            SelectBean.DataBean.ListBean listBean = childlist.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    //判断全选框是否选中
    public void allchecked(boolean bool) {
        for (int i = 0; i < glist.size(); i++) {
            glist.get(i).setChecked(bool);
            for (int j = 0; j < clist.get(i).size(); j++) {
                clist.get(i).get(j).setChecked(bool);
            }
        }
        //计算选中的商品数，并发送到主界面进行显示
        MessageCounEvent msgCount = new MessageCounEvent();
        msgCount.setCount(totalCount());
        msgCount.setMoney(totalMoney());
        EventBus.getDefault().post(msgCount);
        notifyDataSetChanged();
    }

    private float totalMoney() {
        float money = 0f;
        for (int i = 0; i < glist.size(); i++) {
            for (int j = 0; j < clist.get(i).size(); j++) {
                if (clist.get(i).get(j).isChecked()) {
                    //遍历所有的商品，只要是选中状态的，就计算价格
                    int c = clist.get(i).get(j).getNum();
                    double m = clist.get(i).get(j).getPrice();
                    money += c * m;
                }
            }
        }
        return money;
    }

    private int totalCount() {
        count = 0;
        for (int i = 0; i < glist.size(); i++) {
            for (int j = 0; j < clist.get(i).size(); j++) {
                if (clist.get(i).get(j).isChecked()) {
                    //遍历所有的商品，只要是选中状态的，就加1
                    count += clist.get(i).get(j).getNum();
                }
            }
        }
        return count;
    }

    public interface OnNumChangeLisenter {
        void setShop(String sellerid, int pid, int num, boolean selected);
    }

    public void setOnNumChangeLisenter(OnNumChangeLisenter onNumChangeLisenter) {

        this.onNumChangeLisenter = onNumChangeLisenter;
    }


}
