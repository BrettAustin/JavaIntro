public class SimpleObject {
    private String name;
    private int cost;
    private static final String DEFAULT_NAME = "Frank";
    private static final int DEFAULT_COST = 1;

    public SimpleObject(){
        this(DEFAULT_NAME,DEFAULT_COST);
    }

    public SimpleObject(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    private class Node<T>{
        public T node;
        public Node next;

        public Node(T data){
            this(data,null);
        }

        public Node(T data,Node next){
            this.node = data;
            this.next = next;
        }

        public T getNode(){
            return node;
        }

        public void setNode(T newNode){
            this.node = newNode;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node newNext){
            this.next = newNext;
        }
    }
}


