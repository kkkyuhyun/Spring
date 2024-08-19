package org.scoula.ex03.dto;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TodoDTO {
    private String title;

    @DateTimeFormat(pattern ="yyyy/MM/dd")
    private Date dueDate;//date import 할때 util 에 있는 date input 한다.
    //new SimpleDateFormat(" ")

}
