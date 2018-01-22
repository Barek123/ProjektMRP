package pl.marcb.MRP.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.marcb.MRP.service.StorageUpdateService;

@Component
public class Scheduler {
    @Autowired
    private StorageUpdateService storageUpdateService;

    @Scheduled(fixedRate = 50000)
    public void updateStorage(){
        storageUpdateService.updateStorage();
    }
}
