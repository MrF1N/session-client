package by.mrf1n.session_client.model;

import by.mrf1n.session_client.connection.MonoClient;
import by.mrf1n.session_client.service.MenuTools;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Класс пункта меню для меню приложения
 */

public class MenuItem {
    private Integer menuItemNumber;
    private String menuItemName;
    private Consumer<MenuTools> function;
    private List<MenuItem> relatedItems;

    public MenuItem(String menuItemName, Consumer<MenuTools> function) {
        this.menuItemName = menuItemName;
        this.function = function;
    }

    public Integer getMenuItemNumber() {
        return menuItemNumber;
    }

    public void setMenuItemNumber(Integer menuItemNumber) {
        this.menuItemNumber = menuItemNumber;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Consumer<MenuTools> getFunction() {
        return function;
    }

    public void setFunction(Consumer<MenuTools> function) {
        this.function = function;
    }

    public List<MenuItem> getRelatedItems() {
        return relatedItems;
    }

    public void setRelatedItems(List<MenuItem> relatedItems) {
        AtomicInteger counter = new AtomicInteger(1);
        relatedItems.forEach(item -> item.setMenuItemNumber(counter.getAndIncrement()));
        this.relatedItems = relatedItems;
    }

    public Integer getCountRelatedItems() {
        return relatedItems.size();
    }

    public void printMenuItems() {
        this.getRelatedItems().forEach(item -> System.out.println(item.getMenuItemNumber() + ". " + item.getMenuItemName() + ".")
        );

    }

    @Override
    public String toString() {
        return "MenuItem{" +
            "menuItemNumber=" + menuItemNumber +
            ", menuItemName='" + menuItemName + '\'' +
            ", function=" + function +
            '}';
    }

    public void runNextMenuItem(Integer integer, MonoClient monoClient) {
        for (MenuItem relatedItem : relatedItems) {
            if (relatedItem.getMenuItemNumber().equals(integer)){
                relatedItem.function.accept(new MenuTools(monoClient));
                return;
            }
        }
    }
}
