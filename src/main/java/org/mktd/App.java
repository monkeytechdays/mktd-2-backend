package org.mktd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jooby.Jooby;
import org.jooby.json.Jackson;
import org.mktd.model.QuizzResponses;
import org.mktd.model.QuizzResult;
import org.mktd.service.LeaderBoardService;
import org.mktd.service.QuizzService;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    {
        QuizzService quizzService = new QuizzService();
        LeaderBoardService leaderBoardService = new LeaderBoardService();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        use(new Jackson(mapper));

        get("/api/quizz", request -> {
            String userName = request.param("userName").value();
            return quizzService.createQuizz(userName);
        }).produces("json");
        get("/api/quizz/:quizzId/:index", request -> {
            String quizzId = request.param("quizzId").value();
            int index = request.param("index").intValue();
            return quizzService.getPhoto(quizzId, index);
        }).produces("json");
        post("/api/quizz/:quizzId", request -> {
            QuizzResponses responses = request.body(QuizzResponses.class);
            String quizzId = request.param("quizzId").value();
            QuizzResult result = quizzService.computeResult(quizzId, responses);
            leaderBoardService.add(result);
            return result;
        }).produces("json");
        get("/api/leaderboard", leaderBoardService::getResult).produces("json");
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }

}
