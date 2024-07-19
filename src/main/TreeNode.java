// Sousanna Chugunova
// CMSC204
// Dr. Thai

package main;

// TreeNode class represents a node in a binary tree
public class TreeNode<T> {
    T data; // Data stored in the node
    TreeNode<T> left, right; // Left and right children of the node

    // Constructor to initialize the node with data
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
