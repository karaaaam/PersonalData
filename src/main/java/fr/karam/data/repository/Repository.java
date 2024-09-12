package fr.karam.data.repository;

import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;

import java.util.List;

public abstract class Repository<E extends DataSerializable> {

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
            this.entityFetcher = HazelcastManager.INSTANCE. getFetcher(fetcherType);
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

    protected void store(Object entityID, E entity){
        entityFetcher.set(identifier, entityID, entity);
    }

    protected E fetch(Object entityID){
        return (E) entityFetcher.get(identifier, entityID);
    }

    protected <T> List<T> getInternIds(Class<T> clazz){
        return entityFetcher.getAllID(identifier, clazz);
    }

}
