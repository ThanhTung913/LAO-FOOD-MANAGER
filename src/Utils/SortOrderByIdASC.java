package Utils;

import model.OrderProduct;

import java.util.Comparator;

public class SortOrderByIdASC implements Comparator<OrderProduct> {
    @Override
    public int compare(OrderProduct o1, OrderProduct o2) {
        if (o1.getIdOrder() - o2.getIdOrder() > 0){
            return 1;
        } else if(o1.getIdOrder() - o2.getIdOrder() < 0){
            return -1;
        }
        return 0;
    }
}
