import java.util.Arrays;

/** Class to create and check the validity of a hash object
 * 
 * @author Jonathan Gilmour, Zoe Cui
 *
 */
public class Hash {
    private byte[] Hash;

    public Hash(byte[] data) {
        this.Hash = data;
    }
    
    public byte[] getData() {
        return this.Hash;
    }
    
    public boolean isValid() {
        for(int i=0; i<3; i++) {
            if(this.Hash[i] != 0) {
               return false; 
            }
        }
        return true;
    }

    /** gets the string representation of the hash
     * 
     * @return String representing the hash
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(byte b : this.Hash) {
            int temp = Byte.toUnsignedInt(b);
            sb.append(Integer.toHexString(temp));
        }
        return sb.toString();
      
    }
    
    /** checks if the object parameter structurally equals to a hash object
     * 
     * @param other an object
     * @return boolean true or false
     */
    public boolean equals(Object other) {
        boolean bool = other instanceof Hash;
        if(bool) {
           Hash o = (Hash) other;
           return Arrays.equals(o.Hash, this.Hash);
       }
        return false;
    }
}
