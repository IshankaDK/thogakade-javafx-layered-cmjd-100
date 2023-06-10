package doa.custom;

import java.util.ArrayList;

import doa.CrudDAO;
import entity.Item;

public interface ItemDAO extends CrudDAO<Item, String> {

    public ArrayList<Item> getItemCode() throws Exception;

    public boolean updateWhenOrder(Item item) throws Exception;
}
