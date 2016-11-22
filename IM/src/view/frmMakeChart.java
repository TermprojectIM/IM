package view;
//

//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.SymbolAxis;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYBarRenderer;
//import org.jfree.data.xy.XYIntervalSeries;
//import org.jfree.data.xy.XYIntervalSeriesCollection;
//
//import data.Info;
//
//public class frmMakeChart extends JFrame {
//	private JPanel contentPane;
//	
//	private int spots;
//	private XYIntervalSeries[] series;
//	private ChartPanel chartPanel;
//	private XYBarRenderer renderer;
//	private XYPlot plot;
//	private JFreeChart chart;
//	private XYIntervalSeriesCollection dataset;
//	private String[] spotName;
//	
//	public frmMakeChart(Info data[]) {
//		spots = data.length;
//	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 606, 560);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		setVisible(true);
//		
//		series = new XYIntervalSeries[spots];
//		dataset = new XYIntervalSeriesCollection();
//		String[] list = { "" };
//		spotName = new String[spots];
//		
//		for (int i = 0; i < spots; i++) {
//			spotName[i] = data[i].name;
//		}
//
//		for (int i = 0; i < spots; i++) {
//			series[i] = new XYIntervalSeries(spotName[i]);
//			dataset.addSeries(series[i]);
//		}
//
//		for (int i = 0; i < spots; i++) {
//			String split[] = data[i].time.split("-");
//			int start = Integer.parseInt(split[0]);
//			int end = Integer.parseInt(split[1]);
//			plusChart(i, start, end);
//		}
//		
//		renderer = new XYBarRenderer();
//		renderer.setUseYInterval(true);
//		plot = new XYPlot(dataset, new SymbolAxis("", list), new NumberAxis(), renderer);
//		plot.setOrientation(PlotOrientation.HORIZONTAL);
//		
//		chart = new JFreeChart(plot);
//		chartPanel = new ChartPanel(chart);
//		chartPanel.setBounds(27, 332, 525, 157);
//		
//		
//		
//		contentPane.add(chartPanel);
//	}
//
//	public void plusChart(int ID, int start, int end) {
//		series[ID].add(0, -0.3, 0.3, (start+end)/2, start, end);
//	}
//}

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import data.Info;
import javax.swing.JButton;

public class frmMakeChart extends JFrame {

	public frmMakeChart(Info data[]) {
		// super(title);
		setVisible(true);
		setBounds(100, 100, 800, 1000);
		getContentPane().setLayout(null);

		int reTime = 0;
		int start1, start2, start3;
		int end1, end2, end3;
		int temp1, temp2, temp3;
		DefaultPieDataset dataset = new DefaultPieDataset();
		int size = data.length;
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				String split[] = data[i].time.split("-");
				int start = Integer.parseInt(split[0]);
				int end = Integer.parseInt(split[1]);
				int temp = end - start;
				reTime += (start + temp);
				dataset.setValue("X", start);
				dataset.setValue(data[i].name, temp);
			}
			else{
				String split[] = data[i].time.split("-");
				int start = Integer.parseInt(split[0]);
				int end = Integer.parseInt(split[1]);
				int temp = end - start;
				reTime += (start + temp);
			}
		}

		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 300, 300);
		getContentPane().add(chartPanel);
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;
	}
}
