package homework_13.singleton_configuration;

public class ConfigurationManager {

    private String url = "postgres://database";
    private String userName = "Nikita";
    private String password = "123";
    private String path = "Path//username";
    private String logLaval = "Debug";
    private String pathLog = "PathLog//log/loglevel";
    private static ConfigurationManager configurationManager;

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }

    public String getLogLaval() {
        return logLaval;
    }

    public String getPathLog() {
        return pathLog;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLogLaval(String logLaval) {
        this.logLaval = logLaval;
    }

    public void setPathLog(String pathLog) {
        this.pathLog = pathLog;
    }

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }
}