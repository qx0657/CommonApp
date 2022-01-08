package fun.qianxiao.commonapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * 通用视图绑定Activity抽象基类
 *
 * @param <T> ViewBinding
 * @author QianXiao
 * @Date 2021/12/24
 */
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected Context context;
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = getBinding();
        assert binding != null;
        setContentView(binding.getRoot());
        initListener();
        initData();
    }

    @SuppressWarnings("unchecked")
    private T getBinding() {
        try {
            Class<T> tClass = (Class<T>) ((ParameterizedType) Objects.requireNonNull(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            Method method = tClass.getMethod("inflate", LayoutInflater.class);
            return (T) method.invoke(null, getLayoutInflater());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
            return null;
        }
    }

    /**
     * initListener
     */
    protected abstract void initListener();

    /**
     * initData
     */
    protected abstract void initData();
}
