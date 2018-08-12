package cn.onlyloveyd.lazyorgview.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 文 件 名: TreeAdapter
 * 创建日期: 2018/08/11 07/59
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 * @author yidong
 */
public class TreeNode<T> implements Serializable {
    public boolean isRoot = false;
    public int spanSize = 1;
    private int depth;

    private TreeNode parentNode;
    private T nodeEntity;
    private List<TreeNode> childNodes;

    /**
     * 是否为补充节点
     */
    private boolean isCoStar = false;

    public TreeNode(T nodeEntity) {
        this.nodeEntity = nodeEntity;
        childNodes = new ArrayList<>();
    }

    public TreeNode(boolean isCoStar) {
        this.isCoStar = isCoStar;
        childNodes = new ArrayList<>();
    }

    public boolean isCoStar() {
        return isCoStar;
    }

    public void setCoStar(boolean coStar) {
        isCoStar = coStar;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addChildNode(TreeNode childNode) {
        childNode.setParentNode(this);
        if (this.childNodes == null) {
            this.childNodes = new ArrayList<>();
        }
        this.childNodes.add(childNode);
    }

    public void removeChildNode(TreeNode childNode) {
        if (this.childNodes != null) {
            this.childNodes.remove(childNode);
        }
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public T getNodeEntity() {
        return nodeEntity;
    }

    public void setNodeEntity(T nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    public List<TreeNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TreeNode> childNodes) {
        this.childNodes = childNodes;
    }

    public int getLeafChildrenCount() {
        int count = 0;
        if (isLeaf()) {
            return 1;
        }
        for (TreeNode treeNode : childNodes) {
            if (treeNode.isLeaf()) {
                ++count;
            } else {
                count += treeNode.getLeafChildrenCount();
            }
        }
        return count;
    }

    public boolean isLeaf() {
        return childNodes.size() == 0;
    }
}

