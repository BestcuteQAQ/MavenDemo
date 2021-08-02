/**
 * @author wyz
 * @create 2021-06-25 6:21
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class TimeDemo extends JFrame implements Runnable{
    Thread clock;
    final int Xpoint=180;
    final int Ypoint=180;
    final int R=80;
    int xHour=0,yHour=0,xSecond=0,ySecond=0,xMin=0,yMin=0;
    public TimeDemo() {
        super("数字时钟");
        setFont(new Font("黑体", Font.BOLD, 20));
        start();
        setSize(360, 360);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void start()//开始进程
    {
        if(clock==null)//如果进程为空值
        {
            clock=new Thread(this); //实例化进程
            clock.start();//开始进程
        }
    }
    public void run()//运行进程
    {
        while(clock!=null){
            repaint(); //调用paint方法重绘界面
            try {
                Thread.sleep(1000);//线程暂停一秒(1000毫秒)
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }
    public void stop()//停止进程
    {
        clock=null;
    }
    public void paint(Graphics g)//重载组件的paint方法
    {
        Graphics2D g2=(Graphics2D) g;//得到Graphics2D对象
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
        Calendar now =new GregorianCalendar();//实例化日历对象
        now.setTime(new Date());//dateFormat.format(now.getTime())
        String timeInfo="";//输出信息
        int hour=now.get(Calendar.HOUR_OF_DAY);//得到小时数
        int minute=now.get(Calendar.MINUTE);//得到分数
        int second=now.get(Calendar.SECOND);//得到秒数
        if(hour<=9)
            timeInfo+="0"+hour+":";//格式化输出
        else
            timeInfo+=hour+":";
        if(minute<=9)
            timeInfo+="0"+minute+":";
        else
            timeInfo+=minute+":";
        if(second<=9)
            timeInfo+="0"+second+":";
        else
            timeInfo+=second+"";
        g.setColor(Color.white);//设置当前颜色为黄色
        Dimension dim=getSize();//得到窗口尺寸
        g.fillRect(0, 0, dim.width, dim.height);//填充背景
        g.setColor(Color.red);//设置当前颜色
        g.drawString(timeInfo, 130, 340);//显示时间字符串
        g.setColor(Color.black);
        g.drawString(dateFormat.format(now.getTime()),20,60);
        g.setColor(Color.black);
        g.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        for(int i=0,num=12;i<360;i+=6){
            double alfa=Math.toRadians(i);
            int xPox=Xpoint+(int)(R*Math.sin(alfa));
            int yPos=Ypoint-(int)(R*Math.cos(alfa));
            if(i==0)
            {
                if (num%3==0)
                    g.setColor(Color.red); // 数字3,6,9,12为红色
                else
                    g.setColor(Color.black); // 其余数字为黑色
                g.drawString(""+num,xPox-5,yPos+3); // 写数字
                num=(num+1);
            }
            else {
                g.setColor(Color.black);
                g.drawString(".",xPox,yPos);
            }
        }
        g.setColor(Color.black);
        g.fillOval(Xpoint-4,Ypoint-4,8,8);
        //画秒针
        xSecond=(int)(Xpoint+(R-10)*Math.sin(second*(2*Math.PI/60)));
        ySecond=(int)(Ypoint-(R-10)*Math.cos(second*(2*Math.PI/60)));
        g.setColor(Color.red);
        g.drawLine(Xpoint,Ypoint,xSecond,ySecond);
        //画分针
        xMin=(int)(Xpoint+(R-20)*Math.sin((minute+second/60)*(2*Math.PI/60)));
        yMin=(int)(Ypoint-(R-20)*Math.cos((minute+second/60)*(2*Math.PI/60)));
        g.setColor(Color.red);
        g.drawLine(Xpoint,Ypoint,xMin,yMin);
        //画时针
        xHour=(int)(Xpoint+(R-30)*Math.sin((hour+minute/60+second/60/60)*(2*Math.PI/12)));
        yHour=(int)(Ypoint-(R-30)*Math.cos((hour+minute/60+second/60/60)*(2*Math.PI/12)));
        g.setColor(Color.red);
        g.drawLine(Xpoint,Ypoint,xHour,yHour);
    }
    public static void main(String[] args)
    {
        new TimeDemo();
    }
}