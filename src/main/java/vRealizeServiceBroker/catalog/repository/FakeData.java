package vRealizeServiceBroker.catalog.repository;

import vRealizeServiceBroker.catalog.model.Item;

import java.util.ArrayList;
import java.util.List;

public class FakeData {
    public static final List<Item> CATALOG = new ArrayList<>(List.of(
            new Item()
                    .setId("82f17a7c-556f-3071-8f24-6eb177e56bfc")
                    .setName("test-2")
                    .setDescription(""),
            new Item()
                    .setId("646f2da7-d345-3106-96f1-74deb7223105")
                    .setName("test-1")
                    .setDescription("")
    ));
}
