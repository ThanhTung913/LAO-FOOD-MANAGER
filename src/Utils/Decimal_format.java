package Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Decimal_format {
    public static void main(String[] args) {
        formatMoney();
    }
    public static void formatMoney() {
        int floatNumber = 1000000;
        Locale locale = new Locale("vn", "VN");
        String pattern = "###,###";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        System.out.println("Số " + floatNumber + " Sau khi định dạng " + decimalFormat.format(floatNumber) + "VND");
    }
}
