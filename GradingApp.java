import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class GradingApp extends JFrame {
    //Declaring the neccesary variable Labels, Fields an check boxes
    private JLabel nameLabel, surnameLabel, FA1Label, FA2Label, SALabel;
    private JTextField nameField, surnameField, FA1Field, FA2Field, SAField;
    private JButton calcButton, clearButton;
    private JLabel finalScoreLabel;

    public GradingApp(){
        //Setting Up the frame
        setTitle("Student Grading App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));
        setSize(400, 300);

        //Now we create and add Labels and fields for each input type
        //Text fields are empty because they take input from user

        //Name
        nameLabel = new JLabel("Name");
        add(nameLabel);
        nameField= new JTextField();
        add(nameField);
        //Surname
        surnameLabel = new JLabel("Surname");
        add(surnameLabel);
        surnameField = new JTextField();
        add(surnameField);
        //FA1
        FA1Label = new JLabel("Formal Assessment 1");
        add(FA1Label);
        FA1Field = new JTextField();
        add(FA1Field);
        //FA2
        FA2Label = new JLabel("Formal Assessment 2");
        add(FA2Label);
        FA2Field = new JTextField();
        add(FA2Field);
        //SA
        SALabel = new JLabel("Summative Assesment");
        add(SALabel);
        SAField = new JTextField();
        add(SAField);

        //Creating and adding the checkboxes to the fame
        //FA1 check box
        JCheckBox checkbox = new JCheckBox("Wrote all 3 assessments");
        add(checkbox);

        //Creating and adding calculator
        calcButton = new JButton("Calculate Final Score");
        add(calcButton);

        //Creating and adding final mark label
        finalScoreLabel = new JLabel("Final score");
        add(finalScoreLabel);

        //Creating and Adding clear button
        clearButton = new JButton("Clear fields");
        add(clearButton);
        
        //The Logic and calculations
        //Adding action listener to the calculate final score button
        calcButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fetch user input from the field's if all
                String name = nameField.getText();
                String surname = surnameField.getText();
                float FA1 = Float.parseFloat(FA1Field.getText());
                float FA2 = Float.parseFloat(FA2Field.getText());
                float SA = Float.parseFloat(SAField.getText());
                float result;
                String resultStr;
                if(checkbox.isSelected()){
                    result = (float) (((FA1/50)*0.3 + (FA2/30)*0.1 + (SA/70)*0.6)*100);
                    resultStr = String.format("%.2f",result);
                    if(FA1 <= 50 & FA2 <= 30 & SA <= 70){
                        if(result >= 50.0){
                            finalScoreLabel.setText("Final Score: " + resultStr + "%");//Replaces the label for final score
                            JOptionPane.showMessageDialog(null, "Hi " + name + " " + surname +", your final score is " + resultStr +"%. You have passed!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Hi" + name + " " + surname +", your final score is " + resultStr +"%. You have failed!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Final score could not be calculated because the marks you provided out of bounds."+ 
                        "\n Formal Assessment 1: Out of 50 \n Formal Assessment 2: Out of 30 \n Summative Assessment: Out of 75");
                    } 
                }else{
                    JOptionPane.showMessageDialog(null, "Final score could not be calculated because there is a missing mark");
                }
            }
        });
        //Adding event listener for the clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change all field inputs to empty strings to clear
                nameField.setText("");
                surnameField.setText("");
                FA1Field.setText("");
                FA2Field.setText("");
                SAField.setText("");
            }
            
        });
    }
    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new GradingApp().setVisible(true);
            }
        });
    }
}
