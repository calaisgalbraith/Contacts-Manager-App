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

    //method to get phone number & validate proper format
    public String getNumber(String prompt){
        System.out.println(prompt);
        String phoneNumber = getResponse();

        //check if given phoneNUmber is a valid number
        try {
            long phone = Long.parseLong(phoneNumber);

            //check if length of number is correct (10 or 7 digits) & properly format if so
            if(phoneNumber.length() == 10){
                //add two hyphens
                return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
            }
            else if(phoneNumber.length() == 7){
                //add one hyphen
                return phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
            }
            else {
                System.out.println("Error. Phone number can only be 7 or 10 digits long");
                return getNumber(prompt);
            }

        }catch (Exception e){
            System.out.println("Error. Phone number can only contain digits");
            return getNumber(prompt);
        }
    }
}
