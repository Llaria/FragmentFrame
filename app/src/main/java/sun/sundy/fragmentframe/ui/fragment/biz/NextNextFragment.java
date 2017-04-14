package sun.sundy.fragmentframe.ui.fragment.biz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextNextFragment extends BaseFragment {

    public static NextNextFragment newInstance() {
        Bundle args = new Bundle();
        NextNextFragment fragment = new NextNextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_next_next;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("业务功能第二级");
    }

}
