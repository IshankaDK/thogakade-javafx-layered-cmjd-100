package bo.custom;

import java.util.ArrayList;

import dto.ItemDTO;

public interface ItemBo {
    public boolean saveItem(ItemDTO itemDTO) throws Exception;

    public boolean updateItem(ItemDTO itemDTO) throws Exception;

    public boolean deleteItem(String code) throws Exception;

    public ItemDTO getItem(String code) throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;

    public ArrayList<ItemDTO> getAllItemCode() throws Exception;
}
