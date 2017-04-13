package sun.sundy.fragmentframe.ui.fragment.biz;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseFragment;

/**
 * 业务功能主菜单
 * @author Sundy
 * create at 2017/4/13 16:33
 */
public class BizMenuFragment extends BaseFragment {

    private Toolbar mToolbar;

    public static BizMenuFragment newInstance() {
        Bundle args = new Bundle();
        BizMenuFragment fragment = new BizMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biz_menu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("业务功能");
        initToolbarNav(mToolbar);
    }


}
