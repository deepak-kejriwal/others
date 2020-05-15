
package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "companyTag"
})
public class Data {

    @JsonProperty("companyTag")
    private CompanyTag companyTag;
  

    @JsonProperty("companyTag")
    public CompanyTag getCompanyTag() {
        return companyTag;
    }

    @JsonProperty("companyTag")
    public void setCompanyTag(CompanyTag companyTag) {
        this.companyTag = companyTag;
    }



}
