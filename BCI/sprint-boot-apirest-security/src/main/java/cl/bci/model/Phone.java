package cl.bci.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String citycode;

    private String contrycode;

}
