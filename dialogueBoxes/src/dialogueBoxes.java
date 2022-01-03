import javax.swing.*;

public class dialogueBoxes {
    public static void main(String[] args)
    {
        String firstName, lastName;
        firstName = JOptionPane.showInputDialog("What is your first name?");
        lastName =  JOptionPane.showInputDialog("What is your last name?");
        JOptionPane.showMessageDialog(null, "Hello " + firstName +" " + lastName);

        System.exit(0);
    }
}
