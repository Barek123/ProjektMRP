package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcb.MRP.domain.Storage;
import pl.marcb.MRP.domain.repository.StorageRepository;
import pl.marcb.MRP.domain.repository.StorageUpdateRepository;
import pl.marcb.MRP.service.StorageUpdateService;

import java.util.Date;
import java.util.List;

@Service
public class StorageUpdateServiceImpl implements StorageUpdateService {
    private StorageRepository storageRepository;
    private StorageUpdateRepository storageUpdateRepository;

    @Autowired
    public StorageUpdateServiceImpl(StorageRepository storageRepository, StorageUpdateRepository storageUpdateRepository) {
        this.storageRepository = storageRepository;
        this.storageUpdateRepository = storageUpdateRepository;
    }

    @Override
    public void updateStorage() {
        storageUpdateRepository.findAllByDate(new Date()).stream().forEach( c -> {
            List<Storage> storageList = storageRepository.findAllByProduct(c.getProduct());
            if(storageList.size()==1){
                Storage storage = storageList.get(0);
                storage.setValue(c.getNumber());
                storageRepository.save(storage);
                storageUpdateRepository.delete(c.getId());
            }
        });
    }
}
