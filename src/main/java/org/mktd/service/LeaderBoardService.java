package org.mktd.service;

import org.mktd.model.QuizzResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public class LeaderBoardService {

    private final List<QuizzResult> results;

    public LeaderBoardService() {
        super();
        results = new ArrayList<>();
    }


    public void add(QuizzResult result) {
        results.add(result);
    }

    public List<QuizzResult> getResult() {
        return getResult(0, 10);
    }

    private List<QuizzResult> getResult(int page, int pageSize) {
        return results.stream()
                .collect(groupingBy(QuizzResult::getUserName))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(list -> {
                    list.sort(QuizzResult::compareTo);
                    return list.iterator().next();
                })
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
