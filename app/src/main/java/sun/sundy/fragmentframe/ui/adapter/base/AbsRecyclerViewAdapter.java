package sun.sundy.fragmentframe.ui.adapter.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 封装RecyclerView的adapter
 *
 * @author scj
 */
public abstract class AbsRecyclerViewAdapter<T> extends RecyclerView.Adapter<AbsRecyclerViewAdapter.RecyclerViewHolder> {

    protected List<T> items;
    private int itemLayout;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public AbsRecyclerViewAdapter(@NonNull List<T> items, @LayoutRes int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(AbsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        convert(holder, items.get(position), position);
    }

    protected abstract void convert(RecyclerViewHolder holder, T item, int position);

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final SparseArray<View> views;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            this.views = new SparseArray<>();
            if (itemClickListener != null)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(itemView, getLayoutPosition());
                    }
                });

            if (itemLongClickListener != null)
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        itemLongClickListener.onItemLongClick(itemView, getLayoutPosition());
                        return true;
                    }
                });
        }

        @SuppressWarnings("unchecked")
        public <V extends View> V getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (V) view;
        }

    }

}
