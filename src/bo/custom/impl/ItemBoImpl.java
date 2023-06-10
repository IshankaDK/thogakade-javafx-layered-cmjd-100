package bo.custom.impl;

import java.util.ArrayList;

import bo.custom.ItemBo;
import doa.DaoFactory;
import doa.custom.ItemDAO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import entity.Item;

public class ItemBoImpl implements ItemBo {

    private ItemDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return dao.save(item);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return dao.update(item);
    }

    @Override
    public boolean deleteItem(String code) throws Exception {
        return dao.delete(code);
    }

    @Override
    public ItemDTO getItem(String code) throws Exception {
        Item item = dao.get(code);
        if (item != null) {
            return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
        }
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        ArrayList<Item> list = dao.getAll();
        ArrayList<ItemDTO> dtoList = new ArrayList<>();

        for (Item i : list) {
            dtoList.add(new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<ItemDTO> getAllItemCode() throws Exception {
        ArrayList<Item> list = dao.getItemCode();
        ArrayList<ItemDTO> dtoList = new ArrayList<>();

        for (Item i : list) {
            dtoList.add(new ItemDTO(i.getCode()));
        }
        return dtoList;
    }

    @Override
    public boolean updateWhenOrder(ArrayList<OrderDetailDTO> orderDetailDTOs) throws Exception {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            boolean isUpdated = updateWhenOrder(orderDetailDTO);
            if (!isUpdated) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateWhenOrder(OrderDetailDTO orderDetailDTOs) throws Exception {
        return dao.updateWhenOrder(new Item(orderDetailDTOs.getCode(), orderDetailDTOs.getQty()));
    }

}
