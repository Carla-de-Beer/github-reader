package dev.cadebe.github_reader.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    final String userName;
    final String htmlUrl;
    final String avatarUrl;
    final String yearCreated;
    final int numFollowers;
}
