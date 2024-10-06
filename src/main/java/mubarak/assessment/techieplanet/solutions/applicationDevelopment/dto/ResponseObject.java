package mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class ResponseObject<T>{
    private Integer code;
    private boolean status;
    private String message;
    private Object error;
    private T data;
}
