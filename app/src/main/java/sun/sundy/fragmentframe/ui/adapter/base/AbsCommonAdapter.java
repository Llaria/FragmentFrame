package sun.sundy.fragmentframe.ui.adapter.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 封装BaseAdapter
 *
 * @author scj
 */
public abstract class AbsCommonAdapter<T> extends BaseAdapter {

    protected List<T> items;
    private int itemLayout;

    public AbsCommonAdapter(@NonNull List<T> items, @LayoutRes int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    public AbsCommonAdapter(@LayoutRes int itemLayout) {
        this.items = new ArrayList<T>();
        this.itemLayout = itemLayout;
    }

    public void addAll(Collection<T> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void setData(Collection<T> collection) {
        if (collection != null) {
            items.clear();
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void add(T item) {
        if (item != null) {
            items.add(item);
            notifyDataSetChanged();
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= items.size())
            throw new IndexOutOfBoundsException("越界了");
        T result = items.remove(index);
        notifyDataSetChanged();
        return result;
    }

    public boolean remove(T o) {
        boolean removed = items.remove(o);
        if (removed)
            notifyDataSetChanged();
        return removed;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = ViewHolder.get(convertView, parent, itemLayout);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    /**
     * 用于将datas中的数据显示在view上
     * <p/>
     * <li> 1) 从ViewHolder中获取view对象<br/>
     * <li> 2) 从数据集中获取数据<br/>
     * <li> 3) 将数据绑定到view上<br/>
     *
     * @param holder ViewHolder
     * @param item   T
     */
    public abstract void convert(ViewHolder holder, T item, int position);

}