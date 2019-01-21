package lk.ijse.dep.app.business;

import lk.ijse.dep.app.dto.*;
import lk.ijse.dep.app.entity.*;

import java.util.List;
import java.util.stream.Collectors;


public class Converter {
    public static <T extends SuperEntity> T getEntity(SuperDTO dto){
        if(dto instanceof BookingDTO){
            BookingDTO bookingDTO= (BookingDTO) dto;
            return (T) new Booking(bookingDTO.getCustomer_nic(),bookingDTO.getBooking_id(),bookingDTO.getTotal());
        }
        else if(dto instanceof CustomerDTO){
            CustomerDTO customerDTO= (CustomerDTO) dto;
            return (T) new Customer(customerDTO.getCustomer_name(),customerDTO.getCustomer_nic(),customerDTO.getTelephone(),
                    customerDTO.getAddress());

        }
        else if(dto instanceof MealTypeDTO){
            MealTypeDTO mealTypeDTO= (MealTypeDTO) dto;
            return (T) new MealType(mealTypeDTO.getMeal_type_id(),mealTypeDTO.getMeal_type_name(),mealTypeDTO.getPrice());
        }

        else if(dto instanceof PaymentDTO){
            PaymentDTO paymentDTO= (PaymentDTO) dto;
            return (T) new Payment(paymentDTO.getBooking_id(),paymentDTO.getPayment_id(),paymentDTO.getTotal_price(),
                    paymentDTO.getAdvance(),paymentDTO.getBalance(),paymentDTO.getStatus());
        }
        else if(dto instanceof RoomDTO){
            RoomDTO roomDTO= (RoomDTO) dto;
            return (T) new Room(roomDTO.getRoom_no(),roomDTO.getRoom_type());
        }

        else if(dto instanceof RoomBookingDetailDTO){
            RoomBookingDetailDTO roomBookingDetailDTO= (RoomBookingDetailDTO) dto;
            return (T) new RoomBookingDetail(roomBookingDetailDTO.getBooking_id(),roomBookingDetailDTO.getRoom_no(),
                    roomBookingDetailDTO.getMeal_type_id(),roomBookingDetailDTO.getCheck_in_date(),
                    roomBookingDetailDTO.getCheck_out_date(),roomBookingDetailDTO.getDuration());
        }
        else if(dto instanceof RoomTypeDTO){
            RoomTypeDTO roomTypeDTO= (RoomTypeDTO) dto;
            return (T) new RoomType(roomTypeDTO.getRoom_type_name(),roomTypeDTO.getDescription(),roomTypeDTO.getPrice());

        }
        else{
         throw new RuntimeException("This DTO can't be converted to a Entity");

        }
    }


    public static <T extends SuperDTO> T getDTO(SuperEntity entity){
        if(entity instanceof Booking){
          Booking booking= (Booking) entity;
          return (T) new BookingDTO(booking.getCustomer_nic(),booking.getBooking_id(),booking.getTotal());
        }

        else if(entity instanceof Customer){
            Customer customer= (Customer) entity;
            return (T) new CustomerDTO(customer.getCustomer_name(),customer.getCustomer_nic(),customer.getTelephone(),
                    customer.getAddress());
        }
        else if(entity instanceof MealType){
            MealType mealType= (MealType) entity;
            return (T) new MealTypeDTO(mealType.getMeal_type_id(),mealType.getMeal_type_name(),mealType.getPrice());
        }
        else if (entity instanceof Payment){
            Payment payment= (Payment) entity;
            return (T) new PaymentDTO(payment.getBooking_id(),payment.getPayment_id(),payment.getTotal_price(),
                    payment.getAdvance(),payment.getBalance(),payment.getStatus());
        }
        else if(entity instanceof Room){
            Room room= (Room) entity;
            return (T) new RoomDTO(room.getRoom_no(),room.getRoom_type());
        }
        else if(entity instanceof RoomBookingDetail){
            RoomBookingDetail roomBookingDetail= (RoomBookingDetail) entity;
            return (T) new RoomBookingDetailDTO(roomBookingDetail.getRoomBookingDetailPK().getBooking_id(),
                    roomBookingDetail.getRoomBookingDetailPK().getRoom_no(),roomBookingDetail.getMeal_type_id(),
                    roomBookingDetail.getCheck_in_date(),roomBookingDetail.getCheck_out_date(),
                    roomBookingDetail.getDuration());
        }
        else if(entity instanceof RoomType){
            RoomType roomType= (RoomType) entity;
            return (T) new RoomTypeDTO(roomType.getRoom_type_name(),roomType.getDescription(),roomType.getPrice());
        }
        else {
            throw new RuntimeException("This Entity can't be converted to a DTO");
        }
    }


    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }


}
