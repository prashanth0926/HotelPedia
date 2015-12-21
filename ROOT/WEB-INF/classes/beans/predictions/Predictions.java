package beans.predictions;

import java.util.ArrayList;


public class Predictions {

	public Predictions(){};
	
	ArrayList<Descriptions> predictions;
	String status;
	
	public ArrayList<Descriptions> getPredictions() {
		return predictions;
	}

	public void setPredictions(ArrayList<Descriptions> predictions) {
		this.predictions = predictions;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}


