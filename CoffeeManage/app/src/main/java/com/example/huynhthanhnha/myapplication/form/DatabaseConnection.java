package com.example.huynhthanhnha.myapplication.form;

import android.support.design.widget.TabLayout;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NguyenThanh on 13/11/2015.
 */
public class DatabaseConnection {
    //String filePath;
    String filePath = "/data/data/com.example.huynhthanhnha.myapplication/files/coffee_db.db4o";
    ObjectContainer db;
    boolean flag;

    public DatabaseConnection(){
        //new File(filePath).delete();
        if(new File(filePath).exists()) flag = true;
        else flag = false;
    }

    public DatabaseConnection(String filePath) {
        //this.filePath = filePath + "/coffee_db.db4o";
        //System.out.println(this.filePath);
    }

    public void Open(){
        db = Db4oEmbedded.openFile(filePath);
        if(!flag) InitData();
    }

    private void InitData(){
        //==============================PERMISSION AND USER================================================
        List<Permission> listPer = new ArrayList<Permission>();
        Permission per1 = new Permission(1, "Toàn quyền hệ thống");
        Permission per2 = new Permission(2, "Nhân viên");
        listPer.add(per1); listPer.add(per2);

        //List user
        List<User> listUsr = new ArrayList<User>();
        Manager usr1 = new Manager("admin", "admin", "Nguyễn Thanh Phi","362368062");

        Officer usr2 = new Officer("nhanvien", "nhanvien", "Huỳnh Thanh Nhã","365368060");
        Officer usr3 = new Officer("tri", "tri", "Nguyễn Minh Trí","365368060");
        Officer usr4 = new Officer("nguyen", "nguyen", "Trương Hoàng Nguyên","365368060");
        Officer usr5 = new Officer("lan", "lan", "Nguyễn Thi Lan","365368060");
        Officer usr6 = new Officer("liet", "liet", "Võ Văn Liệt","365368060");
        Officer usr7 = new Officer("nam", "nam", "Nguyễn Hoài Nam","365368060");

        listUsr.add(usr1);

        listUsr.add(usr2); listUsr.add(usr3); listUsr.add(usr4);
        listUsr.add(usr5); listUsr.add(usr6); listUsr.add(usr7);

        //Add constraint for relationship
        per1.addUser(usr1); usr1.setPer(per1);

        per2.addUser(usr2); usr2.setPer(per2);
        per2.addUser(usr3); usr3.setPer(per2);
        per2.addUser(usr4); usr4.setPer(per2);
        per2.addUser(usr5); usr5.setPer(per2);
        per2.addUser(usr6); usr6.setPer(per2);
        per2.addUser(usr7); usr7.setPer(per2);

        //==============================PRODUCT AND GROUP PRODUCT================================================
        // Group Product
        List<GroupProduct> listGroupProduct = new ArrayList<GroupProduct>();
        GroupProduct gp1 = new GroupProduct(1,"Cafe");
        GroupProduct gp2 = new GroupProduct(2,"Sinh tố");
        GroupProduct gp3 = new GroupProduct(3,"Nước khoáng");
        GroupProduct gp4 = new GroupProduct(4,"Trà-Lipton");
        GroupProduct gp5 = new GroupProduct(5,"Kem chuối");

        // List Product
        List<Product> listProduct = new ArrayList<Product>();
        Product pd1 = new Product(1,"Cafe đá", "Ly");
        Product pd2 = new Product(2,"Cafe sữa", "Ly");
        Product pd3 = new Product(3,"Cafe nóng", "Tách");
        Product pd4 = new Product(4,"Nước khoáng Aquafina", "Chai");
        Product pd5 = new Product(5,"Nước khoáng LaVie", "Chai");
        Product pd6 = new Product(6,"Lipton nóng", "Tách");
        Product pd7 = new Product(7,"Lipton đá", "Ly");
        Product pd8 = new Product(8,"Sinh tố bơ", "Ly");
        Product pd9 = new Product(9,"Sinh tố dâu", "Ly");
        Product pd10 = new Product(10,"Sinh tố đu đủ", "Ly");
        Product pd11 = new Product(11,"Sinh tố cà rốt", "Ly");

        listGroupProduct.add(gp1); listGroupProduct.add(gp2);
        listGroupProduct.add(gp3); listGroupProduct.add(gp4); listGroupProduct.add(gp5);

        listProduct.add(pd1); listProduct.add(pd2); listProduct.add(pd3);
        listProduct.add(pd4); listProduct.add(pd5); listProduct.add(pd6);
        listProduct.add(pd7); listProduct.add(pd8); listProduct.add(pd9);
        listProduct.add(pd10); listProduct.add(pd11);

        //Add constraint for relationship
        gp1.addProduct(pd1); pd1.setGroupProduct(gp1);
        gp1.addProduct(pd2); pd2.setGroupProduct(gp1);
        gp1.addProduct(pd3); pd3.setGroupProduct(gp1);

        gp2.addProduct(pd8);  pd8.setGroupProduct(gp2);
        gp2.addProduct(pd9);  pd9.setGroupProduct(gp2);
        gp2.addProduct(pd10); pd10.setGroupProduct(gp2);
        gp2.addProduct(pd11); pd11.setGroupProduct(gp2);

        gp3.addProduct(pd4); pd4.setGroupProduct(gp3);
        gp3.addProduct(pd5); pd5.setGroupProduct(gp3);

        gp4.addProduct(pd6); pd6.setGroupProduct(gp4);
        gp4.addProduct(pd7); pd7.setGroupProduct(gp4);

        //==============================DATE AND LIST PRICE================================================
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 11, 15);
        DateClass date = new DateClass(cal);

