package ru.job4j.condition;

/**
 * Class DummyBot answers the questions.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 1
 * @since  0.1
 */
public class DummyBot {

    /**
     * Answer on the simple questions.
     * @param question question from client.
     * @return answer.
     */
    public String answer(String question) {
        String result = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            result = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            result = "До скорой встречи.";
        }
        return result;
    }
}