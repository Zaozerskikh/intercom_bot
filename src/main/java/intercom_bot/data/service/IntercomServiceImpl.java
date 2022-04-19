package intercom_bot.data.service;

import intercom_bot.data.entities.Intercom;
import intercom_bot.data.repositories.IntercomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@EnableAspectJAutoProxy
public class IntercomServiceImpl implements IntercomService {
    private IntercomRepository intercomRepository;

    @Autowired
    public void setIntercomRepository(IntercomRepository intercomRepository) {
        this.intercomRepository = intercomRepository;
    }

    @Override
    public void insert(Intercom entity) {
        intercomRepository.saveAndFlush(entity);
    }

    @Override
    public boolean isAddressAlreadyAdded(String adress, int entranceNumber) {
        return intercomRepository.findAllByHouseAddressEqualsAndEntranceNumberEquals(adress, entranceNumber).size() != 0;
    }

    /**
     * Database search according to user's request.
     * @param request user's request.
     * @return list of finded intercoms.
     */
    @Override
    public List<Intercom> findAddress(String[] request) {
        String[] words = request[0]
                .replaceAll("-", " ")
                .replaceAll("ё", "е")
                .replaceAll("\\s", " ")
                .split(" ");
        StringBuilder patternBuilder = new StringBuilder("%");
        Arrays.stream(words).forEach(keyword -> patternBuilder.append(keyword).append("%"));
        return intercomRepository.findAllByHouseAddressLikeAndEntranceNumberEquals(
                        patternBuilder.toString(),
                        Integer.parseInt(request[1].replaceAll("\\s", "")));
    }
}
