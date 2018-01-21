import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args)
    {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//将时间格式转换成符合Timestamp要求的格式.
        Timestamp goodsC_date =Timestamp.valueOf(nowTime);//把时间转换
        System.out.print("==========");
        System.out.print(goodsC_date);
        System.out.print("==========");


        //String nowTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date("1516519410184"));//将时间格式转换成符合Timestamp要求的格式.
        //Timestamp goodsC_date2 =Timestamp.valueOf(nowTime2);//把时间转换
        Timestamp goodsC_date3 =new Timestamp(Long.valueOf("1516519410185"));
        System.out.print("==========");
        System.out.print(goodsC_date3);
        System.out.print("==========");
    }
}
