import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogIn implements ActionListener {
    Button signInButton, forgotPassword, maintainerButton, createAccount;
    TextField userNameTF, userPasswordTF;
    TextField feedback;
    Frame user;

    public UserLogIn(){
        // For user log in frame
        user = new Frame();


        Label userNameL = new Label("Enter user name");
        userNameL.setBounds(10,60, 100,20);
        user.add(userNameL);

        userNameTF = new TextField(20);
        userNameTF.setBounds(10,85,150,20);
        user.add(userNameTF);

        Label userPasswordL = new Label("Enter Password");
        userPasswordL.setBounds(10,110, 100,20);
        user.add(userPasswordL);

        userPasswordTF = new TextField(20);
        userPasswordTF.setBounds(10,135, 150,20);
        user.add(userPasswordTF);

        signInButton = new Button("Log In");
        signInButton.setBounds(120,180, 60, 20);
        user.add(signInButton);
        signInButton.addActionListener(this);

        createAccount = new Button("Create New Account");
        createAccount.setBounds(50,210, 200, 20);
        user.add(createAccount);
        createAccount.addActionListener(this);

        Label caption1 = new Label("Maintainer log In");
        caption1.setBounds(150,250, 100,20);
        user.add(caption1);

        maintainerButton = new Button(">>");
        maintainerButton.setBounds(250,250, 40, 20);
        user.add(maintainerButton);
        maintainerButton.addActionListener(this);


        feedback = new TextField();                            //testing purpose
        feedback.setBounds(50,200,150,20);
        user.add(feedback);



        user.setSize(300,300);
        user.setVisible(true);
        user.setLayout(null);
        user.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String uName, uPass;


        //sign in button
        if(e.getSource() == signInButton){

            uName = userNameTF.getText();
            uPass = userPasswordTF.getText();
            Boolean check = nullTest(uName,uPass);

            if(!check){
                popUP a = new popUP("Invalid username or password", "Try Again", "Error");
            }
            else{
                LibraryRecords c = new LibraryRecords(uName,uPass,"user");
                c.signUpVerify();
                user.dispose();
            }
        }

        //Maintainer tab button
        if(e.getSource() == maintainerButton){
            MaintainerLogIn B = new MaintainerLogIn();
            user.dispose();
        }

        //Create Account
        if(e.getSource()==createAccount){
            CreateAccounts x = new CreateAccounts("User");
        }
    }

    public Boolean nullTest(String a, String b) {
        if(a.compareToIgnoreCase("")==0 || b.compareToIgnoreCase("")==0){
            return false;
        }
        else if(a.matches(".*\\d.*")){
            return false;
        }
        else{
            return true;
        }
    }
}
