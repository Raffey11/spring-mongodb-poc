package com.example.springmongodbpoc.controller;

import com.example.springmongodbpoc.ProfilePojo;
import com.example.springmongodbpoc.client.MongodbSaveUserClient;
import com.example.springmongodbpoc.model.entities.Profile;
import com.example.springmongodbpoc.model.repos.ProfileRepository;
import com.example.springmongodbpoc.model.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileRepository profileRepo;
    private final ProfileService profileService;
    private final MongodbSaveUserClient client;

    @PostMapping("/get-user-profile/{fileName}")
    public void getUserProfile(@PathVariable("fileName") String fileName) throws Exception {
        List<String> userIds = readCSVFile(fileName);
        Optional<Profile> userProfileOpt = profileRepo.findByUserId(userIds.get(0));
        System.out.println(userProfileOpt);
        if (userProfileOpt.isPresent()) {
            Profile userProfile = userProfileOpt.get();
            client.saveUserProfile(ProfilePojo.builder()
                    .userId(userProfile.getUserId())
                    .username(userProfile.getUsername())
                    .usernames(userProfile.getUsernames())
                    .identities(userProfile.getIdentities())
                    .preferences(userProfile.getPreferences())
                    .signupTimestamp(new Date())
                    .build());
        }
    }

    private List<String> readCSVFile(String fileName) {
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine());
            }
            System.out.println(records);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }
        return records;
    }
}
