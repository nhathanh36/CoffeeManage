package com.example.huynhthanhnha.myapplication.form;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;
import com.example.huynhthanhnha.myapplication.Login;
import com.example.huynhthanhnha.myapplication.MyObject;

import java.util.Comparator;

/**
 * Created by NguyenThanh on 13/11/2015.
 */
public class DatabaseConnection {
    //String filePath;
    String filePath = "/data/data/com.example.huynhthanhnha.myapplication/files/coffee_db.db4o";
    //String filePath = "/data/data/com.example.huynhthanhnha.myapplication/app_data/coffee.db4o";
    ObjectContainer db;
    boolean flag;

    public DatabaseConnection() {
        //new File(filePath).delete();
        if (new File(filePath).exists()) flag = true;
        else flag = false;
    }

    /*
        public DatabaseConnection(String filePath) {
            this.filePath = filePath + "/coffee_db.db4o";
            System.out.println(this.filePath);
        }
    */
    public void Open() {
        EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
        conf.common().objectClass(ProductDetails.class).cascadeOnUpdate(true);
        conf.common().objectClass(Product.class).cascadeOnUpdate(true);
        conf.common().objectClass(GroupProduct.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(conf, filePath);
        if (!flag) InitData();
    }

    private void InitData() {
        //==============================PERMISSION AND USER================================================
        List<Permission> listPer = new ArrayList<Permission>();
        Permission per1 = new Permission(1, "Toàn quyền hệ thống");
        Permission per2 = new Permission(2, "Nhân viên");
        listPer.add(per1);
        listPer.add(per2);

        //List user
        List<User> listUsr = new ArrayList<User>();
        Manager usr1 = new Manager("phi", "phi", "Nguyễn Thanh Phi", "362368062", "Nam");

        Officer usr2 = new Officer("nha", "nha", "Huỳnh Thanh Nhã", "365368060", "Nữ");
        Officer usr3 = new Officer("tri", "tri", "Nguyễn Minh Trí", "365368060", "Nam");
        Officer usr4 = new Officer("nguyen", "nguyen", "Trương Hoàng Nguyên", "365368060", "Nam");
        Officer usr5 = new Officer("lan", "lan", "Nguyễn Thi Lan", "365368060", "Nam");
        Officer usr6 = new Officer("liet", "liet", "Võ Văn Liệt", "365368060", "Nam");
        Officer usr7 = new Officer("nam", "nam", "Nguyễn Hoài Nam", "365368060", "Nam");

        listUsr.add(usr1);

        listUsr.add(usr2);
        listUsr.add(usr3);
        listUsr.add(usr4);
        listUsr.add(usr5);
        listUsr.add(usr6);
        listUsr.add(usr7);

        //Add constraint for relationship
        per1.addUser(usr1);
        usr1.setPer(per1);

        per2.addUser(usr2);
        usr2.setPer(per2);
        per2.addUser(usr3);
        usr3.setPer(per2);
        per2.addUser(usr4);
        usr4.setPer(per2);
        per2.addUser(usr5);
        usr5.setPer(per2);
        per2.addUser(usr6);
        usr6.setPer(per2);
        per2.addUser(usr7);
        usr7.setPer(per2);

        //==============================PRODUCT AND GROUP PRODUCT================================================
        // Group Product
        List<GroupProduct> listGroupProduct = new ArrayList<GroupProduct>();
        GroupProduct gp1 = new GroupProduct(1, "Cafe");
        GroupProduct gp2 = new GroupProduct(2, "Sinh tố");
        GroupProduct gp3 = new GroupProduct(3, "Nước khoáng");
        GroupProduct gp4 = new GroupProduct(4, "Trà-Lipton");
        GroupProduct gp5 = new GroupProduct(5, "Kem chuối");
        GroupProduct gp6 = new GroupProduct(6, "Trà sữa");

        // List Product
        Set<Product> listProduct = new HashSet<Product>();
        Product pd1 = new Product(1, "Cafe đá", "Ly");
        Product pd2 = new Product(2, "Cafe sữa", "Ly");
        Product pd3 = new Product(3, "Cafe nóng", "Tách");
        Product pd4 = new Product(4, "Nước khoáng Aquafina", "Chai");
        Product pd5 = new Product(5, "Nước khoáng LaVie", "Chai");
        Product pd6 = new Product(6, "Lipton nóng", "Tách");
        Product pd7 = new Product(7, "Lipton đá", "Ly");
        Product pd8 = new Product(8, "Sinh tố bơ", "Ly");
        Product pd9 = new Product(9, "Sinh tố dâu", "Ly");
        Product pd10 = new Product(10, "Sinh tố đu đủ", "Ly");
        Product pd11 = new Product(11, "Sinh tố cà rốt", "Ly");

        listGroupProduct.add(gp1);
        listGroupProduct.add(gp2);
        listGroupProduct.add(gp3);
        listGroupProduct.add(gp4);
        listGroupProduct.add(gp5);
        listGroupProduct.add(gp6);

        listProduct.add(pd1);
        listProduct.add(pd2);
        listProduct.add(pd3);
        listProduct.add(pd4);
        listProduct.add(pd5);
        listProduct.add(pd6);
        listProduct.add(pd7);
        listProduct.add(pd8);
        listProduct.add(pd9);
        listProduct.add(pd10);
        listProduct.add(pd11);

        //Add constraint for relationship
        gp1.addProduct(pd1);
        pd1.setGroupProduct(gp1);
        gp1.addProduct(pd2);
        pd2.setGroupProduct(gp1);
        gp1.addProduct(pd3);
        pd3.setGroupProduct(gp1);

        gp2.addProduct(pd8);
        pd8.setGroupProduct(gp2);
        gp2.addProduct(pd9);
        pd9.setGroupProduct(gp2);
        gp2.addProduct(pd10);
        pd10.setGroupProduct(gp2);
        gp2.addProduct(pd11);
        pd11.setGroupProduct(gp2);

        gp3.addProduct(pd4);
        pd4.setGroupProduct(gp3);
        gp3.addProduct(pd5);
        pd5.setGroupProduct(gp3);

        gp4.addProduct(pd6);
        pd6.setGroupProduct(gp4);
        gp4.addProduct(pd7);
        pd7.setGroupProduct(gp4);


        //==============================DATE AND LIST PRICE================================================
        Date date = new Date();
        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "15-11-2015";
        String dateInString1 = "20-10-2014";
        String dateInString2 = "20-11-2015";
        try {
            date = sdf.parse(dateInString);
            date1 = sdf.parse(dateInString1);
            date2 = sdf.parse(dateInString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateClass dateClass = new DateClass(date);
        DateClass dateClass1 = new DateClass(date1);
        DateClass dateClass2 = new DateClass(date2);

        List<ListPrice> listPrices = new ArrayList<ListPrice>();
        ListPrice price1 = new ListPrice(dateClass, pd1, 7000);
        ListPrice price1NewPrice = new ListPrice(dateClass1, pd1, 10000);
        ListPrice price1NewPrice1 = new ListPrice(dateClass2, pd1, 15000);
        ListPrice price2 = new ListPrice(dateClass, pd2, 8000);
        ListPrice price3 = new ListPrice(dateClass, pd3, 9000);
        ListPrice price4 = new ListPrice(dateClass, pd4, 10000);
        ListPrice price5 = new ListPrice(dateClass, pd5, 11000);
        ListPrice price6 = new ListPrice(dateClass, pd6, 12000);
        ListPrice price7 = new ListPrice(dateClass, pd7, 13000);
        ListPrice price8 = new ListPrice(dateClass, pd8, 14000);
        ListPrice price9 = new ListPrice(dateClass, pd9, 15000);
        ListPrice price10 = new ListPrice(dateClass, pd10, 16000);
        ListPrice price11 = new ListPrice(dateClass, pd11, 17000);

        listPrices.add(price1);
        listPrices.add(price2);
        listPrices.add(price3);
        listPrices.add(price4);
        listPrices.add(price5);
        listPrices.add(price6);
        listPrices.add(price7);
        listPrices.add(price8);
        listPrices.add(price9);
        listPrices.add(price10);
        listPrices.add(price11);
        listPrices.add(price1NewPrice);
        listPrices.add(price1NewPrice1);

        pd1.addPrice(price1);
        pd1.addPrice(price1NewPrice);
        pd1.addPrice(price1NewPrice1);
        pd2.addPrice(price2);
        pd3.addPrice(price3);
        pd4.addPrice(price4);
        pd5.addPrice(price5);
        pd6.addPrice(price6);
        pd7.addPrice(price7);
        pd8.addPrice(price8);
        pd9.addPrice(price9);
        pd10.addPrice(price10);
        pd11.addPrice(price11);

        //==============================TABLE================================================
        List<Table> listTable = new ArrayList<Table>();
        Table tb1 = new Table(1, 5);
        Table tb2 = new Table(2, 5);
        Table tb3 = new Table(3, 5);
        Table tb4 = new Table(4, 5);
        Table tb5 = new Table(5, 5);
        Table tb6 = new Table(6, 5);
        listTable.add(tb1);
        listTable.add(tb2);
        listTable.add(tb3);
        listTable.add(tb4);
        listTable.add(tb5);
        listTable.add(tb6);

        db.store(listPer);
        db.store(listUsr);

        db.store(listProduct);
        db.store(listGroupProduct);

        db.store(dateClass);
        db.store(dateClass1);
        db.store(dateClass2);
        db.store(listPrices);

        db.store(listTable);
        db.commit();

    }

    public void TestUpdate() {
        ObjectSet<ProductDetails> details = db.queryByExample(ProductDetails.class);
        for (ProductDetails dt : details) {
            System.out.println("Details THUC UONG: " + dt.getProduct().getProductName() +
                    " So luong => " + dt.getUnitSales() +
                    " Ban: " + dt.getBill().getTable().getIdTable());
        }
    }

    public void TestBill() {
        ObjectSet<Bill> details = db.queryByExample(Bill.class);
        for (Bill dt : details) {
            System.out.println("Bill => " + dt.getBillID());
            for (ProductDetails pro : dt.getListDetailProduct()) {
                System.out.println("Ten details thuoc uong: " + pro.getProduct().getProductName() + " SL: " + pro.getUnitSales());
            }
        }
    }

    public void TestDB() {

        //GROUP
        ObjectSet<GroupProduct> groupProducts = db.queryByExample(GroupProduct.class);
        for (GroupProduct gd : groupProducts) {
            System.out.println("GROUP Name gro: " + gd.getGroupProductName());
        }

        //PRODUCT
        ObjectSet<Product> Products = db.queryByExample(Product.class);
        for (Product gd : Products) {
            System.out.println("Name pro: " + gd.getProductName());
        }


        //PRICE
        ObjectSet<ListPrice> prices = db.queryByExample(ListPrice.class);
        for (ListPrice p : prices) {
            System.out.println("Name: " + p.getProduct().getProductName() +
                    " | Date: " + p.getDateClass().getDate() +
                    "List price: " + p.getPrice());
        }


    }

    /**
     *
     */
    public void PrintProductPrice() {
        ObjectSet<Product> lsProduct = db.queryByExample(Product.class);
        for (Product pd : lsProduct) {
            System.out.println("Product Name 2: " + pd.getProductName() + " Size: " + pd.getListPrice().size());
        }
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public int CheckLogin(final String username, final String password) {
        ObjectSet<User> users = db.query(new Predicate<User>() {
            public boolean match(User user) {
                return user.getUsername().equals(username) && user.getPassword().equals(password);
            }
        });
        if (users.size() == 0) return -1;                        //Username and pass incorrect
        else
            return users.next().getPer().getPermissionId();    //Return number of permission of users
    }

    public List<Table> getTable() {
        List<Table> listTable = new ArrayList<Table>();
        ObjectSet<Table> obsTable = db.queryByExample(Table.class);
        for (Table t : obsTable) {
            listTable.add(t);
            System.out.println("Id table: " + t.getIdTable());
        }
        return listTable;
    }

    //Get list product PRODUCT ACTIVITY
    public List<Product> getListProduct() {
        List<Product> listProduct = new ArrayList<Product>();
        ObjectSet<Product> lsProduct = db.queryByExample(Product.class);
        for (Product pd : lsProduct) {
            listProduct.add(pd);
            //System.out.println("Product Name 1: " + pd.getProductName() + " Unit: " + pd.getUnit());
        }
        return listProduct;
    }

    //Get list product by name group
    public List<Product> getListProductGroupByName(final String name) {
        List<Product> listProduct = new ArrayList<Product>();
        //Get group product has Name result
        ObjectSet<GroupProduct> gro = db.query(new Predicate<GroupProduct>() {
            public boolean match(GroupProduct groupProduct) {
                return groupProduct.getGroupProductName().equals(name);
            }
        });
        if (gro.size() == 0) {
            System.out.println("KHONG THE LAY NHOM HOAC ID NHOM SAI");
        } else
            //Set values for list product
            for (Product pro : gro.next().getListProduct()) {
                listProduct.add(pro);
                System.out.println("TEN THUC UONG 1: " + pro.getProductName());
            }

        return listProduct;
    }


    //Get list group product
    public List<GroupProduct> getListGroup() {
        List<GroupProduct> listGroup = new ArrayList<GroupProduct>();
        ObjectSet<GroupProduct> lsGroup = db.queryByExample(GroupProduct.class);
        for (GroupProduct pd : lsGroup) {
            listGroup.add(pd);
            System.out.println("Group Name: " + pd.getGroupProductName());
        }
        return listGroup;
    }

    public List<Product> getListProduct1() {
        List<Product> listProduct = new ArrayList<Product>();
        ObjectSet<Product> users = db.query(new Predicate<Product>() {
            public boolean match(Product product) {
                return product.getProductId() == 3;
            }
        });
        for (Product pd : listProduct) {
            listProduct.add(pd);
        }
        return listProduct;
    }

    public void InsertProduct(final String groupProductName, String nameOfProduct, String unitOfProduct, long price) {
        Date date = new Date();
        DateClass dateClass = new DateClass(date);
        //Get max id for product
        int maxID = getMaxProductID();

        //Insert product object
        Product prod = new Product(maxID, nameOfProduct, unitOfProduct);

        //Select group product
        ObjectSet<GroupProduct> gro = db.query(new Predicate<GroupProduct>() {
            public boolean match(GroupProduct group) {
                return group.getGroupProductName().equals(groupProductName);
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
    public List<GroupProduct> getListGroupProduct() {
        List<GroupProduct> listGroup = new ArrayList<GroupProduct>();
        // Sort by Group name
        Comparator<GroupProduct> groupProductComparator = new Comparator<GroupProduct>() {

            @Override
            public int compare(GroupProduct t, GroupProduct t1) {
                return t.getGroupProductName().compareTo(t1.getGroupProductName());
            }
        };
        Query query = db.query().sortBy(groupProductComparator);
        query.constrain(GroupProduct.class);
        ObjectSet<GroupProduct> lsGroup = query.execute();//db.queryByExample(GroupProduct.class);
        for (GroupProduct pd : lsGroup) {
            listGroup.add(pd);
        }
        return listGroup;
    }

    //Get list product by ID group
    public List<Product> getListProductOfGroup(final int groupID) {
        List<Product> listProduct = new ArrayList<Product>();
        //Get group product has ID Return 1 result
        ObjectSet<GroupProduct> gro = db.query(new Predicate<GroupProduct>() {
            public boolean match(GroupProduct groupProduct) {
                return groupProduct.getGroupID() == groupID;
            }
        });
        if (gro.size() == 0) {
            //System.out.println("KHONG THE LAY NHOM HOAC ID NHOM SAI");
        } else
            //Set values for list product
            for (Product pro : gro.next().getListProduct()) {
                listProduct.add(pro);
                //System.out.println("TEN THUC UONG 2: " + pro.getProductName());
            }

        return listProduct;
    }

    //Get list product by ID table
    public List<ProductDetails> getListProductOfTable(final int tableID) {
        List<ProductDetails> listDetailProduct = new ArrayList<ProductDetails>();
        //Get group product has ID Return 1 result
        ObjectSet<Bill> bills = db.query(new Predicate<Bill>() {
            public boolean match(Bill bill) {
                return bill.getTable().getIdTable() == tableID &&
                        bill.isState() == true;
            }
        });
        if (bills.size() == 0) {
            System.out.println("KHONG CO HOA DON!!");
        } else {
            final Bill billTemp = bills.next();
            //System.out.println("Bill id: xxx " + billTemp.getBillID());
            ObjectSet<ProductDetails> lproductdetail = db.query(new Predicate<ProductDetails>() {
                public boolean match(ProductDetails l) {
                    return l.getBill().getBillID() == billTemp.getBillID();
                }
            });

            for (ProductDetails details : lproductdetail) {
                listDetailProduct.add(details);
                //System.out.println("TEN THUC UONG 3: " + details.getProduct().getProductName() + " SO LUONG: " + details.getUnitSales());
            }
        }

        return listDetailProduct;
    }

    public Bill checkBillExist(final int tableID) {
        ObjectSet<Bill> bills = db.query(new Predicate<Bill>() {
            public boolean match(Bill bill) {
                return bill.getTable().getIdTable() == tableID && bill.isState() == true;
            }
        });
        if (bills.size() != 1) {
            System.out.println("KHONG CO HOA DON!!");
            return null;
        }
        return bills.next();
    }

    public Officer getOfficer(){
        ObjectSet<Officer> result = db.query(new Predicate<Officer>() {
            public boolean match(Officer officer) {
                return officer.getUsername().equals(Login.getUser().getUsername());
            }
        });
        return  result.next();
    }

    public void InsertProductForBill(Product product, int unitSales, final int tableID){
        Officer officer = getOfficer();
        Calendar calendar = Calendar.getInstance();
        Product productCm = getProductByID(product.getProductId());
        int idBill = 0;
        Table tb;
        //Check bill is existed or not
        Bill bill = checkBillExist(tableID);
        if (bill != null) { //Has bill and has list details product
            System.out.println("InsertProductForBill => UPDATE BILL => TON TAI 1 THUC UONG");
            insertProductDetailForBill(bill, productCm, unitSales, tableID);
        } else {
            System.out.println("InsertProductForBill => THEM BILL MOI (CHUA CO THUC UONG NAO)");
            //Get max bill id
            idBill = getMaxBillID() + 1;
            //System.out.println("ID Bill moi trong ham insert: " + idBill);

            //Insert bill
            Bill billNew = new Bill(idBill, calendar);

            //Insert details product
            //Find max product details id
            int idProductDetail = getMaxProductDetailID() + 1;
            ProductDetails productDetailsNew = new ProductDetails(idProductDetail, unitSales);

            //System.out.println("Produc details trong ham insert: " + productDetailsNew.getProduct().getProductName());

            //Get table and set for bill
            tb = getTableByID(tableID);

            billNew.setTable(tb);                              //Set table for bill
            billNew.setState(true);                            //Set state for bill //true is not pay
            billNew.addListDetailProduct(productDetailsNew);   //Set details product for bill
            billNew.setOfficer(officer);                  //Set officer for bill
            System.out.println("NAME OF OFFICER" + billNew.getOfficer().getName());

            tb.addBill(billNew);                           //Set bill for table
            productDetailsNew.setBill(billNew);            //Set bill for Details product
            productDetailsNew.setProduct(productCm);      //Set product for product details
            officer.addBill(billNew);                      //Add bill for officer

            db.store(productDetailsNew);
            db.store(billNew);

            db.commit();
        }
    }

    protected void insertProductDetailForBill(final Bill bill, final Product product, final int numSales, final int tableId) {
        ProductDetails productOld;
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == bill.getBillID() &&
                        dt.getProduct().getProductId() == product.getProductId() &&
                        dt.getBill().getTable().getIdTable() == tableId;
            }
        });

        if (details.size() == 0) {
            System.out.println("PRODUCT DETAIL KHONG CO GIA TRI TRUNG LAP THUC UONG!!");
            int idProductDetail = getMaxProductDetailID() + 1;
            ProductDetails productDetails = new ProductDetails(idProductDetail, numSales);

            productDetails.setProduct(product);                                     //Auto save product
            bill.addListDetailProduct(productDetails);                              //Add list product details for bill
            productDetails.setBill(bill);                                           //Add bill for product details

            db.store(productDetails);       //Insert product details
            db.store(bill);                 //Update bill has the same id
            db.commit();
        } else {
            productOld = details.next();
            System.out.println("TRUNG THUC UONG TRONG DETAILS!!");
            //get current Unit of product
            int currentUnit = productOld.getUnitSales();
            //System.out.println("UNIT CURRENT = " + currentUnit);
            //Update unit sales for this product
            productOld.setUnitSales((numSales + currentUnit));
            System.out.println("AFTER CURRENT = " + (numSales + currentUnit));
            //System.out.println("GET UNIT SALES: " + productOld.getUnitSales());
            db.store(productOld);
            db.commit();
        }
    }

    //Get table by ID
    public Table getTableByID(final int id) {
        ObjectSet<Table> details = db.query(new Predicate<Table>() {
            public boolean match(Table dt) {
                return dt.getIdTable() == id;
            }
        });
        if (details.size() == 0) {
            System.out.println("LAY TABLE KHONG CO GIA TRI!!");
            return null;
        }
        return details.next();
    }

    public int getMaxBillID() {
        int MaxIDBill = 0;
        ObjectSet<Bill> bills = db.queryByExample(Bill.class);
        if (bills.size() != 0)
            for (Bill b : bills) {
                if (b.getBillID() > MaxIDBill)
                    MaxIDBill = b.getBillID();

            }
        System.out.println("ID trong ham getIDBill => " + MaxIDBill);
        return MaxIDBill;
    }

    public ProductDetails getProductDetails(final int BillID) {
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == BillID;
            }
        });
        if (details.size() == 0) {
            System.out.println("KHONG CO CHI TIET THUC UONG!!");
            return null;
        } else
            return details.next();
    }

    /**
     * @param dates
     * @param currentDate
     * @return closet Date
     */
    public static Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = Math.abs(currentTime - date.getTime());
            if ((minDiff == -1) || (diff < minDiff)) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }

    public float getTotalPriceForBill(final Bill bill) {
        float sumPrice = 0;
        //Get list product details by bill id
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == bill.getBillID();
            }
        });
        //Check product details is exist
        if (details.size() != 0) {
            for (ProductDetails p : details) {
                //sumPrice += p.getUnitSales()*p.getProduct() ; //Sum
            }
        }
        return sumPrice;
    }

    public void updateStateForBill(final int IdTable) {
        Bill bill = checkBillExist(IdTable);
        if (bill != null) { //Bill is existed
            bill.setState(false); //Set state for bill was payed
            db.store(bill);
            db.commit();
        } else
            System.out.println("Khong the thanh toan!");
    }

    public int getMaxProductDetailID() {
        int MaxID = 0;
        ObjectSet<ProductDetails> dt = db.queryByExample(ProductDetails.class);
        if (dt.size() != 0)
            for (ProductDetails p : dt) {
                if (p.getProductDetailID() > MaxID)
                    MaxID = p.getProductDetailID();

            }
        System.out.println("MAX PRODUCT DETAILS ID => " + MaxID);
        return MaxID;
    }

    public void getProductDetails() {
        ObjectSet<ProductDetails> listProductDetail = db.queryByExample(ProductDetails.class);
        for (ProductDetails p : listProductDetail) {
            System.out.println("ID Details " + p.getProductDetailID() +
                    "BILL ID " + p.getBill().getBillID() +
                    " BAN" + p.getBill().getTable().getIdTable() +
                    " DS THUC UONG " + p.getProduct().getProductName() +
                    " SL " + p.getUnitSales() + " XYX");
        }
    }

    public Product getProductByID(final int productID) {
        ObjectSet<Product> details = db.query(new Predicate<Product>() {
            public boolean match(Product dt) {
                return dt.getProductId() == productID;
            }
        });
        if (details.size() == 0) {
            System.out.println("KHONG SELECT DC PRODUCT!!");
            return null;
        } else
            return details.next();
    }

    public long getPriceOfProduct(final int productID) {
        long price = 0;
        final List<Date> dates = new ArrayList<Date>();
        Date closetDate;
        ObjectSet<ListPrice> details = db.query(new Predicate<ListPrice>() {
            public boolean match(ListPrice lp) {
                return lp.getProduct().getProductId() == productID;
            }
        });

        for (ListPrice lp : details) {
            dates.add(lp.getDateClass().getDate());
        }

        closetDate = getNearestDate(dates, new Date());
        for (ListPrice lp : details) {
            if (lp.getDateClass().getDate() == closetDate) {
                price = lp.getPrice();
            }
        }

        return price;
    }


    public List<Officer> getListOfficer() {
        List<Officer> list = new ArrayList<Officer>();
        ObjectSet<Officer> result = db.queryByExample(Officer.class);
        for (Officer officer : result) {
            list.add(officer);
            //System.out.println("Ten: " + officer.getName() + " CMND: " + officer.getCMND() + " Username" + officer.getPassword());
        }
        return list;
    }

    public void UpdatePrice(final Product product, long price) {
        ListPrice lp = new ListPrice();
        DateClass dateClass = new DateClass();
        dateClass.setDate(new Date());
        // Create new object in ListPrice
        lp.setPrice(price);
        System.out.println("PRICE AFTER SET " + lp.getPrice());
        //System.out.println("/*************************************/");
        lp.setDateClass(dateClass);
        System.out.println("DATECLASS AFTER SET " + lp.getDateClass().getDate());
        lp.setDateClass(dateClass);


        // Set price into Product
        ObjectSet<Product> details = db.query(new Predicate<Product>() {
            public boolean match(Product p) {
                return p.getProductId() == product.getProductId();
            }
        });
        lp.setProduct(details.next());

        // Set list price into DateClass
        dateClass.addListPrices(lp);

        System.out.println("LISPRICE AFTER SET " + dateClass.getListPrices().iterator().next().getPrice());

        db.store(lp);
        db.commit();
    }

    public boolean checkTableHasExist(final int tableID) {
        ObjectSet<Bill> bills = db.query(new Predicate<Bill>() {
            public boolean match(Bill bill) {
                return bill.getTable().getIdTable() == tableID && bill.isState() == true;
            }
        });
        if (bills.size() != 1) return true;
        else return false;
    }

    public long getPriceInCurrentBill(Bill bill, final int proID) {
        long price = 0;
        List<Date> listDate = new ArrayList<Date>();
        //Get date of bill
        Calendar cal = bill.getCalendar();
        ObjectSet<ListPrice> result = db.query(new Predicate<ListPrice>() {
            @Override
            public boolean match(ListPrice l) {
                return l.getProduct().getProductId() == proID;
            }
        });

        System.out.println("Date before sort: ");
        //Get price and date of product
        for (ListPrice lp : result) {
            listDate.add(lp.getDateClass().getDate());
            System.out.println("Before" + lp.getDateClass().getDate().toString());
        }

        //Sort date
        Collections.sort(listDate);
        System.out.println("Date after sort: ");
        for (Date lp : listDate) {
            System.out.println("After" + lp.getTime());
        }


        return price;
    }

    public long getPriceTotalOfBill(List<ProductDetails> productDetailsList) {
        long priceTotal = 0;
        long priceOfProduct = 0;
        for (ProductDetails details : productDetailsList) {
            priceOfProduct = getPriceOfProduct(details.getProduct().getProductId());
            priceTotal += priceOfProduct * details.getUnitSales();
        }
        return priceTotal;
    }


    public long getPriceTotalOfBill(final Bill bill){
        long priceTotal = 0;
        long priceOfProduct = 0;
        //Get product details of bill
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == bill.getBillID();
            }
        });

        //Get price of product
        for (ProductDetails p : details){
            priceOfProduct = getPriceProductInCurrentBill(p.getProduct().getProductId(), bill.getCalendar());
            priceTotal += priceOfProduct * p.getUnitSales();
        }
        return priceTotal;
    }

    //Get total price for all bill in statistics
    public long getPriceAllBill(){
        long priceAll = 0;
        /*
        //List<Bill> billList = getListBill();
        for(Bill b: billList) {
            priceAll += getPriceTotalOfBill(b);
        }
        */
        return priceAll;
    }

    public void deleteProductDetail(final ProductDetails productDetails){
        ProductDetails detailsTemp;
        //Get product details to delete
        ObjectSet<ProductDetails> details = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getProductDetailID() == productDetails.getProductDetailID();
            }
        });
        detailsTemp = details.next();
        System.out.println("ID" + detailsTemp.getProductDetailID());
        db.delete(detailsTemp);

        //Find id bill of product details. If number of product details in this bill is O, delete the bill
        ObjectSet<ProductDetails> numOfProductDetail = db.query(new Predicate<ProductDetails>() {
            public boolean match(ProductDetails dt) {
                return dt.getBill().getBillID() == productDetails.getBill().getBillID();
            }
        });
        int numProductOfBill = numOfProductDetail.size();
        if (numProductOfBill == 0) {
            //Find the bill and delete this bill
            ObjectSet<Bill> billDelete = db.query(new Predicate<Bill>() {
                public boolean match(Bill b) {
                    return b.getBillID() == productDetails.getBill().getBillID();
                }
            });
            //Delete bill don't have any product
            db.delete(billDelete.next());
        }
        //Push data into database
        db.commit();
    }

    public void UpdateNameProduct(final int id, String name) {
        Product p;
        ObjectSet<Product> result = db.query(new Predicate<Product>() {
            @Override
            public boolean match(Product product) {
                return product.getProductId() == id;
            }
        });
        p = result.next();
        System.out.println("before Ten san pham: " + p.getProductName());
        p.setProductName(name);
        System.out.println("after Ten san pham: " + p.getProductName());

        db.store(p);
        db.commit();
    }

    public int getMaxProductID() {
        int MaxID = 0;
        ObjectSet<Product> dt = db.queryByExample(Product.class);
        if (dt.size() != 0)
            for (Product p : dt) {
                if (p.getProductId() > MaxID)
                    MaxID = p.getProductId();
            }
        //System.out.println("MAX PRODUCT DETAILS ID => " + MaxID);
        return MaxID;
    }

    public int moveTable(final Table srcTable, final int desTable) {
        //Check desTable is exist
        Bill srcBill = checkBillExist(srcTable.getIdTable());
        Bill desBill = checkBillExist(desTable);
        if (desBill == null) { // DON'T HAVE A BILL
            desBill.setTable(srcBill.getTable());
            return 1;
        } else return 0;
    }

    public List<Table> getListTableEmpty(final int TableID) {
        List<Table> tableList = new ArrayList<Table>();
        ObjectSet<Table> results = db.query(new Predicate<Table>() {
            @Override
            public boolean match(Table table) {
                return table.getIdTable() != TableID && checkTableHasExist(table.getIdTable());
            }
        });

        for (Table tb : results) {
            tableList.add(tb);
            System.out.println("TABLE CAN MOVE: " + tb.getIdTable());
        }

        return tableList;
    }

    public User getUser(final String username) {
        ObjectSet<User> result = db.query(new Predicate<User>() {
            @Override
            public boolean match(User user) {
                return user.getUsername().equals(username);
            }
        });
        return result.next();
    }

    public void deleteProduct(final Product product, final String group) {
        final Product product1;
        GroupProduct gp = new GroupProduct();

        ObjectSet<Product> products = db.query(new Predicate<Product>() {
            @Override
            public boolean match(Product pro) {
                return (pro.getProductId() == product.getProductId());
            }
        });

        product1 = products.next();
        System.out.println("Name xoa: " + product1.getProductName());
        ObjectSet<GroupProduct> groupProducts = db.query(new Predicate<GroupProduct>() {
            @Override
            public boolean match(GroupProduct groupProduct) {
                return (groupProduct.getGroupProductName().equals(group));
            }
        });
        for (GroupProduct g: groupProducts) {
            gp = g;
        }
        gp.deleteProduct(product1);
        db.delete(product1);
        db.commit();
        System.out.println("Size after: " + aaa().size());
    }

    public List<Bill> getListBill(){
        List<Bill> listBill = new ArrayList<Bill>();
        ObjectSet<Bill> result = db.query(new Predicate<Bill>() {
            @Override
            public boolean match(Bill bill) {
                return bill.isState() == false;
            }
        });
        return  listBill;
    }


    public List<Product> aaa() {
        List<Product> products = new ArrayList<>();
        ObjectSet<Product> Products = db.queryByExample(Product.class);
        for (Product gd : Products) {
            products.add(gd);
        }
        return products;
    }



    public long getPriceProductInCurrentBill(final int productID, Calendar cal) {
        long price = 0;
        Date closetDate = new Date();
        Date dataCompare = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString =   String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "-" +
                String.valueOf(cal.get(Calendar.MONTH)+1) + "-" +
                String.valueOf(cal.get(Calendar.YEAR));
        try {
            dataCompare  = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final List<Date> dates = new ArrayList<Date>();
        ObjectSet<ListPrice> details = db.query(new Predicate<ListPrice>() {
            public boolean match(ListPrice lp) {
                return lp.getProduct().getProductId() == productID;
            }
        });

        for (ListPrice lp: details){
            dates.add(lp.getDateClass().getDate());
        }

        //Tang dan ASC
        Collections.sort(dates);

        System.out.println("1.DATE COMPARE: " + dataCompare);

        boolean flagFind = false;
        int size = dates.size();
        for(int i=size-1; i>=0; i--){
            if (dates.get(i).compareTo(dataCompare) <= 0){
                System.out.println("2.DATE: " + dates.get(i));
                closetDate = dates.get(i);
                flagFind = true;
                break;
            }
            //System.out.println("3.DATE: " + dates.get(i));
        }

        if(!flagFind){
            closetDate = dates.get(size-1);
            System.out.println("3.DATE(LAY NGAY DAU TIEN): " + dates.get(size-1));
        }

        for (ListPrice lp: details) {
            if (lp.getDateClass().getDate() == closetDate) {
                price = lp.getPrice();
                break;
            }
        }
        return price;
    }

    public void Close(){
        db.close();
    }


}






/*
        GroupProduct gp7 = new GroupProduct(7, "Rễ tranh");
        GroupProduct gp8= new GroupProduct(8,"Trà chanh");
        GroupProduct gp9 = new GroupProduct(9, "Cocktail");
        GroupProduct gp10 = new GroupProduct(10,"Soda");
        GroupProduct gp11 = new GroupProduct(11,"Nước ép");
        GroupProduct gp12 = new GroupProduct(12,"Sâm");

        listGroupProduct.add(gp7); listGroupProduct.add(gp8); listGroupProduct.add(gp9);
        listGroupProduct.add(gp10); listGroupProduct.add(gp11); listGroupProduct.add(gp12);

        listProduct.add(pd12);
        listProduct.add(pd13); listProduct.add(pd14); listProduct.add(pd14);

        Product pd12 = new Product(12,"Trà sữa kiwi", "Ly");
        Product pd13 = new Product(13,"Trà sữa Capcha", "Ly");
        Product pd14 = new Product(14,"Trà sữa khoai môn", "Ly");
        gp6.addProduct(pd12); pd6.setGroupProduct(gp6);
        gp6.addProduct(pd13); pd6.setGroupProduct(gp6);
        gp6.addProduct(pd14); pd6.setGroupProduct(gp6);
*/
