package fr.karam.data.repository.types;

import com.hazelcast.map.IMap;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;

public abstract class MapRepository<K, V extends DataSerializable> extends Repository<V> {

    private IMap<K, V> map;

    public MapRepository(String identifier) {
        super(identifier, RepositoryType.MAP);
        this.map = HazelcastManager.INSTANCE.getHazelcast().getMap(identifier);
    }

    public V get(K key){
        return null;
    }
}
