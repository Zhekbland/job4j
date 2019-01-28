package ru.job4j.nonblock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Class NonBlockingCache creates Map.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 28.01.2019.
 */
public class NonBlockingCache {
    private Map<Integer, Base> map = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        boolean result = false;
        if (!map.containsKey(model.getId())) {
            map.put(model.getId(), model);
            result = true;
        }
        return result;
    }

    public void update(Base model) {
        map.computeIfPresent(model.getId(), new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer integer, Base base) {
                if (base.getVersion() > model.getVersion()) {
                    throw new OptimisticException("Throw Exception in Thread");
                } else {
                    model.setVersion(model.getVersion() + 1);
                }
                return model;
            }
        });
    }

    public boolean delete(Base model) {
        boolean result = false;
        if (map.containsKey(model.getId())) {
            map.remove(model.getId());
            result = true;
        }
        return result;
    }
}
