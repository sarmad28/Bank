package co.ubl.bank.ServiceImpl;

import co.ubl.bank.DTO.*;
import co.ubl.bank.Email.EmailDTO;
import co.ubl.bank.Email.EmailService;
import co.ubl.bank.Entity.UsersEntity;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Repository.UserRepository;
import co.ubl.bank.Service.TransactionService;
import co.ubl.bank.Service.UsersService;
import co.ubl.bank.Transformer.UsersTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository usersRepository;
    private final EmailService emailService;
    private final TransactionService transactionService;

    public UsersServiceImpl(EmailService emailService, UserRepository usersRepository, TransactionService transactionService) {
        this.emailService = emailService;
        this.usersRepository = usersRepository;
        this.transactionService = transactionService;
    }

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {

        if (usersDTO.getFirstName() != null) {
            UsersEntity userExist = usersRepository.findFirstByFirstName(usersDTO.getFirstName());
            if (userExist != null) throw new CustomResponse("User is already exists", HttpStatus.OK);
        }
        UsersEntity usersEntity = UsersTransformer.getUsersEntity(usersDTO);
        String currentDateTime = getCurrentDateTime();
        usersEntity.setCreatedTime(currentDateTime);
        UsersEntity save = usersRepository.save(usersEntity);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(save.getEmail());
        emailDTO.setSubject("ACCOUNT CREATION");
        emailDTO.setMessage("Congratulations! Your account has been created successfully" +
                "\n\nYOUR ACCOUNT DETAILS" +
                "\nAccount Name:" + save.getFirstName() +
                "" + save.getLastName() +
                "\nAccountNumber:" + save.getAccountNumber());
        emailService.sendEmailAlert(emailDTO);
        return UsersTransformer.getUsersDTO(save);
    }

    @Override
    public UsersDTO update(UsersDTO usersDTO) {
        return null;
    }

    @Override
    public UsersDTO delete(Long id) {
        return null;
    }

    @Override
    public UsersDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UsersDTO> findAll() {
        Iterable<UsersEntity> all = usersRepository.findAll();
        return UsersTransformer.getUsersDTOList(all);
    }

    @Override
    public UsersDTO findByUserName(UsersDTO usersDTO) {
        return null;
    }

    @Override
    public UsersDTO balanceInfo(EnquiryRequest enquiryRequest) {
        UsersEntity u = usersRepository.findFirstByAccountNumber(enquiryRequest.getAccountNumber());
        if (u == null) {
            throw new CustomResponse("Account not found", HttpStatus.OK);
        }
        return UsersTransformer.getUsersDTO(u);

    }

    @Override
    public UsersDTO accountExists(String accountNumber) {
        UsersEntity firstByAccountNumber = usersRepository.findFirstByAccountNumber(accountNumber);
        if (firstByAccountNumber == null) {
            throw new CustomResponse("Account not found", HttpStatus.OK);
        }
        return UsersTransformer.getUsersDTO(firstByAccountNumber);
    }

    @Override
    public BankResponse credit(CreditDebitRequest creditDebitRequest) {
        UsersEntity firstByAccountNumber = usersRepository.findFirstByAccountNumber(creditDebitRequest.getAccountNumber());
        BankResponse bankResponse = new BankResponse();
        if (firstByAccountNumber == null) {
            throw new CustomResponse("Account not found", HttpStatus.OK);
        }
        firstByAccountNumber.setAccountBalance(firstByAccountNumber.getAccountBalance().add(creditDebitRequest.getAmount()));
        usersRepository.save(firstByAccountNumber);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(firstByAccountNumber.getEmail());
        emailDTO.setSubject("ACCOUNT CREDITED");
        emailDTO.setMessage("Your account has been credited PKR " + creditDebitRequest.getAmount());
        emailService.sendEmailAlert(emailDTO);
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setAccountNumber(firstByAccountNumber.getAccountNumber());
        transactionsDTO.setTransactionAmount(creditDebitRequest.getAmount());
        transactionsDTO.setTransactionType("CREDIT");
        transactionsDTO.setTransactionDate(getCurrentDateTime());
        transactionService.createTransaction(transactionsDTO);
        bankResponse.setAccountHolderName(firstByAccountNumber.getFirstName());
        bankResponse.setBankName("UBL");
        bankResponse.setAccountBalance(firstByAccountNumber.getAccountBalance());
        bankResponse.setAccountNumber(firstByAccountNumber.getAccountNumber());
        return bankResponse;

    }

    @Override
    public BankResponse debit(CreditDebitRequest creditDebitRequest) {
        UsersEntity firstByAccountNumber = usersRepository.findFirstByAccountNumber(creditDebitRequest.getAccountNumber());
        BankResponse bankResponse = new BankResponse();
        if (firstByAccountNumber == null) {
            throw new CustomResponse("Account not found", HttpStatus.OK);
        }
        if (firstByAccountNumber.getAccountBalance().compareTo(creditDebitRequest.getAmount()) < 0) {
            // Insufficient balance logic
            throw new CustomResponse("You have Insufficient balance.", HttpStatus.OK);
        }
        firstByAccountNumber.setAccountBalance(firstByAccountNumber.getAccountBalance().subtract(creditDebitRequest.getAmount()));
        usersRepository.save(firstByAccountNumber);
        // for email setup things
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(firstByAccountNumber.getEmail());
        emailDTO.setSubject("ACCOUNT DEBITED");
        emailDTO.setMessage("Your account has been debited PKR " + creditDebitRequest.getAmount());
        emailService.sendEmailAlert(emailDTO);

        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setAccountNumber(firstByAccountNumber.getAccountNumber());
        transactionsDTO.setTransactionAmount(creditDebitRequest.getAmount());
        transactionsDTO.setTransactionType("DEBIT");
        transactionsDTO.setTransactionDate(getCurrentDateTime());
        transactionService.createTransaction(transactionsDTO);

        bankResponse.setAccountHolderName(firstByAccountNumber.getFirstName());
        bankResponse.setBankName("UBL");
        bankResponse.setAccountBalance(firstByAccountNumber.getAccountBalance());
        bankResponse.setAccountNumber(firstByAccountNumber.getAccountNumber());
        return bankResponse;
    }

    @Override
    public BankResponse transfer(TransferRequest transferRequest) {
        BankResponse bankResponse = new BankResponse();
        UsersEntity fromAccount = usersRepository.findFirstByAccountNumber(transferRequest.getFromAccountNumber());
        UsersEntity toAccount = usersRepository.findFirstByAccountNumber(transferRequest.getToAccountNumber());
        if (transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomResponse("Please input some amount.", HttpStatus.OK);
        }
        if (toAccount == null) {
            throw new CustomResponse("Account not found", HttpStatus.OK);
        }
        if (fromAccount.getAccountBalance().compareTo(transferRequest.getAmount()) < 0) {
            // Insufficient balance logic
            throw new CustomResponse("You have Insufficient balance.", HttpStatus.OK);
        }
        fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(transferRequest.getAmount()));
        toAccount.setAccountBalance(toAccount.getAccountBalance().add(transferRequest.getAmount()));
        usersRepository.save(fromAccount);
        usersRepository.save(toAccount);

        //FOR SETUP EMAIL
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(fromAccount.getEmail());
        emailDTO.setSubject("ACCOUNT DEBITED");
        emailDTO.setMessage("Your account has been debited PKR " + transferRequest.getAmount());
        emailService.sendEmailAlert(emailDTO);

        //FOR SETTING TRANSACTIONS
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setAccountNumber(fromAccount.getAccountNumber());
        transactionsDTO.setTransactionAmount(transferRequest.getAmount());
        transactionsDTO.setTransactionType("TRANSFER");
        transactionsDTO.setTransactionDate(getCurrentDateTime());
        transactionService.createTransaction(transactionsDTO);

        //FOR SETUP RESPONSE
        bankResponse.setAccountHolderName(fromAccount.getFirstName());
        bankResponse.setBankName("UBL");
        bankResponse.setAccountBalance(fromAccount.getAccountBalance());
        bankResponse.setAccountNumber(fromAccount.getAccountNumber());
        return bankResponse;
    }

    public String getCurrentDateTime() {
        LocalDateTime timestamp = LocalDateTime.now();
        // Format the timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        return formattedTimestamp;
    }
}
