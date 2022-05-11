package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getSnackBar();

    void add(Product snackBar);

    Product getById(int id);

    void update(int id, Product snackBar);

    int getIndexById(int id);

    Product lockProduct(int id);

    Product unlockProduct(int id);

    Product remove(int id);

    boolean existId(int id);

    boolean checkDuplicateId(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateIdUnlockProduct(int id);

    List<Product> sortProduct();

    List<Product> searchSnackBar(String name);


}
