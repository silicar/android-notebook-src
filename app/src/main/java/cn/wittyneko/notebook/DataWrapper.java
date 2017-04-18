package cn.wittyneko.notebook;

import java.util.List;

/**
 * Created by Brady on 2016/7/8.
 */
public class DataWrapper<T> {
    //存在下级列表
    private boolean hasChild;
    //折叠状态
    private boolean isExpand;
    //列表等级
    private int level;
    //上一级位置
    private int parentPosition;
    //当前等级位置
    private int position;
    //数据
    private T data;
    //下级数据
    private List<T> childList;

    public DataWrapper() {
    }

    public DataWrapper(boolean hasChild, boolean isExpand, int level, int parentPosition, int position, T data) {
        this.hasChild = hasChild;
        this.isExpand = isExpand;
        this.level = level;
        this.parentPosition = parentPosition;
        this.position = position;
        this.data = data;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }
}
