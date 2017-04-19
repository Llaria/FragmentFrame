package sun.sundy.fragmentframe.entity;

import sun.sundy.fragmentframe.utils.myFragment.RxSupportFragment;

/**
 * 菜单GridView
 * @author Sundy
 * create at 2017/4/18 16:39
 */
public class MenuEntity {
    String name;
    int icon;
    RxSupportFragment mClass;

    public MenuEntity(String name, int icon, RxSupportFragment mClass) {
        this.name = name;
        this.icon = icon;
        this.mClass = mClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public RxSupportFragment getmClass() {
        return mClass;
    }

    public void setmClass(RxSupportFragment mClass) {
        this.mClass = mClass;
    }
}
