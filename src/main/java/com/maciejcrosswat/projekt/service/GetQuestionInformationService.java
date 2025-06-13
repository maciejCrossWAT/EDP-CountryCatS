package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.data.Question;
import com.maciejcrosswat.projekt.data.QuestionCategory;
import com.maciejcrosswat.projekt.data.QuestionProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class GetQuestionInformationService extends Service<QuestionProperty> {

    private String clientID = "3d7df620701058d20455dd005953cb86";
    private String clientSecret = "3ed3f86fe7dcd379a49e382898f289bc1bd7846a";
    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzZDdkZjYyMDcwMTA1OGQyMDQ1NWRkMDA1OTUzY2I4NiIsImp0aSI6ImM5M2ZiZmJiZTgxNzFjN2UzOWExYzc0Njc3ZmVjMjE3MzRjZTI4NDZiYzUxNjg2MDlkYjliYmIwZTcyMDc2NWU0ZTU0Yjg0ZDM3YjAxYjUzIiwiaWF0IjoxNzQ5NTY0NzkyLjMzNjYxNSwibmJmIjoxNzQ5NTY0NzkyLjMzNjYxNiwiZXhwIjozMzMwNjQ3MzU5Mi4zMzI2ODcsInN1YiI6Ijc4NDk3MjUzIiwiaXNzIjoiaHR0cHM6Ly9tZXRhLndpa2ltZWRpYS5vcmciLCJyYXRlbGltaXQiOnsicmVxdWVzdHNfcGVyX3VuaXQiOjUwMDAsInVuaXQiOiJIT1VSIn0sInNjb3BlcyI6WyJiYXNpYyJdfQ.blDH5wP-aN7UnebZNmarFOQLOrKqATPChk-rhtsQw1Fdkem2svzGeemUNRKkRb2slvVK1doIyL3R6j5gNIRRkCX8FZuJPk_RzUf_ukjmNYXAYg61w9kOauIx33uyFbwjtlxjGgI_OLdU_gMM30kQ68gqLNRPETKgzSDvt3X5nvdufjMkaXrqavvfj0X76bgpCFgpKmoPN7roHqrslU1QaqGCsITOOOv8DHA17EJusX7AOlk7slefA1EzJM6KltfV25wfRBcpjqZ2V75zvBKwgXPtxDTlsMlaWxUdcMcXOc7CJq-n7lozQT5Wx_HCXV4BIxbZuyafwSElHdJKTXuCCsEWn6OBfP075vkJctlFA59ufoUTu5rTpWuD_CMmp68H4NzImMUn1pr-D36NQ2XTSUySbVSGGfsUURzTUWv7jaVQxTBPo2FYIMdQa951hfuz3mxCX250Q8rWiQxdgIQIeuX6k3NLcGEDRmbnq621M9ISt_QM3SAmMl5LnyHZUeBRJsoIaTUcn1VU3TnH49B4LQnXJL8Hmn5FtAFDbkEKXsGhLc9-KBsi1NXQoWwJsVPK4sgo7Pc3PObvDWA2_GhnN645hWSvGRo3xIO8ez4JKdhnXgqWmMdV9J_G8-B-Hy5oImL5worU6iQn-rcsFuQIE8_YdT0dao_OQqaDZUxNnCQ";

    @Override
    protected Task<QuestionProperty> createTask() {
        return new Task<>() {
            protected QuestionProperty call() throws InterruptedException, URISyntaxException, IOException {

                Main.mainFlagImage.setVisible(false);
                Main.oddFlagImage.setVisible(false);
                Main.isRoundAnswerContainerDisabled.set(true);
                Main.canTimerMove.set(false);

                // choosing the country
                List<String> countriesList = new ArrayList<>();
                countriesList.add("Polish");
                countriesList.add("Chinese");
                countriesList.add("Indian");
                countriesList.add("German");
                countriesList.add("French");
                countriesList.add("Italian");
                countriesList.add("Japanese");
                countriesList.add("Spanish");

                HashMap<String, String> countriesHashMap = new HashMap<>();
                countriesHashMap.put("Polish", "PL");
                countriesHashMap.put("Chinese", "CN");
                countriesHashMap.put("Indian", "IN");
                countriesHashMap.put("German", "DE");
                countriesHashMap.put("French", "FR");
                countriesHashMap.put("Italian", "IT");
                countriesHashMap.put("Japanese", "JP");
                countriesHashMap.put("Spanish", "ES");

                Random generator = new Random();
                String mainCountry = countriesList.get(generator.nextInt(countriesList.size()));
                countriesList.remove(mainCountry);
                String oddCountry = countriesList.get(generator.nextInt(countriesList.size()));
                String mainCountryShort = "https://flagsapi.com/" + countriesHashMap.get(mainCountry) + "/flat/64.png";
                String oddCountryShort = "https://flagsapi.com/" + countriesHashMap.get(oddCountry) + "/flat/64.png";

                // request
                HttpClient client = HttpClient.newHttpClient();
                String baseURL = "https://api.wikimedia.org/core/v1/wikipedia/en/page/List_of_" + mainCountry + "_dishes/with_html";
                HttpRequest getRequest = HttpRequest.newBuilder()
                        .uri(new URI(baseURL))
                        .headers("Authorization", clientID + " " + accessToken, "User-Agent", "CountryCatS")
                        .GET().build();
                HttpResponse<String> mainCountryresponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

                baseURL = "https://api.wikimedia.org/core/v1/wikipedia/en/page/List_of_" + oddCountry + "_dishes/with_html";
                getRequest = HttpRequest.newBuilder()
                        .uri(new URI(baseURL))
                        .headers("Authorization", clientID + " " + accessToken, "User-Agent", "CountryCatS")
                        .GET().build();
                HttpResponse<String> oddCountryresponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

                // handling the html
                List<String> filters = new ArrayList<>();
                filters.add("/");
                filters.add("\\");
                filters.add(":");
                filters.add("Category");
                filters.add("\n");
                filters.add("foods");
                filters.add("dishes");
                filters.add("Foods");
                filters.add("Dishes");
                filters.add("from");
                filters.add("with");
                filters.add("ese ");
                filters.add("snacks");
                filters.add("Snacks");
                filters.add("brand");
                filters.add("Brand");
                filters.add("stani ");
                filters.add("New");
                filters.add("American");
                filters.add("and");
                filters.add("-");
                filters.add("Polish");
                filters.add("Chinese");
                filters.add("Indian");
                filters.add("German");
                filters.add("French");
                filters.add("Italian");
                filters.add("Japanese");
                filters.add("Spanish");
                filters.add("Christmas");
                filters.add("Holiday");
                filters.add("Easter");
                filters.add("United States");

                List<String> endingFilters = new ArrayList<>();
                endingFilters.add("ian");
                endingFilters.add("ish");
                endingFilters.add("ia");
                endingFilters.add("an");
                endingFilters.add("ench");
                endingFilters.add("nese");
                endingFilters.add("lese");
                endingFilters.add("and");
                endingFilters.add("Lanka");
                endingFilters.add("ino");
                endingFilters.add("eshi");
                endingFilters.add("ani");

                // stworzenie listy słów mainCountry i oddCountry
                int splitIndexMain = mainCountryresponse.body().indexOf("\"html\":\"");

                String onlyHtmlMainCountry = mainCountryresponse.body().substring(splitIndexMain + 7);
                onlyHtmlMainCountry = onlyHtmlMainCountry.substring(0, onlyHtmlMainCountry.length() - 2);
                Document doc1 = Jsoup.parse(onlyHtmlMainCountry);
                List<String> mainCountryDishes = new ArrayList<>();

                String onlyHtmlOddCountry = oddCountryresponse.body().substring(splitIndexMain + 7);
                onlyHtmlOddCountry = onlyHtmlOddCountry.substring(0, onlyHtmlOddCountry.length() - 2);
                Document doc2 = Jsoup.parse(onlyHtmlOddCountry);
                List<String> oddCountryDishes = new ArrayList<>();

                Elements mainCountryListElements = doc1.select("li");
                for (Element listElement : mainCountryListElements.asList()) {
                    if (listElement.text().length() > 7 && listElement.text().length() < 20) {
                        boolean flag = false;
                        for (String filter : filters) {
                            if (listElement.text().contains(filter)) {
                                flag = true;
                                break;
                            }
                        }

                        for (String ending : endingFilters) {
                            if (listElement.text().endsWith(ending)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag)
                            continue;
                        else {
                            mainCountryDishes.add(listElement.text());
                        }
                    }
                }

                Elements oddCountryListElements = doc2.select("li");
                for (Element listElement : oddCountryListElements.asList()) {
                    if (listElement.text().length() > 7 && listElement.text().length() < 20) {
                        boolean flag = false;
                        for (String filter : filters) {
                            if (listElement.text().contains(filter)) {
                                flag = true;
                                break;
                            }
                        }

                        for (String ending : endingFilters) {
                            if (listElement.text().endsWith(ending)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag)
                            continue;
                        else {
                            oddCountryDishes.add(listElement.text());
                        }
                    }
                }

                // wybranie 3 dań od main country i 1 od odd country
                int correctAnswerIndex = generator.nextInt(4);
                String[] answers = new String[]{"","","",""};
                for (int i = 0; i < 4; i++) {
                    if (i != correctAnswerIndex) {
                        int index = generator.nextInt(mainCountryDishes.size());
                        answers[i] = mainCountryDishes.get(index);
                        mainCountryDishes.remove(index);
                        oddCountryDishes.remove(answers[i]);
                    }
                    else {
                        int index = generator.nextInt(oddCountryDishes.size());
                        answers[i] = oddCountryDishes.get(index);
                        oddCountryDishes.remove(index);
                        mainCountryDishes.remove(answers[i]);
                    }
                }

                Question question = new Question(
                    QuestionCategory.FOOD,
                    mainCountry,
                    oddCountry,
                    "Which of these dishes is not " + mainCountry + " but " + oddCountry + "?",
                    answers,
                    correctAnswerIndex
                );

                System.out.println(Arrays.toString(answers) + " correct answer: " + correctAnswerIndex);

                QuestionProperty questionProperties = new QuestionProperty(
                    question.getMainCountry(),
                    mainCountryShort,
                    question.getQuestion(),
                    question.getAnswers(),
                    question.getCorrectAnswerIndex()
                );

                if (isCancelled()) {
                    return null;
                }

                updateValue(questionProperties);
                Main.mainFlagImage.setImage(new Image(mainCountryShort));
                Main.oddFlagImage.setImage(new Image(oddCountryShort));
                Main.isRoundAnswerContainerDisabled.set(false);
                Main.mainFlagImage.setVisible(true);
                Main.oddFlagImage.setVisible(true);
                Main.canTimerMove.set(true);

                return questionProperties;
            }
        };
    }
}
