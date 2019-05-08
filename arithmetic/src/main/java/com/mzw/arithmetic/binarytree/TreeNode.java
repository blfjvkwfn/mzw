package com.mzw.arithmetic.binarytree;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Data
@RequiredArgsConstructor()
public class TreeNode {
    @NonNull
    private int value;
    private TreeNode leftTreeNode;
    private TreeNode rightTreeNode;
    private boolean isDeleted;
}
