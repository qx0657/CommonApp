package fun.qianxiao.commonapp;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import fun.qianxiao.commonapp.adapter.MyPageAdapter;
import fun.qianxiao.commonapp.base.BaseActivity;
import fun.qianxiao.commonapp.checkupdate.CheckUpdateManager;
import fun.qianxiao.commonapp.databinding.ActivityMainBinding;
import fun.qianxiao.commonapp.fragment.TestFragment;
import fun.qianxiao.commonapp.utils.PermissionManager;
import fun.qianxiao.commonapp.utils.PrivacyPolicyManager;

/**
 * MainActivity
 *
 * @Author QianXiao
 * @Date 2021/12/24
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();

    @Override
    protected void initListener() {
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (binding.tabLayout.getCurrentTab() != position) {
                    binding.tabLayout.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (binding.viewPager.getCurrentItem() != position) {
                    binding.viewPager.setCurrentItem(position);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initData() {
        PrivacyPolicyManager privacyPolicyManager = new PrivacyPolicyManager(context);
        if (!privacyPolicyManager.isAgreePrivacyPolicy()) {
            privacyPolicyManager.confrim(new PrivacyPolicyManager.OnPrivacyPolicyListener() {
                @Override
                public void onAgree() {
                    requestPermission();
                }

                @Override
                public void onRefuse() {
                    requestPermission();
                }

                private void requestPermission() {
                    PermissionManager permissionManager = new PermissionManager(context);
                    if (!permissionManager.hasAllPermission()) {
                        permissionManager.requestNeeded();
                    }
                }
            });
        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        String[] titles = new String[]{"test1", "test2", "test3", "test4"};
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), fragments, titles);
        binding.viewPager.setAdapter(adapter);

        for (int i = 0; i < titles.length; i++) {
            int finalI = i;
            tabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return titles[finalI];
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
        }
        binding.tabLayout.setTabData(tabEntities);
        //startActivity(AboutActivity.class);
        //BrowserActivity.load(context, "https://www.baidu.com/");

        new CheckUpdateManager(context).check(true);
    }

    private void startActivity(Class<?> ac) {
        this.startActivity(new Intent(context, ac));
    }
}