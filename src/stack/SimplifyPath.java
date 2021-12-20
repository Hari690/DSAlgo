package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert
 * it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such
 * as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or
 * double period '..')
 * Return the simplified canonical path.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        String[] parts = path.split("/");
        Set<String> skip = Set.of("",".");

        for(int i=0;i<parts.length;i++) {
            String dir = parts[i];
            if (dir.equals("..")) {
                if(!stack.isEmpty())
                    stack.pop();
            }
            else if (!skip.contains(dir))
                stack.push(dir);
        }

        if(stack.isEmpty())
            return "/";

        StringBuilder output = new StringBuilder();
        while(!stack.isEmpty()) {
            output.append("/"+stack.pollLast());
        }
        return output.toString();
    }
}
