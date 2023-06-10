package doa.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import doa.CrudUtil;
import doa.custom.ItemDAO;
import entity.Item;

public class ItemDaoImpl implements ItemDAO {

    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)",
                item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET description = ? , unitPrice = ? , qtyOnHand = ? WHERE code = ?",
                item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    @Override
    public Item get(String code) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item WHERE code=?", code);
        if (set.next()) {
            return new Item(set.getString(1), set.getString(2),
                    set.getDouble(3), set.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (set.next()) {
            itemList.add(new Item(
                    set.getString(1), set.getString(2),
                    set.getDouble(3), set.getDouble(4)));
        }

        return itemList;
    }

    @Override
    public ArrayList<Item> getItemCode() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT code FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (set.next()) {
            itemList.add(new Item(
                    set.getString(1)));
        }

        return itemList;
    }

}
