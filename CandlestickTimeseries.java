package charts;

import java.util.Date;

import javax.swing.JFrame;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * Class to plot a candlestick chart overlaid with a timeseries chart
 * 
 *
 */

public class CandlestickTimeseries{
	

	/**
	 * Constructor used to create the chart
	 * @param title 
	 *            Title of the chart
	 * @param time
	 *           2-d array which stores the date and time(format-ss, mm, hh, dd, mm, yyyy) for 
	 *           the timeseries chart
	 * @param ema
	 *          integer array which stores the value of moving average which has to plotted 
	 *          alongside the candlestick
	 * @param date
	 *         date array which stores date and time(format-yyyy, mm,dd,hh,mm,ss) for the 
	 *         candlestick chart
	 * @param high
	 *         double array to store the high for given time
	 * @param low
	 *          double array to store the low for the given time
	 * @param open
	 *          double array to store the open for the given time
	 * @param close
	 *          double array to store the close for the given time
	 * @param vol
	 *          double array to store the volume for the given times
	 */
	
	public CandlestickTimeseries(String title, Integer[][] time,double[] ema, Date[] date, 
			double[] high, double[] low, double[] open, double[] close, double[] vol) {
		
		JFrame frame = new JFrame();
		XYPlot plot = new XYPlot();

		@SuppressWarnings("unused")
		DateAxis domainaxis = new DateAxis("Time");
		domainaxis = (DateAxis) plot.getDomainAxis(); 
       
        NumberAxis rangeaxis = new NumberAxis("Price");
        rangeaxis = (NumberAxis) plot.getRangeAxis();
        rangeaxis.setLowerBound(1000);
        rangeaxis.setRange(1000,2000);
        
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        
        XYDataset dataset = createDataset(time, ema);
		DefaultHighLowDataset dataset1 = createDataset1(date, high, low, open, close, vol);
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,false);
		CandlestickRenderer renderer1 = new CandlestickRenderer();
		
		plot.setDataset(dataset);
		plot.setRenderer(renderer);
		plot.setDataset(1, dataset1);
		plot.setRenderer(1, renderer1);
		JFreeChart chart = new JFreeChart(plot);
		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.setPreferredSize(new java.awt.Dimension(1000, 500));
		frame.setContentPane(chartpanel);
		frame.setVisible(true);
		
	}

	
	public static XYDataset createDataset(Integer[][] time, double[] ema) {
		
		@SuppressWarnings("deprecation")
		TimeSeries s1 = new TimeSeries("Ema Relinfra", Second.class);
		for (int i = 0; i < ema.length; i++) {
			s1.add(new Second(time[i][0],time[i][1],time[i][2],time[i][3],time[i][4],time[i][5]), 
					ema[i]);
		}
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		return dataset;
	}
	public static DefaultHighLowDataset createDataset1(Date[] date, double[] high, double[] low, 
			double[] open, double[] close, double[] vol){
		
		DefaultHighLowDataset dataset1 = new DefaultHighLowDataset("Candlestick", date, high, low,
				open, close, vol);
		return dataset1;
	}

}



