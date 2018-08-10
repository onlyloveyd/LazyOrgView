package com.gzgsnet.constrainlayoutsample;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * File Name: SpaceItemDecoration
 * Created Date: 2018/3/15 09:49
 * Enterprise Email: yidong@gz.csg.cn
 * Description:
 *
 * @author Mraz
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int horizontalSpace;
    int verticalSpace;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;
        outRect.bottom = verticalSpace;
        outRect.top = verticalSpace;
    }

    public SpaceItemDecoration(int horizontalSpace, int verticalSpace) {
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
    }
}