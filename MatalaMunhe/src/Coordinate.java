
public class Coordinate {

	Double Lon,Lat,Alt ;	
	
	public Coordinate(){
		this.Alt = null;
		this.Lat = null;
		this.Lon = null;
	}
	
	public Coordinate(Double lon, Double lat, Double alt) {
		Lon = lon;
		Lat = lat;
		Alt = alt;
	}

	public Double getLon() {
		return Lon;
	}

	public void setLon(Double lon) {
		Lon = lon;
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getAlt() {
		return Alt;
	}

	public void setAlt(Double alt) {
		Alt = alt;
	}

	@Override
	public String toString() {
		return "Coordinate: " + Lon + "," + Lat + "," + Alt;
	}
	
	
	
}
