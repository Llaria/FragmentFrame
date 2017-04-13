package sun.sundy.fragmentframe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import sun.sundy.fragmentframe.R;

/**
 * 底部标签栏，只能3-5个
 * Created by sundi on 2017/4/13.
 */

public class BottomTabView extends FrameLayout {

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-android.R.attr.state_enabled};

    private static final int[] APPCOMPAT_CHECK_ATTRS = {android.support.v7.appcompat.R.attr.colorPrimary};

    private static final int MENU_PRESENTER_ID = 1;

    private final MenuBuilder mMenu;
    private final BottomTabMenuView mMenuView;
    private final BottomNavigationPresenter mPresenter = new BottomNavigationPresenter();
    private MenuInflater mMenuInflater;

    private OnNavigationItemSelectedListener mSelectedListener;
    private OnNavigationItemReselectedListener mReselectedListener;

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("PrivateResource")
    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        checkAppCompatTheme(context);

        // Create the menu
        mMenu = new BottomNavigationMenu(context);

        mMenuView = new BottomTabMenuView(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mMenuView.setLayoutParams(params);

        mMenuView.setFocusable(false);
        mMenuView.setClickable(false);

        mPresenter.setBottomNavigationMenuView(mMenuView);
        mPresenter.setId(MENU_PRESENTER_ID);
        mMenuView.setPresenter(mPresenter);
        mMenu.addMenuPresenter(mPresenter);
        mPresenter.initForMenu(getContext(), mMenu);

        // Custom attributes
        @SuppressLint("PrivateResource") TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.BottomNavigationView, defStyleAttr,
                R.style.Widget_Design_BottomNavigationView);

        if (a.hasValue(R.styleable.BottomNavigationView_itemIconTint)) {
            mMenuView.setIconTintList(
                    a.getColorStateList(R.styleable.BottomNavigationView_itemIconTint));
        } else {
            mMenuView.setIconTintList(
                    createDefaultColorStateList(android.R.attr.textColorSecondary));
        }
        if (a.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {
            mMenuView.setItemTextColor(
                    a.getColorStateList(R.styleable.BottomNavigationView_itemTextColor));
        } else {
            mMenuView.setItemTextColor(
                    createDefaultColorStateList(android.R.attr.textColorSecondary));
        }
        if (a.hasValue(R.styleable.BottomNavigationView_elevation)) {
            ViewCompat.setElevation(this, a.getDimensionPixelSize(
                    R.styleable.BottomNavigationView_elevation, 0));
        }

        int itemBackground = a.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0);
        mMenuView.setItemBackgroundRes(itemBackground);

        if (a.hasValue(R.styleable.BottomNavigationView_menu)) {
            inflateMenu(a.getResourceId(R.styleable.BottomNavigationView_menu, 0));
        }
        a.recycle();

        addView(mMenuView, params);
        if (Build.VERSION.SDK_INT < 21) {
            addCompatibilityTopDivider(context);
        }

        mMenu.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                if (mReselectedListener != null && item.getItemId() == getSelectedItemId()) {
                    mReselectedListener.onNavigationItemReselected(item);
                    return true; // item is already selected
                }
                return mSelectedListener != null
                        && !mSelectedListener.onNavigationItemSelected(item);
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {}
        });
    }

    private void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        a.recycle();
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }

    /**
     * Set a listener that will be notified when a bottom navigation item is selected. This listener
     * will also be notified when the currently selected item is reselected, unless an
     * {@link OnNavigationItemReselectedListener} has also been set.
     *
     * @param listener The listener to notify
     *
     * @see #setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener)
     */
    public void setOnNavigationItemSelectedListener(
            @Nullable OnNavigationItemSelectedListener listener) {
        mSelectedListener = listener;
    }

    /**
     * Set a listener that will be notified when the currently selected bottom navigation item is
     * reselected. This does not require an {@link OnNavigationItemSelectedListener} to be set.
     *
     * @param listener The listener to notify
     *
     * @see #setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
     */
    public void setOnNavigationItemReselectedListener(
            @Nullable OnNavigationItemReselectedListener listener) {
        mReselectedListener = listener;
    }

    /**
     * Returns the {@link Menu} instance associated with this bottom navigation bar.
     */
    @NonNull
    public Menu getMenu() {
        return mMenu;
    }

    /**
     * Inflate a menu resource into this navigation view.
     *
     * <p>Existing items in the menu will not be modified or removed.</p>
     *
     * @param resId ID of a menu resource to inflate
     */
    public void inflateMenu(int resId) {
        mPresenter.setUpdateSuspended(true);
        getMenuInflater().inflate(resId, mMenu);
        mPresenter.setUpdateSuspended(false);
        mPresenter.updateMenuView(true);
    }

    /**
     * @return The maximum number of items that can be shown in BottomNavigationView.
     */
    public int getMaxItemCount() {
        return BottomNavigationMenu.MAX_ITEM_COUNT;
    }

    /**
     * Returns the tint which is applied to our menu items' icons.
     *
     * @see #setItemIconTintList(ColorStateList)
     *
     * @attr ref R.styleable#BottomNavigationView_itemIconTint
     */
    @Nullable
    public ColorStateList getItemIconTintList() {
        return mMenuView.getIconTintList();
    }

    /**
     * Set the tint which is applied to our menu items' icons.
     *
     * @param tint the tint to apply.
     *
     * @attr ref R.styleable#BottomNavigationView_itemIconTint
     */
    public void setItemIconTintList(@Nullable ColorStateList tint) {
        mMenuView.setIconTintList(tint);
    }

    /**
     * Returns colors used for the different states (normal, selected, focused, etc.) of the menu
     * item text.
     *
     * @see #setItemTextColor(ColorStateList)
     *
     * @return the ColorStateList of colors used for the different states of the menu items text.
     *
     * @attr ref R.styleable#BottomNavigationView_itemTextColor
     */
    @Nullable
    public ColorStateList getItemTextColor() {
        return mMenuView.getItemTextColor();
    }

    /**
     * Set the colors to use for the different states (normal, selected, focused, etc.) of the menu
     * item text.
     *
     * @see #getItemTextColor()
     *
     * @attr ref R.styleable#BottomNavigationView_itemTextColor
     */
    public void setItemTextColor(@Nullable ColorStateList textColor) {
        mMenuView.setItemTextColor(textColor);
    }

    /**
     * Returns the background resource of the menu items.
     *
     * @see #setItemBackgroundResource(int)
     *
     * @attr ref R.styleable#BottomNavigationView_itemBackground
     */
    @DrawableRes
    public int getItemBackgroundResource() {
        return mMenuView.getItemBackgroundRes();
    }

    /**
     * Set the background of our menu items to the given resource.
     *
     * @param resId The identifier of the resource.
     *
     * @attr ref R.styleable#BottomNavigationView_itemBackground
     */
    public void setItemBackgroundResource(@DrawableRes int resId) {
        mMenuView.setItemBackgroundRes(resId);
    }

    /**
     * Returns the currently selected menu item ID, or zero if there is no menu.
     *
     * @see #setSelectedItemId(int)
     */
    @IdRes
    public int getSelectedItemId() {
        return mMenuView.getSelectedItemId();
    }

    /**
     * Set the selected menu item ID. This behaves the same as tapping on an item.
     *
     * @param itemId The menu item ID. If no item has this ID, the current selection is unchanged.
     *
     * @see #getSelectedItemId()
     */
    public void setSelectedItemId(@IdRes int itemId) {
        MenuItem item = mMenu.findItem(itemId);
        if (item != null) {
            if (!mMenu.performItemAction(item, mPresenter, 0)) {
                item.setChecked(true);
            }
        }
    }

    /**
     * Listener for handling selection events on bottom navigation items.
     */
    public interface OnNavigationItemSelectedListener {

        /**
         * Called when an item in the bottom navigation menu is selected.
         *
         * @param item The selected item
         *
         * @return true to display the item as the selected item and false if the item should not
         *         be selected. Consider setting non-selectable items as disabled preemptively to
         *         make them appear non-interactive.
         */
        boolean onNavigationItemSelected(@NonNull MenuItem item);
    }

    /**
     * Listener for handling reselection events on bottom navigation items.
     */
    public interface OnNavigationItemReselectedListener {

        /**
         * Called when the currently selected item in the bottom navigation menu is selected again.
         *
         * @param item The selected item
         */
        void onNavigationItemReselected(@NonNull MenuItem item);
    }

    private void addCompatibilityTopDivider(Context context) {
        View divider = new View(context);
        divider.setBackgroundColor(
                ContextCompat.getColor(context, R.color.design_bottom_navigation_shadow_color));
        FrameLayout.LayoutParams dividerParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(
                        R.dimen.design_bottom_navigation_shadow_height));
        divider.setLayoutParams(dividerParams);
        addView(divider);
    }

    private MenuInflater getMenuInflater() {
        if (mMenuInflater == null) {
            mMenuInflater = new SupportMenuInflater(getContext());
        }
        return mMenuInflater;
    }

    private ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
        final TypedValue value = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
            return null;
        }
        ColorStateList baseColor = AppCompatResources.getColorStateList(
                getContext(), value.resourceId);
        if (!getContext().getTheme().resolveAttribute(
                android.support.v7.appcompat.R.attr.colorPrimary, value, true)) {
            return null;
        }
        int colorPrimary = value.data;
        int defaultColor = baseColor.getDefaultColor();
        return new ColorStateList(new int[][]{
                DISABLED_STATE_SET,
                CHECKED_STATE_SET,
                EMPTY_STATE_SET
        }, new int[]{
                baseColor.getColorForState(DISABLED_STATE_SET, defaultColor),
                colorPrimary,
                defaultColor
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.menuPresenterState = new Bundle();
        mMenu.savePresenterStates(savedState.menuPresenterState);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mMenu.restorePresenterStates(savedState.menuPresenterState);
    }

    static class SavedState extends AbsSavedState {
        Bundle menuPresenterState;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            readFromParcel(source, loader);
        }

        @Override
        public void writeToParcel(@NonNull Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBundle(menuPresenterState);
        }

        private void readFromParcel(Parcel in, ClassLoader loader) {
            menuPresenterState = in.readBundle(loader);
        }

        public static final Creator<SavedState> CREATOR =
                ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                        return new SavedState(in, loader);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                });
    }

}
