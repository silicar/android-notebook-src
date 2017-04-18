package cn.wittyneko.adapter;

import android.view.View;

/**
 * Created by Brady on 2016/7/7.
 */
public interface AdapterListener {
    interface OnItemKeyListener {
        boolean onItemKey();
    }

    interface OnItemClickListener {
        boolean onItemClick(View view, int position);
    }

    interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    interface OnChildKeyListener {
        boolean onChildKey();
    }

    interface OnChildClickListener {
        boolean onChildClick(View view, int position);
    }

    interface OnChildLongClickListener {
        boolean onChildLongClick(View view, int position);
    }
}
