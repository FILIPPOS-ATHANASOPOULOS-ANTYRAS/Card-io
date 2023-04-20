import java.util.ArrayList;
import java.util.List;

public class Node {
    private int cardsToRemove;
    private int group;
    private CardDeck cardDeck;
    private List<Node> children = new ArrayList<Node>();

    public Node(int cardsToRemove) {
        this.cardsToRemove = cardsToRemove;
    }

    public Node(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Node(int cardsToRemove, int group) {
        this.cardsToRemove = cardsToRemove;
        this.group = group;
    }

    public Node(int cardsToRemove, int group , CardDeck cardDeck) {
        this.cardsToRemove = cardsToRemove;
        this.group = group;
        this.cardDeck = cardDeck;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    
    public static void printTree(Node root) {
        printTreeHelper(root, "" ,true);
    }
    
    public static void printTreeHelper(Node node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.cardsToRemove);
        for (int i = 0; i < node.children.size() - 1; i++) {
            Node child = node.children.get(i);
            printTreeHelper(child, prefix + (isTail ? "    " : "│   "), false);
        }
        if (node.children.size() > 0) {
            Node child = node.children.get(node.children.size() - 1);
            printTreeHelper(child, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public void createChildren(){
        
        for(CardGroup group : this.cardDeck.cardGroups){
            int cardsToRemove = 1;
            while(cardsToRemove <= group.getMaxCardsToRemove()){
                CardDeck newCardDeck = this.cardDeck;
                Node child = new Node(cardsToRemove, group.getGroupNumber() , newCardDeck);
                child.cardDeck.getGroup(this.group).removeCards( cardsToRemove);
                this.addChild(child);
                child.createChildren();
                cardsToRemove++;
            }
        }
    }


    public int getCardsToRemove() {
        return this.cardsToRemove;
    }

    public void setCardsToRemove(int cardsToRemove) {
        this.cardsToRemove = cardsToRemove;
    }

    public int getGroup() {
        return this.group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public CardDeck getCardDeck() {
        return this.cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }



    public static void main(String[] args) {
        Node root = new Node(0);
        Node child1 = new Node(1, 1);
        Node child2 = new Node(2, 2);
        Node child3 = new Node(3, 3);
        Node child4 = new Node(4, 4);
        Node child5 = new Node(5, 5);

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        root.addChild(child5);

        Node child11 = new Node(11, 11);
        Node child12 = new Node(12, 12);
        child2.addChild(child12);
        child2.addChild(child11);

        root.printTree(root );
    }

}