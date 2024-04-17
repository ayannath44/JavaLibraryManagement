import java.io.*;
import java.util.ArrayList;
import Constants.*;

public class LibraryBookRecords implements Serializable {
    //variables
    public String bookName, bookAuthor;
    int bookQuantity,number;

    @Serial
    private static final long serialVersionUID = 1L;

    //Arraylist for books info

    ArrayList<LibraryBookRecords> booksArrayList = new ArrayList<LibraryBookRecords>();

    //File of Books
    File bookData = new File(constants.pathBookData);

    //Constructors
    LibraryBookRecords(String bookName, String bookAuthor, int bookQuantity){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookQuantity = bookQuantity;
    }
    LibraryBookRecords(String bookName){
        this.bookName = bookName;
    }

    LibraryBookRecords(){
    }
    LibraryBookRecords(String bookName, String bookAuthor,int slNum,int number){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.number = number;
    }

    //Adding books through addbook button
    public void addBooks(){

        boolean firstBookEntry = true;
        if (bookData.exists()){
            firstBookEntry = false;
        }
        if(firstBookEntry){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookData,true))){
                oos.writeObject(this);
                popUP z = new popUP("Book Added Successfully", "Ok", "");
                firstBookEntry = false;
            }catch (Exception k){
                k.printStackTrace();
            }
        }
        else{
            boolean exists = searchBook(this.bookName,this.bookAuthor);
            if(!exists){
                try(AppendingObjectOutputStream oos = new AppendingObjectOutputStream(new FileOutputStream(bookData,true))){
                    oos.writeObject(this);
                    popUP z = new popUP("Book Added", "Go Back", "");
                }catch (Exception k){
                    k.printStackTrace();
                }
            }
        }
    }

    //to read from file to an array
    public void fileToArray(){
        try(ObjectInputStream oiss = new ObjectInputStream(new FileInputStream(constants.pathBookData))) {
            while (true){
                try {
                    LibraryBookRecords bookObj = (LibraryBookRecords) oiss.readObject();
                    booksArrayList.add(bookObj);
                }catch (EOFException EOF){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //To check Book already exists or not
    public boolean searchBook(String bName, String bAuth){

        fileToArray();
        boolean isFound = false;
        for (int i=0; i<booksArrayList.size(); i++){
            if(booksArrayList.get(i).bookName.compareToIgnoreCase(bName) == 0 && booksArrayList.get(i).bookAuthor.compareToIgnoreCase(bAuth) == 0){
                popUP g = new popUP("Already Exists go to search", "Go Back", "");
                isFound = true;
                break;
            }
        }
        return isFound;
    }
    //Arraylist of the books found by book name
    ArrayList<LibraryBookRecords> foundBooks = new ArrayList<>();

    //search from searchbar
    public ArrayList<LibraryBookRecords> searchBookFromSearchBar (){
        fileToArray();
        for(int i=0; i<booksArrayList.size(); i++){
            if(booksArrayList.get(i).bookName.compareToIgnoreCase(this.bookName) == 0){
                foundBooks.add(booksArrayList.get(i));
            }
        }
        return foundBooks;
    }
    //To update Book Quantity
    public void appendData(){
        fileToArray();
        for(int i=0; i<booksArrayList.size(); i++){
            if(booksArrayList.get(i).bookName.compareToIgnoreCase(this.bookName) == 0 && booksArrayList.get(i).bookAuthor.compareToIgnoreCase(this.bookAuthor) == 0){
                booksArrayList.get(i).bookQuantity += this.number;
                arrayToFile(booksArrayList);
                popUP L = new popUP("Quantity Updated","OK","" );
                break;
            }
        }
    }

    // to update book data (Borrow function)
    public void appendDataForBorrow(){
        fileToArray();
        for(int i=0; i<booksArrayList.size(); i++){
            if(booksArrayList.get(i).bookName.compareToIgnoreCase(this.bookName) == 0 && booksArrayList.get(i).bookAuthor.compareToIgnoreCase(this.bookAuthor) == 0){
                if(booksArrayList.get(i).bookQuantity < number){
                    popUP U = new popUP("Book not available", "Ok", "");
                }else {
                    booksArrayList.get(i).bookQuantity -= this.number;
                    arrayToFile(booksArrayList);
                    popUP L = new popUP("Quantity Updated","OK","" );
                    break;
                }
            }
        }
    }
    // To store new array to file
    public void arrayToFile(ArrayList<LibraryBookRecords>A){
        bookData.delete();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(constants.pathBookData))){
            for(LibraryBookRecords bookObject : A){
                oos.writeObject(bookObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
