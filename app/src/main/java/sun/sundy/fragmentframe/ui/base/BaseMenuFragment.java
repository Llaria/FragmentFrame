package sun.sundy.fragmentframe.ui.base;

import android.widget.Toast;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主界面的基类
 * Created by sundi on 2017/4/13.
 */

public abstract class BaseMenuFragment extends SupportFragment{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

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
}
