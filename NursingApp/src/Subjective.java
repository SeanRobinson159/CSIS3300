/**
 * 
 * @author Sean Robinson
 *
 */

public class Subjective {
	private String chiefComplaint;
	private String pastMedicalHistory;
	private String familyMedicalHistory;
	private String socialHistory;
	private String medications;
	private String allergies;
	private String reviewOfSystems;

	// TODO Figure out what check boxes/boolean values need to be created.

	public Subjective() {
		chiefComplaint = "";
		pastMedicalHistory = "";
		familyMedicalHistory = "";
		socialHistory = "";
		medications = "";
		allergies = "";
		reviewOfSystems = "";
	}

	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getPastMedicalHistory() {
		return pastMedicalHistory;
	}

	public void setPastMedicalHistory(String pastMedicalHistory) {
		this.pastMedicalHistory = pastMedicalHistory;
	}

	public String getFamilyMedicalHistory() {
		return familyMedicalHistory;
	}

	public void setFamilyMedicalHistory(String familyMedicalHistory) {
		this.familyMedicalHistory = familyMedicalHistory;
	}

	public String getSocialHistory() {
		return socialHistory;
	}

	public void setSocialHistory(String socialHistory) {
		this.socialHistory = socialHistory;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getReviewOfSystems() {
		return reviewOfSystems;
	}

	public void setReviewOfSystems(String reviewOfSystems) {
		this.reviewOfSystems = reviewOfSystems;
	}

}
