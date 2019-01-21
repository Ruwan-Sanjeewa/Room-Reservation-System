package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;


    public enum DAOTypes{
        BOOKING_DAO,CUSTOMER_DAO,MEAL_TYPE_DAO,PAYMENT_DAO,ROOM_DAO,ROOM_BOOKING_DETAIL_DAO,ROOM_TYPE_DAO,QUERY_DAO,
    }

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            return daoFactory=new DAOFactory();
        }
        return daoFactory;

    }

    public <T> T getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case BOOKING_DAO:
                return (T) new BookingDAOImpl();
            case CUSTOMER_DAO:
                return (T) new CustomerDAOImpl();
            case MEAL_TYPE_DAO:
                return (T) new MealTypeDAOImpl();
            case PAYMENT_DAO:
                return (T) new PaymentDAOImpl();
            case ROOM_DAO:
                return (T) new RoomDAOImpl();
            case ROOM_BOOKING_DETAIL_DAO:
                return (T) new RoomBookingDetailDAOImpl();
            case ROOM_TYPE_DAO:
                return (T) new RoomTypeDAOImpl();
            case QUERY_DAO:
                return (T) new QueryDAOImpl();
                default:
                    return null;

        }
    }

}
