package com.example.springmongodbpoc;

import com.example.springmongodbpoc.model.entities.Profile;
import com.example.springmongodbpoc.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfilePojo {
    public String id;
    private String userId;
    private String username;
    private List<String> usernames;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO8601_SECONDS)
    private Date signupTimestamp;
    private Map<String, Object> preferences;
    @Singular
    private List<Profile.ProviderIdentity> identities;
}
