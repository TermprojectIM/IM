package view;

import javax.swing.JFrame;

import data.HelpInfo;
import data.SelfInfo;
import main.Main;

import java.awt.Font;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.util.Random;

public class frmMakeChartHelp extends JFrame {
	Main main;

	public frmMakeChartHelp(HelpInfo[] data, String day, String begin, String finish, Main main) {
		this.main = main;
		setVisible(true);
		setBounds(100, 100, 1000, 800);
		setLayout(null);

		HelpInfo[] data1 = null;
		HelpInfo[] data2 = null;
		HelpInfo[] data3 = null;

		String temp = null;
		String[] temp1 = new String[4];
		String[] temp2 = new String[4];
		String[] temp3 = new String[4];

		Random random = new Random();

		if (day == "당일 여행") {
			temp1[0] = data[0].name1;
			temp1[1] = data[0].name2;
			temp1[2] = data[0].name3;
			temp1[3] = data[0].name4;

			data1 = new HelpInfo[2];
			temp = temp1[random.nextInt(4)];
			data1[0] = new HelpInfo(temp, 9, 11);
			temp = temp1[random.nextInt(4)];
			data1[1] = new HelpInfo(temp, 13, 17);
		} else if (day == "1박 2일") {
			temp1[0] = data[0].name1;
			temp1[1] = data[0].name2;
			temp1[2] = data[0].name3;
			temp1[3] = data[0].name4;

			temp2[0] = data[1].name1;
			temp2[1] = data[1].name2;
			temp2[2] = data[1].name3;
			temp2[3] = data[1].name4;

			if (begin == "오전") {
				data1 = new HelpInfo[2];
				temp = temp1[random.nextInt(4)];
				data1[0] = new HelpInfo(temp, 9, 11);
				temp = temp1[random.nextInt(4)];
				data1[1] = new HelpInfo(temp, 13, 17);
				if (finish == "오전") {
					data2 = new HelpInfo[1];
					temp = temp2[random.nextInt(4)];
					data2[0] = new HelpInfo(temp, 9, 11);
				} else if (finish == "오후") {
					data2 = new HelpInfo[2];
					temp = temp2[random.nextInt(4)];
					data2[0] = new HelpInfo(temp, 9, 11);
					temp = temp2[random.nextInt(4)];
					data2[1] = new HelpInfo(temp, 13, 17);
				}
			} else if (begin == "오후") {
				data1 = new HelpInfo[1];
				temp = temp1[random.nextInt(4)];
				data1[0] = new HelpInfo(temp, 13, 17);
				if (finish == "오전") {
					data2 = new HelpInfo[1];
					temp = temp2[random.nextInt(4)];
					data2[0] = new HelpInfo(temp, 9, 11);
				} else if (finish == "오후") {
					data2 = new HelpInfo[2];
					temp = temp2[random.nextInt(4)];
					data2[0] = new HelpInfo(temp, 9, 11);
					temp = temp2[random.nextInt(4)];
					data2[1] = new HelpInfo(temp, 13, 17);
				}
			}
		} else if (day == "2박 3일") {
			temp1[0] = data[0].name1;
			temp1[1] = data[0].name2;
			temp1[2] = data[0].name3;
			temp1[3] = data[0].name4;

			temp2[0] = data[1].name1;
			temp2[1] = data[1].name2;
			temp2[2] = data[1].name3;
			temp2[3] = data[1].name4;

			temp3[0] = data[2].name1;
			temp3[1] = data[2].name2;
			temp3[2] = data[2].name3;
			temp3[3] = data[2].name4;

			if (begin == "오전") {
				data1 = new HelpInfo[2];
				temp = temp1[random.nextInt(4)];
				data1[0] = new HelpInfo(temp, 9, 11);
				temp = temp1[random.nextInt(4)];
				data1[1] = new HelpInfo(temp, 13, 17);
				data2 = new HelpInfo[2];
				temp = temp2[random.nextInt(4)];
				data2[0] = new HelpInfo(temp, 9, 11);
				temp = temp2[random.nextInt(4)];
				data2[1] = new HelpInfo(temp, 13, 17);
				if (finish == "오전") {
					data3 = new HelpInfo[1];
					temp = temp3[random.nextInt(4)];
					data3[0] = new HelpInfo(temp, 9, 11);
				} else if (finish == "오후") {
					data3 = new HelpInfo[2];
					temp = temp3[random.nextInt(4)];
					data3[0] = new HelpInfo(temp, 9, 11);
					temp = temp3[random.nextInt(4)];
					data3[1] = new HelpInfo(temp, 13, 17);
				}
			} else if (begin == "오후") {
				data1 = new HelpInfo[1];
				temp = temp1[random.nextInt(4)];
				data1[0] = new HelpInfo(temp, 13, 17);
				data2 = new HelpInfo[2];
				temp = temp2[random.nextInt(4)];
				data2[0] = new HelpInfo(temp, 9, 11);
				temp = temp2[random.nextInt(4)];
				data2[1] = new HelpInfo(temp, 13, 17);
				if (finish == "오전") {
					data3 = new HelpInfo[1];
					temp = temp3[random.nextInt(4)];
					data3[0] = new HelpInfo(temp, 9, 11);
				} else if (finish == "오후") {
					data3 = new HelpInfo[2];
					temp = temp3[random.nextInt(4)];
					data3[0] = new HelpInfo(temp, 9, 11);
					temp = temp3[random.nextInt(4)];
					data3[1] = new HelpInfo(temp, 13, 17);
				}
			}
		}

		if (day == "당일 여행") {
			setTitle("Help - 당일 여행");
			// rabel
			JLabel lblNewLabel = new JLabel(day);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 50));
			lblNewLabel.setBounds(245, 15, 300, 60);
			getContentPane().add(lblNewLabel);
			// ganttChart
			XYIntervalSeriesCollection ganntDataset = creatGannt(data1);
			JFreeChart ganntChart = creatGanntChart(ganntDataset);
			ChartPanel ganntChartPanel = new ChartPanel(ganntChart);
			ganntChartPanel.setBounds(230, 100, 520, 200);
			getContentPane().add(ganntChartPanel);
			// pieChart
			DefaultPieDataset piDataset = creatPi(data1);
			JFreeChart piChart = createPieChart(piDataset);
			ChartPanel piChartPanel = new ChartPanel(piChart);
			piChartPanel.setBounds(30, 100, 200, 200);
			getContentPane().add(piChartPanel);
			// Text
			TextArea textArea1 = new TextArea();
			textArea1.setBounds(230, 300, 520, 100);
			textArea1.setText("교통 비 : " + moveCost(data1) + "\n" + "식 비 : " + eatCost(data1) + "\n");
			for (int i = 0; i < data1.length; i++) {
				textArea1.append(data1[i].name + " : " + data1[i].comment + "\n" + "시간 : " + data1[i].start + "시-"
						+ data1[i].end + "시" + "\n");
			}
			getContentPane().add(textArea1);

