package service;

import Utils.CSVUtils;
import Utils.SortProductByIdASC;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static final String PATH_FILE_SNACKBAR = "src/data/Product.csv";
    private static final String PATH_FILE_LOCKPRODUCT = "src/data/LockProduct.csv";

    @Override
    public List<Product> getSnackBar() {
        List<Product> snackBarList = new ArrayList<>();
        List<String> snackBarPath = CSVUtils.readFile(PATH_FILE_SNACKBAR);
        for (String record : snackBarPath) {
            snackBarList.add(new Product(record));
        }
        return snackBarList;
    }

    @Override
    public void add(Product snackBar) {
        List<Product> snackBarList = getSnackBar();
        snackBarList.add(snackBar);
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(int id) {
        for (Product snackBar : getSnackBar()) {
            if (snackBar.getId() == id) {
                return snackBar;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Product snackBar) {
        int index = getIndexById(id);
        List<Product> snackBarList = getSnackBar();
        snackBarList.remove(index);
        snackBarList.add(index, snackBar);
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getIndexById(int id) {
        for (int i = 0; i < getSnackBar().size(); i++) {
            if (id == getSnackBar().get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Product lockProduct(int id) {
        int index = getIndexById(id);
        List<Product> snackBarList = getSnackBar();
        List<Product> snackBarListLock = getLockProduct();
        snackBarListLock.add(snackBarList.get(index));
        Product snackBarRemove = snackBarList.remove(index);
        try {
            CSVUtils.writeFile(PATH_FILE_LOCKPRODUCT, snackBarListLock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snackBarRemove;
    }

    public List<Product> getLockProduct() {
        List<Product> snackBarList = new ArrayList<>();
        List<String> snackBarListLoc = CSVUtils.readFile(PATH_FILE_LOCKPRODUCT);
        for (String record : snackBarListLoc) {
            snackBarList.add(new Product(record));
        }
        return snackBarList;
    }

    @Override
    public Product unlockProduct(int id) {
        List<Product> userList = getSnackBar();
        List<Product> userLockList = getLockProduct();
        for (Product snackBar : userLockList) {
            if (snackBar.getId() == id) {
                userList.add(snackBar);
                userLockList.remove(snackBar);
                try {
                    CSVUtils.writeFile(PATH_FILE_SNACKBAR, userList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    CSVUtils.writeFile(PATH_FILE_LOCKPRODUCT, userLockList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return snackBar;
            }
        }
        return null;
    }

    @Override
    public Product remove(int id) {
        int index = getIndexById(id);
        List<Product> snackBarList = getSnackBar();
        Product snackBarRemove = snackBarList.remove(index);
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snackBarRemove;
    }

    @Override
    public boolean existId(int id) {
        List<Product> snackBarList = getSnackBar();
        List<Product> lockSnackBarList = getLockProduct();
        for (Product snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        for (Product snackBar : lockSnackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //    public boolean exist(int id){
//
//    }
    @Override
    public boolean checkDuplicateId(int id) {
        List<Product> snackBarList = getSnackBar();
        List<Product> lockSnackBarList = getLockProduct();
        for (Product snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        for (Product snackBar : lockSnackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateIdUnlockProduct(int id) {
        List<Product> snackBarList = getSnackBar();
        List<Product> lockSnackBarList = getLockProduct();
        for (Product snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<Product> snackBarList = getSnackBar();
        List<Product> lockSnackBarList = getLockProduct();

        for (Product snackBar : snackBarList) {
            if (snackBar.getName().equals(name)) {
                return true;
            }
        }
        for (Product snackBar : lockSnackBarList) {
            if (snackBar.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> sortProduct() {
        List<Product> snackBarList = getSnackBar();
        snackBarList.sort(new SortProductByIdASC());
        return snackBarList;
    }

    @Override
    public List<Product> searchSnackBar(String name) {
        List<Product> snackBarList = getSnackBar();
        List<Product> searchSnackBar = new ArrayList<>();
        for (Product snackBar : snackBarList) {
            if (snackBar.getName().toLowerCase().contains(name.toLowerCase())) {
                searchSnackBar.add(snackBar);
            }
        }
        searchSnackBar.sort(new SortProductByIdASC());
        return searchSnackBar;
    }
}
