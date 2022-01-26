package michaelrosenfeld.mobile.mobilemini.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDto {

    private Long id;
    private String make;
    private String model;
    private String constructionYear;
    private String description;
    private int price;
    private String email;


}
