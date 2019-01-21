package lk.ijse.dep.app.business;

import lk.ijse.dep.app.business.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes{
        BOOKING_BO,CUSTOMER_BO,MEAL_TYPE_BO,PAYMENT_BO,ROOM_BO,ROOM_BOOKING_DETAIL,MANAGE_AVAILABLE_ROOMS,MANAGE_ROOM_TYPE_BO
    }


    private BOFactory(){}

    public static BOFactory getInstance(){
        if(boFactory==null){
            return boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T> T getBO(BOTypes boTypes){
        switch (boTypes){
            case BOOKING_BO:
                return (T) new ManageBookingBOImpl();
            case CUSTOMER_BO:
                return (T) new ManageCustomerBOImpl();
            case MEAL_TYPE_BO:
                return (T) new ManageMealTypeBOImpl();
            case PAYMENT_BO:
                return (T) new ManagePaymentBOImpl();
            case ROOM_BO:
                return (T) new ManageRoomBOImpl();
            case ROOM_BOOKING_DETAIL:
                return (T) new ManageRoomBookingDetailBOImpl();
            case MANAGE_AVAILABLE_ROOMS:
                return (T) new ManageAvailableRoomsBOImpl();
            case MANAGE_ROOM_TYPE_BO:
                return (T) new ManageRoomTypeBOImpl();
                default:
                    return null;

        }
    }

}
