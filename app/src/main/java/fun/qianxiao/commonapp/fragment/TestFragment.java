package fun.qianxiao.commonapp.fragment;

import fun.qianxiao.commonapp.base.BaseFragment;
import fun.qianxiao.commonapp.databinding.FragmentTestBinding;

/**
 * TODO
 *
 * @Author QianXiao
 * @Date 2021/12/27
 */
public class TestFragment extends BaseFragment<FragmentTestBinding> {

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        binding.textView.setText("TestFragment");
    }
}
