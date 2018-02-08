package MySQL;

public class Identifiers {

	private  URL url;
	private String USER;
	private String PASSWORD;
	private String tableName;
	
	public Identifiers(URL url, String uSER, String pASSWORD, String tableName) {
		this.url = url;
		USER = uSER;
		PASSWORD = pASSWORD;
		this.tableName = tableName;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "Identifiers [url=" + url + ", USER=" + USER + ", PASSWORD=" + PASSWORD + ", tableName=" + tableName
				+ "]";
	}
	


}
