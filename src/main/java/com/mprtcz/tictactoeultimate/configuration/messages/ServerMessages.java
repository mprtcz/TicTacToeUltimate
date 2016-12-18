package com.mprtcz.tictactoeultimate.configuration.messages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by mprtcz on 2016-12-12.
 */
@Getter
@Setter
public class ServerMessages {

    @Getter
    public enum ServerMessageEnum {
        SECOND_PLAYER_EXISTS("Second player already exists in this game", HttpStatus.BAD_REQUEST),
        OK("OK", HttpStatus.OK),
        HOSTGAME_DOES_NOT_EXIST("game with this host does not exist", HttpStatus.BAD_REQUEST),
        GAME_CREATED("Game Created", HttpStatus.CREATED),
        GAME_DOES_NOT_EXIST("This game does not exist", HttpStatus.BAD_REQUEST),
        COORDINATES_OUT_OF_TABLE("Index exceeds table size", HttpStatus.BAD_REQUEST),
        SYMBOL_ALREADY_IN_PLACE("This place has been taken", HttpStatus.BAD_REQUEST),
        SUCCESSFUL_MOVE("Move has been successfully registered", HttpStatus.BAD_REQUEST),
        ONLY_NUMBERS("Move coordinates are bad", HttpStatus.BAD_REQUEST),
        GENERIC_EXCEPTION("Server has encountered an exception", HttpStatus.INTERNAL_SERVER_ERROR),
        WRONG_PLAYER("Wrong player", HttpStatus.BAD_REQUEST),
        GAME_IS_WON("The game has a winner", HttpStatus.OK);

        private String exceptionValue;
        private HttpStatus httpStatus;

        ServerMessageEnum(String exceptionValue, HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            this.exceptionValue = exceptionValue;
        }
    }

    private ServerMessageEnum messageEnum;
    private String customMessage;

    public ServerMessages(ServerMessageEnum messageEnum, String customMessage) {
        this.messageEnum = messageEnum;
        this.customMessage = customMessage;
    }

    public ServerMessages(ServerMessageEnum serverMessageEnum) {
        this.messageEnum = serverMessageEnum;
    }

    public ResponseEntity convertToResponseEntity() {
        return new ResponseEntity<>(toString(), this.getMessageEnum().getHttpStatus());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.getMessageEnum() != null) {
            stringBuilder.append("Message : ");
            stringBuilder.append(this.getMessageEnum().getExceptionValue());
            stringBuilder.append("\n");
        } else {
            return "how did you manage to construct an empty object like that?";
        }
        if(this.customMessage != null) {
            stringBuilder.append("Custom message : ");
            stringBuilder.append(this.getCustomMessage());
        }
        return stringBuilder.toString();
    }
}
