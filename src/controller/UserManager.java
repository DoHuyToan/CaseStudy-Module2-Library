package controller;

import model.User;
import storage.UserFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements GeneralManager<User> {

    ArrayList<User> userArrayList = new ArrayList<>();
    UserFile userFile = UserFile.getInstance();

    public boolean isLogin(User userLogin) {
        for (User user : userArrayList) {
            if (user.getAccount().equals(userLogin.getAccount())
                    && user.getPassword().equals(userLogin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return userArrayList;
    }

    @Override
    public void add(User user) {
        userArrayList.add(user);
        try {
            userFile.writeFile(userArrayList);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void editByCode(String code, User user) {
        for (int i=0; i<userArrayList.size(); i++){
            if(userArrayList.get(i).getCode().equals(code)){
                userArrayList.set(i, user);
            }
            else System.out.println("Mã code người dùng ko đúng");
        }
    }

    @Override
    public void removeByCode(String code) {
        for (int i=0; i<userArrayList.size(); i++){
            if(userArrayList.get(i).getCode().equals(code)){
                userArrayList.remove(i);
            }
            else System.out.println("Mã code người dùng ko đúng");
        }
    }

    @Override
    public void showAll() {
        for (User u: userArrayList) {
            System.out.println(u);
        }
    }

    @Override
    public User searchByCode(String code) {
        User user = null;
        for (int i=0; i<userArrayList.size(); i++){
            if(userArrayList.get(i).getCode().equals(code)){
                user = userArrayList.get(i);
            }
            else System.out.println("Mã code người dùng ko đúng");
        }
        return user;
    }

    public UserManager(){}

    public UserManager(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

}
