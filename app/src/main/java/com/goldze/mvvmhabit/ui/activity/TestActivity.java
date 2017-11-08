package com.goldze.mvvmhabit.ui.activity;

import android.view.View;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.databinding.ActivityTestBinding;
import com.goldze.mvvmhabit.ui.vm.TestViewModel;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class TestActivity extends BaseActivity<ActivityTestBinding, TestViewModel> {
    @Override
    public int initContentView() {
        return R.layout.activity_test;
    }

    @Override
    public void initData() {
        super.initData();
        binding.trlLayout.setHeaderView(new IHeaderView() {
            @Override
            public View getView() {
                return View.inflate(aty, R.layout.head_view_layout, null);
            }

            @Override
            public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {

            }

            @Override
            public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

            }

            @Override
            public void startAnim(float maxHeadHeight, float headHeight) {

            }

            @Override
            public void onFinish(OnAnimEndListener animEndListener) {

            }

            @Override
            public void reset() {

            }
        });
        viewModel.setOnRefershListener(new BaseViewModel.onRefershListener() {
            @Override
            public void stopRefersh() {
                binding.trlLayout.finishRefreshing();
                binding.trlLayout.finishLoadmore();
            }
        });
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public TestViewModel initViewModel() {
        return new TestViewModel(aty);
    }
}
