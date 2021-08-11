import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Date.DigitUtil;

/**
 * @author wyz
 * @create 2021-06-20 6:46
 */
public class DateAndWork {
    public static void main(String[] args) {
        Date today = new Date();
        String Today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);

        String start = "2021-06-24";
        String teacher = "2021-08-01";
        String str0 = "2021-08-21";
        String str1 = "2021-11-28";
        String str2 = "2021-12-20";
        String str3 = "2021-12-21";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = sdf.parse(start);
            Date teacherDate = sdf.parse(teacher);
            Date date0 = sdf.parse(str0);
            Date date1 = sdf.parse(str1);
            Date date2 = sdf.parse(str2);
            Date date3 = sdf.parse(str3);
            int day = (int) ((today.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
            System.out.println("第"+ DigitUtil.convertString(day)+"天："+Today);

//比较天数
            System.out.println("三支一扶:"+(date0.getTime() - today.getTime()) / (24 * 60 * 60 * 1000)+"天 ");
            System.out.print("国考:"+(date1.getTime() - today.getTime()) / (24 * 60 * 60 * 1000)+"天 ");
            System.out.print("山东省考:"+(date2.getTime() - today.getTime()) / (24 * 60 * 60 * 1000)+"天 ");
            System.out.print("江苏省考:"+(date3.getTime() - today.getTime()) / (24 * 60 * 60 * 1000)+"天");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

