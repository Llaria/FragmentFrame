package sun.sundy.fragmentframe.view;

import android.content.Context;
import android.support.design.internal.BottomNavigationMenuView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 底部标签栏TAB，获取了焦点
 * Created by sundi on 2017/4/13.
 */

public class BottomTabMenuView extends BottomNavigationMenuView {

    public BottomTabMenuView(Context context) {
        super(context);
    }

    public BottomTabMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void buildMenuView() {
        super.buildMenuView();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setClickable(true);
            view.setFocusable(true);
        }
    }
}
