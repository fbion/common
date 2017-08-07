package demo.jfreechart.example2;

import javax.swing.*;
import java.awt.*;

/**
 * 项目名称：test<br/>
 * *********************************<br/>
 * <p>类名称：mainClass</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/22 14:11<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/22 14:11<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class mainClass {
    public static void main(String args[]){
        JFrame frame=new JFrame("Java数据统计图");
        frame.setLayout(new GridLayout(2,2,10,10));
        frame.add(new BarChart().getChartPanel());           //添加柱形图
        frame.add(new PieChart().getChartPanel());           //添加饼状图
        frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}
