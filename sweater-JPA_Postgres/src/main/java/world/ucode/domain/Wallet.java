package world.ucode.domain;

import javax.persistence.*;

import org.springframework.web.bind.annotation.ModelAttribute;
import world.ucode.domain.Currency;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private Long balance;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "currency")
//    private Currency currencyName;
    private String currencyName;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private Registration ownerLogin;
    private String ownerLogin;

    public Wallet(){}

    public Wallet(String name, Long balance, String currencyName, String registration) {
        this.name = name;
        this.balance = balance;
        this.currencyName = currencyName;
        this.ownerLogin = registration;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBalance() {
        return balance;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
}

