import Constants.constants;

import java.io.*;

public class LibraryRecords implements Serializable {

    String name,address,gender,password,userType;
    int age;
    @Serial
    private static final long serialVersionUID = 1L;

    LibraryRecords [] userArray = new LibraryRecords[100];   // to store user info
    LibraryRecords [] maintainerArray = new LibraryRecords[10];   //to store maintainer info

    public LibraryRecords (String name,String address,String gender, int age,String password){
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.password = password;
    }
    public LibraryRecords(String name, String password,String userType){
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    File userData = new File(constants.pathUserData);    // to store user info into file
    File maintainerData = new File(constants.pathMaintainerData);   // to store maintainer info into file

    //To write user file
    public void writeUserFile(){
        boolean firstSignUp = true;
        if(userData.exists()){
            firstSignUp = false;
        }

        if(firstSignUp){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userData,true))){
                oos.writeObject(this);
                popUP z = new popUP("Account Created", "Log In", "");
                firstSignUp = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try(AppendingObjectOutputStream oos = new AppendingObjectOutputStream(new FileOutputStream(userData,true))){
                oos.writeObject(this);
                popUP z = new popUP("Account Created", "Log In", "");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // to write maintainer file
    public void writeMaintainerFile(){

        boolean firstSignUpM = true;
        if(maintainerData.exists()){
            firstSignUpM = false;
        }
        if(firstSignUpM){
            try(ObjectOutputStream oosM = new ObjectOutputStream(new FileOutputStream(maintainerData,true))){
                oosM.writeObject(this);
                popUP z = new popUP("Account Created", "Log In", "");
                firstSignUpM = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try(AppendingObjectOutputStream oosM = new AppendingObjectOutputStream(new FileOutputStream(maintainerData,true))){
                oosM.writeObject(this);
                popUP z = new popUP("Account Created", "Log In", "");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //to check user or maintainer is valid or not
    public void signUpVerify(){
        if(userType.compareToIgnoreCase("user")==0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(constants.pathUserData))) {
                int counter = 0;
                while(true){
                    try{
                        LibraryRecords libObj = (LibraryRecords) ois.readObject();
                        userArray[counter]= libObj;
                        counter++;
                    }catch (EOFException e){
                        break;
                    }
                }
            }catch (Exception l){
                    l.printStackTrace();
            }


            int num = actualValue(userArray);
            boolean flag = false;
            for(int i=0; i<num; i++){
                if((userArray[i].name).compareToIgnoreCase(name)==0 && (userArray[i].password).compareTo(password)==0){
                    UserDashboard g = new UserDashboard(userArray[i],userType);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                popUP a = new popUP("Incorrect username or password","Try Again", "Error");
            }
        }
        if(userType.compareToIgnoreCase("maintainer")==0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(constants.pathMaintainerData))) {
                int counter1 = 0;
                while(true){
                    try{
                        LibraryRecords libObj = (LibraryRecords) ois.readObject();
                        maintainerArray[counter1]= libObj;
                        counter1++;
                    }catch (EOFException e){
                        break;
                    }
                }
            }catch (Exception l){
                l.printStackTrace();
            }

            int num = actualValue(maintainerArray);
            boolean flag = false;
            for(int i=0; i<num; i++){
                if((maintainerArray[i].name).compareToIgnoreCase(name)==0 && (maintainerArray[i].password).compareTo(password)==0){
                    MaintainerDashboard g = new MaintainerDashboard(maintainerArray[i],userType);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                popUP a = new popUP("Incorrect username or password","Try Again", "Error");
            }
        }
    }

    // to determine actual array size
    public int actualValue(LibraryRecords [] tempArray){
        int count = 0;
        for(int k=0; k<tempArray.length; k++){
            if(tempArray[k] == null){
                break;
            }
            count++;
        }
        return count;
    }

}
