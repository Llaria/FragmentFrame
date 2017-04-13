package sun.sundy.fragmentframe.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import sun.sundy.fragmentframe.R;
import sun.sundy.fragmentframe.ui.base.BaseMenuFragment;
import sun.sundy.fragmentframe.ui.fragment.biz.BizMenuFragment;
import sun.sundy.fragmentframe.ui.fragment.query.QueryMenuFragment;
import sun.sundy.fragmentframe.ui.fragment.setting.SettingMenuFragment;
import sun.sundy.fragmentframe.view.BottomTabView;

/**
 * Fragment主界面
 *
 * @author Sundy
 *         create at 2017/4/13 15:59
 */
public class MenuFragment extends BaseMenuFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private int selectedID;
    private int prePosition;

    private SupportFragment[] mFragments = new SupportFragment[3];


    public static MenuFragment newInstance() {

        Bundle args = new Bundle();

        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        if (savedInstanceState == null) {
            mFragments[FIRST] = BizMenuFragment.newInstance();
            mFragments[SECOND] = QueryMenuFragment.newInstance();
            mFragments[THIRD] = SettingMenuFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            mFragments[FIRST] = findChildFragment(BizMenuFragment.class);
            mFragments[SECOND] = findChildFragment(QueryMenuFragment.class);
            mFragments[THIRD] = findChildFragment(SettingMenuFragment.class);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
//        EventBus.getDefault().register(this);
        BottomTabView navigation = (BottomTabView) view.findViewById(R.id.navigation);
        selectedID = navigation.getSelectedItemId();
        switch (selectedID) {
            case R.id.navigation_first:
                prePosition = 0;
                break;
            case R.id.navigation_two:
                prePosition = 1;
                break;
            case R.id.navigation_three:
                prePosition = 2;
                break;
            default:
                prePosition = 0;
                break;
        }
        Log.e("TAG", "initView: " + prePosition );
        navigation.setOnNavigationItemSelectedListener(new BottomTabView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_first:
                        showHideFragment(mFragments[0], mFragments[prePosition]);
                        prePosition = 0;
                        return true;
                    case R.id.navigation_two:
                        showHideFragment(mFragments[1], mFragments[prePosition]);
                        prePosition = 1;
                        return true;
                    case R.id.navigation_three:
                        showHideFragment(mFragments[2], mFragments[prePosition]);
                        prePosition = 2;
                        return true;
                }
                return false;
            }
        });
        navigation.setOnNavigationItemReselectedListener(new BottomTabView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                EventBus.getDefault().post(new TabSelectedEvent(item.getItemId()));
            }
        });
    }

    @Override
    public void onDestroyView() {
//        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

}
