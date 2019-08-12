package priv.thinkam.rent.dao.model.wrap;

import java.io.Serializable;

public class CityWrap implements Serializable{
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
