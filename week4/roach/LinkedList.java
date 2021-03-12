import jdk.internal.net.http.common.FlowTube.TubePublisher;

public class LinkedList {

    private static final int DEFAULT_SIZE = 10;

    private final int size;
    private Node head;
    private Node tail;

    private class Node {

        private int data;
        private int nextNode;

        public Node(int initData) {
            this.data = initData;
        }

    }

    public LinkedList() {
        this.size = DEFAULT_SIZE;
    }

    /**
     * List 의 초기 Size 를 입력한다. 초기 Size 를 입력해주지 않을 경우 DEFAULT_SIZE 로 주입된다.
     * 
     * @param size
     */
    public LinkedList(int size) {
        if (size = 0) {
            size = DEFAULT_SIZE;
        }
        size = this.size;
    }

    public void add(int a) {
        Node node = new Node(a);
        if (isExistHeadNode()) {
            head.nextNode = node;
        }
        if (isExistTailNode()) {
            tail = node;
        }
        head = node;
    }

    public void remove(Node node) {
        if (isHeadNode(node)) {
            // nextHeadNode 가 null 이라도 넣어줘도 됨.
            // 왜냐면 그러면 list 자체가 끝이므로
            nextHeadNode = node.nextNode;
            head = nextHeadNode;
        }
        if (!isHeadNode(node)) {
            tail.nextNode = node;
            tail = node;
        }
    }

    /**
     * head 에서부터 탐색해서 찾고자하는 node 가 있는지 탐색한다.
     * 
     * @param findNode
     * @return
     */
    public boolean contatins(Node findNode) {
        return searchList(head, findNode);
    }

    /**
     * 현재 Head Node 가 존재하는지 확인한다.
     * 
     * @return Head 가 있으면 true 없으면 false
     */
    public boolean isExistHeadNode() {
        return this.head != null;
    }

    /**
     * tail 에 Node 가 있는지를 확인한다.
     * 
     * @return
     */
    public boolean isExistTailNode() {
        return this.tail != null;
    }

    /**
     * 현재 노드가 Head Node 인지 확인한다.
     * 
     * @param node
     * @return
     */
    public boolean isHeadNode(Node node) {
        return this.head == node;
    }

    /**
     * 주어진 노드가 tail 노드인지 확인한다.
     * 
     * @param node
     * @return
     */
    public boolean isTailNode(Node node) {
        return this.tail = node;
    }

    /**
     * 시작점 노드에서부터 findNode 까지를 탐색한다.
     * 
     * @param startNode
     * @param findNode
     * @return
     */
    public boolean searchList(Node startNode, Node findNode) {
        Node node = startNode.nextNode;
        if (startNode == findNode) {
            return true;
        }
        if (node != null) {
            searchList(node, findNode);
        }
        return false;
    }

}
