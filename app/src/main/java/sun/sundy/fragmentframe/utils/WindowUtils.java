package sun.sundy.fragmentframe.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author Sundy
 * create at 2017/4/18 16:43
 */
public class WindowUtils {

    public static int getWindowHeight(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }


}
