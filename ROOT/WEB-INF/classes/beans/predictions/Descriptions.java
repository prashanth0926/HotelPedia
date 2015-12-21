package beans.predictions;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Descriptions
{
	public Descriptions(){
		
	}
	
	String description,id,place_id,reference,types;
	MatchedSubstrings matched_substrings;
	Terms terms;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTypes() {
		return types;
	}
	@JsonIgnore
	public void setTypes(String types) {
		this.types = types;
	}
	public MatchedSubstrings getMatched_substrings() {
		return matched_substrings;
	}
	@JsonIgnore
	public void setMatched_substrings(MatchedSubstrings matched_substrings) {
		this.matched_substrings = matched_substrings;
	}
	public Terms getTerms() {
		return terms;
	}
	@JsonIgnore
	public void setTerms(Terms terms) {
		this.terms = terms;
	}
	
}
