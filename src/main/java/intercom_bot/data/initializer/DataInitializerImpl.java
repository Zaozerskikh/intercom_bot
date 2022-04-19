package intercom_bot.data.initializer;

import intercom_bot.data.entities.Intercom;
import intercom_bot.data.service.IntercomService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;

/**
 * Bean that initializates intercom data once.
 */
@Component
@Scope("singleton")
@Log
public class DataInitializerImpl implements DataInitializer {
    private IntercomService intercomService;

    @Autowired
    public void setIntercomService(IntercomService intercomService) {
        this.intercomService = intercomService;
    }

    @Override
    public void initIntercomData() {
        Scanner scanner;

        // Parsing csv files with intercom codes.
        for (int i = 0; i < 102; i++) {
            scanner = new Scanner(Objects.requireNonNull(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("codes/codes" + i + ".csv")));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = (scanner.nextLine() + " ").replaceAll("ё", "е").split(";");
                if (!intercomService.isAddressAlreadyAdded(data[0], Integer.parseInt(data[1]))) {
                    intercomService.insert(new Intercom(data[0], Integer.parseInt(data[1]),
                            data[2], data[3]));
                }
            }
            log.info("[DATA INIT]: OK --> codes" + i);
        }
    }
}
