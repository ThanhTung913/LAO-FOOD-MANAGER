package Utils;

import model.SnackBar;

import java.util.Comparator;

public class SortProductByIdASC implements Comparator<SnackBar> {
    @Override
    public int compare(SnackBar o1, SnackBar o2) {
        if (o1.getId() - o2.getId() > 0) {
            return 1;
        } else if (o1.getId() - o2.getId() < 0) {
            return -1;
        }
        return 0;
    }
}
