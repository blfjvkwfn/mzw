package com.mzw.arithmetic.binarytree;

import java.util.Objects;

/**
 * 二叉树示例
 * @author Jonathan Meng
 * @date 07/05/2019
 */
public class Demo {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(10);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(35);

        treeNode1.setLeftTreeNode(treeNode2);
        treeNode1.setRightTreeNode(treeNode3);

        treeNode3.setLeftTreeNode(treeNode4);
        treeNode3.setRightTreeNode(treeNode5);
        System.out.println("pre traverse");
        preTraverseBTree(treeNode1);
        System.out.println("in traverse");
        inTraverseBTree(treeNode1);
        System.out.println("back traverse");
        backTraverseBTree(treeNode1);

        System.out.println("create tree");
        int[] arrays = {3, 2, 1, 4, 5};
        TreeRoot root = new TreeRoot();
        for (int value : arrays) {
            createTree(root, value);
        }

        preTraverseBTree(root.getTreeRoot());
        inTraverseBTree(root.getTreeRoot());

        System.out.println("height is: " + getHeight(root.getTreeRoot()));
        System.out.println("max is: " + getMax(root.getTreeRoot()));
    }

    private static int getMax(TreeNode treeRoot) {
        if (Objects.isNull(treeRoot)) {
            return -1;
        }

        int left = getMax(treeRoot.getLeftTreeNode());
        int right = getMax(treeRoot.getRightTreeNode());
        int current = treeRoot.getValue();

        int max = (left > right ? left : right);
        return max > current ? max : current;

    }

    /**
     * 查询深度
     *
     * @param treeNode
     * @return
     */
    public static int getHeight(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }

        int left = getHeight(treeNode.getLeftTreeNode());
        int right = getHeight(treeNode.getRightTreeNode());

        return (right > left ? right : left) + 1;
    }

    private static void createTree(TreeRoot treeRoot, int value) {
        if (Objects.isNull(treeRoot.getTreeRoot())) {
            treeRoot.setTreeRoot(new TreeNode(value));
            return;
        }

        TreeNode tempRoot = treeRoot.getTreeRoot();
        while (Objects.nonNull(tempRoot)) {
            if (value > treeRoot.getTreeRoot().getValue()) {
                //右边没有树根，那就直接插入
                if (Objects.isNull(tempRoot.getRightTreeNode())) {
                    tempRoot.setRightTreeNode(new TreeNode(value));
                    return;
                } else {
                    //如果右边有树根，到右边的树根去
                    tempRoot = tempRoot.getRightTreeNode();
                }
            } else {
                if (Objects.isNull(tempRoot.getLeftTreeNode())) {
                    tempRoot.setLeftTreeNode(new TreeNode(value));
                    return;
                } else {
                    tempRoot = tempRoot.getLeftTreeNode();
                }
            }
        }
    }

    /***
     * 先序遍历
     * 先访问根节点，然后访问左节点，最后访问右节点(根->左->右)
     * @param rootTreeNode
     */
    public static void preTraverseBTree(TreeNode rootTreeNode) {
        if (Objects.isNull(rootTreeNode)) {
            return;
        }

        System.out.println(rootTreeNode.getValue());

        preTraverseBTree(rootTreeNode.getLeftTreeNode());
        preTraverseBTree(rootTreeNode.getRightTreeNode());
    }

    /**
     * 中序遍历
     * 先访问左节点，然后访问根节点，最后访问右节点(左->根->右)
     *
     * @param rootTreeNode
     */
    public static void inTraverseBTree(TreeNode rootTreeNode) {
        if (Objects.isNull(rootTreeNode)) {
            return;
        }

        inTraverseBTree(rootTreeNode.getLeftTreeNode());

        System.out.println(rootTreeNode.getValue());

        inTraverseBTree(rootTreeNode.getRightTreeNode());
    }

    /**
     * 后序遍历
     * 先访问左节点，然后访问右节点，最后访问根节点(左->右->根)
     *
     * @param rootTreeNode
     */
    public static void backTraverseBTree(TreeNode rootTreeNode) {
        if (Objects.isNull(rootTreeNode)) {
            return;
        }

        TreeNode leftTreeNode = rootTreeNode.getLeftTreeNode();
        TreeNode rightTreeNode = rootTreeNode.getRightTreeNode();
        if (Objects.nonNull(leftTreeNode)) {
            backTraverseBTree(leftTreeNode);
        }

        if (Objects.nonNull(rightTreeNode)) {
            backTraverseBTree(rightTreeNode);
        }

        System.out.println(rootTreeNode.getValue());
    }
}
