package dev.cadebe.github_reader.service;

import dev.cadebe.github_reader.model.GitHubRepository;
import dev.cadebe.github_reader.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

public interface ReaderService {

    JsonArray getJsonArrayRepos(String userName);

    JsonArray getJsonArraySubscriptions(String userName);

    List<JsonElement> buildDistinctCombinedRepoList(JsonArray array1, JsonArray array2);

    List<GitHubRepository> getAllRepositories(List<JsonElement> list, String userName);

    User getUser(String userNameInput);

    int countAllRepositories(List<GitHubRepository> repositories);

    int countAllRepositoriesWithLanguages(List<GitHubRepository> repositories);

    Map<String, Integer> getAllLanguages(List<GitHubRepository> repositories);

    Map<String, Double> getLanguageFrequencies(Map<String, Integer> langMap, int langCount);
}
