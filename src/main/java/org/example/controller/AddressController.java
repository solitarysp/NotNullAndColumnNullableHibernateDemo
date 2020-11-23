package org.example.controller;

import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.AddressEntity;
import org.example.repo.AddressRepo;
import org.example.reponse.AddressRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/address")
public class AddressController extends BaseResource {

    @Autowired
    AddressRepo addressRepo;

    @GetMapping()
    public DeferredResult<?> get() {
        ListenableFuture<?> future = asyncExecute(() -> {
            List<AddressEntity> addressEntities = (List<AddressEntity>) addressRepo.findAll();

            AddressRP usersRP = new AddressRP();
            usersRP.setData(addressEntities);
            usersRP.setStatus(200);
            return usersRP;
        });
        return callBackResponse(future);

    }

    @PostMapping
    @Transactional
    public DeferredResult<?> post(@RequestHeader(value = "name",required = false) String name) {
        ListenableFuture<?> future = asyncExecute(() -> {
            log.info("test");
            AddressEntity address = new AddressEntity();
            address.setName(name);
            return addressRepo.save(address);
        });
        return callBackResponse(future);

    }

}
