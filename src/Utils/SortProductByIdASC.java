package Utils;

import model.Product;

import java.util.Comparator;

public class SortProductByIdASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getId() - o2.getId() > 0) {
            return 1;
        } else if (o1.getId() - o2.getId() < 0) {
            return -1;
        }
        return 0;
    }
}
