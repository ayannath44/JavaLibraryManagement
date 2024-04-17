import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserDashboard implements ActionListener{
    Frame uDash   ;
    Button editButton, searchButton, selectBook,exit;
    TextField searchBar;
    List foundBooksList;
    ArrayList<LibraryBookRecords> foundBooks = new ArrayList<>();


    
    public UserDashboard(LibraryRecords profile, String type){


        // for user dashboard
        uDash = new Frame("Welcome to Library");

        Label profileName = new Label(profile.name);
        profileName.setBounds(870, 50, 100,30);
        profileName.setBackground(Color.lightGray);
        uDash.add(profileName);

        Label userType = new Label("(" + type + ")");
        userType.setBounds(900,70,100,30);
        uDash.add(userType);

        editButton = new Button("Edit profile");
        editButton.setBounds(870,110,100,30);
        uDash.add(editButton);

        searchBar = new TextField("Search Book");
        searchBar.setBounds(80,140,600,20);
        searchBar.setBackground(Color.lightGray);
        uDash.add(searchBar);

        searchButton = new Button("Search");
        searchButton.setBounds(700,140,80,20);
        searchButton.setBackground(Color.LIGHT_GRAY);
        uDash.add(searchButton);
        searchButton.addActionListener(this);

        Label l1 = new Label("SL No.");
        l1.setBounds(80,210,50,20);
        uDash.add(l1);

        Label l2 = new Label("Book Name");
        l2.setBounds(200,210,100,20);
        uDash.add(l2);

        Label l3 = new Label("Book Author");
        l3.setBounds(400,210,100,20);
        uDash.add(l3);

        foundBooksList = new List(10, false);
        foundBooksList.setBounds(80,240,600,80);
        uDash.add(foundBooksList);

        selectBook = new Button("Select");
        selectBook.setBounds(450,400,100,20);
        uDash.add(selectBook);
        selectBook.addActionListener(this);

        exit = new Button("EXIT");
        exit.setBounds(450,450,100,20);
        uDash.add(exit);
        exit.addActionListener(this);


        uDash.setSize(1000,800);
        uDash.setLayout(null);
        uDash.setVisible(true);
        uDash.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String bName,  selectedBook;


        if(e.getSource() == searchButton) {
            boolean nullCheck = false;
            foundBooksList.clear();

            bName = searchBar.getText();
            if (bName.equalsIgnoreCase("Search Books")) {
                popUP H = new popUP("Empty field", "Try Again", "ERROR");
                nullCheck = true;
            }
            if (bName.equalsIgnoreCase("")) {
                popUP H = new popUP("Empty field", "Try Again", "ERROR");
                nullCheck = true;
            }
            if (!nullCheck) {
                LibraryBookRecords A = new LibraryBookRecords(bName);
                foundBooks = A.searchBookFromSearchBar();
                int size = foundBooks.size();
                if (size == 0) {
                    foundBooksList.add("No Books Found");
                } else {
                    for (int i = 0; i < size; i++) {
                        foundBooksList.add((i + 1) + "                                   " + foundBooks.get(i).bookName + "                                                        " + foundBooks.get(i).bookAuthor);
                    }
                }
            }
        }
        if(e.getSource()==selectBook){
            try {
                selectedBook = foundBooksList.getItem(foundBooksList.getSelectedIndex());
                int index = selectedBook.indexOf(" ");
                int slNo = Integer.parseInt(selectedBook.substring(0, index));
                popUP x = new popUP(slNo, foundBooks, "user");
            }catch (ArrayIndexOutOfBoundsException AIO){
                popUP J = new popUP("Choose Book First", "OK", "");
            }
        }
        if(e.getSource()==exit){
            uDash.dispose();
        }
    }
}
