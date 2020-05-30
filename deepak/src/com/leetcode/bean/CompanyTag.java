
package com.leetcode.bean;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "translatedName",
    "frequencies",
    "questions"
})
public class CompanyTag {

    @JsonProperty("name")
    private String name;
    @JsonProperty("translatedName")
    private Object translatedName;
    @JsonProperty("frequencies")
    private Map<Integer,List<Integer>> frequencies;
    @JsonProperty("questions")
    private List<Question> questions = null;
   
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("translatedName")
    public Object getTranslatedName() {
        return translatedName;
    }

    @JsonProperty("translatedName")
    public void setTranslatedName(Object translatedName) {
        this.translatedName = translatedName;
    }

    public Map<Integer, List<Integer>> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(Map<Integer, List<Integer>> frequencies) {
		this.frequencies = frequencies;
	}

	@JsonProperty("questions")
    public List<Question> getQuestions() {
        return questions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


}
