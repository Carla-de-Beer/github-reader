package com.cadebe.github_reader.controller;

import com.cadebe.github_reader.model.GitHubRepository;
import com.cadebe.github_reader.model.User;
import com.cadebe.github_reader.service.ReaderServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"", "/", "/index", "/index.html"})
public class ReaderController {

    private static ReaderServiceImpl readerServiceImpl;

    public ReaderController(ReaderServiceImpl readerServiceImpl) {
        ReaderController.readerServiceImpl = readerServiceImpl;
    }

    @GetMapping
    public String greetingForm(Model model) {
        model.addAttribute("gitHubName", "");
        return "index";
    }

    @PostMapping
    public String greetingSubmit(@RequestParam String gitHubName, Model model) {
        String userName = gitHubName.trim().replace(" ", "-");

        JsonArray jsonArrayRepos = ReaderController.readerServiceImpl.getJsonArrayRepos(userName);
        JsonArray jsonArraySubscriptions = ReaderController.readerServiceImpl.getJsonArraySubscriptions(userName);

        List<JsonElement> list = ReaderController.readerServiceImpl.buildDistinctCombinedRepoList(jsonArrayRepos, jsonArraySubscriptions);
        List<GitHubRepository> repoList = ReaderController.readerServiceImpl.getAllRepositories(list, userName);

        int repoCount = ReaderController.readerServiceImpl.countAllRepositories(repoList);
        int reposWithLanguages = ReaderController.readerServiceImpl.countAllRepositoriesWithLanguages(repoList);

        User user = ReaderController.readerServiceImpl.getUser(userName);

        Map<String, Integer> langMap = ReaderController.readerServiceImpl.getAllLanguages(repoList);
        Map<String, Double> freqMap = ReaderController.readerServiceImpl.getLanguageFrequencies(langMap, reposWithLanguages);

        model.addAttribute("user", user);
        model.addAttribute("repoCount", repoCount);
        model.addAttribute("repoList", repoList);
        model.addAttribute("frequencies", freqMap);
        return "displayInfo";
    }
}