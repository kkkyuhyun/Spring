package org.scoula;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Setter;

@Component
@Data
@RequiredArgsConstructor
public class Restaurant {
    //@Autowired
    //java reflection 이용하여 외부에서 주입해서 자동으로 연결하겠다는 것
   final private Chef chef;
    //spring context bean 한해서 자동으로 등록을 해준다
    //생성자 앞에 @Autowired를 붙였다.

    //생성자가 하나★밖에 없다면 매개변수 주입을 해달라는 것
    //Restaurant(Chef chef) -> spring 4버전부터 추가
}