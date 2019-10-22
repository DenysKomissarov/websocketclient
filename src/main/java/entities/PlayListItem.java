package entities;

import org.infinispan.util.concurrent.ConcurrentHashSet;

import java.util.Set;

public class PlayListItem {

    private String itemId;
    private String itemName;
    private Set<User> listens;

    public PlayListItem(String itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.listens = new ConcurrentHashSet<>();
    }

    public PlayListItem(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<User> getListens() {
        return listens;
    }

    @Override
    public int hashCode() {
        return this.itemId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayListItem)) {
            return false;
        }
        PlayListItem that = (PlayListItem) obj;

        return that.getItemId().equals(this.itemId);
    }
}
