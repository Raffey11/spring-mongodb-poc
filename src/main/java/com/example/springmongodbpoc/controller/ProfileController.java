package com.example.springmongodbpoc.controller;

import com.example.springmongodbpoc.ProfilePojo;
import com.example.springmongodbpoc.model.entities.Profile;
import com.example.springmongodbpoc.model.repos.primary.PrimaryProfileRepository;
import com.example.springmongodbpoc.model.repos.secondary.SecondaryProfileRepository;
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

    private final PrimaryProfileRepository primaryProfileRepository;
    private final SecondaryProfileRepository secondaryProfileRepository;
    private final ProfileService profileService;

    @PostMapping("/get-user-profile/{fileName}")
    public void getUserProfile(@PathVariable("fileName") String fileName) throws Exception {
        List<String> userIds = readCSVFile(fileName);
        Optional<Profile> userProfileOpt = primaryProfileRepository.findByUserId(userIds.get(0));
        System.out.println(userProfileOpt);
        if (!userProfileOpt.isPresent()) {
            return;
        }
        Profile userProfile = userProfileOpt.get();
        Optional<Profile> secondUserProfile = secondaryProfileRepository.findByUserId(userProfile.getUserId());
        if (secondUserProfile.isPresent()) {
            return;
        }
        secondaryProfileRepository.save(userProfile);
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
