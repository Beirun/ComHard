import javax.swing.*;
import java.io.*;

public class LoginB {
    String email, userName = "nametest", password, tempString, passwordEntered;

    String[] checkPassword;
    JFrame frame;
    public LoginB(){

    }

    public void fileReader(){
        try {
            File fileCreated = new File("resources/"+userName+".txt");
            if(!fileCreated.exists()){
                System.out.println("test");
            }else {
                FileReader reader = new FileReader(fileCreated);
                int data = reader.read();
                tempString = Character.toString ((char) data);
                data = reader.read();
                while(data != -1){
                    System.out.print((char) data);
                    tempString += Character.toString((char) data);
                    data = reader.read();
                }

                System.out.println("\n\ntesttiti\n"+tempString);
                checkPassword = tempString.split("\n");
                System.out.println("\n\n\n"+checkPassword[1]);
            }
        }catch (IOException ignored){}

    }

    public void passwordCorrect(){
        if(passwordEntered.equals(password)){
            frame.add(new DashboardPanel(frame));
        }
    }

    public static void main(String[] args) {
        LoginB b = new LoginB();

        b.fileReader();
    }
}