        List<ListPrice> listPrices = new ArrayList<ListPrice>();
        ListPrice price1 = new ListPrice(date, pd1, 7000);
        ListPrice price2 = new ListPrice(date, pd2, 8000);
        ListPrice price3 = new ListPrice(date, pd3, 9000);
        ListPrice price4 = new ListPrice(date, pd4, 10000);
        ListPrice price5 = new ListPrice(date, pd5, 11000);
        ListPrice price6 = new ListPrice(date, pd6, 12000);
        ListPrice price7 = new ListPrice(date, pd7, 13000);
        ListPrice price8 = new ListPrice(date, pd8, 14000);
        ListPrice price9 = new ListPrice(date, pd9, 15000);
        ListPrice price10 = new ListPrice(date, pd10, 16000);
        ListPrice price11 = new ListPrice(date, pd11, 17000);

        listPrices.add(price1); listPrices.add(price2); listPrices.add(price3);
        listPrices.add(price4); listPrices.add(price5); listPrices.add(price6);
        listPrices.add(price7); listPrices.add(price8); listPrices.add(price9);
        listPrices.add(price10); listPrices.add(price11);

        pd1.addPrice(price1); pd2.addPrice(price2); pd3.addPrice(price3); pd4.addPrice(price4);
        pd5.addPrice(price5); pd6.addPrice(price6); pd7.addPrice(price7); pd8.addPrice(price8);
        pd9.addPrice(price9); pd10.addPrice(price10); pd11.addPrice(price11);

        //==============================TABLE================================================
        List<Table> listTable = new ArrayList<Table>();
        Table tb1 = new Table(1, 5);
        Table tb2 = new Table(2, 5);
        Table tb3 = new Table(3, 5);
        Table tb4 = new Table(4, 5);
        Table tb5 = new Table(5, 5);
        Table tb6 = new Table(6, 5);
        listTable.add(tb1); listTable.add(tb2); listTable.add(tb3);
        listTable.add(tb4); listTable.add(tb5); listTable.add(tb6);

        db.store(listPer);
        db.store(listUsr);

        db.store(listProduct);
        db.store(listGroupProduct);

        db.store(date);
        db.store(listPrices);

