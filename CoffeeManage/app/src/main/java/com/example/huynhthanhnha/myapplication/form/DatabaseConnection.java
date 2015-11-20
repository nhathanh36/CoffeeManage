package com.example.huynhthanhnha.myapplication.form;

import android.support.design.widget.TabLayout;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.CacheConfiguration;
import com.db4o.config.CommonConfiguration;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.config.EmbeddedConfigurationItem;
import com.db4o.config.FileConfiguration;
import com.db4o.config.IdSystemConfiguration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by NguyenThanh on 13/11/2015.
 */
public class DatabaseConnection {
    //String filePath;
    String filePath = "/data/data/com.example.huynhthanhnha.myapplication/files/coffee_db.db4o";
    ObjectContainer db;
    boolean flag;

    public DatabaseConnection(){
        new File(filePath).delete();
        if(new File(filePath).exists()) flag = true;
        else flag = false;
    }

    public DatabaseConnection(String filePath) {
        //this.filePath = filePath + "/coffee_db.db4o";
        //System.out.println(this.filePath);
    }

    public void Open(){
        EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
        conf.common().objectClass(ProductDetails.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(conf, filePath);
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
        Set<Product> listProduct = new HashSet<Product>();
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

    public void TestUpdate(){
        ObjectSet<ProductDetails> details = db.queryByExample(ProductDetails.class);
        for (ProductDetails dt : details){
            System.out.println("Details THUC UONG: " + dt.getProduct().getProductName() +
                    " So luong => " + dt.getUnitSales() +
                    " Ban: " + dt.getBill().getTable().getIdTable());
        }
    }

    public void TestBill(){
        ObjectSet<Bill> details = db.queryByExample(Bill.class);
        for (Bill dt : details){
            System.out.println("Bill => " + dt.getBillID());
            for(ProductDetails pro : dt.getListDetailProduct()){
                System.out.println("Ten details thuoc uong: " + pro.getProduct().getProductName() + " SL: " + pro.getUnitSales());
            }
        }
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


    //Get list group product
    public List<GroupProduct> getListGroup(){
        List<GroupProduct> listGroup = new ArrayList<GroupProduct>();
        ObjectSet<GroupProduct> lsGroup = db.queryByExample(GroupProduct.class);
        for (GroupProduct pd: lsGroup) {
            listGroup.add(pd);
            System.out.println("Group Name: " + pd.getGroupProductName());
        }
        return listGroup;
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
        List<GroupProduct> listGroup = new ArrayList<GroupProduct>();
        ObjectSet<GroupProduct> lsGroup = db.queryByExample(GroupProduct.class);
        for (GroupProduct pd: lsGroup) {
            listGroup.add(pd);
            System.out.println("Group Product Name: " + pd.getGroupProductName() + " ID: " + pd.getGroupID());
            for(Product p : pd.getListProduct()){
                System.out.println("List: "+p.getProductName());
            }
        }
        return listGroup;
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
        if(bills.size() == 0){
            System.out.println("KHONG CO HOA DON!!");
        }
        else
        for(ProductDetails details : bills.next().getListDetailProduct()){
            listDetailProduct.add(details);
            System.out.println(" TEN THUC UONG: " + details.getProduct().getProductName() + " SO LUONG: " + details.getUnitSales());
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
            return null;
        }
        return bills.next();
    }

    public void InsertProductForBill(Product product, int unitSales, final int tableID){
        Calendar calendar = Calendar.getInstance();
        int idBill = 0;
        Table tb;
        //Check bill is existed or not
        Bill bill = checkBillExist(tableID);
        if(bill != null){ //Has bill and has list details product
            System.out.println("InsertProductForBill => UPDATE BILL => TON TAI 1 THUC UONG");
            insertProductDetailForBill(bill, product, unitSales, tableID);
        }else{
            System.out.println("InsertProductForBill => THEM BILL MOI (CHUA CO THUC UONG NAO)");
            //Get max bill id
            idBill = getMaxIDBill() + 1; System.out.println("ID Bill moi: " + idBill);

            //Insert bill
            bill = new Bill(idBill, calendar);

            //Insert details product
            ProductDetails productDetailsNew = new ProductDetails(product, unitSales);
            System.out.println("Produc details: " + productDetailsNew.getProduct().getProductName());

            //Get table and set for bill
            tb = getTableByID(tableID);

            bill.setTable(tb);                              //Set table for bill
            bill.setState(true);                            //Set state for bill //true is not pay
            bill.addListDetailProduct(productDetailsNew);   //Set details product for bill

            tb.addBill(bill);                   //Set bill for table
            productDetailsNew.setBill(bill);    //Set bill for Detais product


            db.store(productDetailsNew);
            db.store(bill);
            db.commit();
        }
    }
    public void insertProductDetailForBill(final Bill bill, final Product product, final int numSales, final int tableId){
        ProductDetails productOld;
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == bill.getBillID() &&
                        dt.getProduct().getProductId() == product.getProductId() &&
                        dt.getBill().getTable().getIdTable() == tableId;
            }
        });
        productOld = details.next();
        if(details.size() == 0){
            System.out.println("PRODUCT DETAIL KHONG CO GIA TRI TRUNG LAP THUC UONG!!");
            ProductDetails productDetails = new ProductDetails(product, numSales);  //Auto save product
            bill.addListDetailProduct(productDetails);                              //Add list product details for bill
            productDetails.setBill(bill);                                           //Add bill for product details
            db.store(productDetails);
            db.commit();
        }
        else{
            System.out.println("TRUNG THUC UONG TRONG DETAILS!!");
            //get current Unit of product
            int currentUnit = productOld.getUnitSales();
            System.out.println("UNIT CURRENT = " + currentUnit);
            //Update unit sales for this product
            productOld.setUnitSales((numSales + currentUnit));
            System.out.println("AFTER CURRENT = " + (numSales + currentUnit));
            System.out.println("GET UNIT SALES: " + productOld.getUnitSales());
            db.store(productOld);
            db.commit();
        }
    }
    //Get table by ID
    public Table getTableByID(final int id){
        ObjectSet<Table> details = db.query(new Predicate<Table>() {
            public boolean match(Table dt) {
                return dt.getIdTable() == id;
            }
        });
        if(details.size() == 0){
            System.out.println("LAY TABLE KHONG CO GIA TRI!!");
            return null;
        }
        return details.next();
    }

    public int getMaxIDBill(){
        int maxID = 0;
        //Compare bill
        Comparator<Bill> billComparator = new Comparator<Bill>() {
            public int compare(Bill b1, Bill b2){
                return String.valueOf(b1.getBillID()).compareTo(String.valueOf(b2.getBillID()));
            }
        };

        ObjectSet<Bill> details = db.query(new Predicate<Bill>() {
            public boolean match(Bill dt) {
                return dt.getBillID() > 0;
            }
        }, billComparator);

        System.out.println("Size bill: " + details.size());

        //Get first bill
        if(details.size() == 0){
            maxID = 1;
        }else{
            maxID = Integer.valueOf(details.next().getBillID());
        }
        System.out.println("MAX BILL ID: " + maxID);

        return maxID;
    }

    public ProductDetails getProductDetails(final int BillID){
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == BillID;
            }
        });
        if(details.size() == 0){
            System.out.println("KHONG CO CHI TIET THUC UONG!!");
        }
        return details.next();
    }

    public void Close(){
        db.close();
    }
}
