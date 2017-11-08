package com.goldze.mvvmhabit.ui.vm;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.entity.TestModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class TestViewModel extends BaseViewModel {
    public TestViewModel(Context context) {
        super(context);


    }

    //网络访问点击事件
    public BindingCommand netWorkClick = new BindingCommand(new Action0() {
        @Override
        public void call() {
            requstNetWork();
        }
    });

    /**
     * 模拟请求网络
     */
    private void requstNetWork() {
        showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();

                List<TestModel> list = new ArrayList<TestModel>();
                for (int i = 0; i < 4; i++) {
                    list.add(new TestModel("这个是第" + (i + 1) + "项"));
                }
                for (TestModel test : list) {
                    observableList.add(new TestItemViewModel(context, test));
                }

            }
        }, 3000);
    }

    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new Action0() {
        @Override
        public void call() {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    observableList.clear();
                    List<TestModel> list = new ArrayList<TestModel>();
                    for (int i = 0; i < 4; i++) {
                        list.add(new TestModel("这个是第" + (i + 1) + "项"));
                    }
                    for (TestModel test : list) {
                        observableList.add(new TestItemViewModel(context, test));
                    }
                    mOnRefershListener.stopRefersh();
                }
            }, 2000);
        }
    });

    //上拉加载
    public BindingCommand onLoadMoreCommand = new BindingCommand(new Action0() {
        @Override
        public void call() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<TestModel> list = new ArrayList<TestModel>();
                    for (int i = 0; i < 4; i++) {
                        list.add(new TestModel("这个是第" + (i + 1) + "项"));
                    }
                    for (TestModel test : list) {
                        observableList.add(new TestItemViewModel(context, test));
                    }
                    mOnRefershListener.stopRefersh();
                }
            }, 2000);

        }
    });

    //给RecyclerView添加ObservableList
    public final ObservableList<TestItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemView
    public final ItemViewSelector<TestItemViewModel> itemView = new ItemViewSelector<TestItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, TestItemViewModel item) {
            //设置item中ViewModel的id和item的layout
            itemView.set(BR.viewModel, R.layout.item_test);
        }

        @Override
        public int viewTypeCount() {

            //RecyclerView需要划分的部分数，如果是一个list,就返回1，如果带有head和list，就返回2
            return 1;
        }
    };

}
