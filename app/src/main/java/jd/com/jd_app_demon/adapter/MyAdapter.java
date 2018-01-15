package jd.com.jd_app_demon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String titles[] = {"商品","详情","评价",};

    public MyAdapter(FragmentManager fm , List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    /**
     * 返回ViewPager的当前页
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回当成ViewPager的页卡数量
     * @return
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }


    /**
     * 导航条上显示的标题栏字符串
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}