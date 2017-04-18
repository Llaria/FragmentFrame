package sun.sundy.fragmentframe.ui.fragment.biz;


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

/**
 * 业务功能主菜单
 *
 * @author Sundy
 * create at 2017/4/13 16:33
 */
public class BizMenuFragment extends BaseMenuFragment {

    @Bind(R.id.biz_gridView)
    GridView bizGridView;

    List<MenuEntity> menuEntities = new ArrayList<>();

    public static BizMenuFragment newInstance() {
        Bundle args = new Bundle();
        BizMenuFragment fragment = new BizMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_biz_menu;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("业务菜单");

        menuEntities.add(new MenuEntity("[1]收货", R.mipmap.ic_launcher, NextFragment.class));
        menuEntities.add(new MenuEntity("[2]装车", R.mipmap.ic_launcher, NextFragment.class));
        menuEntities.add(new MenuEntity("[3]卸车", R.mipmap.ic_launcher, NextFragment.class));
        menuEntities.add(new MenuEntity("[4]派件", R.mipmap.ic_launcher, NextFragment.class));


        BizMenuAdapter mainMenuAdapter = new BizMenuAdapter(menuEntities, getActivity());
        bizGridView.setAdapter(mainMenuAdapter);
        /*Button button = (Button) view.findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StartBrotherEvent(NextFragment.newInstance()));
            }
        });*/
    }

    @OnItemClick(R.id.biz_gridView)
    void onItemClick(int position) {
        MenuEntity menuEntity = menuEntities.get(position);
        Class aClass = menuEntity.getmClass();
        if (null != aClass) {
            EventBus.getDefault().post(new StartBrotherEvent(NextFragment.newInstance()));
        } else {
            Toast.makeText(_mActivity, "[" + menuEntity.getName() + "]" + "暂未开发！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
