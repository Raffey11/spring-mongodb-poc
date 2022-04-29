package com.example.springmongodbpoc.client;

import com.example.springmongodbpoc.ProfilePojo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "esb", url = "${mongodb.microservice}")
public interface MongodbSaveUserClient {

    @PostMapping("/v1.0/save-user-profile")
    void saveUserProfile(@RequestBody ProfilePojo userProfile);
}
