package com.darkprograms.speech.unit;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @Expose
    private List<Alternative> alternative = new ArrayList<Alternative>();
    @SerializedName("final")
    @Expose
    private Boolean _final;

    /**
     *
     * @return
     * The alternative
     */
    public List<Alternative> getAlternative() {
        return alternative;
    }

    /**
     *
     * @param alternative
     * The alternative
     */
    public void setAlternative(List<Alternative> alternative) {
        this.alternative = alternative;
    }

    /**
     *
     * @return
     * The _final
     */
    public Boolean getFinal() {
        return _final;
    }

    /**
     *
     * @param _final
     * The final
     */
    public void setFinal(Boolean _final) {
        this._final = _final;
    }

}