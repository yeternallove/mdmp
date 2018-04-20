package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.fragments.MainFragments.HomeFragment;
import com.eternallove.mdmp.ui.fragments.MainFragments.MeFragment;
import com.eternallove.mdmp.ui.fragments.MainFragments.TaskFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public final static String[] TAG_FRAGMENT = new String[]{"CHAT", "ACC", "SCH", "ME"};//标签

    private List<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment fragmentNow;
    private FragmentManager fm;

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNav;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
//        setOverflowShowingAlways();
//        int mId= PreferenceManager.getDefaultSharedPreferences(this).getInt("mId",-1);
//        if(mId == -1){
//            Login2Activity.actionStart(this);
//        }
        fragments.add(new HomeFragment());
        fragments.add(new TaskFragment());
        fragments.add(new MeFragment());
        stateCheck(savedInstanceState);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                switch (item.getItemId()) {
//                    case R.id.bottom_Nav_item_1:
//                        switchContent(fragmentNow,fragments.get(0),TAG_FRAGMENT[0]);
//                        break;
                    case R.id.bottom_Nav_item_2:
//                        Login2Activity.actionStart(MainActivity.this);
                        switchContent(fragmentNow,fragments.get(0),TAG_FRAGMENT[1]);
                        break;
                    case R.id.bottom_Nav_item_3:
//                        LoginActivity.actionStart(MainActivity.this);
                        switchContent(fragmentNow,fragments.get(1),TAG_FRAGMENT[2]);
                        break;
                    case R.id.bottom_Nav_item_4:
                        switchContent(fragmentNow,fragments.get(2),TAG_FRAGMENT[3]);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_actionbar_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                Toast.makeText(this, "你点击了“搜索”按键！", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_setting:
//                Toast.makeText(this, "你点击了“设置”按键！", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_synchronous:
//                Toast.makeText(this, "你点击了“同步”按键！", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    /**
     * fragment 切换
     *
     * @param from
     * @param to
     */
    public void switchContent(BaseFragment from, BaseFragment to, String tag) {
        if (from != to) {
            fragmentNow = to;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(R.id.framelayout_main, to, tag).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    /**
     * 状态检测 用于内存不足时的时候保证fragment不会重叠
     */
    private void stateCheck(Bundle saveInstanceState) {
        if (saveInstanceState == null) {
            fragmentNow = fragments.get(0);
            fm.beginTransaction().add(R.id.framelayout_main, fragmentNow,TAG_FRAGMENT[0]).commit();
        } else {
            FragmentTransaction transaction = fm.beginTransaction();
            boolean show = true;
            for (String tag : TAG_FRAGMENT) {
                BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);
                if (fragment != null) {
                    if (show) {
                        transaction.show(fragment);
                        show = false;
                    } else {
                        transaction.hide(fragment);
                    }
                }
            }
            transaction.commit();
        }
    }
    /**
     * 出现OverflowShowing图标
     * @param featureId
     * @param menu
     * @return
     */
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }

    /**
     * 强制出现OverflowMenu
     */
//    private void setOverflowShowingAlways() {
//        try {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//            if(menuKeyField != null) {
//                menuKeyField.setAccessible(true);
//                menuKeyField.setBoolean(config, false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
