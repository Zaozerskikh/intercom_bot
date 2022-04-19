package intercom_bot.data.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Intercom entity.
 */
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "intercoms")
public class Intercom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(name = "house_address")
    private String houseAddress;

    @Setter
    @Column(name = "entrance_number")
    private int entranceNumber;

    @Setter
    @Column(name = "first_code")
    private String firstCode;

    @Setter
    @Column(name = "second_code")
    private String secondCode;

    public Intercom(String houseAddress, int entranceNumber, String firstCode, String secondCode) {
        this.houseAddress = houseAddress;
        this.entranceNumber = entranceNumber;
        this.firstCode = firstCode;
        this.secondCode = secondCode;
    }
}
