package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * First of all design a trie. For each function maintain two lists - one for function ending at this nodes which have isvar
 * equal to True and one for functions ending at this node and isvar is false.
 *
 * Now, when you traverse the new argument list say I, I, I. For any argument which is not the last arg for instance
 * the first two I in this case, check if the current node has any isVarTrue list.
 *     - Now check if all the successor arg[i:] are same as the current Node we are at. If yes, this means our
 *     isVar condition is satisfied.
 * If it is the last argument, then check for True list + false list.
 */
class Function {
    String name;
    List<String> argumentTypes;
    boolean isVariadic;
    Function(String name, List<String> argumentTypes, boolean isVariadic) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.isVariadic = isVariadic;
    }
}

class TrieNode {
    Map<String,TrieNode> map;
    List<String> isVarList;
    // for the last argument.
    List<String> notIsVarList;
    TrieNode() {
        map = new HashMap<>();
        isVarList = new ArrayList<>();
        notIsVarList = new ArrayList<>();
    }
}

class ConfluentFunctionsMatch {

    public TrieNode register(Set<Function> functionSet)  {
       return createTrie(functionSet);
    }

    public List<String> findMatches(TrieNode root, List<String> argumentTypes) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        for(int i=0; i<argumentTypes.size();i++) {
            String argument = argumentTypes.get(i);
            if(!node.map.containsKey(argument))
                return result;
            else {
                if(i!= argumentTypes.size() && node.map.get(argument).isVarList.size()>0) {
                    if(same(argumentTypes, i, argument))
                        result.addAll(node.isVarList);
                }
                if(i==argumentTypes.size()-1) {
                    if(node.map.get(argument).isVarList.size()>0)
                        result.addAll(node.isVarList);
                    if(node.map.get(argument).notIsVarList.size()>0)
                        result.addAll(node.notIsVarList);
                }
                node = node.map.get(argument);
            }
        }

        return result;
    }

    private boolean same(List<String> argumentTypes, int i, String argument) {
        while(i<argumentTypes.size()) {
            if(argumentTypes.get(i)!=argument)
                return false;
            i++;
        }
        return true;
    }


    private TrieNode createTrie(Set<Function> dictionary) {
        TrieNode root = new TrieNode();
        TrieNode node = root;
        for(Function function : dictionary) {
            for (String argument : function.argumentTypes) {
                if (!node.map.containsKey(argument)) {
                    node.map.put(argument, new TrieNode());
                }
                node = node.map.get(argument);
            }
            if(function.isVariadic)
                node.isVarList.add(function.name);
            else
                node.notIsVarList.add(function.name);
        }
        return root;
    }
}
