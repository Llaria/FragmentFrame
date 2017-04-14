package sun.sundy.fragmentframe.ui.activity;

import android.os.Bundle;
import android.util.Log;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.fragment.MenuFragment;
import sun.sundy.fragmentframe.utils.myFragment.FragmentLifecycleCallbacks;
import sun.sundy.fragmentframe.utils.myFragment.RxSupportActivity;
import sun.sundy.fragmentframe.utils.myFragment.RxSupportFragment;

public class MenuActivity extends RxSupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.root_container, MenuFragment.newInstance());
        }

        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(RxSupportFragment fragment) {
                Log.i("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }

            @Override
            public void onFragmentCreated(RxSupportFragment fragment, Bundle savedInstanceState) {
                super.onFragmentCreated(fragment, savedInstanceState);
            }
            // 省略其余生命周期方法
        });
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
//        return new DefaultHorizontalAnimator();
//        return super.onCreateFragmentAnimator();
    }


}
