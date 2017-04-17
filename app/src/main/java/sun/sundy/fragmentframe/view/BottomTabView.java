package sun.sundy.fragmentframe.view;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * 修改BottomNavigationView获取标签焦点
 * Created by sundi on 2017/4/17.
 */

public class BottomTabView extends BottomNavigationView {

    private BottomNavigationMenuView mMenuView;
    private BottomNavigationItemView[] mButtons;

    public BottomTabView(Context context) {
        super(context);
        init();
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 改变获取焦点和可点击属性
     */
    private void init() {
        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        BottomNavigationItemView[] mButtons = getBottomNavigationItemViews();
        for (BottomNavigationItemView button : mButtons) {
            button.setFocusable(true);
            button.setClickable(true);
        }
        mMenuView.setFocusable(true);
        mMenuView.setClickable(true);
        mMenuView.updateMenuView();
    }



    /**
     * 获取父类中mMenuView
     */
    private BottomNavigationMenuView getBottomNavigationMenuView() {
        if (null == mMenuView)
            mMenuView = getField(getClass().getSuperclass(), this, "mMenuView");
        return mMenuView;
    }

    /**
     * 获取bottom中标签
     */
    public BottomNavigationItemView[] getBottomNavigationItemViews() {
        if (null != mButtons)
            return mButtons;
        BottomNavigationMenuView mMenuView = getBottomNavigationMenuView();
        mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons");
        return mButtons;
    }

    /**
     * 获取父类中属性
     *
     * @param targetClass class name
     * @param instance    the filed owner
     * @param fieldName property name
     * @param <T> type
     * @return field if success, null otherwise.
     */
    private <T> T getField(Class targetClass, Object instance, String fieldName) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重置父类中属性
     * @param targetClass class name
     * @param instance    the filed owner
     * @param fieldName property name
     * @param value value
     */
    private void setField(Class targetClass, Object instance, String fieldName, Object value) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
