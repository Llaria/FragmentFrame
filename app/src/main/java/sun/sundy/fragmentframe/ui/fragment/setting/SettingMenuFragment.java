package sun.sundy.fragmentframe.ui.fragment.setting;


import android.os.Bundle;
import android.view.View;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;

/**
 * 设置主菜单
 * @author Sundy
 * create at 2017/4/14 9:26
 */
public class SettingMenuFragment extends BaseMenuFragment {

    public static SettingMenuFragment newInstance() {
        Bundle args = new Bundle();
        SettingMenuFragment fragment = new SettingMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_setting_menu;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("设置菜单");
    }

}
