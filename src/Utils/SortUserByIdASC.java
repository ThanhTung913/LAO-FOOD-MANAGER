package Utils;

import model.User;

import java.util.Comparator;

public class SortUserByIdASC implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getIdUser() - o2.getIdUser() > 0) {
            return 1;
        } else if (o1.getIdUser() - o2.getIdUser() < 0) {
            return -1;
        }
        return 0;
    }
}
