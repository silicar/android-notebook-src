package cn.wittyneko.notebook;

import android.content.Intent;

import java.util.List;

/**
 * Created by Brady on 2016/7/9.
 */
public class DataVo {
    String title;
    Intent intent;
    List<DataVo> dataVoList;

    public DataVo() {
    }

    public DataVo(String title, List<DataVo> dataVoList) {
        this.title = title;
        this.dataVoList = dataVoList;
    }

    public DataVo(String title, Intent intent) {
        this.title = title;
        this.intent = intent;
    }

    public DataVo(String title, Intent intent, List<DataVo> dataVoList) {
        this.title = title;
        this.intent = intent;
        this.dataVoList = dataVoList;
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

    public List<DataVo> getDataVoList() {
        return dataVoList;
    }

    public void setDataVoList(List<DataVo> dataVoList) {
        this.dataVoList = dataVoList;
    }
}
