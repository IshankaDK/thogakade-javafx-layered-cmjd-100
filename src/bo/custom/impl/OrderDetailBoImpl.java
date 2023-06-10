package bo.custom.impl;

import java.util.ArrayList;

import bo.custom.OrderDetailBo;
import doa.DaoFactory;
import doa.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

public class OrderDetailBoImpl implements OrderDetailBo {

    OrderDetailDAO orderDetailDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER_DETAIL);

    @Override
    public boolean saveOrderDetail(ArrayList<OrderDetailDTO> dtoList) throws Exception {
        for (OrderDetailDTO dto : dtoList) {
            boolean isSaved = saveOrderDetail(dto);
            if (!isSaved) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(OrderDetailDTO dto) throws Exception {
        return orderDetailDAO.save(new OrderDetail(dto.getOrderId(), dto.getCode(), dto.getQty(), dto.getUnitPrice()));
    }

}
