package ru.job4j.nonblock;

/**
 * Class OptimisticException throws exception about Race Condition.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 28.01.2019.
 */
public class OptimisticException extends RuntimeException {
    OptimisticException(String msg) {
        super(msg);
    }
}
