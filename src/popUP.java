import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class popUP implements ActionListener{
    private static Dialog d;
    Button b, addBook,exit,add,borrow;
    Frame f;
    TextField bookNameTF, bookAuthorTF, bookQuantityTF,bookNumberTF;

    // for add book button popUp
    popUP(){
        f = new Frame();
        d = new Dialog(f , "Add Books", true);
        d.setLayout( new BorderLayout() );
        popUP.d.setVisible(false);

        Label bookName = new Label("Enter Book Name: ");
        bookName.setBounds(50,100,100,20);
        d.add(bookName);

        bookNameTF = new TextField();
        bookNameTF.setBounds(50,130,600,20);
        d.add(bookNameTF);

        Label bookAuthor = new Label("Enter Author Name: ");
        bookAuthor.setBounds(50,200,100,20);
        d.add(bookAuthor);

        bookAuthorTF = new TextField();
        bookAuthorTF.setBounds(50,230,600,20);
        d.add(bookAuthorTF);

        bookQuantityTF = new TextField("QTY");
        bookQuantityTF.setBounds(50,300,50,20);
        d.add(bookQuantityTF);

        addBook = new Button("Add");
        addBook.setBounds(450,340,100,20);
        d.add(addBook);
        addBook.addActionListener(this);

        exit = new Button("Exit");
        exit.setBounds(450,400,100,20);
        d.add(exit);
        exit.addActionListener(this);

        d.add(new Label("")); //ignore



        d.setSize(1000,600);
        d.setLocationRelativeTo(f);
        d.setVisible(true);

    }
    // for any error popup or others
    popUP(String errorName, String buttonName,String title) {
        f= new Frame();
        d = new Dialog(f , title, true);
        d.setLayout( new FlowLayout() );
        b = new Button (buttonName);
        //popUP.d.setVisible(false);
        d.add( new Label (errorName));
        d.add(b);
        b.addActionListener(this);
        d.setSize(300,100);
        d.setLocationRelativeTo(f);
        d.setVisible(true);

    }

    //temp arraylist for found books
    ArrayList<LibraryBookRecords> fBooks = new ArrayList<>();
    int slNum;

    //popup for add books or select books from search
    popUP(int slNo, ArrayList <LibraryBookRecords> foundBooks, String type){
        fBooks = foundBooks;
        slNum = slNo;
        f = new Frame();
        d = new Dialog(f ,"", true);
        d.setLayout( new BorderLayout() );

        // maintainer
        if(type.equalsIgnoreCase("maintainer")){
            Label bookInfo = new Label(foundBooks.get(slNo-1).bookName + "          " + foundBooks.get(slNo-1).bookAuthor + "          " + foundBooks.get(slNo-1).bookQuantity);
            bookInfo.setBounds(50,70,300,20);
            d.add(bookInfo);

            bookNumberTF = new TextField();
            bookNumberTF.setBounds(125,120,150,20);
            d.add(bookNumberTF);

            add = new Button("Add");
            add.setBounds(150,200,100,20);
            d.add(add);
            add.addActionListener(this);

            exit = new Button("EXIT");
            exit.setBounds(150,250,100,20);
            d.add(exit);
            exit.addActionListener(this);

            d.add(new Label(""));
        }
        //user
        else if(type.equalsIgnoreCase("user")){
            Label bookInfo = new Label(foundBooks.get(slNo-1).bookName + "          " + foundBooks.get(slNo-1).bookAuthor);
            bookInfo.setBounds(50,70,300,20);
            d.add(bookInfo);

            borrow = new Button("Borrow");
            borrow.setBounds(150,200,100,20);
            d.add(borrow);
            borrow.addActionListener(this);

            exit = new Button("Exit");
            exit.setBounds(150,250,100,20);
            d.add(exit);
            exit.addActionListener(this);

            d.add(new Label(""));
        }

        popUP.d.setVisible(false);
        d.setSize(400,300);
        d.setLocationRelativeTo(f);
        d.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String bName = "", bAuthor = "";
        int bQty = 0;
        if(e.getSource()==b){
            f.dispose();
        }
        if(e.getSource()==addBook){
            try{
                bName = bookNameTF.getText();
                bAuthor = bookAuthorTF.getText();
                if(Objects.equals(bName, "") || Objects.equals(bAuthor, "")){
                    throw new NullValueException();
                }
                bQty = Integer.parseInt(bookQuantityTF.getText());
                LibraryBookRecords A = new LibraryBookRecords(bName, bAuthor, bQty);
                A.addBooks();
            }catch (NullValueException nfe){
                popUP nullValue = new popUP("Empty Field", "Try Again", "");
            }catch (NumberFormatException nve){
                popUP Null = new popUP("Invalid book quantity", "Try Again", "");
            }
        }
        if(e.getSource()==exit){
            d.dispose();
            f.dispose();
        }
        if(e.getSource()==add){
            try {
                int number = Integer.parseInt(bookNumberTF.getText());
                if(number == 0){
                    throw new NumberFormatException();
                }
                LibraryBookRecords N = new LibraryBookRecords(fBooks.get(slNum-1).bookName,fBooks.get(slNum-1).bookAuthor,slNum,number);
                N.appendData();
                d.dispose();
                f.dispose();
            }catch (NumberFormatException nfe){
                popUP L = new popUP("Enter Book Quantity", "Ok", "");
            }
        }
        //borrow function
        if(e.getSource()==borrow){
            int qty = 1;
            LibraryBookRecords N = new LibraryBookRecords(fBooks.get(slNum-1).bookName,fBooks.get(slNum-1).bookAuthor,slNum,qty);
            N.appendDataForBorrow();
            d.dispose();
            f.dispose();
        }
    }
}
