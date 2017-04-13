package sun.sundy.fragmentframe.ui.fragment.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingMenuFragment extends BaseMenuFragment {


    public SettingMenuFragment() {
        // Required empty public constructor
    }

    public static SettingMenuFragment newInstance() {

        Bundle args = new Bundle();

        SettingMenuFragment fragment = new SettingMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_menu, container, false);
    }

}
