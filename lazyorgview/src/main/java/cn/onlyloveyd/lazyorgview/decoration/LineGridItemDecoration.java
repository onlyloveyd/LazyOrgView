package cn.onlyloveyd.lazyorgview.decoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.onlyloveyd.lazyorgview.widget.LazyOrgConfig;
import java.util.ArrayList;
import java.util.List;

import cn.onlyloveyd.lazyorgview.adapter.TreeAdapter;
import cn.onlyloveyd.lazyorgview.adapter.TreeNode;

/**
 * 文 件 名: LazyOrgView
 * 创建日期: 2018/08/11 07/59
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 * @author yidong
 */
public class LineGridItemDecoration extends RecyclerView.ItemDecoration {

    private List<TreeNode> treeNodes;
    private Paint mPaint;
    private LazyOrgConfig config;

    public LineGridItemDecoration(Context context, LazyOrgConfig orgConfig) {
        treeNodes = new ArrayList<>();
        this.config = orgConfig;

        mPaint = new Paint();
        mPaint.setColor(config.getLineColor());
        mPaint.setStrokeWidth(config.getLineWidth());
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        c.save();
        treeNodes.clear();
        treeNodes.addAll(((TreeAdapter) parent.getAdapter()).getData());

        for (int i = treeNodes.size() - 1; i >= 0; i--) {
            TreeNode curNode = treeNodes.get(i);
            TreeAdapter.TreeViewHolder curNodeViewHolder = (TreeAdapter.TreeViewHolder) parent.findViewHolderForAdapterPosition(i);
            int parentNodeIndex = findParentNodePos(curNode);
            if (parentNodeIndex != -1) {
                TreeAdapter.TreeViewHolder parentNodeViewHolder = (TreeAdapter.TreeViewHolder) parent.findViewHolderForAdapterPosition(parentNodeIndex);

                if (curNodeViewHolder != null && parentNodeViewHolder != null) {
                    float startx, starty, endx, endy;
                    startx = curNodeViewHolder.itemView.getLeft() + curNodeViewHolder.itemView.getWidth() / 2;
                    starty = curNodeViewHolder.itemView.getTop();

                    endx = parentNodeViewHolder.itemView.getLeft() + parentNodeViewHolder.itemView.getWidth() / 2;
                    endy = parentNodeViewHolder.itemView.getBottom();

//                    startx = getItemLeftOfRecyclerView(parent, curNodeViewHolder.itemView) + curNodeViewHolder.itemView.getWidth() / 2;
//                    starty = getItemTopOfRecyclerView(parent, curNodeViewHolder.itemView);
//
//                    endx = getItemLeftOfRecyclerView(parent, parentNodeViewHolder.itemView) + parentNodeViewHolder.itemView.getWidth() / 2;
//                    endy = getItemBottomOfRecyclerView(parent, parentNodeViewHolder.itemView);

                    float centeronex, centeroney, centertwox, centertwoy;
                    centeronex = startx;
                    centeroney = (starty + endy) / 2;

                    centertwox = endx;
                    centertwoy = centeroney;

                    c.drawLine(startx, starty, centeronex, centeroney, mPaint);
                    c.drawLine(centeronex, centertwoy, centertwox, centertwoy, mPaint);
                    c.drawLine(centertwox, centertwoy, endx, endy, mPaint);
                }
            }
        }
        c.restore();
    }

    /**
     * ��ȡitemview�����RecyclerView��left
     *
     * @param superParent
     * @return
     */
    private float getItemLeftOfRecyclerView(RecyclerView superParent, View view) {
        if (view.getParent() == superParent) {
            return view.getLeft();
        } else {
            return view.getLeft() + getItemLeftOfRecyclerView(superParent, (View) view.getParent());
        }
    }

    private float getItemTopOfRecyclerView(RecyclerView superParent, View view) {
        if (view.getParent() == superParent) {
            return view.getTop();
        } else {
            return view.getTop() + getItemTopOfRecyclerView(superParent, (View) view.getParent());
        }
    }

    private float getItemBottomOfRecyclerView(RecyclerView superParent, View view) {
        if (view.getParent() == superParent) {
            return view.getBottom();
        } else {
            return view.getBottom() + getItemBottomOfRecyclerView(superParent, (View) view.getParent());
        }
    }

    private int findParentNodePos(TreeNode child) {
        TreeNode parent = child.getParentNode();
        if (parent == null || child.isCoStar()) {
            return -1;
        }
        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNode currentNode = treeNodes.get(i);
            if (parent.equals(currentNode)) {
                return i;
            }
        }
        return -1;
    }

}
