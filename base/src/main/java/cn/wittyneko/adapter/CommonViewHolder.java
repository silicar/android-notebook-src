package cn.wittyneko.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wittyneko.views.TagGenerate;

public class CommonViewHolder {
    public static final int VIEW_HOLDER = TagGenerate.Tag.VIEW_HOLDER;
    //public static final int DATA_BINDING = TagGenerate.Tag.obtainSystemTag();
    private ViewDataBinding mBinding;
    private final SparseArray<View> mViews;
    private int mPosition;
    private int mOldPosition;
    public View itemView;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this(context, parent, layoutId, position, true);
    }

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position, boolean isDataBinding) {
        if (isDataBinding) {
            mPosition = position;
            mViews = null;
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
            itemView = mBinding.getRoot();
            itemView.setTag(VIEW_HOLDER, this);
        } else {
            mPosition = position;
            mViews = new SparseArray<View>();
            itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            // setTag
            itemView.setTag(VIEW_HOLDER, this);
        }
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context     上下文
     * @param position    位置
     * @param convertView 旧视图，没有则新建
     * @param parent      父控件
     * @param layoutId    视图对应的布局文件
     * @return
     */
    public static CommonViewHolder get(Context context, int position, View convertView,
                                       ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        //((AdapterViewHolder)convertView.getTag()).setPosition(position);
        try {
            ((CommonViewHolder) convertView.getTag(VIEW_HOLDER)).setOldPosition(((CommonViewHolder) convertView.getTag(VIEW_HOLDER)).getPosition());
            ((CommonViewHolder) convertView.getTag(VIEW_HOLDER)).setPosition(position);
        } catch (Exception e) {
            convertView.setTag(VIEW_HOLDER, new CommonViewHolder(context, parent, layoutId, position));
        }
        return (CommonViewHolder) convertView.getTag(VIEW_HOLDER);
    }

    public static CommonViewHolder get(Context context, int position, ViewGroup parent, int layoutId) {
        return new CommonViewHolder(context, parent, layoutId, position);
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
        return view;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getOldPosition() {
        return mOldPosition;
    }

    public void setOldPosition(int oldPosition) {
        mOldPosition = oldPosition;
    }

    public Object getTag() {
        return itemView.getTag(VIEW_HOLDER);
    }
}
