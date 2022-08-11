package com.learningapp.exception;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "error")
@Data
public class ErrorResponse {
    private String message;
    private List<String> details;
    private Long timeStamp;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
        this.timeStamp = System.currentTimeMillis ();
    }

    public ErrorResponse(){}
}
