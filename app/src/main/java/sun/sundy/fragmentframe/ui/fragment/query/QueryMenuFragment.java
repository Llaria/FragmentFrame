package sun.sundy.fragmentframe.ui.fragment.query;


import android.os.Bundle;
import android.view.View;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;

/**
 * 查询主菜单
 * @author Sundy
 * create at 2017/4/14 9:26
 */
public class QueryMenuFragment extends BaseMenuFragment {

    public static QueryMenuFragment newInstance() {
        Bundle args = new Bundle();
        QueryMenuFragment fragment = new QueryMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_query_menu;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("查询菜单");
    }

}
