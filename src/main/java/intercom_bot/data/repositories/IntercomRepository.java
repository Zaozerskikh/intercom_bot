package intercom_bot.data.repositories;

import intercom_bot.data.entities.Intercom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IntercomRepository extends JpaRepository<Intercom, Long>  {
    List<Intercom> findAllByHouseAddressLikeAndEntranceNumberEquals(String addressPattern, int entranceNumber);

    // Avoids addresses repetitions in the DB during its initial filling.
    List<Intercom> findAllByHouseAddressEqualsAndEntranceNumberEquals(String address, int entranceNumber);
}
