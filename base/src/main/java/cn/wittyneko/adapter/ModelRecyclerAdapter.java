package cn.wittyneko.adapter;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.wittyneko.views.TagGenerate;

/**
 * Created by Brady on 2016/7/7.
 */
public abstract class ModelRecyclerAdapter<T> {

    public static final int POSITION = TagGenerate.Tag.POSITION;
    protected Context mContext;

    public List<T> list;
    public RecyclerAdapter<T> adapter;

    //Item控件事件监听
    //protected View.OnKeyListener mOnItemKeyListener;
    protected View.OnClickListener mOnItemClickListener;
    protected View.OnLongClickListener mOnItemLongClickListener;
    //protected View.OnKeyListener mOnChildKeyListener;
    protected View.OnClickListener mOnChildClickListener;
    protected View.OnLongClickListener mOnChildLongClickListener;

    //Adapter回调接口
    //private AdapterListener.OnItemKeyListener onItemKeyListener;
    private AdapterListener.OnItemClickListener onItemClickListener;
    private AdapterListener.OnItemLongClickListener onItemLongClickListener;
    //private AdapterListener.OnChildKeyListener onChildKeyListener;
    private AdapterListener.OnChildClickListener onChildClickListener;
    private AdapterListener.OnChildLongClickListener onChildLongClickListener;

    public ModelRecyclerAdapter(Context context, int layoutId) {
        mContext = context;
        list = new ArrayList<>();
        //回调接口返回true则拦截当前类方法,false则执行回调接口和当前类方法
        mOnItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(POSITION);
                if (onItemClickListener != null) {
                    if (!onItemClickListener.onItemClick(v, position))
                        onItemClick(v, position);
                } else
                    onItemClick(v, position);
            }
        };
        mOnItemLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = (int) v.getTag(POSITION);
                if (onItemLongClickListener != null)
                    return onItemLongClickListener.onItemLongClick(v, position) ?
                            true : onItemLongClick(v, position);
                return onItemLongClick(v, position);
            }
        };
        mOnChildClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(POSITION);
                if (onChildClickListener != null) {
                    if (!onChildClickListener.onChildClick(v, position))
                        onChildClick(v, position);
                } else
                    onChildClick(v, position);
            }
        };
        mOnChildLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = (int) v.getTag(POSITION);
                if (onItemLongClickListener != null)
                    return onChildLongClickListener.onChildLongClick(v, position) ?
                            true : onChildLongClick(v, position);
                return onChildLongClick(v, position);
            }
        };
    }

    //Adapter事件处理
    protected abstract void onItemClick(View view, int position);

    protected abstract boolean onItemLongClick(View view, int position);

    protected abstract void onChildClick(View view, int position);

    protected abstract boolean onChildLongClick(View view, int position);
}
