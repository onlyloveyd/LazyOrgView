package cn.onlyloveyd.lazyorgview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import cn.onlyloveyd.lazyorgview.adapter.TreeAdapter;
import cn.onlyloveyd.lazyorgview.adapter.TreeNode;
import cn.onlyloveyd.lazyorgview.decoration.LineGridItemDecoration;
import cn.onlyloveyd.lazyorgview.decoration.SpaceItemDecoration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * 文 件 名: LazyOrgView
 * 创 建 人: 易冬
 * 创建日期: 2018/08/11 07/59
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 * @author yidong
 */
public class LazyOrgView extends RecyclerView {

    /**
     * 部分属性配置
     */
    private LazyOrgConfig lazyOrgConfig;

	/**
	 * 外部是否设置过LazyOrgConfig
	 */
	private boolean isConfiged = false;

    /**
     * 整理后节点数据
     */
    private List<TreeNode> mData;

    /**
     * 每层节点数
     */
    private HashMap<Integer, Integer> depthCountMap;

    private GridLayoutManager gridLayoutManager;

    /**
     * 深度遍历辅助队列
     */
    private Queue<TreeNode> tmpQueue;

    /**
     * 叶子节点总数
     */
    private int maxLeaf = 0;

    /**
     * 最大层级
     */
    private int maxLevel = 0;

    private TreeNode rootNode;


    public LazyOrgView(Context context) {
        super(context);
        init();
    }

    public LazyOrgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LazyOrgView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
    	lazyOrgConfig = new LazyOrgConfig();
        mData = new ArrayList<>();
        depthCountMap = new HashMap<>();
        tmpQueue = new ArrayDeque<>();
    }

	/**
	 * 设置LazyOrgConfig
	 */
	public void setLazyOrgConfig(LazyOrgConfig config){
		isConfiged = true;
		this.lazyOrgConfig = config;
    	if (rootNode != null) {
      		setRootNode(rootNode);
		}
	}

    public void setRootNode(TreeNode root) {
        if(!root.isRoot) {
            throw new IllegalArgumentException("传入的节点不是根结点");
        } else {
        	mData.clear();
        	maxLeaf =0;
        	maxLevel = 0;
        	depthCountMap.clear();

        	this.rootNode = root;

            arrangeTree(root);
            addEmptyNode(root);
            convertToList(root);
            calSpan(mData);

            TreeAdapter treeAdapter = new TreeAdapter(mData, lazyOrgConfig);
            gridLayoutManager = new GridLayoutManager(getContext(), maxLeaf);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mData.get(position).spanSize;
                }
            });
            this.setAdapter(treeAdapter);
            this.setLayoutManager(gridLayoutManager);
            this.addItemDecoration(new LineGridItemDecoration(getContext(), lazyOrgConfig));
            this.addItemDecoration(new SpaceItemDecoration(4, 48));
        }
    }

	/**
	 * 获取最大叶子数，最大层次以及每层节点数
	 * @param root
	 */
	private void arrangeTree(TreeNode root) {
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
			arrangeTree(treeNode);
        }
    }

	/**
	 * 填充空白节点方便后续通过recyclerview展示
	 * @param root
	 */
	private void addEmptyNode(TreeNode root) {
        if (root.isLeaf() && root.getDepth() < maxLevel) {
            TreeNode node = new TreeNode(true);
            node.setDepth(root.getDepth() + 1);
			addEmptyNode(node);
            root.addChildNode(node);
        } else {
            for (int i = 0; i < root.getChildNodes().size(); i++) {
				addEmptyNode((TreeNode) root.getChildNodes().get(i));
            }
        }
    }

	/**
	 * 计算节点Span值方便通过recyclerview展示
	 * @param datas
	 */
	private void calSpan(List<TreeNode> datas) {
        for (TreeNode node : mData) {
            if (node.isLeaf()) {
                node.spanSize = 1;
            } else {
                node.spanSize = node.getLeafChildrenCount();
            }
        }
    }

	/**
	 * Tree转List，方便后续通过recyclerview展示
	 * @param root
	 */
	private void convertToList(TreeNode root) {
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
