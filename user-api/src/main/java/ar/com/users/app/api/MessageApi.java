package ar.com.users.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonRootName(value = "message")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Information.")
public class MessageApi {

    @JsonProperty
    @ApiModelProperty(notes = "Message.", required = true)
    private String message;
    
    public MessageApi() {}
    
    public MessageApi(String message)
    {
    	this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
