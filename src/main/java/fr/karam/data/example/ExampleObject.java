package fr.karam.data.example;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.document.EntityDocument;
import fr.karam.data.store.types.mongo.MongoFetcher;


import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExampleObject implements EntitySerializable, Identifiable<UUID> {

    private UUID uuid;
    private String name;
    private int credit;
    private List<String> subEntities;
    private Map<String, String> keyWords;


    // you need to have an empty constructor
    public ExampleObject() {

    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put(MongoFetcher.IDENTIFIER_KEY, uuid);
        document.put("name", name);
        document.put("credit", credit);
        document.put("subEntities", subEntities);
        document.put("keyWords", keyWords);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.uuid = document.getUUID(MongoFetcher.IDENTIFIER_KEY);
        this.name = document.getString("name");
        this.credit = document.getInteger("credit");
        this.subEntities = document.getList("subEntities", String.class);
        this.keyWords = document.get("keyWords", Map.class);
    }

    public UUID getUuid() {
        return uuid;
    }

    public ExampleObject setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExampleObject setName(String name) {
        this.name = name;
        return this;
    }

    public int getCredit() {
        return credit;
    }

    public ExampleObject setCredit(int credit) {
        this.credit = credit;
        return this;
    }

    public List<String> getSubEntities() {
        return subEntities;
    }

    public ExampleObject setSubEntities(List<String> subEntities) {
        this.subEntities = subEntities;
        return this;
    }

    public Map<String, String> getKeyWords() {
        return keyWords;
    }

    public ExampleObject setKeyWords(Map<String, String> keyWords) {
        this.keyWords = keyWords;
        return this;
    }

    @Override
    public UUID getID() {
        return this.uuid;
    }
}
