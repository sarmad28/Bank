package co.ubl.bank.Service;

import co.ubl.bank.DTO.AuthDTO;

import java.util.List;

public interface AuthService {

    AuthDTO createUser(AuthDTO authDTO);

    AuthDTO update(AuthDTO authDTO);

    AuthDTO delete(Long id);

    AuthDTO findById(Long id );

    List<AuthDTO> findAll();

    AuthDTO findByUserName(AuthDTO authDTO);

}
