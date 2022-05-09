package view;

public class Thread_Login implements Runnable{
    @Override
    public void run() {
        String a = "XIN";
        String b = " CHÀO";
        String c = " CÁC";
        String d = " BẠN";
        String e = " ĐÃ";
        String f = " GHÉ";
        String g = " THĂM";
        String h = " CỬA";
        String ti = " HÀNG";
        String tut = " CỦA";
        String uio = " MÌNH!!!";
        String[] list = {a,b,c,d,e,f,g,h,ti,tut,uio};

        for (int i = 0; i< list.length;i++ ){
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.print(list[i]);
        }
    }
}
