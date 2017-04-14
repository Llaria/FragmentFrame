package sun.sundy.fragmentframe.ui.fragment.biz;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.event.StartBrotherEvent;
import sun.sundy.fragmentframe.ui.base.BaseFragment;

/**
 * 业务功能测试条目一
 * @author Sundy
 * create at 2017/4/14 9:42
 */
public class NextFragment extends BaseFragment {

    public static NextFragment newInstance() {
        Bundle args = new Bundle();
        NextFragment fragment = new NextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_next;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("业务功能第一级");
        Button button = (Button) view.findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StartBrotherEvent(NextNextFragment.newInstance()));
            }
        });
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);


    }
}
