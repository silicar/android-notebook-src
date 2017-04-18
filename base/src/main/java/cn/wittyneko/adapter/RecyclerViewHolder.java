package cn.wittyneko.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Brady on 2016/7/7.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;
    private final SparseArray<View> mViews;

    public RecyclerViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mViews = null;
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<View>();
    }

    public View getItemView() {
        return itemView;
    }

    public <T extends ViewDataBinding> T getBinding() {
        return (T) mBinding;
    }

    /**
     * 通过控件的Id获取对应的子控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        //view.setTag(mPosition);
        return view;
    }
}