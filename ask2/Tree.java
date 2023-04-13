import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Tree{
    int cardsToRemove;
    int group;
    List<Tree> children = new LinkedList<>();


    Tree(){
        this.cardsToRemove = 0;
    }

    Tree(int cardsToRemove ){
        this.cardsToRemove = cardsToRemove;
    }

    Tree(int cardsToRemove , int group){
        this.cardsToRemove = cardsToRemove;
        this.group = group;
    }

    Tree(int cardsToRemove, List<Tree> children){
        this.cardsToRemove = cardsToRemove;
        this.children = children;
    }

    public void printTree(Tree root){
        if(root == null) return;
        Queue<Tree> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int length = queue.size();
            for(int i = 0 ; i < length; i++){
                Tree node = queue.poll();
                System.out.print(node.cardsToRemove + " ");
                for(Tree item : node.children){
                    queue.offer(item);
                }
            }
            System.out.println();
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

    public List<Tree> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }



    public  void main(String[] args) {
        Tree root = new Tree(1);
        root.children.add(new Tree(2));
        root.children.add(new Tree(2));
        root.children.add(new Tree(2));
        root.children.get(0).children.add(new Tree(3));
        root.children.get(1).children.add(new Tree(3));
        root.children.get(0).children.add(new Tree(3));


        printTree(root);


        
    }
}