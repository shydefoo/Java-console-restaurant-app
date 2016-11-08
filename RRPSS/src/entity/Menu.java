package entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Menu class, represents restaurant menu. Contains 2 ArrrayList, 1 storing
 * individual MenuItem objects and one storing PromotionalPackage Objects.
 *
 * @author Shide
 *
 */
public class Menu {

    private ArrayList<MenuItem> menuItem;
    private ArrayList<String> types;
    private ArrayList<PromotionalPackage> pPackage;

    public Menu() {
        menuItem = new ArrayList<MenuItem>();
        types = new ArrayList<String>();
        pPackage = new ArrayList<PromotionalPackage>();
        types.add("Mains"); //Testing Menu Types
        types.add("Desserts");
        types.add("Drinks");
        types.add("Promotional Package");
    }

    /**
     * getTypes method
     *
     * @return types of items restaurant serves, eg. Mains, Drinks, etc.
     */
    public ArrayList<String> getTypes() {
        return this.types;
    }

    /**
     * Addnew type of menu item into the menuItem.
     *
     * @param name what to call the new type.
     */
    public void addTypes(String name) {
        types.add(name);
    }

    /**
     * Prints out all types of items in menuItem
     */
    public void printTypes() {
        System.out.println("Types are: ");
        for (int i = 0; i < types.size(); i++) {
            System.out.println((i + 1) + "." + types.get(i));
        }
    }

    /**
     *
     * @return Promotional Packages
     */
    public ArrayList<PromotionalPackage> getPPackage() {
        return this.pPackage;
    }

    public void setPPackage(ArrayList<PromotionalPackage> PPackage) {
        this.pPackage = PPackage;
    }

    /**
     *
     * @return Main menu
     */
    public ArrayList<MenuItem> getMenuItem() {
        return this.menuItem;
    }

    public void setMenuItem(ArrayList<MenuItem> menu) {
        this.menuItem = menu;
    }

    /**
     * Retrieves the position of the menu item id in the menuItem
     *
     * @param id the position of the menu item in the menuItem
     * @return the reference of the menu item
     */
    public MenuItem getMenuItemID(int id) {
        return menuItem.get(id - 1);
    }

    /**
     * Creates new menu item and adds it into menuItem.
     *
     * @param name name of the item.
     * @param price price of the item.
     * @param desc description of the item.
     * @param type type of item, eg Mains, Drinks, Desserts, etc.
     */
    public void addItem(String name, double price, String desc, String type) {
        MenuItem item = new MenuItem(name, price, desc, type);
        menuItem.add(item);
        System.out.println("Item has been added");

    }

    /**
     * Creates a new promotional package and adds it into pPackage.
     *
     * @param name name of promotional package
     * @param price price of promotional package
     * @param desc description of promotional package
     * @param type what type of package it is.
     * @param a the items that make up the promotional package.
     */
    public void addPPackage(String name, double price, String desc, String type, ArrayList<MenuItem> a) {
        PromotionalPackage tempPP = new PromotionalPackage(name, price, desc, type, a);
        System.out.println("Adding promotional package");
        pPackage.add(tempPP);
    }

    /**
     * Append an item in menuItem.
     *
     * @param name name of the item.
     * @param price price of the item.
     * @param desc description of the item.
     * @param type type of item, eg Mains, Drinks, Desserts, etc.
     * @param n the index of the array
     */
    public void updateItem(String name, double price, String desc, String type, int n) {
        MenuItem temp = null;
        //temp = menuItem.get(n);
        ArrayList<MenuItem> updateItem = reOrderItems(type);
        temp = updateItem.get(n);
        temp.setName(name);
        temp.setPrice(price);
        temp.setDesc(desc);
        System.out.println("Item has been updated");
    }

    /**
     * Extracts item from menuItem according to type specified.
     *
     * @param itemType type of item to extract, eg Mains, Drinks, etc.
     * @return ArrayList containing MenuItem Objects.
     */
    public ArrayList<MenuItem> reOrderItems(String itemType) {
        ArrayList<MenuItem> updateItem = new ArrayList<MenuItem>();
        // System.out.println("Size: " + size);
        for (int i = 0; i < menuItem.size(); i++) {
            if (menuItem.get(i).getType().equals(itemType)) {
                //System.out.println(menuItem.get(i));
                updateItem.add(menuItem.get(i));
            }
        }
        for (int i = 0; i < pPackage.size(); i++) {
            if (itemType.equals("Promotional Package")) {
                updateItem.add(pPackage.get(i));
            }
        }
        return updateItem;

    }

    /**
     * Delete item from menuItem.
     *
     * @param n position of item in menuItem
     * @param type item type
     */
    public void removeItem(int n, String type) {
        ArrayList<MenuItem> deleteType = reOrderItems(type);
        MenuItem tempMenuItem = deleteType.get(n);
        menuItem.remove(tempMenuItem);
        //    menuItem.get(n).notifyPPackage();
        System.out.println("Item has been removed");
        for (int i = 0; i < pPackage.size(); i++) {
            ArrayList<MenuItem> temp = new ArrayList<MenuItem>();
            temp = pPackage.get(i).getItemList();
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).equals(tempMenuItem)) {
                    temp.remove(tempMenuItem);
                }
            }

        }
    }

    /* public void displayMenuAndItems(ArrayList<MenuItem> mItem) {
        for (int j = 0; j < types.size(); j++) {
            System.out.println("######" + types.get(j) + "######");
            for (int i = 0; i < mItem.size(); i++) {
                if (mItem.get(i).getType().equals(types.get(j))) {
                    System.out.printf("%-30s", mItem.get(i).getName());
                    System.out.printf("%20s%n",
                            new DecimalFormat("$###,##0.00").format(mItem.get(i).getPrice()));
                    System.out.println("\"" + mItem.get(i).getDescription() + "\"");
                }
            }
        }
        //Promotional Packages
        System.out.println("******" + "Promotional Packages" + "******");
        for (int i = 0; i < pPackage.size(); i++) {
            System.out.println("i:" + i);
            System.out.printf("%-30s", "******" + pPackage.get(i).getName() + "******");
            System.out.printf("%20s%n",
                    new DecimalFormat("$###,##0.00").format(pPackage.get(i).getPrice()));
            System.out.println("\"" + pPackage.get(i).getDesc() + "\"");
            ArrayList<MenuItem> pItemList = pPackage.get(i).getItemList();
            for (int j = 0; j < pItemList.size(); j++) {
                System.out.println("- " + pItemList.get(j).getName());
            }
        }
    }*/
    /**
     * Prints all items in menuItem.
     */
    public void printMenuItemsMenu() {
        System.out.println("Printing Items in ItemMenu");
        for (int i = 0; i < menuItem.size(); i++) {
            menuItem.get(i).printItem();
        }
    }

    /**
     * Prints all promotional packages in pPackage.
     */
    public void printPromotionalPackages() {
        System.out.println("Printing Items in Promotional Package");
        for (int i = 0; i < pPackage.size(); i++) {
            pPackage.get(i).printPromotionalPackage();
        }
    }

    public void printMenuItem() {
        int x;
        System.out.println("Menu:");
        for (MenuItem s : menuItem) {
            x = menuItem.indexOf(s);
            x++;
            System.out.print(x + ". ");
            s.printItem();
        }
    }

    //Promotional Package Functions
}
