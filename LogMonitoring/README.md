
## Description
This program is for parsing an HTTP Access Log file and capturing session data 
for every 10secs and raising alerts for previous 2mins if the average 
traffic across 10sec sessions exceeds a certain threshold.

## Assumption
The program works only as long as there is data available from the file
during those time intervals which means if the HTTP access log is empty 
for some seconds then the program will not raise alerts or display stats
considering those time intervals.

## Running the program
Run main program in `LogParser.java` which runs on sample file `sample_csv.txt`.
One Unit Test file added in AlertProcessorTest.java. Can be run as main program.

# Areas of improvement
1. Make it multi-threaded to process multiple log files.
2. Cache session data so we don't have to aggregate on the fly.
3. Handle missing data in the file if needed.