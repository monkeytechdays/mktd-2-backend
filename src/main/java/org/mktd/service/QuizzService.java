package org.mktd.service;

import org.mktd.model.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

public class QuizzService {

    private static List<Monkey> monkeys = Arrays.asList(
            new Monkey("Gibbon à mains blanches", "https://upload.wikimedia.org/wikipedia/commons/d/db/Hylobates-lar-05-Chiang-Mai.jpg",
                    "Par Ladislav Král (Travail personnel) [CC BY-SA 3.0 (http://creativecommons.org/licenses/by-sa/3.0)], via Wikimedia Commons"),
            new Monkey("Siamangs", "https://upload.wikimedia.org/wikipedia/commons/d/de/Symphalangus_syndactylus%2C_Chiba_Zoo%2C_Japan.jpg",
                    "By suneko (http://www.flickr.com/photos/suneko/373310729/) [CC BY 2.0 (http://creativecommons.org/licenses/by/2.0)], via Wikimedia Commons"),
            new Monkey("Babouin anubis ", "https://upload.wikimedia.org/wikipedia/commons/4/4c/Olive_baboon.jpg",
                    "By Stolz, Gary M. [Public domain], via Wikimedia Commons"),
            new Monkey("Orang Outan", "https://upload.wikimedia.org/wikipedia/commons/b/be/Orang_Utan%2C_Semenggok_Forest_Reserve%2C_Sarawak%2C_Borneo%2C_Malaysia.JPG",
                    "By Eleifert (Own work) [GFDL (http://www.gnu.org/copyleft/fdl.html) or CC-BY-SA-3.0 (http://creativecommons.org/licenses/by-sa/3.0/)], via Wikimedia Commons"),
            new Monkey("Patas", "https://upload.wikimedia.org/wikipedia/commons/d/d2/Erythrocebus-patas-patas-Burkina-faso.JPG",
                    "By Rasfaya A.K.A NASSARASTA (Own work) [CC BY 3.0 (http://creativecommons.org/licenses/by/3.0)], via Wikimedia Commons"),
            new Monkey("Nasique", "https://upload.wikimedia.org/wikipedia/commons/e/e5/Proboscis_Monkey_in_Borneo.jpg",
                    "By David Dennis [CC BY-SA 2.0 (http://creativecommons.org/licenses/by-sa/2.0)], via Wikimedia Commons"),
            new Monkey("Ouistiti pygmée", "https://upload.wikimedia.org/wikipedia/commons/b/b7/Dv%C3%A6rgsilkeabe_Callithrix_pygmaea.jpg",
                    "By Malene Thyssen (User Malene) (Own work) [GFDL (http://www.gnu.org/copyleft/fdl.html), CC-BY-SA-3.0 (http://creativecommons.org/licenses/by-sa/3.0/) or CC BY-SA 2.5 (http://creativecommons.org/licenses/by-sa/2.5)], via Wikimedia Commons"),
            new Monkey("Atèle", "https://upload.wikimedia.org/wikipedia/commons/e/e3/AtelesGeoffroyi.jpg",
                    "By Lea Maimone. (Own work) [CC BY-SA 2.0 (http://creativecommons.org/licenses/by-sa/2.0)], via Wikimedia Commons"),
            new Monkey("Douc", "https://upload.wikimedia.org/wikipedia/commons/4/49/Kleideraffe-01.jpg",
                    "By Wilfried Berns / www.Tiermotive.de [CC BY-SA 2.0 de (http://creativecommons.org/licenses/by-sa/2.0/de/deed.en)], via Wikimedia Commons"),
            new Monkey("Titi roux ", "https://upload.wikimedia.org/wikipedia/commons/6/6d/Coppery_Titi_1.jpg",
                    "By Tony Hisgett (Flickr: Red Titi Monkey 1) [CC BY 2.0 (http://creativecommons.org/licenses/by/2.0)], via Wikimedia Commons"),
            new Monkey("Hurleur du Guatemala", "https://upload.wikimedia.org/wikipedia/commons/2/29/Alouatta_pigra_Belize_Zoo_2.jpg",
                    "By Dave Johnso (Flickr) [CC BY 2.0 (http://creativecommons.org/licenses/by/2.0)], via Wikimedia Commons"),
            new Monkey("Brachytèle", "https://upload.wikimedia.org/wikipedia/commons/1/1a/Brachyteles_hypoxanthus2.jpg",
                    "By Paulo B. Chaves [CC BY 2.0 (http://creativecommons.org/licenses/by/2.0)], via Wikimedia Commons"),
            new Monkey("Lagotriches", "https://upload.wikimedia.org/wikipedia/commons/7/7b/Lagothrix_lagotricha.jpg",
                    "By Evgenia Kononova (This image was copied from wikipedia:en. by Ipaat) [Public domain], via Wikimedia Commons"),
            new Monkey("Lagotriche à queue jaune", "https://upload.wikimedia.org/wikipedia/commons/8/89/Cola_amarilla.jpg",
                    "By Platyrrhinus (Own work) [CC BY-SA 3.0 (http://creativecommons.org/licenses/by-sa/3.0) or GFDL (http://www.gnu.org/copyleft/fdl.html)], via Wikimedia Commons"),
            new Monkey("Tamarin aux mains rousses", "https://upload.wikimedia.org/wikipedia/commons/c/c3/Saguinus_midas_in_Lisbon_Zoo.JPG",
                    "© Salix / Wikimedia Commons, via Wikimedia Commons"),
            new Monkey("Gorille", "https://upload.wikimedia.org/wikipedia/commons/9/97/Silverback_of_Ntambara_group.JPG",
                    "By Azurfrog (Own work) [CC BY-SA 4.0 (http://creativecommons.org/licenses/by-sa/4.0)], via Wikimedia Commons"),
            new Monkey("Chimpanzé", "https://upload.wikimedia.org/wikipedia/commons/6/62/Schimpanse_Zoo_Leipzig.jpg",
                    "By Thomas Lersch (Own work) [GFDL (http://www.gnu.org/copyleft/fdl.html), CC-BY-SA-3.0 (http://creativecommons.org/licenses/by-sa/3.0/) or CC BY 2.5 (http://creativecommons.org/licenses/by/2.5)], via Wikimedia Commons"));

