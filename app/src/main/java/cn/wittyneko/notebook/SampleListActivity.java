package cn.wittyneko.notebook;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.wittyneko.adapter.AdapterListener;
import cn.wittyneko.notebook.databinding.ActivitySampleListBinding;

public class SampleListActivity extends AppCompatActivity {

    ActivitySampleListBinding binding;
    SampleListAdapter sampleListAdapter;
    List<DataVO> dataVOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_list);
        initData();
        initSampleList();
        initListener();
    }

    /**
     * 初始化例子列表
     */
    private void initData() {
        dataVOList = new ArrayList<>();
        //特殊控件
        special();
        //网络
        network();
        //Material Design
        material();
        //测试
        test();
    }

    private void special() {
        List<DataVO> voList = new ArrayList<>();
        dataVOList.add(new DataVO("特殊控件", voList));
    }

    private void network() {
        List<DataVO> voList = new ArrayList<>();
        dataVOList.add(new DataVO("网络请求", voList));
    }

    private void material() {
        List<DataVO> voList = new ArrayList<>();
        dataVOList.add(new DataVO("Material Design", voList));
    }

    private void test() {
        List<DataVO> voList = new ArrayList<>();
        dataVOList.add(new DataVO("测试", voList));
    }

    /**
     * 初始化数据
     */
    private void initSampleList() {
        sampleListAdapter = new SampleListAdapter(this);
        sampleListAdapter.addList(dataVOList, 1, 0);
        binding.listView.setAdapter(sampleListAdapter.adapter);
    }

    private void initListener() {
        sampleListAdapter.setOnItemClickListener(new AdapterListener.OnItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                DataWrapper<DataVO> vo = sampleListAdapter.list.get(position);
                //Log.e("Expand", "-" + vo.isExpand());
                //Log.e("HasChild", "-" + vo.isHasChild());
                if (vo.isHasChild()) {
                    if (!vo.isExpand()) {
                        sampleListAdapter.addList(vo.getChildList(), vo.getLevel() + 1, vo.getPosition());
                    } else {
                        sampleListAdapter.removeList(vo.getLevel(), vo.getPosition());
                    }
                    vo.setExpand(!vo.isExpand());
                    sampleListAdapter.adapter.notifyDataSetChanged();
                } else {
                    Intent intent = vo.getData().getIntent();
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }
}
