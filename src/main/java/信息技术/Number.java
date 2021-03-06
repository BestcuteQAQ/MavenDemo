package 信息技术;

import org.junit.Test;

/**
 * @author wyz
 * @create 2021-07-21 22:29
 */
public class Number {

//    正整数啊a是某个整数b的平方，
//    那么a叫完全平方数
//    若某个整数10000以内加100后是一个完全平方数，
//    再加168也是一个完全平方数，
//    编程求此数？
    @Test
    public void Demo1(){
    double i,x,y,z;
        for ( i = 0; i < 10000; i++) {
            x=Math.sqrt(i+100);
            y=Math.sqrt(i+168);
            if(((x*x) == (i+100) )&& ((y*y )==(i+168))){
                System.out.println(i);
            }
        }
}

//    九九乘法表
    @Test
    public void Demo2(){
        int i ,j,result;
        for (i = 1; i < 10; i++) {
            for ( j = 1; j <= i; j++) {
                result = i * j;
                System.out.print(j +"x"+i+"="+result+" ");
            }
            System.out.println();
        }
    }

    //    求100以内素数
    @Test
    public void Demo3(){
        //使用外层循环来控制1-100的数据的遍历
        for(int i = 1 ; i <= 100 ; i++){
            //素数就是除1及其本身外不能被其他整数整除
            int j = 2;
            while(i > j){
                //如果可以被小于他的数据整除，表示该数不是素数，跳出循环
                if(i % j == 0){
                    break;
                }
                j++;
            }
            //如果没有找到比其小的数据可以整除，则表示该数是素数
            if(i == j){
                System.out.print(i+" ");
            }
        }
    }
}
