package service;

import model.SnackBar;

import java.util.List;

public interface ISnackBarService {
    List<SnackBar> getSnackBar();

    void add(SnackBar snackBar);

    SnackBar getById(int id);

    void update(int id, SnackBar snackBar);

    int getIndexById(int id);

    SnackBar lockSnackBar(int id);

    SnackBar unlockSnackBar(int id);

    boolean existId(int id);

    boolean checkDuplicateId(int id);

    boolean checkDuplicateName(String name);

    List<SnackBar> sortSnackbar();

    List<SnackBar> searchSnackBar(String name);


}
