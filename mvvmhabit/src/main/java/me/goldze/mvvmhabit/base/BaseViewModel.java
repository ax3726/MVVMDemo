package me.goldze.mvvmhabit.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

/**
 * Created by goldze on 2017/6/15.
 */
public class BaseViewModel implements IBaseViewModel {
    protected Context context;
    public onRefershListener mOnRefershListener;

    public void setOnRefershListener(onRefershListener mOnRefershListener) {
        this.mOnRefershListener = mOnRefershListener;
    }

    public BaseViewModel() {
    }

    public BaseViewModel(Context context) {
        this.context = context;
    }

    public BaseViewModel(Fragment fragment) {
        this(fragment.getContext());
    }

    private MaterialDialog dialog;

    public void showDialog() {
        showDialog("请稍后...");
    }

    public void showDialog(String title) {
        if (dialog != null) {
            dialog.show();
        } else {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(context, title, true);
            dialog = builder.show();
        }
    }


    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    public void startContainerActivity(String canonicalName, Bundle bundle) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        context.startActivity(intent);
    }

    @Override
    public void onCreateView() {
    }

    @Override
    public void onDestroyView() {
    }

    @Override
    public void registerRxBus() {
    }

    @Override
    public void removeRxBus() {
    }

    public interface onRefershListener {
        void stopRefersh();
    }
}
