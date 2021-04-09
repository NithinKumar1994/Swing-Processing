# Swing-Processing
Swing Processing using Java Spring boot.

Sensors record data from a three-axis accelerometer and a three-axis gyroscope. In order to
appropriately use that data, we also need to record the timestamp at which the samples were taken. For
any one swing, we'll have about a thousand such samples. We'd like you to put together a data structure
that would represent all of this data.A sample data file arranged in a CSV format. The columns
are (in order) timestamp, ax, ay, az, wx, wy, wz. 


csv file is scanned and data is processed and stored in arraylists.This data is used to process the swing data using the 4 functions.<br>
<br>● searchContinuityAboveValue(data, indexBegin, indexEnd, threshold,
winLength) - from indexBegin to indexEnd, search data for values that are higher than
threshold. Returns the first index where data has values that meet this criteria for at least
winLength samples in a row.</br>
<br>● backSearchContinuityWithinRange(data, indexBegin, indexEnd,
thresholdLo, thresholdHi, winLength) - from indexBegin to indexEnd (where
indexBegin is larger than indexEnd), search data for values that are higher than
thresholdLo and lower than thresholdHi. Returns the first index where data has values that
meet this criteria for at least winLength samples in a row.</br>
<br>● searchContinuityAboveValueTwoSignals(data1, data2, indexBegin,
indexEnd, threshold1, threshold2, winLength) - from indexBegin to indexEnd,
search data1 for values that are higher than threshold1 and also search data2 for values
that are higher than threshold2. Returns the first index where both data1 and data2 have
values that meet these criteria for at least winLength samples in a row.</br>
<br>● searchMultiContinuityWithinRange(data, indexBegin, indexEnd,
thresholdLo, thresholdHi, winLength) - from indexBegin to indexEnd, search data
for values that are higher than thresholdLo and lower than thresholdHi. Returns the
starting index and ending index of all continuous samples that meet this criteria for at least
winLength data points.</br>