    public QuizzService() {
        super();
        quizzAnswers = new ConcurrentHashMap<>();
    }

    private final ConcurrentMap<String, QuizzAnswers> quizzAnswers;

    private static class PairQuestionAnswer {
        private Question question;
        private Answer answer;
    }

    private PairQuestionAnswer createQuestion() {
        shuffle(monkeys);
        Monkey monkey = monkeys.iterator().next();
        List<Monkey> list = QuizzService.monkeys.subList(0, QuizzService.monkeys.size() - 1);

        List<String> suggestions = Stream.concat(list.stream().limit(3), Stream.of(monkey))
                .map(Monkey::getRace)
                .collect(toList());
        shuffle(suggestions);

        PairQuestionAnswer result = new PairQuestionAnswer();
        result.question = new Question(suggestions);
        result.answer = new Answer(monkey);
        return result;
    }

    public Quizz createQuizz(String userName) {
        return createQuizz(userName, 5);
    }

    public Quizz createQuizz(String userName, int nbQuestions) {
        String id = UUID.randomUUID().toString();
        List<PairQuestionAnswer> questionAnswers = IntStream.range(0, nbQuestions)
                .mapToObj(index -> createQuestion())
                .collect(toList());
        List<Question> questions = questionAnswers.stream()
                .map(pair -> pair.question)
                .collect(toList());
        List<Answer> answers = questionAnswers.stream()
                .map(pair -> pair.answer)
                .collect(toList());

        quizzAnswers.put(id, new QuizzAnswers(userName, answers)); // register answer
        return new Quizz(id, userName, questions);
    }

    public Photo getPhoto(String quizzId, int questionIndex) {
        return quizzAnswers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(quizzId))
                .map(entry -> entry.getValue().getAnswers().get(questionIndex))
                .map(answer -> answer.getMonkey().getPhoto())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("No photo for quizz %s[%s]", quizzId, questionIndex)));
    }

    public QuizzResult computeResult(String quizzId, QuizzResponses response) {
        QuizzAnswers soluce = quizzAnswers.remove(quizzId);
        Duration tooks = Duration.ofNanos(System.nanoTime() - soluce.getStartAt());
        int score = 0;
        for (int i = 0; i < soluce.getAnswers().size(); i++) {
            String userAns = response.getResponses().get(i);
            Answer answer = soluce.getAnswers().get(i);
            score += userAns.equals(answer.getMonkey().getRace()) ? 1 : 0;
        }
        return new QuizzResult(soluce.getUserName(), score, tooks);
    }

}
