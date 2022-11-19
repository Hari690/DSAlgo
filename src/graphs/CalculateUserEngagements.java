package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * At Twitter, we want to drive healthy, valuable conversation. The first part of addressing this challenge is coming up with a way to determine how interactive/engaging a conversation is.
 * If an audience is consistently engaged in a conversation,
 * then the likelihood that it is valuable is much higher. Therefore, we want to figure out how many times a user is engaged in a given conversation.
 * If we represent a conversation as an N-ary tree, then we can define an "engagement" as a user replying to one or more responses from separate users to a Tweet they've made.
 * Given the root node to a conversation tree, determine how many times the author is engaged in the entire conversation.
 * A's tweet
 * B replied (Conversation 1)
 * C replies to B's previous reply
 * and A replies back to C's reply.
 * C replied (Conversation 2)
 * A replies to C's comment
 * <p>
 * and it goes on.....
 * <p>
 * A-> D -> C  engagement 0.
 */
public class CalculateUserEngagements {

    public int calculateEngagements(Node root) {
        return dfs(root, null, root.user);
    }

    private int dfs(CalculateUserEngagements.Node root, String prevUser, String author) {
        if (root == null)
            return 0;

        int result = 0;
        if (root.user.equals(author) && prevUser != null && !prevUser.equals(author))
            result++;

        for (CalculateUserEngagements.Node replyNode : root.replyNodes) {
            result += dfs(replyNode, root.user, author);
        }
        return result;
    }

    public static void main(String[] args) {
        CalculateUserEngagements.Node node1 = new CalculateUserEngagements.Node();
        node1.user = "A";

        CalculateUserEngagements.Node node2 = new CalculateUserEngagements.Node();
        node2.user = "B";

        CalculateUserEngagements.Node node3 = new CalculateUserEngagements.Node();
        node3.user = "A";

        List<CalculateUserEngagements.Node> replies1 = new ArrayList<>();
        replies1.add(node2);
        node1.replyNodes = replies1;

        List<CalculateUserEngagements.Node> replies2 = new ArrayList<>();
        replies2.add(node3);
        node2.replyNodes = replies2;

        CalculateUserEngagements solution = new CalculateUserEngagements();
        System.out.println(solution.calculateEngagements(node1));
        // A - B - A - A - A - B
    }

    static class Node {
        String user;
        List<CalculateUserEngagements.Node> replyNodes = new ArrayList<>();
    }
}
