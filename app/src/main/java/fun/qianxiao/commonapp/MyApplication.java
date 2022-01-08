package fun.qianxiao.commonapp;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.umeng.commonsdk.UMConfigure;

/**
 * MyApplication
 *
 * @Author QianXiao
 * @Date 2021/12/24
 */
public class MyApplication extends Application {
    public static final String TAG = "COMMON_APP";
    public static final String UMENGG_APP_ID = "";

    @Override
    public void onCreate() {
        super.onCreate();

        //logger
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .tag(TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        // 友盟
        UMConfigure.setLogEnabled(true);
        UMConfigure.preInit(this, UMENGG_APP_ID, "android");
        UMConfigure.init(this, UMENGG_APP_ID, "android", UMConfigure.DEVICE_TYPE_PHONE, "");

    }
}
