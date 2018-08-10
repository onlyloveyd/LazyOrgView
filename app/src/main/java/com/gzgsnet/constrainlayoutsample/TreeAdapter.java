package com.gzgsnet.constrainlayoutsample;

import android.support.annotation.NonNull;
import android.support.percent.PercentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Description
 *
 * @author yidong (yidong@gz.csg.cn)
 * @date 2018/8/9 10:18
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeViewHolder> {

    private List<TreeNode> data;
    private RecyclerView parentRecyclerView;

    public TreeAdapter(List<TreeNode> datas) {
        data = new ArrayList<>();
        data.addAll(datas);
    }

    @Override
    public TreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentRecyclerView = (RecyclerView) parent;
        View container = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null);
        return new TreeViewHolder(container);
    }

    @Override
    public void onBindViewHolder(TreeViewHolder holder, int position) {
        TreeNode node = data.get(position);
        if (node.isCoStar()) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.textView.setText(node.getNodeEntity().toString());
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class TreeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TreeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name);
        }
    }

    public List<TreeNode> getData() {
        return data;
    }

    public void setData(List<TreeNode> tmp) {
        data.clear();
        data.addAll(tmp);
        notifyDataSetChanged();
    }

}
