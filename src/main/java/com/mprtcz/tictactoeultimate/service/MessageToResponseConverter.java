package com.mprtcz.tictactoeultimate.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by mprtcz on 2016-12-10.
 */
public class MessageToResponseConverter {

    public static ResponseEntity convertToResponseEntity(ServerMessages exceptionMessage) {
        if(exceptionMessage == ServerMessages.OK) {
            return new ResponseEntity(HttpStatus.OK);
        } else if (exceptionMessage == ServerMessages.GENERIC_EXCEPTION){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(exceptionMessage.getExceptionValue(), HttpStatus.BAD_REQUEST);
        }
    }
}
