package csc450.BackEnd;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

@Entity
public class UnityDataRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tokens;

    @Embedded
    private PlayerPosition playerPosition;

    @ElementCollection
    @CollectionTable(name = "seeds_collected", joinColumns = @JoinColumn(name = "unity_data_id"))
    @MapKeyColumn(name = "seed_name")
    @Column(name = "collected")
    private Map<String, Boolean> seedsCollected;

    @ElementCollection
    @CollectionTable(name = "grid_data", joinColumns = @JoinColumn(name = "unity_data_id"))
    @MapKeyColumn(name = "grid_key")
    @Column(name = "grid_value")
    private Map<String, String> grid;

    @ElementCollection
    @CollectionTable(name = "inventory_seeds", joinColumns = @JoinColumn(name = "unity_data_id"))
    @MapKeyColumn(name = "seed_name")
    @Column(name = "seed_value")
    private Map<String, String> inventorySeeds;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Map<String, Boolean> getSeedsCollected() {
        return seedsCollected;
    }

    public void setSeedsCollected(Map<String, Boolean> seedsCollected) {
        this.seedsCollected = seedsCollected;
    }

    public Map<String, String> getGrid() {
        return grid;
    }

    public void setGrid(Map<String, String> grid) {
        this.grid = grid;
    }

    public Map<String, String> getInventorySeeds() {
        return inventorySeeds;
    }

    public void setInventorySeeds(Map<String, String> inventorySeeds) {
        this.inventorySeeds = inventorySeeds;
    }
}

@Embeddable
class PlayerPosition {
    private float x;
    private float y;
    private float z;

    // Getters and setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}