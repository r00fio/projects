package com.darkprograms.speech.recognizer;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Class that holds the response and confidence of a Google recognizer request
 *
 * @author Luke Kuza, Duncan Jauncey, Aaron Gokaslan
 ******************************************************************************/
public class GoogleResponse {

    /**
     * Variable that holds the response
     */
    private String response;
    /**
     * Variable that holds the confidence score
     */
    private Double confidence;

    /**
     * List that holds other possible responses for this request.
     */
    private List<String> otherPossibleResponses = new ArrayList<String>(20);

    /**
     * Constructor
     */
    public GoogleResponse() {

    }


    /**
     * Gets the response text of what was said in the submitted Audio to Google
     *
     * @return String representation of what was said
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set the response
     *
     * @param response The response
     */
    protected void setResponse(String response) {
        this.response = response;
    }

    /**
     * Gets the confidence score for the specific request
     *
     * @return The confidence score, ex .922343324323
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * Set the confidence score for this request
     *
     * @param confidence The confidence score
     */
    protected void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    /**
     * Get other possible responses for this request.
     * @return other possible responses
     */
    public List<String> getOtherPossibleResponses() {
        return otherPossibleResponses;
    }
    
    /**
     * Gets all returned responses for this request
     * @return All returned responses
     */
    public List<String> getAllPossibleResponses() {
    	List<String> tmp = otherPossibleResponses;
    	tmp.add(0,response);
    	return tmp;
    }

    public void addPossibleResponse(String response){
        if (otherPossibleResponses == null){
            otherPossibleResponses = new ArrayList<String>();
        }
        otherPossibleResponses.add(response);
    }

}
