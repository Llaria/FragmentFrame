package sun.sundy.fragmentframe.entity;

/**
 * 业务菜单GridView
 * @author Sundy
 * create at 2017/4/18 16:39
 */
public class MenuEntity {
    String name;
    int icon;
    Class mClass;

    public MenuEntity(String name, int icon, Class mClass) {
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

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
}
