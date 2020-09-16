package dev.cadebe.github_reader.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GitHubRepository {

    final String repoName;
    final String urlLink;
    final String description;
    final String language;
    final String createdYear;
    final String updatedYear;
    final int stargazersCount;
    final boolean isFork;
    final boolean isOwner;
}
