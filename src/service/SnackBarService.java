package service;

import Utils.CSVUtils;
import Utils.SortProductByIdASC;
import model.SnackBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnackBarService implements ISnackBarService {
    private static final String PATH_FILE_SNACKBAR = "src/data/Product.csv";
    private static final String PATH_FILE_LOCKPRODUCT = "src/data/LockProduct.csv";

    @Override
    public List<SnackBar> getSnackBar() {
        List<SnackBar> snackBarList = new ArrayList<>();
        List<String> snackBarPath = CSVUtils.readFile(PATH_FILE_SNACKBAR);
        for (String record : snackBarPath) {
            snackBarList.add(new SnackBar(record));
        }
        return snackBarList;
    }

    @Override
    public void add(SnackBar snackBar) {
        List<SnackBar> snackBarList = getSnackBar();
        snackBarList.add(snackBar);
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SnackBar getById(int id) {
        for (SnackBar snackBar : getSnackBar()) {
            if (snackBar.getId() == id) {
                return snackBar;
            }
        }
        return null;
    }

    @Override
    public void update(int id, SnackBar snackBar) {
        int index = getIndexById(id);
        List<SnackBar> snackBarList = getSnackBar();
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
    public SnackBar lockSnackBar(int id) {
        int index = getIndexById(id);
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> snackBarListLock = getLockProduct();
        snackBarListLock.add(snackBarList.get(index));
        SnackBar snackBarRemove = snackBarList.remove(index);
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

    public List<SnackBar> getLockProduct() {
        List<SnackBar> snackBarList = new ArrayList<>();
        List<String> snackBarListLoc = CSVUtils.readFile(PATH_FILE_LOCKPRODUCT);
        for (String record : snackBarListLoc) {
            snackBarList.add(new SnackBar(record));
        }
        return snackBarList;
    }

    @Override
    public SnackBar unlockSnackBar(int id) {
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> unlockSnackBarlist = getLockProduct();
        SnackBar unLockSnackBar = null;
        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                unlockSnackBarlist.remove(snackBar);
                unLockSnackBar = snackBar;
                break;
            }
            snackBarList.add(unLockSnackBar);
            try {
                CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                CSVUtils.writeFile(PATH_FILE_LOCKPRODUCT, unlockSnackBarlist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return unLockSnackBar;
    }

    public SnackBar remove(int id) {
        int index = getIndexById(id);
        List<SnackBar> snackBarList = getSnackBar();
        SnackBar snackBarRemove = snackBarList.remove(index);
        try {
            CSVUtils.writeFile(PATH_FILE_SNACKBAR, snackBarList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snackBarRemove;
    }

    @Override
    public boolean existId(int id) {
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> lockSnackBarList = getLockProduct();
        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        for (SnackBar snackBar : lockSnackBarList) {
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
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> lockSnackBarList = getLockProduct();
        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        for (SnackBar snackBar : lockSnackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateIdUnlockProduct(int id) {
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> lockSnackBarList = getLockProduct();
        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> lockSnackBarList = getLockProduct();

        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getName().equals(name)) {
                return true;
            }
        }
        for (SnackBar snackBar : lockSnackBarList) {
            if (snackBar.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SnackBar> sortSnackbar() {
        List<SnackBar> snackBarList = getSnackBar();
        snackBarList.sort(new SortProductByIdASC());
        return snackBarList;
    }

    @Override
    public List<SnackBar> searchSnackBar(String name) {
        List<SnackBar> snackBarList = getSnackBar();
        List<SnackBar> searchSnackBar = new ArrayList<>();
        for (SnackBar snackBar : snackBarList) {
            if (snackBar.getName().toLowerCase().contains(name.toLowerCase())) {
                searchSnackBar.add(snackBar);
            }
        }
        searchSnackBar.sort(new SortProductByIdASC());
        return searchSnackBar;
    }
}
