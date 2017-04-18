package cn.wittyneko.notebook;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.wittyneko.adapter.ModelCommonAdapter;


/**
 * Created by Brady on 2016/7/12.
 */
public abstract class DataWrapperAdapter extends ModelCommonAdapter<DataWrapper> {
    
    public DataWrapperAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    /**
     * 改变展开状态
     *
     * @param position
     * @return
     */
    public boolean changeExpand(int position) {
        DataWrapper<DataVO> vo = list.get(position);
        //Log.e("Expand", "->" + vo.isExpand());
        //Log.e("HasChild", "->" + vo.isHasChild());
        if (vo.isHasChild()) {
            if (!vo.isExpand()) {
                addList(vo.getChildList(), vo.getLevel() + 1, vo.getPosition());
            } else {
                removeList(vo.getLevel(), vo.getPosition());
            }
            vo.setExpand(!vo.isExpand());
            return true;
        }
        return false;
    }

    /**
     * 添加(展开)列表
     *
     * @param dataVOList 列表
     * @param level      等级
     * @param parentPosition   上一级位置
     */
    public void addList(List<DataVO> dataVOList, int level, int parentPosition) {

        List<DataWrapper> dataWrapperList = new ArrayList<>();
        for (int i = 0; i < dataVOList.size(); i++) {
            DataVO vo = dataVOList.get(i);
            DataWrapper<DataVO> dataWrapper = new DataWrapper<>(false, false, level, parentPosition, i, vo);
            if (vo.getDataVOList() != null && !vo.getDataVOList().isEmpty()) {
                dataWrapper.setHasChild(true);
                dataWrapper.setChildList(vo.getDataVOList());
                //addList(vo.getDataVOList(), level + 1, i);
            }
            dataWrapperList.add(dataWrapper);
        }
        if (level == 1) {
            list.addAll(dataWrapperList);
        } else {
            DataWrapper<DataVO> vo;
            for (int i = 0; i < list.size(); i++) {
                vo = list.get(i);
                if (vo.getLevel() == level - 1 && vo.getPosition() == parentPosition) {
                    list.addAll(i + 1, dataWrapperList);
                    break;
                }
            }
        }
    }

    /**
     * 删除(折叠)列表
     *
     * @param level    等级
     * @param parentPosition 位置
     */
    public void removeList(int level, int parentPosition) {
        Iterator<DataWrapper> iterator = list.iterator();
        removeList(iterator, level, parentPosition, -1);
    }

    /**
     * 删除(折叠)列表
     *
     * @param iterator
     * @param level       等级
     * @param parentPosition    位置
     * @param oldPosition 上一级位置
     */
    private void removeList(Iterator<DataWrapper> iterator, int level, int parentPosition, int oldPosition) {
        DataWrapper<DataVO> vo;
        while (iterator.hasNext()) {
            vo = iterator.next();
            //Log.e("level", "->" + vo.getLevel());
            //Log.e("ParentPosition", "->" + vo.getParentPosition());
            //Log.e("position", "->" + parentPosition);
            if (vo.getLevel() == level + 1 && vo.getParentPosition() == parentPosition) {
                iterator.remove();
                //存在下级并且已展开
                if (vo.isHasChild() && vo.isExpand()) {
                    removeList(iterator, vo.getLevel(), vo.getPosition(), parentPosition);
                }
            } else if (vo.getLevel() == level && vo.getParentPosition() == oldPosition) {
                //存在待删除的上一级(只有-1位置表示不存在上一级)
                iterator.remove();
                break;
            } else if (vo.getLevel() == level && vo.getPosition() > parentPosition) {
                //超出删除位置，结束遍历
                break;
            }
        }
    }

    //展开全部
    public void expandAll(List<DataVO> dataVOList, int level, int parentPosition) {
        removeList(level, parentPosition);
        List<DataWrapper> dataWrapperList = new ArrayList<>();
        addExpandAll(dataVOList, dataWrapperList, level, parentPosition);
        if (level == 1) {
            list.addAll(dataWrapperList);
        } else {
            DataWrapper<DataVO> vo;
            for (int i = 0; i < list.size(); i++) {
                vo = list.get(i);
                if (vo.getLevel() == level - 1 && vo.getPosition() == parentPosition) {
                    list.addAll(i + 1, dataWrapperList);
                    break;
                }
            }
        }
    }

    private void addExpandAll(List<DataVO> dataVOList, List<DataWrapper> dataWrapperList, int level, int parentPosition){
        for (int i = 0; i < dataVOList.size(); i++) {
            DataVO vo = dataVOList.get(i);
            DataWrapper<DataVO> dataWrapper = new DataWrapper<>(false, false, level, parentPosition, i, vo);
            if (vo.getDataVOList() != null && !vo.getDataVOList().isEmpty()) {
                dataWrapper.setHasChild(true);
                dataWrapper.setChildList(vo.getDataVOList());
                addExpandAll(vo.getDataVOList(), dataWrapperList, level + 1, i);
            }
            dataWrapperList.add(dataWrapper);
        }
    }

    //折叠全部
    public void foldAll(int level, int parentPosition) {
        Iterator<DataWrapper> iterator = list.iterator();
        DataWrapper<DataVO> vo;
        while (iterator.hasNext()) {
            vo = iterator.next();
            if (vo.getLevel() == level && vo.getParentPosition() == parentPosition) {
                if (vo.isHasChild() && vo.isExpand()) {
                    removeList(iterator, vo.getLevel(), vo.getPosition(), parentPosition);
                }
            } else if (vo.getLevel() == level -1 && vo.getPosition() > parentPosition) {
                break;
            }
        }
    }
}
