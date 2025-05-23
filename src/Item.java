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
    private double itemPrice;

    public Item(String name, ItemCategory itemCategory, ItemType itemType, double itemPrice) {
        this.name = name;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return itemPrice;
    }

    public void setPrice(double itemPrice) {
        this.itemPrice = itemPrice;
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
