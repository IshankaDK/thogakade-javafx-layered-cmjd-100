package bo.custom.impl;

import java.util.ArrayList;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.OrderDetailBo;
import db.DBConnection;
import doa.DaoFactory;
import doa.custom.OrderDAO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Order;

public class OrderBoImpl implements OrderBo {

    OrderDAO orderDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER);
    OrderDetailBo detailBo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER_DETAIL);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);

    @Override
    public boolean saveOrder(OrderDTO dto, ArrayList<OrderDetailDTO> orderDetailDTOs) throws Exception {
        // transaction
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isSaved = orderDAO.save(new Order(dto.getOrderID(), dto.getOrderDate(), dto.getCustomerID()));
            boolean isDetailsSaved = detailBo.saveOrderDetail(orderDetailDTOs);
            boolean isUpdated = itemBo.updateWhenOrder(orderDetailDTOs);

            if (isSaved && isDetailsSaved && isUpdated) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        } catch (Exception e) {
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

}
