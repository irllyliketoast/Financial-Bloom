package csc450.BackEnd;

import java.util.List;

public class UnityDataRequest {
    public int tokens;
    public PlayerPosition playerPosition;
    public SerializableDictionary<String, Boolean> seedsCollected;
    public SerializableDictionary<String, String> grid;
    public SerializableDictionary<String, String> inventorySeeds;
    
    // Getters and setters
    public PlayerPosition getPlayerPosition(){
        return playerPosition;
    }
    public SerializableDictionary getSeedsCollected(){
        return seedsCollected;
    }
    public SerializableDictionary getGrid(){
        return grid;
    }
    public SerializableDictionary getInventorySeeds(){
        return inventorySeeds;
    }
}
class PlayerPosition {
    public float x;
    public float y;
    public float z;
}

class SerializableDictionary<K, V> {
    public List<K> keys;
    public List<V> values;

    public java.util.Map<K, V> toMap() {
        java.util.Map<K, V> map = new java.util.HashMap<>();
        if (keys != null && values != null && keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                map.put(keys.get(i), values.get(i));
            }
        }
        return map;
    }


}
