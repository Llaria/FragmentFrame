package sun.sundy.fragmentframe.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.event.StartBrotherEvent;
import sun.sundy.fragmentframe.utils.myFragment.SwipeBackFragment;

/**
 * 通用Fragment基类
 * Created by sundi on 2017/4/13.
 */

public abstract class BaseFragment extends SwipeBackFragment {

    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setInflaterView(), container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);
        initView(view,savedInstanceState);
        return attachToSwipeBack(view);
    }

    /**
     * 设置title
     */
    protected void setTitleName(String title) {
        try {
            if (mToolbar != null) {
                mToolbar.setTitle(title /*+ "[" + preference.getUserName() + "]"*/);
                initToolbarNav(mToolbar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int setInflaterView();
    protected abstract void initView(View view, Bundle savedInstanceState);

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    /**
     * 启动同级Fragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }
}
