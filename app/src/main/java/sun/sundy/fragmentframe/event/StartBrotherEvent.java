package sun.sundy.fragmentframe.event;

import sun.sundy.fragmentframe.utils.myFragment.RxSupportFragment;

/**
 * 启动同级Fragment
 * @author Sundy
 * create at 2017/4/14 9:44
 */
public class StartBrotherEvent {
    public RxSupportFragment targetFragment;

    public StartBrotherEvent(RxSupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
