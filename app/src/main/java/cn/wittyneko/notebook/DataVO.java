package cn.wittyneko.notebook;

import android.content.Intent;

import java.util.List;

/**
 * Created by Brady on 2016/7/9.
 */
public class DataVO {
    String title;
    Intent intent;
    List<DataVO> dataVOList;

    public DataVO() {
    }

    public DataVO(String title, List<DataVO> dataVOList) {
        this.title = title;
        this.dataVOList = dataVOList;
    }

    public DataVO(String title, Intent intent) {
        this.title = title;
        this.intent = intent;
    }

    public DataVO(String title, Intent intent, List<DataVO> dataVOList) {
        this.title = title;
        this.intent = intent;
        this.dataVOList = dataVOList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<DataVO> getDataVOList() {
        return dataVOList;
    }

    public void setDataVOList(List<DataVO> dataVOList) {
        this.dataVOList = dataVOList;
    }
}
