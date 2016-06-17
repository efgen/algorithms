package net.efgen.algorithms;

public class SuffixAutomata {
    private Node root;
    private Node last;

    private class Node {
        Node link;
        Node[] next;
        int len;
        boolean terminal;

        Node(Node o, int len) {
            next = o.next.clone();
            this.len = len;
        }

        Node(int len) {
            next = new Node[26];
            this.len = len;
        }
    }

    public SuffixAutomata(String s) {
        last = root = new Node(0);
        for (char c : s.toCharArray()) {
            addChar(c);
        }
        addTerminal();
    }

    private void addChar(char symbol) {
        int c = symbol - 'a';
        Node cur = last;
        last = new Node(cur.len + 1);
        while (cur != null && cur.next[c] == null) {
            cur.next[c] = last;
            cur = cur.link;
        }

        if (cur != null) {
            Node q = cur.next[c];
            if (q.len == cur.len + 1) {
                last.link = q;
            } else {
                Node r = new Node(q, cur.len + 1);
                r.link = q.link;
                q.link = r;
                last.link = r;
                while (cur != null && cur.next[c] == q) {
                    cur.next[c] = r;
                    cur = cur.link;
                }
            }
        } else {
            last.link = root;
        }
    }

    private void addTerminal() {
        Node cur = last;
        while (cur != null) {
            cur.terminal = true;
            cur = cur.link;
        }
    }
}
