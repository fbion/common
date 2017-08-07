package demo.jfreechart.example3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * 项目名称：test<br/>
 * *********************************<br/>
 * <p>类名称：DynamicDataDemo2Test_LF</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/23 10:30<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/23 10:30<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class DynamicDataDemo2Test_LF extends ApplicationFrame {

    static class DemoPanel extends JPanel implements ActionListener {

        private TimeSeries series1;

        private TimeSeries series2;

        private double lastValue1;

        private double lastValue2;

        public Timer time = new Timer(100, this);


        public void actionPerformed(ActionEvent actionevent) {
            boolean flag = false;
            boolean flag1 = false;
            if (actionevent.getActionCommand().equals("ADD_DATA_1")) {
                System.out.println("ADD_DATA_1");
                flag = true;
            } else if (actionevent.getActionCommand().equals("ADD_DATA_2")) {
                System.out.println("ADD_DATA_2");
                flag1 = true;
            } else if (actionevent.getActionCommand().equals("ADD_BOTH")) {
                System.out.println("ADD_BOTH");
                if (time.isRunning()) {
                    time.stop();
                } else {
                    time.start();
                }
            } else if (actionevent.getActionCommand().equals("Timer")) {
                System.out.println("Timer");
                flag = true;
                flag1 = true;
            }
            if (flag) {
                double d = 0.90000000000000002D + 0.20000000000000001D * Math
                        .random();
                lastValue1 = lastValue1 * d;
                Millisecond millisecond = new Millisecond();
                System.out.println("1");
//                System.out.println("Now = " + millisecond.toString());
                series1.add(new Millisecond(), lastValue1);
            }
            if (flag1) {
                double d1 = 0.90000000000000002D + 0.20000000000000001D * Math
                        .random();
                lastValue2 = lastValue2 * d1;
                Millisecond millisecond1 = new Millisecond();
                System.out.println("2");
//                System.out.println("Now = " + millisecond1.toString());
                series2.add(new Millisecond(), lastValue2);

            }
        }


        public DemoPanel() {

            super(new BorderLayout());
            time.setActionCommand("Timer");
            lastValue1 = 100D;
            lastValue2 = 500D;

            series1 = new TimeSeries(
                    "Random 1",
                    DynamicDataDemo2Test_LF.class$org$jfree$data$time$Millisecond);

            series2 = new TimeSeries(
                    "Random 2",
                    DynamicDataDemo2Test_LF.class$org$jfree$data$time$Millisecond);
            TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
                    series1);

            TimeSeriesCollection timeseriescollection1 = new TimeSeriesCollection(
                    series2);

            JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
                    "折线图Demo", "日期", "值",
                    timeseriescollection, true, true, false);
            jfreechart.setBackgroundPaint(Color.white);
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            xyplot.setBackgroundPaint(Color.lightGray);
            xyplot.setDomainGridlinePaint(Color.white);
            xyplot.setRangeGridlinePaint(Color.white);
            xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
            ValueAxis valueaxis = xyplot.getDomainAxis();
            valueaxis.setAutoRange(true);
            valueaxis.setFixedAutoRange(10000D);
            xyplot.setDataset(1, timeseriescollection1);

            NumberAxis numberaxis = new NumberAxis("Range Axis 2");
            numberaxis.setAutoRangeIncludesZero(false);
            xyplot.setRenderer(1, new DefaultXYItemRenderer());
            xyplot.setRangeAxis(1, numberaxis);
            xyplot.mapDatasetToRangeAxis(1, 1);
            ChartPanel chartpanel = new ChartPanel(jfreechart);

            add(chartpanel);

            JButton jbutton = new JButton("Add To Series 1");
            jbutton.setActionCommand("ADD_DATA_1");
            jbutton.addActionListener(this);
            JButton jbutton1 = new JButton("Add To Series 2");
            jbutton1.setActionCommand("ADD_DATA_2");
            jbutton1.addActionListener(this);
            JButton jbutton2 = new JButton("Add To Both");
            jbutton2.setActionCommand("ADD_BOTH");
            jbutton2.addActionListener(this);
            JPanel jpanel = new JPanel(new FlowLayout());
            jpanel.setBackground(Color.white);
            jpanel.add(jbutton);
            jpanel.add(jbutton1);
            jpanel.add(jbutton2);
            add(jpanel, "South");
            chartpanel.setPreferredSize(new Dimension(500, 270));

            // System.out.println(jfreechart.getPlot());
            // Plot categoryplot = jfreechart.getPlot();
            // categoryplot.setOrientation(PlotOrientation.HORIZONTAL);
            DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
            dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
            { //插入你的
                Font subjetcFont = new Font("宋体", Font.BOLD, 20);
                jfreechart.setTitle(new TextTitle("测试", subjetcFont));
                Font leftFont = new Font("宋体", Font.PLAIN, 12);

                LegendTitle legendTitle = jfreechart.getLegend(0);
                legendTitle.setItemFont(leftFont);

                jfreechart.setBackgroundPaint(Color.white);
                XYPlot plot = (XYPlot) jfreechart.getPlot();
                //取得横轴
                DateAxis categoryAxis = (DateAxis) plot.getDomainAxis();
                //设置横轴的字体
                categoryAxis.setLabelFont(leftFont);
                //设置分类标签以45度倾斜
                //     categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
                //设置分类标签字体
                categoryAxis.setTickLabelFont(leftFont);
                //时间轴间距是5秒,格式为小时:分钟
                int count = 5;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                categoryAxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, count, formatter));
                categoryAxis.setDateFormatOverride(formatter);
                //取得纵轴
                NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
                //设置纵轴的字体
                numberAxis.setLabelFont(leftFont);
                numberAxis.setAutoRange(false);
                numberAxis.setAutoTickUnitSelection(false);
                numberAxis.setTickUnit(new NumberTickUnit(2));
                numberAxis.setRangeWithMargins(33, 39);
            }
        }
    }

    static Class class$org$jfree$data$time$Millisecond = DynamicDataDemo2Test_LF
            .class$("org.jfree.data.time.Millisecond"); /* synthetic field */


    public DynamicDataDemo2Test_LF(String s) {
        super(s);
        setContentPane(new DemoPanel());
    }


    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }


    public static void main(String args[]) {
        DynamicDataDemo2Test_LF dynamicdatademo2 = new DynamicDataDemo2Test_LF(
                "JFreeChart: DynamicDataDemo2.java");
        dynamicdatademo2.pack();
        RefineryUtilities.centerFrameOnScreen(dynamicdatademo2);
        dynamicdatademo2.setVisible(true);
    }


    static Class class$(String s) {
        Class clazz = null;
        try {
            clazz = Class.forName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}