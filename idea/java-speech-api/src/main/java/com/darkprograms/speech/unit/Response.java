package com.darkprograms.speech.unit;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Response {

    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("result_index")
    @Expose
    private Integer resultIndex;

    /**
     *
     * @return
     * The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The resultIndex
     */
    public Integer getResultIndex() {
        return resultIndex;
    }

    /**
     *
     * @param resultIndex
     * The result_index
     */
    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

}