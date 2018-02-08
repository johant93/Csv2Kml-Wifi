package MySQL;

public class URL {
	
	//URL example : "jdbc:mysql://"+_ip+":3306/oop_course_ariel"
	
	private final String jdbc = "jdbc:mysql://";
	private String ip;
	private String port;
	private String dbName;
	
	public URL(String ip, String port, String dbName) {
		
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public String toString() {
		return jdbc + ip + ":" + port + "/" + dbName;
	}
	//jdbc:mysql://"+_ip+":3306/oop_course_ariel


}
