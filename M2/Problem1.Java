package M2;

import java.util.Arrays;

public class Problem1 {
    public static void main(String[] args) {
        //Don't edit anything here
        int[] a1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] a2 = new int[]{0, 1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        int[] a3 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] a4 = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        
        processArray(a1);
        processArray(a2);
        processArray(a3);
        processArray(a4);
    }
    static void processArray(int[] arr){
        System.out.println("Processing Array:" + Arrays.toString(arr));
        System.out.println("Odds output:");
        //TODO add/edit code here //iag8
                                  //IT114-006
                                  //02/07/23
        for (int i=0; i<arr.length; i++){//Creates the forloop that gets variable i set to 0, compares it to the length of processArray(int[arr]) 
                                         //and stops when its no longer smaller, and finally it increments value i each time. 

            if(arr[i]%2 != 0){           //here arr gets the value of i which if we take i being 2, it will be the 3nd value in an array and then divides 
                                         //that value by 2. The % operator divides two numbers and then get the remainder. To find odd numbers we just simply
                                         //have to make sure the remainder is not equal to 0 because all odd numbers can't be divided by 2.

                System.out.print(arr[i]+" ");//Prints the number that passed the if check. At this point only the odd values passed.
            }
        }
        //end add/edit section
        System.out.println();
        System.out.println("End process");
    }
    
}