        db.store(listTable);
        db.commit();




    }

    public void TestDB(){

        //GROUP
        ObjectSet<GroupProduct> groupProducts = db.queryByExample(GroupProduct.class);
        for (GroupProduct gd : groupProducts){
            System.out.println("GROUP Name gro: " + gd.getGroupProductName());
        }

        //PRODUCT
        ObjectSet<Product> Products = db.queryByExample(Product.class);
        for (Product gd : Products){
            System.out.println("Name pro: " + gd.getProductName());
        }


        //PRICE
        ObjectSet<ListPrice> prices = db.queryByExample(ListPrice.class);
        for (ListPrice p : prices){
            System.out.println("Name: "+ p.getProduct().getProductName() +
                    " | Date: " + p.getDateClass().getDate().get(Calendar.DATE)+ "/" + p.getDateClass().getDate().get(Calendar.MONTH)+"/"+ p.getDateClass().getDate().get(Calendar.YEAR) +
                    "List price: " + p.getPrice());
        }


    }

    public void PrintProductPrice(){
        ObjectSet<Product> lsProduct = db.queryByExample(Product.class);
        for (Product pd: lsProduct) {
            System.out.println("Product Name: " + pd.getProductName() + " Size: " + pd.getListPrice().size());
        }
    }

    public int CheckLogin(final String username, final String password){
        ObjectSet<User> users = db.query(new Predicate<User>() {
            public boolean match(User user) {
                return user.getUsername().equals(username) && user.getPassword().equals(password);
            }
        });
        if(users.size() == 0) return -1;                        //Username and pass incorrect
        else return users.next().getPer().getPermissionId();    //Return number of permission of users
    }

    public List<Table> getTable() {
        List<Table> listTable = new ArrayList<Table>();
        ObjectSet<Table> obsTable = db.queryByExample(Table.class);
        for (Table t: obsTable) {
            listTable.add(t);
            System.out.println("Id table: " + t.getIdTable());
        }
        return listTable;
    }

    //Get list product PRODUCT ACTIVITY
    public List<Product> getListProduct(){
        List<Product> listProduct = new ArrayList<Product>();
        ObjectSet<Product> lsProduct = db.queryByExample(Product.class);
        for (Product pd: lsProduct) {
            listProduct.add(pd);
            System.out.println("Product Name: " + pd.getProductName() + " Unit: " + pd.getUnit());
        }
        return listProduct;
    }


    //Get List product by group product
    public List<Product> getListProductByGroup(String group){
        List<Product> listProductInGroup = new ArrayList<Product>();
        List<Product> listProduct = new ArrayList<Product>();
        ObjectSet<Product> lsProduct = db.queryByExample(Product.class);
        for (Product pd: lsProduct) {
            listProduct.add(pd);
            System.out.println("Product Name: " + pd.getProductName() + " Unit: " + pd.getUnit());
        }

        Iterator iterator = listProduct.iterator();
        while (iterator.hasNext()) {
            Product pro = (Product) iterator.next();
            if (pro.getGroupProduct().getGroupProductName().equals(group)) {
                listProductInGroup.add(pro);
            }
        }
        return listProductInGroup;
    }

    public List<Product> getListProduct1(){
        List<Product> listProduct = new ArrayList<Product>();
        ObjectSet<Product> users = db.query(new Predicate<Product>() {
            public boolean match(Product product) {
                return product.getProductId() == 3;
            }
        });
        for (Product pd: listProduct) {
            listProduct.add(pd);
        }
        return listProduct;
    }

    public void InsertProduct(int idGroupProduct, String nameOfProduct, String unitOfProduct, long price){
        Calendar cal = Calendar.getInstance();
        DateClass dateClass = new DateClass(cal);
        //Get max id for product
        int maxID = 0;

        //Insert product object
        Product prod = new Product(maxID, nameOfProduct, unitOfProduct);

        //Select group product
        ObjectSet<GroupProduct> gro = db.query(new Predicate<GroupProduct>() {
            public boolean match(GroupProduct group) {
                return group.getGroupID() == 1;
            }
        });
        GroupProduct groupProduct = gro.next();

        //Insert ListPrice object
        ListPrice listPrice = new ListPrice(dateClass, prod, price); //added ref

        //Insert DateClass object //Chua xu ly trung ngay khi insert

        dateClass.addListPrices(listPrice);

        groupProduct.addProduct(prod);
        prod.setGroupProduct(groupProduct);
        prod.addPrice(listPrice);

        db.store(prod);
        db.commit();
    }

    //Get list product PRODUCT ACTIVITY
    public List<GroupProduct> getListGroupProduct(){
        List<GroupProduct> listProduct = new ArrayList<GroupProduct>();
        ObjectSet<GroupProduct> lsProduct = db.queryByExample(GroupProduct.class);
        for (GroupProduct pd: lsProduct) {
            listProduct.add(pd);
            System.out.println("Group Product Name: " + pd.getGroupProductName() + " ID: " + pd.getGroupID());
        }
        return listProduct;
    }


    //Get list product by ID group
    public List<Product> getListProductOfGroup(final int groupID){
        List<Product> listProduct = new ArrayList<Product>();
        //Get group product has ID Return 1 result
        ObjectSet<GroupProduct> gro = db.query(new Predicate<GroupProduct>() {
            public boolean match(GroupProduct groupProduct) {
                return groupProduct.getGroupID() == groupID;
            }
        });
        if(gro.size() != 1) {
            System.out.println("KHONG THE LAY NHOM HOAC ID NHOM SAI");
        }
        else
        //Set values for list product
        for(Product pro : gro.next().getListProduct()){
            listProduct.add(pro);
            System.out.println("TEN THUC UONG: " + pro.getProductName());
        }

        return listProduct;
    }

    //Get list product by ID table
    public List<ProductDetails> getListProductOfTable(final int tableID){
        List<ProductDetails> listDetailProduct = new ArrayList<ProductDetails>();
        //Get group product has ID Return 1 result
        ObjectSet<Bill> bills = db.query(new Predicate<Bill>() {
            public boolean match(Bill bill) {
                return bill.getTable().getIdTable() == tableID && bill.isState() == true;
            }
        });
        if(bills.size() != 1){
            System.out.println("KHONG CO HOA DON!!");
        }
        else
        for(ProductDetails details : bills.next().getListDetailProduct()){
            listDetailProduct.add(details);
            System.out.println("TEN THUC UONG: " + details.getProduct().getProductName() + "SO LUONG: " + details.getUnitSales());
        }

        return listDetailProduct;
    }

    public Bill checkBillExist(final  int tableID){
        ObjectSet<Bill> bills = db.query(new Predicate<Bill>() {
            public boolean match(Bill bill) {
                return bill.getTable().getIdTable() == tableID && bill.isState() == true;
            }
        });
        if(bills.size() != 1){
            System.out.println("KHONG CO HOA DON!!");
        }
        return bills.next();
    }
    public void InsertProductForBill(Product product, int unitSales, final int tableID){
        Calendar calendar = Calendar.getInstance();

        //Get table for bill
        ObjectSet<Table> tables = db.query(new Predicate<Table>() {
            public boolean match(Table tb) {
                return tb.getIdTable() == tableID;
            }
        });
        if(tables.size() != 1){
            System.out.println("KHONG LAY DUOC TABLE (InsertProductForBill)!!");
        }
        else{
            Table newTable = tables.next();
            //Check bill is existed or not
            Bill bill = checkBillExist(tableID);
            if(bill != null){ //Has bill and has list details product
                ProductDetails productDetailsOld = getProductDetails(bill.getBillID()); ////////******************
                //Insert product into bill
                bill.addListDetailProduct(productDetailsOld);
            } else{
                ProductDetails productDetailsNew = new ProductDetails(product, unitSales);
                bill.addListDetailProduct(productDetailsNew);
                productDetailsNew.setBill(bill);
                db.store(productDetailsNew);
                db.store(bill);
                db.commit();
            }

        }
    }

    public ProductDetails getProductDetails(final int BillID){
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == BillID;
            }
        });
        if(details.size() != 1){
            System.out.println("KHONG CO CHI TIET THUC UONG!!");
        }
        return details.next();
    }

    public void Close(){
        db.close();
    }
}
