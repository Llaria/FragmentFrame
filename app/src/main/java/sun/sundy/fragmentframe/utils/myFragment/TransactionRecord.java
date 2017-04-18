package sun.sundy.fragmentframe.utils.myFragment;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by sundi on 2017/4/18.
 */

public final class TransactionRecord {
    public String tag;
    public Integer requestCode;
    public Integer launchMode;
    public Boolean withPop;
    public ArrayList<SharedElement> sharedElementList;

    public static class SharedElement {
        public View sharedElement;
        public String sharedName;

        public SharedElement(View sharedElement, String sharedName) {
            this.sharedElement = sharedElement;
            this.sharedName = sharedName;
        }
    }
}
