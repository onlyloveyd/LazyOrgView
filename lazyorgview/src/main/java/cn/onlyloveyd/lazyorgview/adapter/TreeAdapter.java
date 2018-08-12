package cn.onlyloveyd.lazyorgview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.onlyloveyd.lazyorgview.R;
import cn.onlyloveyd.lazyorgview.widget.LazyOrgConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: TreeAdapter
 * 创建日期: 2018/08/11 07/59
 * 邮 箱: onlyloveyd@gmail.com
 * 博 客: https://onlyloveyd.cn
 * 描 述：
 * @author yidong
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeViewHolder> {

  private List<TreeNode> data;
  private LazyOrgConfig config;

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
  public void onBindViewHolder(TreeViewHolder holder, int position) {
    TreeNode node = data.get(position);
    if (node.isCoStar()) {
      holder.itemView.setVisibility(View.GONE);
    } else {
      holder.textView.setText(node.getNodeEntity().toString());
      holder.textView.setTextSize(config.getTextSize());
      holder.textView.setTextColor(config.getTextColor());
      holder.textView.setBackgroundColor(config.getTextBgColor());
      holder.textView.setBackgroundResource(config.getTextBgRes());
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class TreeViewHolder extends RecyclerView.ViewHolder {
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
