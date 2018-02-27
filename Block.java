import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**This class represents a block of our blockchain
 * 
 * @author Jonathan Gilmour, Zoe Cui
 *
 */
public class Block {
    
     private int number;
     private int data;
     private Hash prevHash;
     private long nonce;
     private Hash thisHash;
    
     /** creates a block and discovers the nonce
      * 
      * @param num number of the block
      * @param amount amount of money transfered
      * @param prevHash previous hash
      * @throws NoSuchAlgorithmException if the algorithm is invalid
      */
    public Block (int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.number = num;
        this.data = amount;
        this.prevHash = prevHash;
        
        long i = 0;
        MessageDigest md = MessageDigest.getInstance("sha-256");
      
        do {
            md.update(ByteBuffer.allocate(4).putInt(num).array());
            md.update(ByteBuffer.allocate(4).putInt(amount).array());
            if(prevHash != null) {
                md.update(prevHash.getData());
            }
            md.update(ByteBuffer.allocate(8).putLong(i).array());
            thisHash = new Hash(md.digest());
            nonce = i;
            i++;
        } while(!(this.thisHash.isValid())); 
        
    }

   /** creates a block
     * 
     * @param num number of the block
     * @param amount amount of money transfered
     * @param prevHash previous hash
     * @param nonce the nonce
     * @throws NoSuchAlgorithmException if the algorithm is invalid
     */
    public Block (int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        number = num;
        data = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(ByteBuffer.allocate(4).putInt(num).array());
        md.update(ByteBuffer.allocate(4).putInt(amount).array());
        if(prevHash != null) {
           md.update(prevHash.getData());
        }
        md.update(ByteBuffer.allocate(4).putLong(nonce).array());
        thisHash = new Hash(md.digest());
    }
    
    public int getNum() {
        return this.number;
    }
    
    public int getAmount() {
        return this.data;
    }
    
    public long getNonce() {
        return this.nonce;
    }
    
    public Hash getPrevHash() {
        return this.prevHash;
    }
    
    public Hash getHash() {
        return this.thisHash;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block ");
        sb.append(getNum());
        sb.append(" (Amount: ");
        sb.append(getAmount());
        sb.append(", Nonce: ");
        sb.append(getNonce());
        sb.append(", prevHash: ");
        sb.append(getPrevHash());
        sb.append(", hash: ");
        sb.append(getHash());
        sb.append(")");
        
        return sb.toString();
    }
}
