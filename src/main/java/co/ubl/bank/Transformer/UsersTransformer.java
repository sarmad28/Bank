package co.ubl.bank.Transformer;

import co.ubl.bank.DTO.UsersDTO;
import co.ubl.bank.Entity.UsersEntity;
import co.ubl.bank.util.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UsersTransformer {

    public static UsersDTO getUsersDTO(UsersEntity usersEntity) {
        UsersDTO dto = new UsersDTO();
        dto.setId(usersEntity.getId());
        dto.setGender(usersEntity.getGender());
        dto.setEmail(usersEntity.getEmail());
        dto.setStatus(usersEntity.getStatus());
        dto.setAccountNumber(usersEntity.getAccountNumber());
        dto.setFirstName(usersEntity.getFirstName());
        dto.setLastName(usersEntity.getLastName());
        dto.setAccountBalance(usersEntity.getAccountBalance());
        dto.setMobileNumber(usersEntity.getMobileNumber());
        return dto;
    }

    public static UsersEntity getUsersEntity(UsersDTO usersDTO) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(usersDTO.getId());
        usersEntity.setGender(usersDTO.getGender());
        usersEntity.setEmail(usersDTO.getEmail());
        usersEntity.setStatus(usersDTO.getStatus());
        usersEntity.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
        usersEntity.setFirstName(usersDTO.getFirstName());
        usersEntity.setLastName(usersDTO.getLastName());
        if (usersDTO.getAccountBalance() == null) {
            usersEntity.setAccountBalance(BigDecimal.valueOf(0.0));
        } else {
            usersEntity.setAccountBalance(usersDTO.getAccountBalance());
        }
        usersEntity.setMobileNumber(usersDTO.getMobileNumber());
        return usersEntity;
    }

    public static List<UsersDTO> getUsersDTOList(Iterable<UsersEntity> all) {
        return StreamSupport.stream(all.spliterator(), false).map(UsersTransformer::getUsersDTO).collect(Collectors.toList());
    }
}