			setBounds(100, 100, 800, 500);
		} else if (day == "1박 2일") {
			setTitle("Help - 1박 2일");
			// rabel
			JLabel lblNewLabel1 = new JLabel(day);
			lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel1.setFont(new Font("함초롬돋움", Font.PLAIN, 50));
			lblNewLabel1.setBounds(245, 15, 300, 60);
			getContentPane().add(lblNewLabel1);
			// ganttChart
			XYIntervalSeriesCollection ganntDataset1 = creatGannt(data1);
			JFreeChart ganntChart1 = creatGanntChart(ganntDataset1);
			ChartPanel ganntChartPanel1 = new ChartPanel(ganntChart1);
			ganntChartPanel1.setBounds(230, 100, 520, 200);
			getContentPane().add(ganntChartPanel1);
			// pieChart
			DefaultPieDataset piDataset1 = creatPi(data1);
			JFreeChart piChart1 = createPieChart(piDataset1);
			ChartPanel piChartPanel1 = new ChartPanel(piChart1);
			piChartPanel1.setBounds(30, 100, 200, 200);
			getContentPane().add(piChartPanel1);
			// Text
			TextArea textArea1 = new TextArea();
			textArea1.setBounds(230, 300, 520, 100);

			textArea1.setText("교통 비 : " + moveCost(data1) + "\n" + "식 비 : " + eatCost(data1) + "\n" + "숙박 비 : "
					+ data1[0].sleepCost + "\n");
			for (int i = 0; i < data1.length; i++) {
				textArea1.append(data1[i].name + " : " + data1[i].comment + "\n" + "시간 : " + data1[i].start + "시-"
						+ data1[i].end + "시" + "\n");
			}

			getContentPane().add(textArea1);

			// ganttChart
			XYIntervalSeriesCollection ganntDataset2 = creatGannt(data2);
			JFreeChart ganntChart2 = creatGanntChart(ganntDataset2);
			ChartPanel ganntChartPanel2 = new ChartPanel(ganntChart2);
			ganntChartPanel2.setBounds(230, 450, 520, 200);
			getContentPane().add(ganntChartPanel2);
			// pieChart
			DefaultPieDataset piDataset2 = creatPi(data2);
			JFreeChart piChart2 = createPieChart(piDataset2);
			ChartPanel piChartPanel2 = new ChartPanel(piChart2);
			piChartPanel2.setBounds(30, 450, 200, 200);
			getContentPane().add(piChartPanel2);
			// Text
			TextArea textArea2 = new TextArea();
			textArea2.setBounds(230, 650, 520, 100);

			textArea2.setText("교통 비 : " + moveCost(data2) + "\n" + "식 비 : " + eatCost(data2) + "\n");
			for (int i = 0; i < data2.length; i++) {
				textArea2.append(data2[i].name + " : " + data2[i].comment + "\n" + "시간 : " + data2[i].start + "시-"
						+ data2[i].end + "시" + "\n");
			}
			getContentPane().add(textArea2);

			setBounds(100, 100, 800, 850);
		} else if (day == "2박 3일") {
			setTitle("Help - 2박 3일");
			// rabel
			JLabel lblNewLabel1 = new JLabel(day);
			lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel1.setFont(new Font("함초롬돋움", Font.PLAIN, 30));
			lblNewLabel1.setBounds(245, 10, 300, 40);
			getContentPane().add(lblNewLabel1);
			// ganttChart
			XYIntervalSeriesCollection ganntDataset1 = creatGannt(data1);
			JFreeChart ganntChart1 = creatGanntChart(ganntDataset1);
			ChartPanel ganntChartPanel1 = new ChartPanel(ganntChart1);
			ganntChartPanel1.setBounds(230, 50, 520, 200);
			getContentPane().add(ganntChartPanel1);
			// pieChart
			DefaultPieDataset piDataset1 = creatPi(data1);
			JFreeChart piChart1 = createPieChart(piDataset1);
			ChartPanel piChartPanel1 = new ChartPanel(piChart1);
			piChartPanel1.setBounds(30, 50, 200, 200);
			getContentPane().add(piChartPanel1);
			// Text
			TextArea textArea1 = new TextArea();
			textArea1.setBounds(230, 250, 520, 100);

			textArea1.setText("교통 비 : " + moveCost(data1) + "\n" + "식 비 : " + eatCost(data1) + "\n" + "숙박 비 : "
					+ data1[0].sleepCost + "\n");
			for (int i = 0; i < data1.length; i++) {
				textArea1.append(data1[i].name + " : " + data1[i].comment + "\n" + "시간 : " + data1[i].start + "시-"
						+ data1[i].end + "시" + "\n");
			}

			getContentPane().add(textArea1);

			// ganttChart
			XYIntervalSeriesCollection ganntDataset2 = creatGannt(data2);
			JFreeChart ganntChart2 = creatGanntChart(ganntDataset2);
			ChartPanel ganntChartPanel2 = new ChartPanel(ganntChart2);
			ganntChartPanel2.setBounds(230, 350, 520, 200);
			getContentPane().add(ganntChartPanel2);
			// pieChart
			DefaultPieDataset piDataset2 = creatPi(data2);
			JFreeChart piChart2 = createPieChart(piDataset2);
			ChartPanel piChartPanel2 = new ChartPanel(piChart2);
			piChartPanel2.setBounds(30, 350, 200, 200);
			getContentPane().add(piChartPanel2);
			// Text
			TextArea textArea2 = new TextArea();
			textArea2.setBounds(230, 550, 520, 100);
			textArea2.setText("교통 비 : " + moveCost(data2) + "\n" + "식 비 : " + eatCost(data2) + "\n" + "숙박 비 : "
					+ data2[0].sleepCost + "\n");
			for (int i = 0; i < data2.length; i++) {
				textArea2.append(data2[i].name + " : " + data2[i].comment + "\n" + "시간 : " + data2[i].start + "시-"
						+ data2[i].end + "시" + "\n");
			}
			getContentPane().add(textArea2);

			// ganttChart
			XYIntervalSeriesCollection ganntDataset3 = creatGannt(data3);
			JFreeChart ganntChart3 = creatGanntChart(ganntDataset3);
			ChartPanel ganntChartPanel3 = new ChartPanel(ganntChart3);
			ganntChartPanel3.setBounds(230, 650, 520, 200);
			getContentPane().add(ganntChartPanel3);
			// pieChart
			DefaultPieDataset piDataset3 = creatPi(data3);
			JFreeChart piChart3 = createPieChart(piDataset3);
			ChartPanel piChartPanel3 = new ChartPanel(piChart3);
			piChartPanel3.setBounds(30, 650, 200, 200);
			getContentPane().add(piChartPanel3);
			// Text
			TextArea textArea3 = new TextArea();
			textArea3.setBounds(230, 850, 520, 100);

			textArea3.setText("교통 비 : " + moveCost(data3) + "\n" + "식 비 : " + eatCost(data3) + "\n");
			for (int i = 0; i < data3.length; i++) {
				textArea3.append(data3[i].name + " : " + data3[i].comment + "\n" + "시간 : " + data3[i].start + "시-"
						+ data3[i].end + "시" + "\n");
			}

			getContentPane().add(textArea3);

			setBounds(100, 100, 800, 1050);
		}
	}

	private static XYIntervalSeriesCollection creatGannt(HelpInfo data[]) {
		XYIntervalSeriesCollection ganntDataset = new XYIntervalSeriesCollection();
		int size = data.length;

		XYIntervalSeries[] series = new XYIntervalSeries[size];

		String[] spotName = new String[size];

		for (int i = 0; i < size; i++) {
			spotName[i] = data[i].name;
			for (int j = 0; j < i; j++) {
				if (spotName[j] == data[i].name) {
					spotName[i] = " " + data[i].name;
				}
			}
		}

		for (int i = 0; i < size; i++) {
			series[i] = new XYIntervalSeries(spotName[i]);
			ganntDataset.addSeries(series[i]);
		}

		for (int i = 0; i < size; i++) {
			series[i].add(0, -0.3, 0.3, (data[i].start + data[i].end) / 2, data[i].start, data[i].end);
		}

		return ganntDataset;
	}

	private static JFreeChart creatGanntChart(XYIntervalSeriesCollection ganntDataset) {
		String[] list = { "" };
		XYBarRenderer renderer = new XYBarRenderer();
		renderer.setUseYInterval(true);
		XYPlot plot = new XYPlot(ganntDataset, new SymbolAxis("", list), new NumberAxis(), renderer);
		plot.setOrientation(PlotOrientation.HORIZONTAL);
		JFreeChart ganntChart = new JFreeChart(plot);
		return ganntChart;
	}

	private static DefaultPieDataset creatPi(HelpInfo data[]) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int size = data.length;
		int startArry[] = new int[size];
		int endArry[] = new int[size];

		for (int i = 0; i < size; i++) {
			startArry[i] = data[i].start;
			endArry[i] = data[i].end;
		}

		int reTime = 24;
		int j = 0;
		int x = 1;
		int temp1 = 0, temp2 = 0;
		for (int i = 0;; i++) {
			if (reTime != 0) {
				if (i == startArry[j]) {
					temp1 = endArry[j] - startArry[j];
					dataset.setValue(data[j].name + "\n" + startArry[j] + "시-" + endArry[j] + "시", temp1);
					i = endArry[j] - 1;
					reTime -= temp1;
					if (j < size - 1) {
						j++;
					}
				} else if (i != startArry[j]) {
					if (i + 1 == startArry[j] || reTime - 1 == 0) {
						dataset.setValue("휴식" + x, temp2 + 1);
						reTime -= 1;
						temp2 = 0;
						x++;
					} else {
						reTime -= 1;
						temp2 += 1;
					}
				}
			} else if (reTime == 0) {
				break;
			}
		}
		return dataset;
	}

	private static JFreeChart createPieChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart(null, dataset, false, false, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;
	}

	private static int moveCost(HelpInfo data[]) {
		int size = data.length;
		int cost = 0;
		for (int i = 0; i < size; i++) {
			cost += data[i].moveCost;
		}
		return cost;
	}

	private static int eatCost(HelpInfo data[]) {
		int size = data.length;
		int cost = 0;
		for (int i = 0; i < size; i++) {
			cost += data[i].eatCost;
		}
		return cost;
	}

}
