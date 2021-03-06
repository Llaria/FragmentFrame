package sun.sundy.fragmentframe.ui.fragment.setting;


import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;
import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.entity.MenuEntity;
import sun.sundy.fragmentframe.event.StartBrotherEvent;
import sun.sundy.fragmentframe.ui.adapter.BizMenuAdapter;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;
import sun.sundy.fragmentframe.ui.fragment.biz.NextFragment;
import sun.sundy.fragmentframe.ui.fragment.biz.NextNextFragment;
import sun.sundy.fragmentframe.utils.myFragment.RxSupportFragment;

/**
 * 设置主菜单
 * @author Sundy
 * create at 2017/4/14 9:26
 */
public class SettingMenuFragment extends BaseMenuFragment {

    @Bind(R.id.setting_gridView)
    GridView settingGridView;

    List<MenuEntity> menuEntities = new ArrayList<>();

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

        menuEntities.add(new MenuEntity("[1]业务设置", R.mipmap.ic_launcher, NextNextFragment.newInstance()));
        menuEntities.add(new MenuEntity("[2]登陆设置", R.mipmap.ic_launcher, NextFragment.newInstance()));
        menuEntities.add(new MenuEntity("[3]系统设置", R.mipmap.ic_launcher, NextFragment.newInstance()));

        BizMenuAdapter mainMenuAdapter = new BizMenuAdapter(menuEntities, getActivity());
        settingGridView.setAdapter(mainMenuAdapter);
    }

    @OnItemClick(R.id.setting_gridView)
    void onItemClick(int position) {
        MenuEntity menuEntity = menuEntities.get(position);
        RxSupportFragment aClass = menuEntity.getmClass();
        if (null != aClass) {
            EventBus.getDefault().post(new StartBrotherEvent(aClass));
        } else {
            Toast.makeText(_mActivity, "[" + menuEntity.getName() + "]" + "暂未开发！", Toast.LENGTH_SHORT).show();
        }

    }

}
