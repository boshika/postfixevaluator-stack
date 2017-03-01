# Postfix Expressions to Machine Leanring Instruction-Stack Implementation

### Stacks

The classic definition includes the following four operations:
Push (newEntry) Place a new element into the collection. The value provided becomes the new topmost item in the collection. Usually there is no output associated with this operation. 
Pop () Remove the topmost item from the stack. 
Top () Returns, but does not remove, the topmost item from the stack. 
isEmpty () Determines whether the stack is empty 

These four operations make Stack ADT a perfect choice to translate postfix expressions. As, for each expressions the translation requires evaluating the expressions by iterating over the input string, parsing each operand/operator individually, and converting it and printing the machine learning instructions that evaluate all operations.


1.1 Recursion vs Iterative Approach

While the problem can be solved recursively, it would not be the  best approach, as recursion is requires a lot more computation and memory load. Even though the size of computation in this program is very small, it makes sense to adopt an iterative approach, keeping scalability, and hence viability of the program in mind.

### Design

I designed my algorithm with efficiency in mind, hence I decided to use a fixed-size array. I chose a fixed-size of 100, since it I wanted to set limitations on how big the post-fix expressions can be, subsequently to reduce memory usage. 
I implemented algorithm for both, text file inputs, and also command line inputs. 
To evaluate an expression from the command line

```java
java PostFixEval AB+CD*EFG$HI+/
```

```java
Output
#################
expression: AB+CD*EFG+/
#################
######### Translating: AB+CD*EFG+/ into machine code.
LD A
AD B
ST TEMP1
LD C
ML D
ST TEMP2
LD F
AD G
ST TEMP3
LD E
DV TEMP3
ST TEMP4
##### Finished #####
```
To evaluate an expression from a text file

```java
java PostFixEval ../PostfixMachineLangInput.txt

to send this to an output text file
java PostFixEval ../PostfixMachineLangInput.txt >> ../../Output/output_PostFixEvalInput2.txt

```

```java
#################
file name: ../PostfixMachineLangInput.txt
#################
############# line 1############found Postfix expression: AB+C-
######### Translating: AB+C- into machine code.
LD A
AD B
ST TEMP1
LD TEMP1
SB C
ST TEMP2


############# line 2############found Postfix expression: ABC+-
######### Translating: ABC+- into machine code.
LD B
AD C
ST TEMP1
LD A
SB TEMP1
ST TEMP2


############# line 3############found Postfix expression: AB-C+DEF-+$
######### Translating: AB-C+DEF-+$ into machine code.
LD A
SB B
ST TEMP1
LD TEMP1
AD C
ST TEMP2
LD E
SB F
ST TEMP3
LD D
AD TEMP3
ST TEMP4
LD TEMP2
PW TEMP4
ST TEMP5


############# line 4############found Postfix expression: ABCDE-+$*EF*-
######### Translating: ABCDE-+$*EF*- into machine code.
LD D
SB E
ST TEMP1
LD C
AD TEMP1
ST TEMP2
LD B
PW TEMP2
ST TEMP3
LD A
ML TEMP3
ST TEMP4
LD E
ML F
ST TEMP5
LD TEMP4
SB TEMP5
ST TEMP6


############# line 5############found Postfix expression: ABC+*CBA-+*
######### Translating: ABC+*CBA-+* into machine code.
LD B
AD C
ST TEMP1
LD A
ML TEMP1
ST TEMP2
LD B
SB A
ST TEMP3
LD C
AD TEMP3
ST TEMP4
LD TEMP2
ML TEMP4
ST TEMP5


############# line 6############found Postfix expression: ABC+/CBA*+
######### Translating: ABC+/CBA*+ into machine code.
LD B
AD C
ST TEMP1
LD A
DV TEMP1
ST TEMP2
LD B
ML A
ST TEMP3
LD C
AD TEMP3
ST TEMP4


############# line 7############found Postfix expression: AB-*CBA+-*
######### Translating: AB-*CBA+-* into machine code.
LD A
SB B
ST TEMP1
ERROR: Invalid expression encountered: exiting
Bad: EMPTY STACK ON ARG2


############# line 8############found Postfix expression: ABC-/BA-+/
######### Translating: ABC-/BA-+/ into machine code.
LD B
SB C
ST TEMP1
LD A
DV TEMP1
ST TEMP2
LD B
SB A
ST TEMP3
LD TEMP2
AD TEMP3
ST TEMP4
ERROR: Invalid expression encountered: exiting
Bad: EMPTY STACK ON ARG2


############# line 9############found Postfix expression: ABC+$CBA-+*
######### Translating: ABC+$CBA-+* into machine code.
LD B
AD C
ST TEMP1
LD A
PW TEMP1
ST TEMP2
LD B
SB A
ST TEMP3
LD C
AD TEMP3
ST TEMP4
LD TEMP2
ML TEMP4
ST TEMP5


############# line 10############found Postfix expression: AB0+/CBA+-/
######### Translating: AB0+/CBA+-/ into machine code.
ERROR: Invalid expression encountered: exiting
Bad: Unrecognized characterr: 0 at position: 2

```

### Efficiency and Cost Analysis

The implementation ties in with the stack implementation. Insertions and deletions are always done at one end of the stack, so the cost of the operation is O(1). The complexity to evaluate a postfix expression overall is O(n), since all of this is done in a linear way, in one single loop.






