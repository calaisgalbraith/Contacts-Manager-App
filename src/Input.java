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

    //method to get boolean
    public boolean getAnswer(String prompt){
        System.out.println(prompt);
        String answer = getResponse();
        return answer.equals("y") || answer.equals("Y");
    }

    //method to get string
    public String getResponse(){
        return scanner.nextLine();
    }

    //method to get string w/ prompt
    public String getResponse(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
