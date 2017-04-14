package sun.sundy.fragmentframe.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.event.StartBrotherEvent;
import sun.sundy.fragmentframe.utils.myFragment.RxSupportFragment;

/**
 * 主界面的基类
 * Created by sundi on 2017/4/13.
 */

public abstract class BaseMenuFragment extends RxSupportFragment {

    private Toolbar mToolbar;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setInflaterView(), container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        EventBus.getDefault().register(this);
        initView(view,savedInstanceState);
        return view;
    }

    /**
     * 设置title
     */
    protected void setTitleName(String title) {
        try {
            if (mToolbar != null) {
                mToolbar.setTitle(title /*+ "[" + preference.getUserName() + "]"*/);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int setInflaterView();
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 处理回退事件
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出！", Toast.LENGTH_SHORT).show();
        }
        return true;
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
    }
}
