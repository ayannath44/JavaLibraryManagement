import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MaintainerLogIn implements ActionListener{

    //variables
    Button signInButton, forgotPassword, userButton, createAccount;
    TextField maintainerNameTF, maintainerPasswordTF;
    Frame maintainer;

    public MaintainerLogIn(){
        // For maintainer log in frame
        maintainer = new Frame();

        Label maintainerNameL = new Label("Enter maintainer name");
        maintainerNameL.setBounds(10,60, 150,20);
        maintainer.add(maintainerNameL);

        maintainerNameTF = new TextField(20);
        maintainerNameTF.setBounds(10,85,150,20);
        maintainer.add(maintainerNameTF);

        Label maintainerPasswordL = new Label("Enter Password");
        maintainerPasswordL.setBounds(10,110, 100,20);
        maintainer.add(maintainerPasswordL);

        maintainerPasswordTF = new TextField(20);
        maintainerPasswordTF.setBounds(10,135, 150,20);
        maintainer.add(maintainerPasswordTF);

        signInButton = new Button("Log In");
        signInButton.setBounds(120,180, 60, 20);
        maintainer.add(signInButton);
        signInButton.addActionListener(this);

        createAccount = new Button("Create New Account");
        createAccount.setBounds(50,210, 200, 20);
        maintainer.add(createAccount);
        createAccount.addActionListener(this);

        Label caption1 = new Label("User log In");
        caption1.setBounds(150,250, 100,20);
        maintainer.add(caption1);

        userButton = new Button("->");
        userButton.setBounds(250,250, 40, 20);
        maintainer.add(userButton);
        userButton.addActionListener(this);

        maintainer.add(new Label());

        maintainer.setSize(300,300);
        maintainer.setVisible(true);
        maintainer.setLayout(null);
        maintainer.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        String mName, mPass;

        // for sign in
        if(e.getSource() == signInButton){
            mName = maintainerNameTF.getText();
            mPass = maintainerPasswordTF.getText();
            Boolean check = nullTest(mName,mPass);

            if(!check){
                popUP a = new popUP("Invalid username or password", "Try Again", "Error");
            }
            else{
                LibraryRecords c = new LibraryRecords(mName,mPass,"maintainer");
                c.signUpVerify();
                maintainer.dispose();
            }
        }
        //Create Account
        if(e.getSource()==createAccount){
            CreateAccounts x = new CreateAccounts("maintainer");
        }

        //for user button
        if(e.getSource() == userButton){
            UserLogIn B = new UserLogIn();
            maintainer.dispose();
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
