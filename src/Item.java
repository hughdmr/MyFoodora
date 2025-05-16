public class Item {
    public enum ItemCategory {
        STARTER,
        MAIN,
        DESSERT
    }

    public enum ItemType {
        STANDARD,
        VEGETARIAN,
        GLUTEN_FREE,
    }

    private String name;
    private ItemCategory itemCategory;
    private ItemType itemType;

    public Item(String name, ItemCategory itemCategory, ItemType itemType) {
        this.name = name;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
