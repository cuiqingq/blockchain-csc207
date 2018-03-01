import java.security.NoSuchAlgorithmException;
import java.util.Scanner;



/** Class to create a driver that contains a main function
 * 
 * @author Jonathan Gilmour, Zoe Cui
 *
 */

public class BlockChainDriver {
    
    /**main function takes inputs from the keyboard and outputs to screen, after it completes the execution, main closes the stream
     * 
     * @param args takes one argument from the keyboard as the initial balance
     * @throws NumberFormatException throws an exception if the number format is wrong
     * @throws NoSuchAlgorithmException throws an exception if the algorithm is wrong
     */
    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
        boolean go = true;
        Scanner kb = new Scanner(System.in);
        BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
        Block mined = null;
        while(go) {
            System.out.print("\n" + chain.toString());
            System.out.print("Command? ");
            String command = kb.next();
            
            switch (command) {
            case "help" : System.out.println("Valid commands:\n" + 
                    "    mine: discovers the nonce for a given transaction\n" + 
                    "    append: appends a new block onto the end of the chain\n" + 
                    "    remove: removes the last block from the end of the chain\n" + 
                    "    check: checks that the block chain is valid\n" + 
                    "    report: reports the balances of Alice and Bob\n" + 
                    "    help: prints this list of commands\n" + 
                    "    quit: quits the program\n");
                    break;
            case "quit" : go = false;
                    break;
            case "mine" : System.out.print("Amount transferred? ");
                          mined = chain.mine(Integer.parseInt(kb.next())); //make sure that when we mine a new we don't lose the block
                          System.out.println("amount = " + mined.getAmount() + " nonce = " + mined.getNonce());
                          break;
            case "append" : System.out.print("Amount transferred? ");
                            int amount = Integer.parseInt(kb.next());
                            System.out.print("Nonce? ");
                            long nonce = Long.parseLong(kb.next());
                            if(amount == mined.getAmount() &&  nonce == mined.getNonce()) {
                                   chain.append(mined); 
                            } else {
                                System.out.println("No such mined block, try again");
                            }
                            break;
            case "remove" : chain.removeLast();
                            break;
            case "check" : if(chain.isValidBlockChain()) {
                                System.out.println("Check is valid!");
                            }
                            else {
                                System.out.println("Check is invalid.");
                            }
                            break;
            case "report" : chain.printBalances();
                            break;
            default : System.out.println("Invalid command");
                      break;              
            
            }
        }
        kb.close();
    }

}
