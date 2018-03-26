package Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class DummyLogStore {
    private Map<String, String> logMap;

    private static DummyLogStore ourInstance = new DummyLogStore();

    public static DummyLogStore getInstance() {
        return ourInstance;
    }

    private DummyLogStore() {
        logMap = new LinkedHashMap<>();
        this.setup();
    }

    private void setup() {
        logMap.put("10", "Site: Coh Pipi, Date: 20 Dec 2017, Depth: 12m, Clarity: 20m");
        logMap.put("11", "Site: Coh Tao, Date: 23 Dec 2017, Depth: 14m, Clarity: 10m");
        logMap.put("12", "Site: Coh pham, Date: 24 Dec 2017, Depth: 13m, Clarity: 22m");
    }

    public String getLogByID(String id) {
        return logMap.get(id);
    }

    public void addLog(String id, String log) {
        logMap.put(id, log);
    }
}
