/**
 *
 * Converts postfix expression into machine instructions.
 *
 * Expressions can be evaluated either from a text file, or can be supplied directly
 * on the commmand line for conversion.
 *
 */



public class PostFixEval {
    public static void main(String[]args){
        boolean isFileInput = false;

        for(int i = 0; i < args.length; i++) {
            isFileInput = new File(args[i].exists());

            if(isFileInput) {
                System.out.println("##############################");
                System.out.println("File name" + args[i]);
                System.out.println("##############################");
            } else {
                System.out.println("##############################");
                System.out.println("Given Expression" + args[i]);
                System.out.println("##############################");
            }

            System.out.println("########FINISHED PARSING ALL EXPRESSION########");
        }
    }
}
