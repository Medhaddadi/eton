package com.dosi.eton.ExeceptionHandler;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageResponse {
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("liste resultats")
    private String listeResultats;


    public MessageResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public MessageResponse(int statusCode, String message, Object listeResultats) {
        this.statusCode = statusCode;
        this.message = message;
        this.listeResultats = listeResultats.toString();
    }
}
