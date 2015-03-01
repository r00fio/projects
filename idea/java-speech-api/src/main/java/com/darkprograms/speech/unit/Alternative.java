package com.darkprograms.speech.unit;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Alternative {

    @Expose
    private String transcript;
    @Expose
    private Double confidence;

    /**
     *
     * @return
     * The transcript
     */
    public String getTranscript() {
        return transcript;
    }

    /**
     *
     * @param transcript
     * The transcript
     */
    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    /**
     *
     * @return
     * The confidence
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     *
     * @param confidence
     * The confidence
     */
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}


