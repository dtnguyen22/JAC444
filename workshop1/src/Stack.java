public class Stack {
    static char[] dataStack;
    static int top = 0;

    public static void push(char aChar) {
        Stack.dataStack[top] = aChar;
        Stack.top++;
    }

    public static char pop() {
        Stack.top--;
        return Stack.dataStack[top];
        //after taking the last element of the array, we reduce its size
    }

    public static boolean isPalindrome(char[] charArray) {
        if (charArray.length > 0) {
            Stack.dataStack = new char[charArray.length];
            //push first half of the charArray into Stack
            for(int i = 0; i < charArray.length; i++){
                Stack.push(charArray[i]);
            }

            for (int i = 0; i< charArray.length;i++){
                //get the first char of charArray and compare it with the last char of Stack
                // this is like we are comparing charArray with a reversed version of itself
                if(charArray[i] != Stack.pop()){
                    return false;
                    //if there is one char that doesnt match, it means this charArray is not a palindrome
                }
            }
        }else{
            return false;
        }
        return true;
    }
}
