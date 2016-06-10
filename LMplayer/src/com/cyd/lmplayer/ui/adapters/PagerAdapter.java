/**
 * 
 */

package com.cyd.lmplayer.ui.adapters;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.cyd.lmplayer.helpers.RefreshableFragment;

/**
 * @author 陈雅迪 
 * @function 片段页面操作，滑动页面后页面的相应更新
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    public PagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * This method update the fragments that extends the {@link RefreshableFragment} class
     */
    public void refresh() {
        for (int i = 0; i < mFragments.size(); i++) {
            if( mFragments.get(i) instanceof RefreshableFragment ) {
                ((RefreshableFragment)mFragments.get(i)).refresh();
            }
        }
    }

}
