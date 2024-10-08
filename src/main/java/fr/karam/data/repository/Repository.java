package fr.karam.data.repository;

import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;
import fr.karam.data.utils.ClassProvider;

import java.util.Collections;
import java.util.List;

public abstract class Repository<E extends EntitySerializable> implements IRepository, ClassProvider<E> {

    protected final String identifier;
    protected final RepositoryType type;

    protected FetcherType fetcherType;
    protected EntityFetcher entityFetcher;

    protected Repository(String identifier, RepositoryType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Repository(String identifier, RepositoryType type, FetcherType fetcherType) {
        this.identifier = identifier;
        this.type = type;

        this.fetcherType = fetcherType;

        if(fetcherType != null){
            this.entityFetcher = HazelcastManager.INSTANCE.getFetcher(fetcherType);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public RepositoryType getType() {
        return type;
    }

    public FetcherType getFetcherType() {
        return fetcherType;
    }

    public EntityFetcher getEntityFetcher() {
        return entityFetcher;
    }

    public boolean isStore() {
        return this.entityFetcher != null;
    }

    protected void store(Object entityID, E entity){
        if(entityFetcher == null) return;
        entityFetcher.set(identifier, entityID, entity);
    }

    protected E fetch(Object entityID){
        if(entityFetcher == null) return null;
        return entityFetcher.get(identifier, entityID, getGenericClazz());
    }

    protected void eradicate(Object entityID){
        if(entityFetcher == null) return;
        entityFetcher.remove(identifier, entityID);
    }

    protected <T> List<T> getInternIds(Class<T> clazz){
        if(entityFetcher == null) return Collections.emptyList();
        return entityFetcher.getAllID(identifier, clazz);
    }

}
