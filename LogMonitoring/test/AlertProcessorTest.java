

class AlertProcessorTest {

    private AlertProcessor alertProcessor = new AlertProcessor();

    public static <T> void assertEqual(T actual, T expected) {
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            throw new AssertionError(
                "Expected:\n  " + expected +
                    "\nActual:\n  " + actual +
                    "\n");
        }
    }

    public static void main(String[] args) {
        AlertProcessorTest alertProcessorTest = new AlertProcessorTest();
        alertProcessorTest.testWindow_containsAlertPlusLagDurationRecords();
        alertProcessorTest.testWindow_hasAlertedForHighTraffic();
    }


    public void testWindow_containsAlertPlusLagDurationRecords() {
        long startTime = 1549573864;
        for(long time=startTime;time<startTime+200;time++) {
            LogLine logLine = new LogLine("10.0.0.4","-","apache", String.valueOf(time),"POST /api/help HTTP/1.0","200","1234");
            alertProcessor.process(logLine);
        }
        assertEqual(alertProcessor.getAlertStats().size(),AlertProcessor.LAG_DURATIONS_SECS + AlertProcessor.ALERT_DURATION_SECS + 1);
        alertProcessor.getAlertStats().clear();
    }

    public void testWindow_hasAlertedForHighTraffic() {
        long startTime = 1549573864;
        for(int j=0;j<130;j++) {
            for(int i=0;i<20;i++) {
                LogLine logLine = new LogLine("10.0.0.4","-","apache", string.valueOf(startTime),"POST /api/help HTTP/1.0","200","1234");
                alertProcessor.process(logLine);
            }
            startTime++;
        }
        boolean alertStatusBefore = alertProcessor.isInAlert();
        for(int j=0;j<80;j++) {
            for(int i=0;i<2;i++) {
                LogLine logLine = new LogLine("10.0.0.4","-","apache", string.valueOf(startTime),"POST /api/help HTTP/1.0","200","1234");
                alertProcessor.process(logLine);
            }
            startTime++;
        }
        boolean alertStatusAfter = alertProcessor.isInAlert();
        assertEqual(alertStatusBefore,true);
        assertEqual(alertStatusAfter,false);
    }
}