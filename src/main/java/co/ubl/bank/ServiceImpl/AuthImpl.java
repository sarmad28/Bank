package co.ubl.bank.ServiceImpl;

import co.ubl.bank.DTO.AuthDTO;
import co.ubl.bank.Entity.AuthEntity;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Repository.AuthRepository;
import co.ubl.bank.Security.util.JwtTokenUtil;
import co.ubl.bank.Service.AuthService;
import co.ubl.bank.Transformer.AuthTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;


    public AuthImpl(JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, AuthRepository authRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
    }

    @Override
    public AuthDTO createUser(AuthDTO authDTO) {
        AuthEntity authEntity = AuthTransformer.getUserEntity(authDTO);
        AuthEntity save = authRepository.save(authEntity);
        AuthDTO authDTO1 = AuthTransformer.getUserDTO(save);
        return authDTO1;
    }

    @Override
    public AuthDTO update(AuthDTO authDTO) {
        return null;
    }

    @Override
    public AuthDTO delete(Long id) {
        return null;
    }

    @Override
    public AuthDTO findById(Long id) {
        Optional<AuthEntity> byId = authRepository.findById(id);
        if (byId.isPresent()) {
            return AuthTransformer.getUserDTO(byId.get());
        }
        return null;
    }

    @Override
    public List<AuthDTO> findAll() {
        List<AuthDTO> authDTOS = new ArrayList<>();
        Iterable<AuthEntity> all = authRepository.findAll();
        for (AuthEntity authEntity : all) {
            authDTOS.add(AuthTransformer.getUserDTO(authEntity));
        }
        return authDTOS;
    }

    @Override
    public AuthDTO findByUserName(AuthDTO authDTO) {

        AuthEntity user = authRepository.findByUserName(authDTO.getUserName());
        if (user == null) {
            throw new CustomResponse("User Not Found: ", HttpStatus.OK);
        }
        if (authDTO.getUserName() == null || authDTO.getPassword() == null) {
            throw new CustomResponse("Username or password can not be null", HttpStatus.OK);
        }
        if (authDTO.getPassword().length() >= 0 && authDTO.getPassword().length() <= 5) {
            throw new CustomResponse("Password should be at least 6 characters long", HttpStatus.OK);
        }
        if (user != null && passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getUserName());
            user.setToken(token);
            AuthDTO authDTO1 = AuthTransformer.getUserDTO(user);
            throw new CustomResponse("Success", HttpStatus.OK, authDTO1);
        }
        throw new CustomResponse("username or password is incorrect", HttpStatus.OK);
    }
}
