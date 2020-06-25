package com.app.esfita.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanInfo {

   @SerializedName("status")
   @Expose
   String status;

   @SerializedName("totalScore")
   @Expose
   String totalScore;

   @SerializedName("prediction")
    @Expose
    String prediction;

   @SerializedName("personalScore")
    @Expose
    String personalScore;

   @SerializedName("employmentScore")
    @Expose
    String employmentScore;

   @SerializedName("bankingScore")
    @Expose
    String bankingScore;

   @SerializedName("cibilScore")
    @Expose
    String cibilScore;

   public LoanInfo(){

   }

    public LoanInfo(String status, String totalScore, String prediction, String personalScore, String employmentScore,
                    String bankingScore, String cibilScore) {
        this.status = status;
        this.totalScore = totalScore;
        this.prediction = prediction;
        this.personalScore = personalScore;
        this.employmentScore = employmentScore;
        this.bankingScore = bankingScore;
        this.cibilScore = cibilScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getPersonalScore() {
        return personalScore;
    }

    public void setPersonalScore(String personalScore) {
        this.personalScore = personalScore;
    }

    public String getEmploymentScore() {
        return employmentScore;
    }

    public void setEmploymentScore(String employmentScore) {
        this.employmentScore = employmentScore;
    }

    public String getBankingScore() {
        return bankingScore;
    }

    public void setBankingScore(String bankingScore) {
        this.bankingScore = bankingScore;
    }

    public String getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(String cibilScore) {
        this.cibilScore = cibilScore;
    }
}
