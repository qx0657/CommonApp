package fun.qianxiao.commonapp.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ThreadUtils;

import fun.qianxiao.commonapp.MainActivity;
import fun.qianxiao.commonapp.base.BaseActivity;
import fun.qianxiao.commonapp.databinding.ActivitySplashBinding;

/**
 * TODO
 *
 * @Author QianXiao
 * @Date 2021/12/27
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        BarUtils.transparentStatusBar(this);
        ThreadUtils.runOnUiThreadDelayed(this::enterApp, 2000);
    }

    private void enterApp() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    /**
     * 禁用返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
