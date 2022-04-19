package intercom_bot.data.service;

import intercom_bot.data.entities.Intercom;

import java.util.List;

public interface IntercomService {
    void insert(Intercom entity);

    List<Intercom> findAddress(String[] request);

    boolean isAddressAlreadyAdded(String adress, int entranceNumber);
}
