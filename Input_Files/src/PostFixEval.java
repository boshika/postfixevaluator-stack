/**
 *
 * Converts postfix expression into machine instructions.
 *
 * Expressions can be evaluated either from a text file, or can be supplied directly
 * on the commmand line for conversion.
 *
 */


import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.util.EmptyStackException;

public class PostFixEval {
    public static void main(String[]args){
        boolean isFileInput = false;
        for (int i=0; i < args.length; i++) {
            isFileInput = new File(args[i]).exists();

            if (isFileInput){
                System.out.println("#################");
                System.out.println("file name: " + args[i]);
                System.out.println("#################");
                parseFromFile(args[i]);
            } else {
                System.out.println("#################");
                System.out.println("expression: " + args[i]);
                System.out.println("#################");
                try{
                    translate(args[i]);
                } catch (Bad e) {
                    System.err.println(e);
                }
            }
            System.out.println("##### Finished #####");
        }
    }

    public static void parseFromFile(String filename) {
        String line = null;
        int lineCounter = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                while ((line = br.readLine()) != null) {
                    lineCounter++;
                    System.out.println("############# line " + lineCounter +
                            "############found Postfix expression: " + line);

                    try {
                        translate(line);
                    } catch (Bad e){
                        System.err.println("ERROR: Invalid expression encountered: exiting");
                        System.err.println(e);
                    }

                    System.out.println("\n");
                }

                br.close();
            } catch (java.io.IOException e) {
                System.out.println("EOF encountered");
            }

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Cannot find file: " + filename);
            return;
        }
    }

    private static boolean isOperator(String character) {
        return (character.equals("+") ||
                character.equals("-") ||
                character.equals("*") ||
                character.equals("/") ||
                character.equals("$"));
    }

    private static String selectCMD(String operator) {
        if (operator.equals("+")) {
            return "AD";
        } else if (operator.equals("-")) {
            return "SB";
        } else if (operator.equals("*")) {
            return "ML";
        } else if (operator.equals("/")) {
            return "DV";
        } else if (operator.equals("$")) {
            return "PW";
        } else {
            return "BadOperator";
        }

    }

    public static void translate(String expression)
            throws Bad {

        Stack variables = new Stack();
        int tempNum = 1;
        String arg1;
        String arg2;
        String op;
        String command;

        System.out.println("######### Translating: " + expression + " into machine code.");

        if (expression.trim().length() == 0) {
            throw new Bad("EMPTY STACK");
        }


        for (int i=0; i<expression.length(); i++){
            //if operator, pop arguements and evaluate
            if (isOperator(expression.substring(i, i+1))){
                if (variables.isEmpty()) {
                    throw new Bad("EMPTY STACK");
                }

                command = selectCMD(expression.substring(i, i+1));

                try {
                    arg1 = variables.pop();
                } catch (EmptyStackException e) {
                    throw new Bad("EMPTY STACK ON ARG1");
                }

                try {
                    arg2 = variables.pop();
                } catch (EmptyStackException e) {
                    throw new Bad("EMPTY STACK ON ARG2");
                }

                variables.push("TEMP" + tempNum);
                System.out.println("LD " + arg2);
                System.out.println(command + " " + arg1);
                System.out.println("ST " + "TEMP" + tempNum);
                tempNum++;


            } else if (Character.isLetter(expression.charAt(i))) {
                //if variable, push onto stack
                variables.push(expression.substring(i, i+1));
            } else if (Character.isWhitespace(expression.charAt(i))) {
                //Ignore whitespace
                continue;
            } else {
                throw new Bad("Unrecognized characterr: " +
                        expression.charAt(i) +
                        " at position: " + i);
            }
        }

        if (variables.isEmpty() == false) {
            while (variables.isEmpty() == false) {
                if (variables.pop().startsWith("TEMP") == false){
                    throw new Bad("Leftover variables after evaluation.");
                }
            }
        }

    }
}


