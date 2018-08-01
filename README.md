# HQAnalysis
Moving Averages class:
Ema subclass:
Used to calculate exponential moving average. Constructor takes in the parameter as an argument (26 for 26-day ema). Method calculateEma is used to calculate the ema which takes in the quantity whose ema is to be calculated (closing price if the ema of closing price is to be calculated).

Sma class:
Used to calculate simple moving average. Constructor takes in the parameter as an argument (26 for 26-day ema). Method calculateSma is used to calculate the sma which takes in the quantity whose sma is to be calculated (closing price if the sma of closing price is to be calculated).

Vwma subclass:
Used to calculate volume weighted moving average. Constructor takes in the parameter as an argument (26 for 26-day vwma). Method calculatevwma is used to calculate the vwma which takes in the quantity whose vwma is to be calculated and the volume (in this order).

Ama subclass:
Used to calculate adaptive moving average. Constructor takes in the parameter as an argument (26 for 26-day ama). Method calculateAma is used to calculate the ama which takes in the quantity whose ama is to be calculated (closing price if the ama of closing price is to be calculated).

Vma subclass:
Used to calculate variable moving average. Constructor takes in the parameter as an argument (26 for 26-day vma). Method calculateVma is used to calculate the vma which takes in the quantity whose vma is to be calculated (closing price if the vma of closing price is to be calculated).
Momentum Indicators class:
RSI subclass:
Used to calculate relative strength index. Constructor takes in the parameter as an argument (26 for 26-day RSI). Method calculateRSI is used to calculate the RSI which takes in the quantity whose RSI is to be calculated (closing price if the RSI of closing price is to be calculated).

MFI subclass:
Used to calculate money flow index. Constructor takes in the parameter as an argument (26 for 26-day MFI). Method calculateMFI is used to calculate the MFI which takes in the quantities whose MFI is to be calculated (closing price, high price, low price, volume).

Stochastics subclass:
Used to calculate Stochastic indicator. Constructor takes in two parameters as an argument  (parameter for calculation of stochastic, parameter for calculation of sma of stochastic). Method calculateStochastic is used to calculate the Stochastic which takes in the quantity whose Stochastic is to be calculated (closing price if the Stochastic of closing price is to be calculated).

Williams %R subclass:
Used to calculate Williams%R indicator. Constructor takes in the parameter as an argument (26 for 26-day Williams%R). Method calculate WilliamsR is used to calculate the Williams%R which takes in the quantity whose Wiliams%R is to be calculated (closing price if the Williams%R of closing price is to be calculated).


CandleStickTimeSeries-
This class can be used to create an OHLC chart overlaid with a time series of your choice. The constructor for this class takes in the following arguments (in this order):
String which is the title of your chart
Integer [][]: each row corresponds to a time. 6 columns which correspond to ss, mm, hh, dd, mm, yyyy.
double[]: array of the value whose time series chart is to be plotted.(ema/sma)
Date []: Array of dates corresponding to each time plotted.
double[]: Array of open values.
double[]: Array of high values.
double[]: Array of low values.
double[]: Array of close values.
double[]: Array of volume values.
