package org.kodluyoruz.mybank.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.config.Auditable;
import org.kodluyoruz.mybank.customer.Customer;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cards")
public class Card extends Auditable<String> {

    @Id
    @GeneratedValue
    private long id;

    private String cardNo;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private double usableLimit;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    public CardDto toCardDto() {
        return CardDto.builder()
                .id(this.id)
                .cardNo(this.cardNo)
                .cardType(this.cardType)
                .account(this.account)
                .customer(this.customer)
                .build();
    }

}
