package trees;

public class FindNodeInClone {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target)
            return cloned;
        TreeNode res = getTargetCopy(original.left, cloned.left, target);
        if (res != null)
            return res;
        return getTargetCopy(original.right, cloned.right, target);
    }

    public static void main(String[] args) {
        new FindNodeInClone().getTargetCopy(new TreeNode(3), new TreeNode(3), new TreeNode(3));
    }
}
