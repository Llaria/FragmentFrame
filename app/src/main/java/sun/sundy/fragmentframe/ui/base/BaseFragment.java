package sun.sundy.fragmentframe.ui.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import sun.sundy.fragmentframe.R;

/**
 * 通用Fragment基类
 * Created by sundi on 2017/4/13.
 */

public abstract class BaseFragment extends SwipeBackFragment{

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
