package co.ubl.bank.Email;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmailDTO {
    private String subject;
    private String recipient;
    private String message;
    private String attachments;
}
