package sun.sundy.fragmentframe.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author Sundy
 * create at 2017/4/18 16:43
 */
public class WindowUtils {

    public static int getWindowWidth(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
        return width3;
    }


}
