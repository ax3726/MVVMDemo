package com.goldze.mvvmhabit.ui.vm;

import android.content.Context;

import com.goldze.mvvmhabit.entity.TestModel;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class TestItemViewModel extends BaseViewModel {
    public TestModel testModel;

    public TestItemViewModel(Context context, TestModel testModel) {
        super(context);
        this.testModel = testModel;
    }

}
