import java.security.NoSuchAlgorithmException;

/** Class to create and interact with the BlockChain
 * 
 * @author Jonathan Gilmour, Zoe Cui
 *
 */
public class BlockChain {
    int size;
    Node first;
    Node last;
    
    /** Node class to hold blocks and allow linked-list operations
     */
    static class Node {
        Node prev;
        Block current;
        Node next;
        
        public Node(Block blc) {
            prev = null;
            current = blc;
            next = null;
        }
        
        public Node(Block blc, Node previous) {
            prev = previous;
            current = blc;
            next = null;
        }
    }
    
    /** Initializes the BlockChain
     * 
     * @param initial starting amount of Alice's money
     * @throws NoSuchAlgorithmException
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        Block blc = new Block (0, initial, null);
        Node node = new Node(blc);
        
        this.first = node;
        this.last = node;
        this.size = 0;
    }
    
    /** Mines a block
     * 
     * @param amount money amount to transfer
     * @return the mined Block
     * @throws NoSuchAlgorithmException
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        Block ret = new Block ((size + 1), amount, this.last.current.getHash());
        size++;
        return ret;
    }
    
    public int getSize() {
        return this.size;
    }
    
    /** Appends a block to the blockchain
     * 
     * @param blk Block to be appended
     * @throws IllegalArgumentException
     */
    public void append(Block blk)  throws IllegalArgumentException {
        Node node =  new Node(blk, this.last);
        this.last.next = node;
        this.last = node;
    }
    
    /** Removes the last block of the blockchain if the chain contains more than one block
     * 
     * @return boolean explaining if the removal was successful
     */
    public boolean removeLast() {
        if (first == last) {
            return false;
        } else {
            last = last.prev;
            last.next = null;
            size--;
        }
        return true;
    }
    public Hash getHash() {
        return last.current.getHash();
    }
    
    /** Checks the validity of the blockchain transactions
     * 
     * @return boolean expressing whether transactions are valid
     */
    public boolean isValidBlockChain(){
        int AliceBalance = first.current.getAmount();
        int BobBalance = 0;
        Node temp = first.next;
        do{
            int amt = temp.current.getAmount();
            if(amt < 0 && (-1*amt) <= AliceBalance) {
                BobBalance-= amt;
                AliceBalance+= amt;
            } else if (amt >= 0 && amt <= BobBalance ){
                BobBalance-= amt;
                AliceBalance+= amt;
            } else {
                return false;
            }
            temp = temp.next;
        }while (temp != null);    
        return true;

    }
    
    /** Prints out Alice and Bob's balances
     * 
     */
    public void printBalances() {
        int AliceBalance = first.current.getAmount();
        int BobBalance = 0;
        Node temp = first.next;
        do{
            int amt = temp.current.getAmount();
            if(amt > 0) {
                BobBalance-= amt;
                AliceBalance+= amt;
            } else {
                BobBalance-= amt;
                AliceBalance+= amt;
            }
            temp = temp.next;
        }while (temp != null);
        
        System.out.println("Alice: " + AliceBalance + ", Bob: " + BobBalance);
    }
    
    /** Returns the string representation of the blockchain
     * 
     * @return String representing the blockchain
     */
    public String toString() {
        Node temp = first;
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(temp.current.toString()); 
            sb.append("\n");
            temp = temp.next;
        }while (temp != null);
        
        return sb.toString();
    }
}
