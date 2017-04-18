package sun.sundy.fragmentframe.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.entity.MenuEntity;
import sun.sundy.fragmentframe.ui.adapter.base.AbsCommonAdapter;
import sun.sundy.fragmentframe.ui.adapter.base.ViewHolder;
import sun.sundy.fragmentframe.utils.WindowUtils;

/**
 * 业务主菜单
 * @author Sundy
 * create at 2017/4/18 16:39
 */
public class BizMenuAdapter extends AbsCommonAdapter<MenuEntity> {
    private int height;

    public BizMenuAdapter(@NonNull List<MenuEntity> items, Context context) {
        super(items,  R.layout.item_menu);
        height= WindowUtils.getWindowHeight(context);
    }

    @Override
    public void convert(ViewHolder holder, MenuEntity item, int position) {
        LinearLayout viewGroup = holder.getView(R.id.itemParent);
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = height/5;
        ImageView imageView = holder.getView(R.id.img_icon);
        imageView.setImageResource(item.getIcon());

        TextView textView = holder.getView(R.id.tv_name);
        textView.setText(item.getName());
    }
}
