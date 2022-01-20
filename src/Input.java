//class to get and validate user Input

import java.util.Scanner;

public class Input {

    private final Scanner scanner = new Scanner(System.in);

    //method to get integer
    public int getInt(String prompt, int min, int max){
        System.out.println(prompt);

        int number;
        try{
            number = Integer.parseInt(scanner.nextLine());

            if(number > max || number < min){
                System.out.println("Error! Number is not within given range");
                System.out.println("Please enter a number between " + min + " and " + max);
                return getInt(prompt, min, max);
            }
            else{
                return number;
            }
        }catch (Exception e){
            System.out.println("Error! Input is not a valid integer");
            return getInt(prompt, min, max);
        }
    }


}
