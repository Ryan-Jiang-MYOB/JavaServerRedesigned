package Service.LogService;

import Constants.DummyLogStore;

import java.util.Date;

public class GetLogService {
    private DummyLogStore logStore = DummyLogStore.getInstance();

    public String getLog(String id) {
        String logOutput = "Your Log Query is Processed at: " + new Date().toString() + "\n\n";
        String logString = logStore.getLogByID(id);
        if (logString != null) {
            logOutput += logString;
            return logOutput;
        } else {
            return null;
        }
    }
}
