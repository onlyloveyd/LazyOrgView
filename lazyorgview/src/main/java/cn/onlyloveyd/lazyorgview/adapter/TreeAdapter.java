package cn.onlyloveyd.lazyorgview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.onlyloveyd.lazyorgview.R;
import cn.onlyloveyd.lazyorgview.widget.LazyOrgConfig;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeViewHolder> {

    private List<TreeNode> data;
    private LazyOrgConfig config;

    private OnItemClickListener mItemClickListener;

    public TreeAdapter(List<TreeNode> datas, LazyOrgConfig orgConfig) {
        this.data = new ArrayList<>();
        this.data.addAll(datas);
        this.config = orgConfig;
    }

    @Override
    public TreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View container = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null);
        return new TreeViewHolder(container);
    }

    @Override
    public void onBindViewHolder(TreeViewHolder holder, final int position) {
        final TreeNode node = data.get(position);
        if (node.isCoStar()) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.textView.setText(node.getNodeEntity().toString());
            holder.textView.setTextSize(config.getTextSize());
            holder.textView.setTextColor(config.getTextColor());
            holder.textView.setBackgroundColor(config.getTextBgColor());
            holder.textView.setBackgroundResource(config.getTextBgRes());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(position, node);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<TreeNode> getData() {
        return data;
    }

    public void setData(List<TreeNode> tmp) {
        data.clear();
        data.addAll(tmp);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.mItemClickListener = l;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, TreeNode node);
    }

    public class TreeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TreeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name);
        }
    }
}
