package cn.onlyloveyd.lazyorgview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

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