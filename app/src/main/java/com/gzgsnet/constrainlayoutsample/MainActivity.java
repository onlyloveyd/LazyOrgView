package com.gzgsnet.constrainlayoutsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * @author Mraz
 */
public class MainActivity extends AppCompatActivity {

    List<TreeNode> mData;

    HashMap<Integer, Integer> depthCountMap;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    Queue<TreeNode> tmpQueue;

    private int maxLeaf = 0;
    private int maxLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_content);

        mData = new ArrayList<>();
        depthCountMap = new HashMap<>();
        tmpQueue = new ArrayDeque<>();


        TreeNode root = new TreeNode("贵阳供电局");
        root.isRoot = true;

        TreeNode A = new TreeNode("输电管理所");
        TreeNode Aa = new TreeNode("线路运检一班");
        TreeNode Ab = new TreeNode("线路运检二班");
        TreeNode Ac = new TreeNode("线路运检三班");
        TreeNode Ad = new TreeNode("线路运检四班");
        TreeNode Ae = new TreeNode("线路运检五班");

        TreeNode B = new TreeNode("输电管理所2");
        TreeNode Ba = new TreeNode("线路运检一班1");
        TreeNode Bb = new TreeNode("线路运检二班2");

        root.addChildNode(A);
        root.addChildNode(B);

        A.addChildNode(Aa);
        A.addChildNode(Ab);
        A.addChildNode(Ac);
        A.addChildNode(Ad);
        A.addChildNode(Ae);

        B.addChildNode(Ba);
        B.addChildNode(Bb);


        arrangeData(root);
        addEmptyData(root);
        calData(root);
        calSpan(mData);

        TreeAdapter treeAdapter = new TreeAdapter(mData);
        gridLayoutManager = new GridLayoutManager(this, maxLeaf);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mData.get(position).spanSize;
            }
        });
        recyclerView.setAdapter(treeAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new LineGridItemDecoration(this));
        recyclerView.addItemDecoration(new SpaceItemDecoration(4, 48));
    }

    private void arrangeData(TreeNode root) {
        if (root.getChildNodes().size() == 0) {
            ++maxLeaf;
        }
        if (root.isRoot) {
            root.setDepth(1);
            depthCountMap.put(1, 1);
        }
        int childrenSize = root.getChildNodes().size();
        for (int i = 0; i < childrenSize; i++) {
            TreeNode treeNode = (TreeNode) root.getChildNodes().get(i);
            int currentLevel = root.getDepth() + 1;
            treeNode.setDepth(currentLevel);

            if (depthCountMap.get(currentLevel) == null) {
                depthCountMap.put(currentLevel, 1);
            } else {
                depthCountMap.put(currentLevel, depthCountMap.get(currentLevel) + 1);
            }
            if (currentLevel > maxLevel) {
                maxLevel = currentLevel;
            }
            arrangeData(treeNode);
        }
    }

    private void addEmptyData(TreeNode root) {
        if (root.isLeaf() && root.getDepth() < maxLevel) {
            TreeNode node = new TreeNode(true);
            node.setDepth(root.getDepth() + 1);
            addEmptyData(node);
            root.addChildNode(node);
        } else {
            for (int i = 0; i < root.getChildNodes().size(); i++) {
                addEmptyData((TreeNode) root.getChildNodes().get(i));
            }
        }
    }

    private void calSpan(List<TreeNode> datas) {
        for (TreeNode node : mData) {
            if (node.isLeaf()) {
                node.spanSize = 1;
            } else {
                node.spanSize = node.getLeafChildrenCount();
            }
        }
    }

    private void calData(TreeNode root) {
        tmpQueue.add(root);
        mData.add(root);
        while (tmpQueue.size() != 0) {
            TreeNode tempNode = tmpQueue.poll();
            if (tempNode.getChildNodes() != null) {
                List<TreeNode> list = tempNode.getChildNodes();
                for (int i = 0; i < list.size(); i++) {
                    tmpQueue.add(list.get(i));
                    mData.add(list.get(i));
                }
            }
        }
    }
}
