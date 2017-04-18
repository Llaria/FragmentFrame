package sun.sundy.fragmentframe.ui.adapter.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通用ViewHolder
 *
 * @author
 */
public class ViewHolder {

    private final SparseArray<View> views;
    private View convertView;

    private ViewHolder(ViewGroup parent, int layoutId) {
        this.views = new SparseArray<>();
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,
                false);
        convertView.setTag(this);
    }

    /**
     * 获得一个ViewHolder对象
     */
    public static ViewHolder get(View convertView,
                                 ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(parent, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

}
