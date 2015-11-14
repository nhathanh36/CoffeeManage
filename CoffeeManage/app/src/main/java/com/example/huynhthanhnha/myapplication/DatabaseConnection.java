package com.example.huynhthanhnha.myapplication;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanh on 13/11/2015.
 */
public class DatabaseConnection {
    String filePath;
    ObjectContainer db;
    boolean flag;
    public DatabaseConnection(String filePath) {
        this.filePath = filePath + "/coffee_db.db4o";
    }

    public void Open(){
        if(new File(filePath).exists()) flag = true;
        db = Db4oEmbedded.openFile(filePath);
        if(!flag) InitData();
        else System.out.println("File exist");
    }

    private void InitData(){
        List<Permission> listPer = new ArrayList<Permission>();
        Permission per1 = new Permission(1, "Toàn quyền hệ thống");
        Permission per2 = new Permission(2, "Nhân viên");
        listPer.add(per1); listPer.add(per2);

        //List user
        List<User> listUsr = new ArrayList<User>();
        Manager usr1 = new Manager("admin", "admin", "Nguyễn Thanh Phi","362368062");
        Officer usr2 = new Officer("nhanvien", "nhanvien", "Huỳnh Thanh Nhã","365368060");
        listUsr.add(usr1);
        listUsr.add(usr2);

        //Add constraint for relationship
        per1.addUser(usr1); usr1.setPer(per1);
        per2.addUser(usr2); usr2.setPer(per2);

        db.store(listPer);
        db.store(listUsr);
        db.commit();
    }

    public int CheckLogin(final String username, final String password){
        ObjectSet<User> users = db.query(new Predicate<User>(){
            public boolean match(User user){
                return user.getUsername().equals(username) && user.getPassword().equals(password);
            }
        });
        if(users.size() == 0) return -1;                        //Username and pass incorrect
        else return users.next().getPer().getPermissionId();    //Return number of permission of users
    }

    public void Close(){
        db.close();
    }
}
