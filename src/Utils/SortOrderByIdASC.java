package Utils;

import model.OrderItem;

import java.util.Comparator;

public class SortOrderByIdASC implements Comparator<OrderItem> {
    @Override
    public int compare(OrderItem o1, OrderItem o2) {
        if (o1.getIdOrder() - o2.getIdOrder() > 0){
            return 1;
        } else if(o1.getIdOrder() - o2.getIdOrder() < 0){
            return -1;
        }
        return 0;
    }
}
