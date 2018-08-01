package technicalAnalysis;

import java.util.ArrayList;

/**
 * Class used to calculate various momentum indicators-
 * RSI,MFI,Stochastics,William %R
 * 
 * @author RADHIKA PASARI
 * 
 */

public class MomentumIndicators {
	/**
	 * Class to calculate the relative strength index/RSI
	 * 
	 * 
	 */
	public static class RSI {
		int parameter;
		double close;
		double gain;
		double loss;
	    ArrayList<Double>  prdif;
		
        
		/**
		 * Constructor to initialize the parameter,the current closing price,
		 * the arraylist which stores the difference between consecutive prices
		 * 
		 * @param x
		 *            Gives the period over which RSI is to be calculated
		 */
		public RSI(int x) {
			parameter = x;
			close = 0;
			prdif = new ArrayList<Double>();
			
		}

		/**
		 * Method to calculate RSI
		 * 
		 * @param t
		 *            Current value of time series
		 * @return Returns the RSI
		 */
		public double CalculateRSI(double t) {
			double rsi = 0;
			double gain = 0;
			double loss = 0;
			if (this.close != 0) {
				prdif.add(t-this.close);
			}
			this.close = t;
			if (prdif.size() == parameter) {
				for (int i = 0; i < parameter; i++) {
					if (prdif.get(prdif.size() - 1 - i) > 0) {
						gain = gain + prdif.get(prdif.size() - 1 - i)
								/ parameter;
					} else  {
						loss = loss + Math.abs(prdif.get(prdif.size() - 1 - i))
								/ parameter;
					}
				}
				rsi = 100 * gain / (loss + gain);
			} else if (prdif.size() > parameter) {
				if (prdif.get(prdif.size() - 1) > 0) {
					gain = (this.gain * (parameter - 1) + prdif.get(prdif
							.size() - 1)) / parameter;
					loss = this.loss*(parameter-1)/parameter;
				} else {
					gain = this.gain*(parameter-1)/parameter;
					loss = (this.loss * (parameter - 1) + Math.abs(prdif.get(prdif
							.size() - 1))) / parameter;
				} 
				rsi = 100 * gain / (loss + gain);
			}
			this.gain = gain;
			this.loss = loss;
			return rsi;
		}
	}

	/**
	 * 
	 * Class to calculate the money flow index
	 * 
	 */
	public static class MFI {
		int parameter;
		double rmf;
		double gain;
		double loss;
		ArrayList<Double> prdif;

		/**
		 * Constructor to initialize the parameter,the current raw money flow,
		 * the arraylist which stores the difference between consecutive raw
		 * money flow
		 * 
		 * @param x
		 *            Gives the period over which MFI is to be calculated
		 */
		public MFI(int x) {
			parameter = x;
			rmf = 0;
			prdif = new ArrayList<Double>();
		}

		/**
		 * Method to calculate the MFI
		 * 
		 * @param cl
		 *            closing price for current timestamp
		 * @param hi
		 *            high for current timestamp
		 * @param lo
		 *            low for current timestamp
		 * @param vol
		 *            volume for current timestamp
		 * @return returns the value of MFI for current timestamp
		 */
		public double CalculateMFI(double cl, double hi, double lo, double vol) {
			double rsi = 0;
			double gain = 0;
			double loss = 0;
			if (this.rmf != 0) {
				prdif.add((cl + hi + lo) * vol / 3 - this.rmf);
			}
			this.rmf = (cl + hi + lo) * vol / 3;
			if (prdif.size() == parameter) {
				for (int i = 0; i < parameter; i++) {
					if (prdif.get(prdif.size() - 1 - i) > 0) {
						gain = gain + prdif.get(prdif.size() - 1 - i)
								/ parameter;
					} else  {
						loss = loss + Math.abs(prdif.get(prdif.size() - 1 - i))
								/ parameter;
					}
				}
				rsi = 100 * gain / (loss + gain);
			} else if (prdif.size() > parameter) {
				if (prdif.get(prdif.size() - 1) > 0) {
					gain = (this.gain * (parameter - 1) + prdif.get(prdif
							.size() - 1)) / parameter;
					loss = this.loss*(parameter-1)/parameter;
				} else {
					gain = this.gain*(parameter-1)/parameter;
					loss = (this.loss * (parameter - 1) + Math.abs(prdif.get(prdif
							.size() - 1))) / parameter;
				} 
				rsi = 100 * gain / (loss + gain);
			}
			this.gain = gain;
			this.loss = loss;
			return rsi;
		}
	}

	/**
	 * 
	 * Class to calculate the Stochastic indicator
	 * 
	 */
	public static class Stochastic {
		int days;
		double st;
		
		ArrayList<Double> price;
		MovingAverages.sma sma;

		/**
		 * Constructor to initialize the number of days,the arraylist which will
		 * store the entire time series,sma which is an object of the class used
		 * to calculate sma
		 * 
		 * @param x
		 *            Number of days over which the indicator is to be
		 *            calculated
		 * @param y
		 *            Number of days over which the Stochastic indicator is to
		 *            be smoothened
		 */
		public Stochastic(int x, int y) {
			days = x;
			st = 0;
			price = new ArrayList<Double>();
			sma = new MovingAverages.sma(y);
		}

		/**
		 * Method to calculate the Stochastic indicator
		 * 
		 * @param t
		 *            Value of time series at current timestamp.
		 * @return Returns the value of Stochastic indicator at current
		 *         timestamp
		 */
		public double CalculateStochastic(double t) {
			double st = 0;
			price.add(t);
			double max = 0;
			double min = Double.POSITIVE_INFINITY;
			if (price.size() >= days) {
				for (int i = 0; i < days; i++) {
					if (price.get(price.size() - 1 - i) > max) {
						max = price.get(price.size() - 1 - i);
					} if (price.get(price.size() - 1 - i) < min) {
						min = price.get(price.size() - 1 - i);
					}
				}
				st = (100 * (t - min)) / (max - min);
			}
			this.st = st;
			return st;
		}

		/**
		 * Method to calculate the simple moving average of the Stochastic
		 * indicator
		 * 
		 * @return returns the sma of the Stochastic indicator
		 */
		public double CalculateStochasticSma() {
			double av = sma.calculateSma(st);
			return av;
		}

	}

	/**
	 * 
	 * Class to calculate WilliamR indicator
	 * 
	 */
	public static class WilliamsR {
		int days;
		ArrayList<Double> price;

		/**
		 * Constructor to initialize the days and arraylist which will store the
		 * entire timeseries
		 * 
		 * @param x
		 *            Number of days over which WilliamsR is to be calculated
		 */
		public WilliamsR(int x) {
			days = x;
			price = new ArrayList<Double>();
		}

		/**
		 * Method to calculate WilliamsR
		 * 
		 * @param t
		 *            Value of timeseries at current timestamp
		 * @return Returns the WilliamR indicator for current timestamp
		 */
		public double CalculateWilliamsR(double t) {
			double r = 0;
			price.add(t);
			double max = 0;
			double min = Double.POSITIVE_INFINITY;
			if (price.size() >= days) {
				for (int i = 0; i < days; i++) {
					if (price.get(price.size() - 1 - i) > max) {
						max = price.get(price.size() - 1 - i);
					} if (price.get(price.size() - 1 - i) < min) {
						min = price.get(price.size() - 1 - i);
					}
				}
				r = -100 * (max - t) / (max - min);
			}
			return r;
		}
	}
}
