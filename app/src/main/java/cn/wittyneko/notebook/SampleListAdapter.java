package cn.wittyneko.notebook;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import cn.wittyneko.adapter.CommonAdapter;
import cn.wittyneko.adapter.CommonViewHolder;
import cn.wittyneko.notebook.databinding.ItemSampleListBinding;


/**
 * Created by Brady on 2016/7/8.
 */
public class SampleListAdapter extends DataWrapperAdapter {

    Resources r = Resources.getSystem();

    public SampleListAdapter(Context context) {
        this(context, R.layout.item_sample_list);
    }

    public SampleListAdapter(Context context, int layoutId) {
        super(context, layoutId);
        adapter = new CommonAdapter<DataWrapper>(context, list, layoutId) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, DataWrapper item, int position) {
                holder.itemView.setTag(POSITION, position);
                holder.itemView.setOnClickListener(mOnItemClickListener);
                ItemSampleListBinding binding = holder.getBinding();
                setBackground(binding, item);
                switch (item.getLevel()) {
                    case 1:
                        setStatus(binding, item, 24);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                        break;
                    case 2:
                        setStatus(binding, item, 20);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        break;
                    case 3:
                        setStatus(binding, item, 16);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        break;
                    case 4:
                        setStatus(binding, item, 12);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                        break;
                    default:
                        setStatus(binding, item, 12);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                        break;
                }
                DataVO dataVO = (DataVO) item.getData();
                //StringBuilder str = new StringBuilder();
                //if (item.getChildList()!= null && !item.getChildList().isEmpty())
                //    str.append(item.getLevel()).append("级，列表 ");
                //else
                //    str.append(item.getLevel()).append("级，条目 ");
                //str.append(dataVO.getTitle());
                binding.titleTv.setText(dataVO.getTitle());
            }
        };
    }

    private void setStatus(ItemSampleListBinding binding, DataWrapper item, float size) {
        if (item.getChildList() != null && !item.getChildList().isEmpty()) {
            if (item.isExpand())
                binding.statusIv.setBackgroundResource(R.drawable.draw_unfold_status_24dp);
            else
                binding.statusIv.setBackgroundResource(R.drawable.draw_fold_status_24dp);
        } else {
            binding.statusIv.setBackgroundResource(R.drawable.draw_star_status_24dp);
        }

        binding.statusIv.getLayoutParams().width = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size, r.getDisplayMetrics());
        binding.statusIv.getLayoutParams().height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size, r.getDisplayMetrics());
    }

    private void setBackground(ItemSampleListBinding binding, DataWrapper item){
        switch (item.getLevel() % 6){
            case 1:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level1));
                break;
            case 2:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level2));
                break;
            case 3:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level3));
                break;
            case 4:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level4));
                break;
            case 5:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level5));
                break;
            case 0:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level6));
                break;
            case 7:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level7));
                break;
            case 8:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level8));
                break;
            case 9:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level9));
                break;
            case 10:
                binding.getRoot().setBackgroundColor(mContext.getResources().getColor(R.color.blue_level10));
                break;
        }
    }

    @Override
    protected void onItemClick(View view, int position) {
        Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean onItemLongClick(View view, int position) {
        return false;
    }

    @Override
    protected void onChildClick(View view, int position) {

    }

    @Override
    protected boolean onChildLongClick(View view, int position) {
        return false;
    }
}
