package pl.marcb.MRP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcb.MRP.domain.Storage;
import pl.marcb.MRP.domain.repository.StorageRepository;
import pl.marcb.MRP.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {
    private StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public Storage insertStorage(Storage storage) {
        return storageRepository.save(storage);
    }
}
