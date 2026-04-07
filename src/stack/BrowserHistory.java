package stack;

import java.util.*;

class BrowserHistory {
    Stack<String> back = new Stack<>();
    Stack<String> forward = new Stack<>();
    String curr;

    public BrowserHistory(String homepage) {
        curr = homepage;
    }

    public void visit(String url) {
        back.push(curr);
        curr = url;
        forward.clear(); // 🔥 key
    }

    public String back(int steps) {
        while (steps-- > 0 && !back.isEmpty()) {
            forward.push(curr);
            curr = back.pop();
        }
        return curr;
    }

    public String forward(int steps) {
        while (steps-- > 0 && !forward.isEmpty()) {
            back.push(curr);
            curr = forward.pop();
        }
        return curr;
    }
}
