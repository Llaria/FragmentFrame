package sun.sundy.fragmentframe.ui.fragment.biz;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.event.StartBrotherEvent;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;

/**
 * 业务功能主菜单
 * @author Sundy
 * create at 2017/4/13 16:33
 */
public class BizMenuFragment extends BaseMenuFragment {

    public static BizMenuFragment newInstance() {
        Bundle args = new Bundle();
        BizMenuFragment fragment = new BizMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setInflaterView() {
        return R.layout.fragment_biz_menu;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setTitleName("业务菜单");
        Button button = (Button) view.findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StartBrotherEvent(NextFragment.newInstance()));
            }
        });
    }
}
