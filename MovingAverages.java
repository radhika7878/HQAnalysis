package technicalAnalysis;

import java.util.ArrayList;
/**
 * 
 * @author RADHIKA PASARI
 *Class to calculate various moving averages-simple,exponential,
 *volume weighted,variable and adaptive using efficiency ratio
 */

public class MovingAverages {
/**
 * Calculates the exponential moving average of a time series
 * 
 *
 */
	public static class ema {
		int parameter;
		double e;
	/**
	 * Constructor to initialize the parameter 
	 * @param x
	 *        number of days whose moving average is to be calculated
	 */
		public ema(int x) {
			parameter = x;
			e = 0;
		}
/**
 * method to calculate exponential moving average
 * @param t
 *         value of time series whose ema is being calculated(op/hi/lo/cl)    
 * @return
 *        returns the value of the exponential moving average 
 */
		public double calculateEma(double t) {
			double e;
			
			if(this.e==0){
				e=t;
				}
				else{
				e = (t * 2 / (parameter + 1) +  this.e* (parameter - 1)
						/ (parameter + 1));
			}
			this.e = e;
			return e;
		}
	}
	/**
	 * Calculates the simple moving average of a time series
	 * 
	 *
	 */
	public static class sma{
		int days;
		double s;
		ArrayList<Double> v;
		/**
		 * Construtor to initialize days and arraylist which contains all the values
		 * @param x
		 *         number of days over which moving average is to be calculated 
		 */       
		public sma(int x){
			days = x;
			v = new ArrayList<Double>();
		}
		/**
		 * Method to calculate simple moving average
		 * @param t 
		 *        Value of current time series 
		 * @return
		 *       Value of Simple moving average
		 */
		public double calculateSma(double t){
			v.add(t);
			double s = 0;
			if(v.size()<days){	
			}
			else if(v.size() == days){
				for(int i=0; i<v.size();i++){
					s = s + v.get(i)/days;
				}		
			}
			else{
			s = this.s + t/days - v.get(v.size()-days-1)/days;	
		     }this.s = s;
		return s;
		}
	}
	/**
	 * 
	 *Calculates the volume weighted moving average of a time series
	 *
	 */
	public static class vwma{
		int days;
		ArrayList<Double> vol;
		ArrayList<Double> prvol;
		/**
		 * Constructor to intitialize the days,arraylists which stores the value of 
		 * the price*vol and vol
		 * @param x
		 *        Gives the number of days over which vwma is to be calculated
		 */
		public vwma(int x){
			days = x;
			prvol = new ArrayList<Double>();
			vol = new ArrayList<Double>();
		}
		/**
		 * Method to calculate the vwma
		 * @param p
		 *        current price of time series
		 * @param v
		 *        current volume of time series
		 * @return
		 *       returns the value of the vwma for that particular time
		 */
		public double calculatevwma(double p,double v){
			vol.add(v);
			prvol.add(p*v);
			double vwma = 0;
			if(vol.size()>=days){
				double n = 0;
				double d = 0;
				for(int i =0;i<days;i++){
				 n = n + prvol.get(prvol.size() - days + i);
				 d = d + vol.get(vol.size() - days + i);
				}
				vwma = n/d;
			}
			return vwma;
		}
	}
	/**
	 * 
	 * Class to calculate efficiency ratio which is used to calculate the 
	 * variable moving average and adaptive moving average
	 *
	 */
	public static class er{
		int days;
		ArrayList<Double> cl;
		/**
		 * Constructor to initialize the the number of days and the arraylist which 
		 * stores the entire time series
		 * @param x
		 *         Gives the number of days
		 */
		public er(int x){
			days = x;
			cl = new ArrayList<Double>();
		}
		/**
		 * Method to calculate the efficiency ratio at any time
		 * @param close
		 *           Gives the current price of the time series
		 * @return
		 *         Returns the efficiency ratio at that time
		 */
		public double calculateEr(double close){
			cl.add(close);
			double av = 0;
			if(cl.size()>days){
				double d=0;
				for(int i =0 ;i<days;i++){
					d = d + (Math.abs(cl.get(cl.size()-1-i) - cl.get(cl.size()-2-i)));
				}	if(d!=0){
				av = (Math.abs(cl.get(cl.size()-1)-cl.get(cl.size()-1-days)))/d;}
			}return av;
		}	
	}
	/**
	 * 
	 * Class to calculate the adaptive moving average
	 *
	 */
	public static class ama{
		int parameter;
		double av,t;
		er effratio;
		/**
		 * Constructor to initialize the parameter and the object effratio of the 
		 * class er which will be used to calculate the efficiency ratio
		 * @param x
		 *        Gives the period over which moving average is to be calculated
		 */
		public ama(int x){
			parameter = x;
			av = 0;
			t=0;
			 effratio = new er(parameter);
		}
		/**
		 * Method to calculate ama
		 * @param t
		 *         Value of current time series
		 * @return
		 *       returns the ama
		 */
		public double calculateAma(double t){
			double av = 0;	
			double e = effratio.calculateEr(t);
			double ssc = e*0.60215 + 0.06425;
			double vi = Math.pow(ssc,2);
			if(e!=0 && this.av==0){
				av = t*vi + this.t*(1-vi) ;
			}
			else if(this.av!=0){
				av = t*vi + this.av*(1-vi);	
			}
			this.t = t;
			this.av = av;
			return av;
	}
  }
	/**
	 * 
	 * Class to calculate variable moving average using efficiency ratio as volatility index
	 *
	 */
	public static class vma{
		int parameter;
		double av,d,t;
		er effratio;
		/**
		 * Constructor to initialize the parameter and the object effratio of the 
		 * class er which is used to calculate the efficiency ratio
		 * @param x
		 *         Gives the period over which moving average is to be calculated
		 */
		public vma(int x){
			parameter = x;
			av = 0;
			t = 0;
			d = ((double)2)/(parameter+1);
			effratio = new er(parameter);
		}
		/**
		 * Method to calculate vma
		 * @param t
		 *         Value of current time series
		 * @return
		 *       Returns the value of vma
		 */
		public double calculateVma(double t){
			double av = 0;
			double ssc = effratio.calculateEr(t);
			if(ssc!=0 && this.av==0){
				av = d*ssc*t + (1-(d*ssc))*this.t;
			}
			else{
				av = d*ssc*t + (1-(d*ssc))*this.av;	
			}
			this.t = t;
			this.av = av;
			return av;
		}
	}
}