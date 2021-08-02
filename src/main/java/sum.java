/**
 * @author wyz
 * @create 2021-07-31 20:35
 */
public class sum {
    public static double changshi(double a){
        return a*20*0.5;
    }
    public static double yanyu(double b){
        return b*40*0.8;
    }
    public static double panduan(double c){
        return c*20*1.4;
    }
    public static double ziliao(double d){
        return d*20;
    }
    public static double shuliang(double e){
        return e*10;
    }
    public static void main(String[] args) {
      double price =
        changshi(1)+
                yanyu(1)+
                    panduan(1)+
                        ziliao(1)+
                            shuliang(1);
        System.out.println(price);
    }
}
