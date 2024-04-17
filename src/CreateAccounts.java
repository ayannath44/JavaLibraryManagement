import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateAccounts implements ActionListener{
    Frame CAccounts;
    TextField nameTF, addressTF, ageTF, password1TF, password2TF;
    Button signUpButton, exit;
    CheckboxGroup cbg;
    Checkbox checkBox1,checkBox2;

    String genderS,accountType;

    public CreateAccounts(String type){
        CAccounts = new Frame("Create Account");

        CAccounts.add(new Label("Sign Up"));
        accountType = type;

        Label nameL = new Label("Enter your name: ");
        nameL.setBounds(10,50,150,20);
        CAccounts.add(nameL);

        nameTF = new TextField();
        nameTF.setBounds(170,50,200,20);
        CAccounts.add(nameTF);

        Label addressL = new Label("Enter your address: ");
        addressL.setBounds(10,90,150,20);
        CAccounts.add(addressL);

        addressTF = new TextField();
        addressTF.setBounds(170,90,200,20);
        CAccounts.add(addressTF);

        Label genderL = new Label("Enter your gender: ");
        genderL.setBounds(10,130,150,20);
        CAccounts.add(genderL);

        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox checkBox1 = new Checkbox("Male", cbg, false);
        checkBox1.setBounds(200,130, 100,20);
        Checkbox checkBox2 = new Checkbox("Female", cbg, false);
        checkBox2.setBounds(200,160, 100,20);
        CAccounts.add(checkBox1);
        CAccounts.add(checkBox2);
        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                genderS = "Male";
            }
        });
        checkBox2.addItemListener(new ItemListener() {
                                      public void itemStateChanged(ItemEvent e) {
                                          genderS = "Female";
                                      }
        });
        Label ageL = new Label("Enter your age: ");
        ageL.setBounds(10,210,200,20);
        CAccounts.add(ageL);

        ageTF = new TextField();
        ageTF.setBounds(220,210,40,20);
        CAccounts.add(ageTF);

        Label passwordL1 = new Label("Enter a password: ");
        passwordL1.setBounds(10,250,200,20);
        CAccounts.add(passwordL1);

        password1TF = new TextField();
        password1TF.setBounds(220,250,150,20);
        CAccounts.add(password1TF);

        Label passwordL2 = new Label("Enter password again: ");
        passwordL2.setBounds(10,290,200,20);
        CAccounts.add(passwordL2);

        password2TF = new TextField();
        password2TF.setBounds(220,290,150,20);
        CAccounts.add(password2TF);

        signUpButton = new Button("Sign Up");
        signUpButton.setBounds(100,350,100,20);
        CAccounts.add(signUpButton);
        signUpButton.addActionListener(this);

        exit = new Button("Exit");
        exit.setBounds(300,350,100,20);
        CAccounts.add(exit);
        exit.addActionListener(this);


        CAccounts.setSize(500,400);
        CAccounts.setLayout(null);
        CAccounts.setVisible(true);
        CAccounts.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== signUpButton){
            if(nameTF.getText() == null || addressTF.getText() == null || ageTF.getText() == null || password1TF.getText() == null || genderS == null){
                popUP L = new popUP("Incomplete field", "OK", "");
            }
            else if(nameTF.getText().matches(".*\\d.*")){
                popUP L = new popUP("Name contains numbers", "OK", "");
            }
            else {
                try {
                    if (password1TF.getText().compareTo(password2TF.getText()) == 0) {
                        LibraryRecords A = new LibraryRecords(nameTF.getText(), addressTF.getText(), genderS, Integer.parseInt(ageTF.getText()), password1TF.getText());
                        if (accountType.compareToIgnoreCase("user") == 0) {
                        A.writeUserFile();
                        } else if (accountType.compareToIgnoreCase("maintainer") == 0) {
                        A.writeMaintainerFile();
                    }
                    CAccounts.dispose();

                    } else {
                    popUP x = new popUP("Password Mismatch", "Try Again", "Error");
                    }
                }catch (NumberFormatException NFE){
                    popUP H = new popUP("Error in Age", "OK", "ERROR");
                }
            }
        }
        if(e.getSource() == exit){
            CAccounts.dispose();
        }
    }

}
